# 简介

网易18年校招 JAVA 工程师入职前“通关大boss”作业的实现，大致就是实现一个单卖家、单买家的内容销售项目。

详细要求见 http://easeplan.netease.com/#/detail/70

# 主要技术框架
spring boot + spring mvc + mybatis + jwt 

# 运行说明

- 需要 jdk8+, mvn, mysql
- 先修改以下配置文件中的数据库配置
> ./src/main/resources/application.properties
- 记得在 mysql 中配置一个与上述修改一致的 database
- 运行
> mvn spring-boot:run
- done

# 账号密码
- 买家 buyer reyub
- 卖家 seller relles

# 其他说明

- 启动后会自动创建数据库表，并且添加一条内容，方便点着玩
- 要求中对于是否能重复购买的要求前后不一致，本实现允许重复购买

# 引用说明

- 直接引用了样例网站的 style.css, md5.js 文件
- 因使用上述 css, freemarker 模板参考了样例网站的 html 结构

