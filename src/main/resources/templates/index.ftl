<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/header.ftl">

<div class="g-doc">
    <#if user?? && user.role == 'buyer'>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li <#if !filter>class="z-sel"</#if> ><a href="/">查看全部内容</a></li>
                <#if user?? && user.role == 'buyer'>
                    <li <#if filter>class="z-sel"</#if> >
                        <a href="/?filter=true">只看未购商品</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
    </#if>
    <#if !items?? || !items?has_content>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
    <#else>
        <div class="n-plist">
            <ul class="f-cb" id="plist">
            <#list items as x>
                <li id="p-${x.id}">
                    <a href="/detail/${x.id}" class="link">
                        <div class="img"><img src="${x.image}" alt="${x.title}"></div>
                        <h3>${x.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${x.price}</span></div>
                        <#if user??>
                            <#if user.role == 'buyer' && (x.purchased > 0)><span class="had"><b>已购买</b></span></#if>
                            <#if user.role == 'seller' && (x.sold > 0)><span class="had"><b>已售出${x.sold}件</b></span></#if>
                        </#if>
                    </a>
                    <#if user?? && user.role == 'seller' && x.sold == 0><span class="u-btn u-btn-normal u-btn-xs del" data-del="${x.id}">删除</span></#if>
                </li>
            </#list>
            </ul>
        </div>
    </#if>
</div>

<#include "./include/footer.ftl">
<script type="text/javascript" src="/static/js/pageIndex.js"></script>
</body>
</html>