<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/support.ftl">
<#include "./include/header.ftl">

<#if user?? && user.role == 'buyer'>
<div class="nav-tabs">
    <nav class="nav d-flex justify-content-start">
        <a class="p-2" href="/?filter=non_purchased">只看未购商品</a>
        <a class="p-2" href="/">查看全部商品</a>
    </nav>
</div>
</#if>

<#if !products?? || !products?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
<#else>
    <#list products as x>
        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow">
                            <img class="card-img-top" style="height: 225px; width: 100%; display: block;"
                                 src="${x.image}" data-holder-rendered="true">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center">
                                    <h6>${x.title}</h6>
                                    <h6 class="card-title pricing-card-title">¥ ${x.price}</h6>
                                </div>
                                <div class="d-flex justify-content-between align-items-center">
                                    <#if user??>
                                        <#if user.role == 'buyer'&& x.purchased > 0>
                                        <span class="had"><b>已购买${x.purchased}件</b></span>
                                        </#if>
                                        <#if user.role == 'seller'>
                                            <span class="had"><b>已售出${x.sold}件</b></span>
                                        </#if>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</#if>

<#include "./include/footer.ftl">
<script type="text/javascript" src="/static/js/global.js"></script>
<script type="text/javascript" src="/static/js/pageIndex.js"></script>
</body>
</html>