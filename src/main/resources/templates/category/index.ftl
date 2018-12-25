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
                    <form role="form" action="/sell/seller/category/save" method="post">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" name="categoryName" value="${(productCategory.categoryName)!''}" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input type="text" name="categoryType" value="${(productCategory.categoryType)!''}" class="form-control"/>
                        </div>

                        <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
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