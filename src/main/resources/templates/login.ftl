<!DOCTYPE html>
<html>
<#include "./include/head.ftl">
<body>
<#include "./include/header.ftl">
<input type="hidden" id="referer" value="${referer!"/"}">
<div class="m-form m-form-ht n-login" id="loginForm">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" id="username" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" id="password"/>
        </div>
    </div>
    <div class="" style="min-height: 50px">
        <label class="fmlab" id="errorLabel" style="display: none; color: red">用户名或密码错误！</label>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button id="submitBtn" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
        </div>
    </div>
</div>
<#include "./include/footer.ftl">
<script type="text/javascript" src="/static/js/md5.js"></script>
<script type="text/javascript" src="/static/js/login.js"></script>
</body>
</html>