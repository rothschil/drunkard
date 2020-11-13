
![Mozilla Add-on](https://img.shields.io/amo/dw/drunkard?color=fedcba&label=drunkard&logo=postwoman&logoColor=rgb&style=for-the-badge)

# 1. 项目简介

`drunkard：酒鬼; 醉鬼;`

本项目是基于SpringBoot开发的脚手架模块，已经集成各种通用组件，适合构建项目所用。

# 2. 技术选型

SpringBoot、Maven、Jnuit、MySQL

# 3. 测试策略

测试类型 | 代码目录 | 测试内容
-- | -- | -- |
单元测试 | src/test/java | 包含核心领域模型（包含领域对象和Factory类）的测试
组件测试 | src/componentTest/java | 用于测试一些核心的组件级对象，比如Repository
API测试 | src/apiTest/java | 模拟客户端调用API

# 4. 技术架构

# 5. 部署架构

# 6. 外部依赖

项目运行时所依赖的外部集成方，比如订单系统会依赖于会员系统；

# 7. 环境信息

各个环境的访问方式，数据库连接等；

# 8. 编码实践

统一的编码实践，比如异常处理原则、分页封装等；

# 9. FAQ

开发过程中常见问题的解答。