package com.huaibei.service.impl;

import com.huaibei.config.WechatAccountConfig;
import com.huaibei.dto.OrderMasterDTO;
import com.huaibei.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: y
 * @CreateDate: 2018/12/27 11:16 AM
 * @Version: 1.0
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Override
    public void orderStatus(OrderMasterDTO orderMasterDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderMasterDTO.getBuyerOpenid());
        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("keyword1","亲,记得收货。"),
                new WxMpTemplateData("keyword2","微信点餐"),
                new WxMpTemplateData("keyword3","15000000000。"),
                new WxMpTemplateData("keyword4",orderMasterDTO.getOrderId()),
                new WxMpTemplateData("keyword5",orderMasterDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword6","¥" + orderMasterDTO.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎下次光临！")
        );

        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (WxErrorException e){
            log.error("[微信模版消息] 发送失败，{}",e);
        }
    }
}
