package com.huaibei.service.impl;

import com.huaibei.beans.ProductInfo;
import com.huaibei.dao.ProductInfoDao;
import com.huaibei.dto.CartDTO;
import com.huaibei.enmus.ProductStatusEnmu;
import com.huaibei.enmus.ResultEnum;
import com.huaibei.exception.SellException;
import com.huaibei.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "product")
public class ProductInfoServiceImpl implements ProductInfoService{

    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
//    @Cacheable(cacheNames = "product",key = "100")
    @Cacheable(key = "100")
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnmu.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return productInfoDao.findAll(pageable);
    }

    @Override
    @CachePut(key = "100")
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList){
            ProductInfo one = productInfoDao.findOne(cartDTO.getProductId());
            if(one == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = one.getProductStock() + cartDTO.getProductQuantity();
            one.setProductStock(result);
            productInfoDao.save(one);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList){
            ProductInfo one = productInfoDao.findOne(cartDTO.getProductId());
            if(one == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = one.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRUDUCT_STORK_ERROR);
            }
            one.setProductStock(result);
            productInfoDao.save(one);
        }
    }

    @Override
    public ProductInfo offSell(String productId) {
        ProductInfo one = productInfoDao.findOne(productId);
        if(one == null){
            log.error("[商品下架错误] 商品不存在 productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(one.getProductStatusEnmu() == ProductStatusEnmu.DOWN){
            log.error("[商品下架错误] 商品已是下架状态 productId={}",productId);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        one.setProductStatus(ProductStatusEnmu.DOWN.getCode());
        ProductInfo result = productInfoDao.save(one);
        return result;
    }

    @Override
    public ProductInfo onSell(String productId) {
        ProductInfo one = productInfoDao.findOne(productId);
        if(one == null){
            log.error("[商品上架错误] 商品不存在 productId={}",productId);
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(one.getProductStatusEnmu() == ProductStatusEnmu.UP){
            log.error("[商品上架错误] 商品已是上架状态 productId={}",productId);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        one.setProductStatus(ProductStatusEnmu.UP.getCode());
        ProductInfo result = productInfoDao.save(one);
        return result;
    }
}
