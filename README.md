
![Mozilla Add-on](https://img.shields.io/amo/dw/drunkard?color=fedcba&label=drunkard&logo=postwoman&logoColor=rgb&style=for-the-badge)

# 1. 项目简介

`drunkard：酒鬼; 醉鬼;`

想做一套后台管理系统，在此期间主要参考的开源项目 `RuoYi`，谁让自己前端知识储备不够，实在是写不出来那样的效果，没办法，只能抄。

本项目是基于SpringBoot开发的脚手架模块，已经集成 `MyBatis` 作为持久层组件，为了方便提供两种不同的主键生成策略，一种是利用 `Redis` ；另一种是利用数据库自身的ID策略，两种方式各有差异，主要针对不同的场景。

## 1.1. 内置功能

## 1.2. 集成OAuth2


## 1.3. 集成数据校验

`Spring Validation` 对 `hibernate validatio`n 进行了二次封装，可以让我们更加方便地使用数据校验功能。这边我们通过 Spring Boot 来引用校验功能。

若使用的 Spring Boot 版本小于 2.3.x，`spring-boot-starter-web` 会自动引入 `hibernate-validator` 的依赖。

如果 Spring Boot 版本大于 2.3.x，则需要手动引入依赖，我这版本就是 大于 2.3.x，所以需要手工引入

~~~
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>6.0.1.Final</version>
</dependency>
~~~

注解 | 释义
:- | :-
@Null | 被注释的元素必须为 null
@NotNull | 被注释的元素必须不为 null
@AssertTrue | 被注释的元素必须为 true
@AssertFalse | 被注释的元素必须为 false
@Min(value) | 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@Max(value) | 被注释的元素必须是一个数字，其值必须小于等于指定的最大值 
@DecimalMin(value) | 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
@DecimalMax(value) | 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
@Size(max, min) | 被注释的元素的大小必须在指定的范围内，元素必须为集合，代表集合个数
@Digits (integer, fraction) | 被注释的元素必须是一个数字，其值必须在可接受的范围内
@Past | 被注释的元素必须是一个过去的日期
@Future | 被注释的元素必须是一个将来的日期
@Email | 被注释的元素必须是电子邮箱地址
@Length(min=, max=) | 被注释的字符串的大小必须在指定的范围内，必须为数组或者字符串，若微数组则表示为数组长度，字符串则表示为字符串长度
@NotEmpty | 被注释的字符串的必须非空
@Range(min=, max=) | 被注释的元素必须在合适的范围内
@Pattern(regexp = ) | 正则表达式校验
@Valid | 对象级联校验,即校验对象中对象的属性

## 1.4. 项目结构

# 2. 技术选型

SpringBoot、Maven、Jnuit、MySQL、JDK8+

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