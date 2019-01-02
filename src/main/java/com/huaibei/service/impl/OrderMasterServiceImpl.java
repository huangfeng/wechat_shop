package com.huaibei.service.impl;

import com.huaibei.beans.OrderDetail;
import com.huaibei.beans.OrderMaster;
import com.huaibei.beans.ProductInfo;
import com.huaibei.converter.OrderMaster2OrderDTOConverter;
import com.huaibei.dao.OrderDetailDao;
import com.huaibei.dao.OrderMasterDao;
import com.huaibei.dto.CartDTO;
import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.enmus.OrderStatusEnum;
import com.huaibei.enmus.PayStatusEnum;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.ResponseBankException;
import com.huaibei.exception.SellException;
import com.huaibei.service.*;
import com.huaibei.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/13 10:02 AM
 * @Version: 1.0
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService{

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private PayService payService;

    @Autowired
    private PushMessageService pushMessageService;

    @Autowired
    private WebSocket webSocket;
    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList = new ArrayList<>();
        //1查询商品数量 价格
        for (OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()){
            ProductInfo one = productInfoService.findOne(orderDetail.getProductId());
            if(one == null){
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//                    throw new ResponseBankException();
            }
            //2计算订单总价
            orderAmount = one.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(one,orderDetail);
            orderDetailDao.save(orderDetail);
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);

        }


        //3写入订单数据 orderMaster 和 orderDetail
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.save(orderMaster);
        //4扣库存
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());

        productInfoService.decreaseStock(cartDTOList);

        //发送websocket消息
        webSocket.sendMessage("有新的订单:" + orderMaster.getOrderId());

        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXEST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXEST);
        }
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster,orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }

    @Override
    public Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderMasterDTO> dtoList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderMasterDTO>(dtoList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderMasterDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterDaoAll = orderMasterDao.findAll(pageable);
        List<OrderMasterDTO> orderMasterDTOS = OrderMaster2OrderDTOConverter.convert(orderMasterDaoAll.getContent());
        return new PageImpl<OrderMasterDTO>(orderMasterDTOS,pageable,orderMasterDaoAll.getTotalElements());
    }

    @Override
    @Transactional
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[取消订单] 订单状态不正确， orderId={}, orderStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态

        orderMasterDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        OrderMaster master = orderMasterDao.save(orderMaster);

        if(master == null){
            log.error("[订单取消] 更新失败， orderMaster={}",master);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if(CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("[取消订单] 订单中无商品 orderDTO={} ，orderStatus={}",orderMasterDTO,orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果支付需要退款
        if(orderMasterDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            payService.refund(orderMasterDTO);
        }

        return orderMasterDTO;
    }

    @Override
        public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        //查询订单状态
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[完结订单] 订单状态不正确 orderId={}, orderStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        OrderMaster save = orderMasterDao.save(orderMaster);
        if(save == null){
            log.error("[完结订单] 更新失败 orderId={}, orderStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //推送模板消息
        pushMessageService.orderStatus(orderMasterDTO);
        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {


        //判断订单状态
        if(!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("[订单支付] 订单状态不正确 orderId={}, orderStatus={}",orderMasterDTO.getOrderId(),orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断订单支付状态
        if(!orderMasterDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("[订单支付] 订单支付状态不正确 orderId={}, 订单支付状态={}",orderMasterDTO.getOrderId(),orderMasterDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        orderMasterDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO,orderMaster);
        OrderMaster save = orderMasterDao.save(orderMaster);
        if(save == null){
            log.error("[订单支付] 订单支付状态更新失败 orderId={}, 订单支付状态={}, data={}",orderMasterDTO.getOrderId(),orderMasterDTO.getPayStatus(),orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDTO;

    }
}
