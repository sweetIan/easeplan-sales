<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
        <#if user??>
            <#if user.role == 'seller'>
                卖家
            <#else>
                买家
            </#if>
            <span class="name">${user.nickname}</span>
            ，你好！
            <a href="/logout">[退出]</a>
        <#else>
            <a href="/login">[登录]</a>
        </#if>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <#if user??>
                <#if user.role == 'seller'>
                    <li><a href="/new">发布</a></li>
                <#else>
                    <li><a href="/balance">财务</a></li>
                    <li><a href="/cart">购物车</a></li>
                </#if>
            </#if>
        </ul>
    </div>
</div>