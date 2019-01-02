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
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>
                                订单ID
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>
                                金额
                            </th>
                            <th>
                                订单状态
                            </th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderMasterDTOPage.getContent() as orderMaster>
                        <tr>
                            <td>
                            ${orderMaster.orderId}
                            </td>
                            <td>
                            ${orderMaster.buyerName}
                            </td>
                            <td>
                            ${orderMaster.buyerPhone}
                            </td>
                            <td>
                            ${orderMaster.buyerAddress}
                            </td>
                            <td>
                            ${orderMaster.orderAmount}
                            </td>
                            <td>
                            ${orderMaster.getOrderStatusEnum().msg}
                            </td>
                            <td>
                            ${orderMaster.getPayStatusEnum().msg}
                            </td>
                            <td>
                            ${orderMaster.createTime}
                            </td>
                            <td>
                                <a href="/sell/seller/order/detail?orderId=${orderMaster.orderId}">详情</a>
                            </td>
                            </td>
                            <td>
                                <#if orderMaster.getOrderStatusEnum().msg == "新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderMaster.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if orderMasterDTOPage.getNumber()+1 lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else >
                        <li>
                            <a href="/sell/seller/order/list?page=${orderMasterDTOPage.getNumber()+1 - 1}&size=${orderMasterDTOPage.getSize()}">上一页</a>
                        </li>
                    </#if>


                    <#list 1..orderMasterDTOPage.getTotalPages() as index>
                        <#if orderMasterDTOPage.getNumber()+1 == index>
                            <li class="disabled">
                                <a href="#">${index}</a>
                            </li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?page=${index}&size=${orderMasterDTOPage.getSize()}">${index}</a>
                            </li>
                        </#if>
                    </#list>


                    <#if orderMasterDTOPage.getNumber()+1 gte orderMasterDTOPage.getTotalPages()>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${orderMasterDTOPage.getNumber()+1 + 1}&size=${orderMasterDTOPage.getSize()}">下一页</a>
                        </li>
                    </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>


</div>
<#--<#list orderMasterDTOPage.content as orderMaster >-->
<#--${orderMaster.orderId}-->
<#--</#list>-->
<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                有新的订单...
            </div>
            <div class="modal-footer">
                <button type="button" onclick="document.getElementById('notice').pause()" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="location.reload()" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>

    </div>

</div>
<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://rhyme.nat300.top/sell/webSocket');
    } else {
        alert('该浏览器不支持websocket！');
    }
    websocket.onopen = function (event) {
        console.log('建立链接 event = ', event);
    }
    websocket.onclose = function () {
        console.log('关闭链接');
    }
    websocket.onmessage = function (event) {
        console.log('收到消息 event = ', event);
        $('#myModal').modal('show');
        //chrome://flags/#autoplay-policy
        document.getElementById("notice").play();
    }
    websocket.onerror = function () {
        alert('websocket 通信发生错误');
    }
    window.onbeforeunload = function () {
        websocket.close();
    }

</script>
</body>
</html>