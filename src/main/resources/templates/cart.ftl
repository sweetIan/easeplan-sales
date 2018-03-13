<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/header.ftl">
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="cartTable" class="m-table m-table-row n-table g-b3">
        <tbody>
        <tr>
            <th>标题</th>
            <th>价格</th>
            <th>数量</th>
            <th></th>
        </tr>
        <#if (deletedItemCount > 0)>
            <tr><td colspan="4"><div>系统已自动删除${deletedItemCount}条失效内容！</div></td></tr>
        </#if>
        <#list cartItems as x>
        <tr>
            <td>${x.title}</td>
            <td>¥${x.price}</td>
            <td>
                <input type="hidden" value="${x.id}"/>
                <span class="totalNum">${x.amount}</span>
                <button class="u-btn u-btn-primary changeAmount">修改数量</button>
                <div class="amountInput" style="display: none;">
                    <input type="number" placeholder="输入数量"/>
                    <button class="u-btn u-btn-primary submitBtn">确定</button>
                    <button class="u-btn u-btn-primary cancelBtn">取消</button>
                </div>
            </td>
            <td>
                <input type="hidden" value="${x.id}"/>
                <button class="u-btn u-btn-primary removeBtn">移除</button>
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
    <div id="act-btn">
        <input type="hidden" id="referer" value="${referer!"/"}">
        <button class="u-btn u-btn-primary" id="back">退出</button>
        <button class="u-btn u-btn-primary" id="purchase">购买</button>
    </div>
</div>
<#include "./include/footer.ftl">
<script type="text/javascript" src="/static/js/cart.js"></script>
</body>
</html>