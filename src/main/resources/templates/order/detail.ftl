<!DOCTYPE html>
<html lang="en">
<head>
<#include "../common/header.ftl">
</head>
<body>

<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                订单总金额
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                            ${orderMasterDTO.orderId}
                            </td>
                            <td>
                            ${orderMasterDTO.orderAmount}
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>

            <#--详细信息-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderMasterDTO.orderDetailList as orderDetail>
                        <tr>
                            <td>
                            ${orderDetail.productId}
                            </td>
                            <td>
                            ${orderDetail.productName}
                            </td>
                            <td>
                            ${orderDetail.productPrice}
                            </td>
                            <td>
                            ${orderDetail.productQuantity}
                            </td>
                            <td>
                            ${orderDetail.productQuantity * orderDetail.productPrice}
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="container">
                    <div class="row clearfix">
                    <#if orderMasterDTO.getOrderStatusEnum().msg == "新订单">
                        <div class="col-md-12 column">
                            <a type="button" href="/sell/seller/order/finish?orderId=${orderMasterDTO.orderId}" class="btn btn-default btn-primary">完成订单</a>
                            <a type="button" href="/sell/seller/order/cancel?orderId=${orderMasterDTO.orderId}" class="btn btn-danger btn-primary">取消订单</a>
                        </div>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
