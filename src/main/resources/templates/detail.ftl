<#setting number_format="#">
<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/header.ftl">
<div class="g-doc">
    <#if !item??>
        <div class="n-result">
            <h3>内容不存在！</h3>
        </div>
    <#else>
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${item.image}" alt=""></div>
        <div class="cnt">
            <h2>${item.title}</h2>
            <p class="summary">${item.summary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${item.price}</span>
            </div>
            <div class="oprt f-cb">
                <#if user?? && user.role=="buyer">
                    <button class="u-btn u-btn-primary" id="buyBtn" data-buy="${item.id}">购 买</button>
                    <#if buyHistory?? && buyHistory.size() > 0>
                    <span class="u-btn u-btn-primary z-dis">已购买</span>
                    <span class="buyprice">当时购买价格：¥1.2</span>
                    </#if>
                </#if>
                <#if user?? && user.role=="seller">
                <a href="/edit/${item.id}" class="u-btn u-btn-primary">编 辑</a>
                </#if>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${item.detail}
    </div>
    </#if>
</div>
<#include "./include/footer.ftl">
<script type="text/javascript" src="/static/js/detail.js"></script>
</body>
</html>