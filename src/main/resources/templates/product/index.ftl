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
                    <form role="form" action="/sell/seller/product/save" method="post">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" name="productName" value="${(productInfo.productName)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="text" name="productPrice" value="${(productInfo.productPrice)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input type="number" name="productStock" value="${(productInfo.productStock)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input type="text" name="productDescription" value="${(productInfo.productDescription)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img src="${(productInfo.productIcon)!''}" alt="">
                            <input type="text" name="productIcon" value="${(productInfo.productIcon)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType">
                                <#list productCategories as category>
                                    <option value="${category.categoryType}"<#if (productInfo.categoryType) ?? && (productInfo.categoryType) == category.categoryType>selected</#if>>${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


</div>
<#--<#list orderMasterDTOPage.content as orderMaster >-->
<#--${orderMaster.orderId}-->
<#--</#list>-->
</body>
</html>