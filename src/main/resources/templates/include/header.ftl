<header class="navbar bg-light">
    <div class="nav flex-row">
    <#if user??>
        <#if user.role == 'seller'>
            卖家
        <#else>
            买家
        </#if>
        <span class="name">${user.username}</span>
        ，你好！
        <a class="nav-item" href="/logout">[退出]</a>
    <#else>
        <a class="nav-item" href="/login">[登录]</a>
    </#if>
    </div>

    <ul class="nav flex-row ">
        <li class="nav-item"><a href="/">首页</a></li>
    <#if user??>
        <#if user.role == 'seller'>
            <li class="nav-item"><a href="/new_product">发布</a></li>
        <#else>
            <li class="nav-item"><a href="/balance">财务</a></li>
            <li class="nav-item"><a href="/cart">购物车</a></li>
        </#if>
    </#if>
    </ul>
</header>