<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/header.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>内容<#if edit>编辑<<#else>发布</#if></h2>
    </div>
    <#if edit && !item??>
    <div class="n-result">
        <h3>内容不存在！</h3>
    </div>
    <#else>
    <div class="n-public">
        <div class="m-form m-form-ht" id="form">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <#if edit>
                    <input type="hidden" id="id" value="${item.id}"/>
                    </#if>
                    <input class="u-ipt ipt" id="title" <#if edit>value="${item.title}</#if>" placeholder="2-80字符"/>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" id="summary" <#if edit>value="${item.summary}</#if>" placeholder="2-140字符" ／>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input name="pic" type="radio" value="url" checked=""> 图片地址
                    <input name="pic" type="radio" value="file"> 本地上传
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt">
                    <input id="image" class="u-ipt ipt" <#if edit>value="${item.image}</#if>" placeholder="图片地址"/>
                </div>
                <div id="uploadForm" class="fmipt" style="display:none;">
                    <input id="fileSelect" class="u-ipt ipt" type="file" name="file" />
                    <input id="uploadBtn" class="u-btn u-btn-primary" type="submit" value="上传" />
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" id="detail" rows="10" placeholder="2-1000个字符"><#if edit>${item.detail}</#if></textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <input class="u-ipt price" id="price" value="<#if edit>${item.price}</#if>"/>元
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button id="submit" class="u-btn u-btn-primary u-btn-lg">保 存</button>
                </div>
            </div>
        </div>
        <span class="imgpre"><img id="imgpre" src="<#if edit>${item.image}</#if>" alt=""/></span>
    </div>
    </#if>
</div>
<#include "./include/footer.ftl">
<script type="text/javascript" src="/static/js/edit.js"></script>
</body>
</html>