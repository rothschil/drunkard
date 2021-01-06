
<!-- TOC -->

- [1. 项目简介](#1-项目简介)
    - [1.1. 友情关联](#11-友情关联)
    - [1.2. 概述](#12-概述)
    - [1.3. 项目结构](#13-项目结构)
    - [1.4. 技术选型](#14-技术选型)
        - [1.4.1. 自动生成实体等文件](#141-自动生成实体等文件)
        - [1.4.2. 分页](#142-分页)
    - [1.5. 测试策略](#15-测试策略)
    - [1.6. 技术架构](#16-技术架构)
    - [1.7. 部署架构](#17-部署架构)
    - [1.8. 外部依赖](#18-外部依赖)
- [2. 内置功能](#2-内置功能)
    - [2.1. Springboot集成JPA，开箱即用](#21-springboot集成jpa开箱即用)
        - [2.1.1. pom文件](#211-pom文件)
        - [2.1.2. 自定义Repository工厂类](#212-自定义repository工厂类)
        - [2.1.3. 抽象实体基类](#213-抽象实体基类)
        - [2.1.4. 抽象Service基类](#214-抽象service基类)
        - [2.1.5. 如何使用？](#215-如何使用)
            - [2.1.5.1. 继承实体基类](#2151-继承实体基类)
            - [2.1.5.2. 继承repository基类](#2152-继承repository基类)
            - [2.1.5.3. 继承Service基类](#2153-继承service基类)
            - [2.1.5.4. 启动类](#2154-启动类)
        - [2.1.6. 源码地址，如果觉得对你有帮助，请Star](#216-源码地址如果觉得对你有帮助请star)
    - [2.2. 自动刷新MybatisXML](#22-自动刷新mybatisxml)
        - [2.2.1. 配置文件](#221-配置文件)
        - [2.2.2. 关键步骤](#222-关键步骤)
        - [2.2.3. 源码地址，如果觉得对你有帮助，请Star](#223-源码地址如果觉得对你有帮助请star)
    - [2.3. 手写爬虫获取国家统计局行政区划数据](#23-手写爬虫获取国家统计局行政区划数据)
        - [2.3.1. 依赖包](#231-依赖包)
        - [2.3.2. 核心实现代码](#232-核心实现代码)
        - [2.3.3. 单元测试](#233-单元测试)
        - [2.3.4. 打开浏览器](#234-打开浏览器)
        - [2.3.5. 源码地址，如果觉得对你有帮助，请Star](#235-源码地址如果觉得对你有帮助请star)
    - [2.4. 集成ip2region离线IP地名映射](#24-集成ip2region离线ip地名映射)
        - [2.4.1. 打开浏览器](#241-打开浏览器)
        - [2.4.2. 源码地址，如果觉得对你有帮助，请Star](#242-源码地址如果觉得对你有帮助请star)
    - [2.5. Http响应内容统一封装](#25-http响应内容统一封装)
        - [2.5.1. 消息体](#251-消息体)
            - [2.5.1.1. 正常响应](#2511-正常响应)
            - [2.5.1.2. 异常响应](#2512-异常响应)
        - [2.5.2. 拦截器](#252-拦截器)
            - [2.5.2.1. Annoation注解](#2521-annoation注解)
            - [2.5.2.2. 拦截器](#2522-拦截器)
            - [2.5.2.3. 全局异常](#2523-全局异常)
        - [2.5.3. 例子](#253-例子)
        - [2.5.4. 源码地址，如果觉得对你有帮助，请Star](#254-源码地址如果觉得对你有帮助请star)
    - [2.6. 集成OAuth2](#26-集成oauth2)
    - [2.7. 集成数据校验](#27-集成数据校验)
- [3. FAQ](#3-faq)

<!-- /TOC -->
# 1. 项目简介

![Drunkard-WONGS](https://img.shields.io/badge/Drunkard-WONGS-blueviolet)
![Drunkard-Version](https://img.shields.io/badge/version-v2.0.0--alpha.2-orange)
![Drunkard-Downloads](https://img.shields.io/badge/Downloads-300-brightgreen)
![Drunkard-Stars](https://img.shields.io/badge/Stars-148-ff69b4)
![Drunkard-size](https://img.shields.io/badge/size-25%20MB-blue)
![Drunkard-license](https://img.shields.io/badge/license-MIT-green)
![Drunkard-coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)
![Java](https://img.shields.io/badge/Java-%3E%3D%201.8-green)
![Gradle](https://img.shields.io/badge/Gradle-%3E%3D%206.5-yellowgreen)
![MySQL](https://img.shields.io/badge/MySQL-%3E%3D%205.7-important)
![Redis](https://img.shields.io/badge/Redis-%3E%3D%204.0-yellowgreen)
![Idea](https://img.shields.io/badge/IDEA-%3E%3D%202020.2-informational)
![SpringBoot](https://img.shields.io/badge/SpringBoot-%3E%3D%202.3.4-druid)
![Druid](https://img.shields.io/badge/druid-%3E%3D%201.2.3-green)
![Mybatis](https://img.shields.io/badge/Mybatis-%3E%3D%203.5.5-red)
![RedisClient](https://img.shields.io/badge/RedisClient-%3E%3D%203.3.0-blue)

## 1.1. 友情关联

[简书-首页](https://www.jianshu.com/u/72ee6146c91a)：平常写的内容都在上面

![简书-首页](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201216153149.png)

[Gitee首页](https://gitee.com/rothschil/)：Gitee首页

![Gitee首页](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201216153238.png)

[Git.IO首页](https://rothschil.github.io/)：Git.IO首页

![Git.IO首页](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201216173106.png)

[Github首页](https://github.com/rothschil)：Github首页

![Git首页](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201216153436.png)

[CSDN](https://blog.csdn.net/rothchil)：CSDN首页

![CSDN首页](https://abram.oss-cn-shanghai.aliyuncs.com/blog/drunkard/20210106100317.png)

[Weathertop](https://github.com/king-angmar/weathertop)：其他开源的部分,已停止维护

![Weathertop](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201216153542.png)

## 1.2. 概述

`drunkard:	英[ˈdrʌŋkəd]    美[ˈdrʌŋkərd]   酒鬼; 醉鬼;`

醉酒状态下，可以忘却很多烦恼，倒是很羡慕那些 `酒鬼`们，每天都可以忘记好多事情。再者与同道中的人`喝`起来酒越喝越暖，也可以说 `酒逢知己千杯少`吧，中华古文化中对`酒`可以说有很多名人名句。信手拈来的有曹孟德`对酒当歌，人生几何`这样的焦虑；也有东坡先生 `明月几时有？把酒问青天`怀情；再有杜牧先生的 `借问酒家何处有,牧童遥指杏花村`的念`酒`。

以前听说魏晋时期的 `竹林七贤` 中有两人嗜酒如命 `嵇康`、`刘伶`,小时候还见到酒的品牌就以这两人名字命名的，甚是觉得好玩不得了。

言归正传，一直依赖，自己就想做一套后台管理系统，在此期间主要前端参考的开源项目 `RuoYi`，谁让自己前端知识储备不够，实在是写不出来那样的效果，没办法，只能抄。

本项目是基于SpringBoot开发的脚手架模块，已经集成 `MyBatis` 作为持久层组件，为了方便提供两种不同的主键生成策略，一种是利用 `Redis` ；另一种是利用数据库自身的ID策略，两种方式各有差异，主要针对不同的场景。

同时本人也将日常学习的一些材料也整理出来，放到 [github.io](https://rothschil.github.io/) 的个人网站上，希望对大家有帮助。

- ![https://rothschil.github.io/](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201203104059.png)

## 1.3. 项目结构

~~~
|-- akkad-base                         ------------基包
|   |-- base-utils                     ------------通用工具包
|   |-- persistence-jpa                ------------基于JPA封装持久层
|   |-- persistence-mybatis            ------------基于MyBatis封装持久层
|   |   |-- mybatis-base               ------------Mybatis抽象基类封装
|   |   |   |-- resources              ------------
|   |   |   |   |-- conf               ------------Mybatis自动刷新配置路径
|   |   |-- mybatis-no-pk              ------------依赖DB主键版本
|   |   |-- mybatis-pk-redis           ------------依赖Redis生成主键的版本
|-- akkad-springboot                   ------------springboot集成开源组件
|   |   |-- springboot-dist-lock       ------------分布式锁例子
|   |   |-- springboot-dist-trans      ------------分布式事物
|   |   |-- springboot-elasticsearch   ------------集成ES
|   |   |-- springboot-jwt             ------------JWT示例
|   |   |-- springboot-mq              ------------集成消息MQ组件
|   |   |-- springboot-nosql           ------------集成oauth2的版本
|-- akkad-war3                         ------------常用的示例
|   |   |-- war3-area                  ------------获取行政区域的版本
|   |   |-- war3-infi                  ------------集成RuoYi 实现通用管理后台
|   |   |-- war3-moon                  ------------集成Mybatis扫描文件
|   |   |-- war3-oauth2                ------------集成oauth2的版本
|   |   |-- war3-sky                   ------------集成JPA的样例
|-- README.md
|-- LICENSE
~~~

## 1.4. 技术选型

SpringBoot、Gradle、Jnuit、MySQL、JDK8+

### 1.4.1. 自动生成实体等文件

在pom文件中加上`generator`依赖，在 `configurationFile` 中指定`generatorConfig.xml` 文件的位置，剩下一些数据库连接以及配置项，都在配置文件`generatorConfig.xml`中指定即可，方便又实用。

- pom文件

~~~yml
<build>
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.2</version>
            <configuration>
                <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
                <overwrite>true</overwrite>
                <verbose>true</verbose>
            </configuration>
        </plugin>
    </plugins>
</build>

~~~

- generatorConfig.xml文件

这里我将数据库配置信息独立出来在另外一个文件，即`generator.properties`，`table` 节点自定义，可以一次性写个表。

~~~xml
<generatorConfiguration>
    <properties resource="generator/generator.properties"></properties>
    <!--数据库驱动-->
    <classPathEntry location="${jdbc.driverLocation}"/>

    <context id="DB2Tables" targetRuntime="MyBatis3" defaultModelType="flat">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库链接地址账号密码-->
        <jdbcConnection driverClass="${jdbc.driverClass}" connectionURL="${jdbc.connectionURL}"
                        userId="${jdbc.userId}" password="${jdbc.password}">
        </jdbcConnection>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!--生成Model类存放位置-->
        <javaModelGenerator targetPackage="${targetPackage}.entity" targetProject="${targetProject}">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!--生成映射文件存放位置-->
        <sqlMapGenerator targetPackage="${targetPackage}.mapping" targetProject="${targetProject}">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>
        <!--生成Dao类存放位置-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="${targetPackage}.mapper" targetProject="${targetProject}">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <table tableName="register_user_his"  schema="RegisterUserHis"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false"
               selectByExampleQueryId="false">
        </table>
    </context>
</generatorConfiguration>
~~~

- generator.properties文件

指定数据库驱动路径以及数据库URL、用户名、密码等即可

~~~properties
jdbc.driverLocation=G:\\Devp_Repository\\Jar\\mysql-connector-java-5.1.46.jar
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.connectionURL=jdbc:mysql://localhost:3306/addbook?characterEncoding=utf8&useSSL=true
jdbc.userId=root
jdbc.password=123456
targetProject=D:/data
targetPackage=xyz.wongs.drunkard.domain.addbook
// Generator
generator.targetProject=src/main/java

~~~

最后就是执行阶段，两种方式，我使用的`maven`插件命令方式来执行，直接双击。

![generator插件](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201203101834.png)

### 1.4.2. 分页

分页组件是继承开源**com.github.pagehelper**

~~~xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>${Last Version}</version>
</dependency>
~~~

在应用中PageHelper.startPage即可，后面需要紧跟着持久层语句，否则分页失效

~~~java
public PageInfo<T> selectPage(PaginationInfo pgInfo, T t) {
    PageHelper.startPage(pgInfo.getPageNum(), pgInfo.getPageSize());
    List<T> lt = getMapper().getList(t);
    PageInfo<T> pageInfo = new PageInfo<T>(lt);
    return pageInfo;
}
~~~

## 1.5. 测试策略

测试类型 | 代码目录 | 测试内容
-- | -- | -- |
单元测试 | src/test/java | 包含核心领域模型（包含领域对象和Factory类）的测试
组件测试 | src/componentTest/java | 用于测试一些核心的组件级对象，比如Repository
API测试 | src/apiTest/java | 模拟客户端调用API

## 1.6. 技术架构

## 1.7. 部署架构

## 1.8. 外部依赖

项目运行时所依赖的外部集成方；

# 2. 内置功能

## 2.1. Springboot集成JPA，开箱即用

不知为什么在以前的一段时间内，我特别喜欢用 `JPA` ，它给我印象就是小巧灵便，为我省去了很多不必要的编码，带给我不一样的代码输出效率，因为业务在垂直划分过程中都相对来说封闭，要求在编码过程中相对来说实体关联没有那么复杂，而且项目本身的交付周期特别短，我就选择 `JPA` 作为我们某个特定项目的专用持久层框架，当然  `JPA` 自身的优势就不说啦。

项目架构目的就是为了高质量、低成本、更便捷的交付，也是我那段时间里秉承的思路。

下面言归正传，我们用我之前项目在springboot集成 `JPA` 来做一个演示。

### 2.1.1. pom文件

pom中引入 `spring-boot-starter-data-jpa` 依赖，注意版本。

~~~xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
    <version>2.3.4.RELEASE</version>
</dependency>
~~~

### 2.1.2. 自定义Repository工厂类

 `JPA` 默认工厂类 `JpaRepositoryFactoryBean` ，并不能满足我们实际要求，比如我们想在插入或者修改的适合做些事情，默认工厂类就不会支持，所以我们重写一个自己的，继承 `JpaRepositoryFactoryBean`即可。

 ![JpaRepositoryFactoryBean工厂类](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201218103056.png)

 继承后重写 `createRepositoryFactory` 方法，指定我们自己的，这里我用一个内静态类来，这个内部类 `BaseRepositoryFactory` 也需要继承 `JpaRepositoryFactory` 工厂，此处我们指定自己真实的工厂类实现 `BaseRepositoryImpl`。

~~~java

@Override
protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new BaseRepositoryFactory(entityManager);
}

private static class BaseRepositoryFactory<T,I extends Serializable> extends JpaRepositoryFactory{

        private final EntityManager em;

        public BaseRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @Override
        protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
            return new BaseRepositoryImpl<T, I>((Class<T>) information.getDomainType(), em);
        }


        /**
            *  设置具体的实现类的class
            * @param metadata
            * @return
            */
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
}
~~~

下面就可以在我们自己的类做我们的自己事情，同时我们实现类中抽象出来一个接口暴露对外，这个接口定义我们需要共用的方法，具体实现我们额外实现。这样设计有个好处，就是耦合度降低，扩展方便。

![类之间关系图](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201218105948.png)

- 抽象接口

~~~java
@NoRepositoryBean
@Transactional(readOnly=true,rollbackFor = Exception.class)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 根据主键删除
     *
     * @param ids
     */
    void delete(ID[] ids);

    /**
     *
     * @param sql
     * @return
     */
    List<Object[]> listBySQL(String sql);

    public Long getTargetId(String sql);

    /**
     *
     * @param sql
     * @param args
     */
    @Transactional(rollbackFor = Exception.class)
    void updateBySql(String sql,Object...args);


    @Transactional(rollbackFor = Exception.class)
    void updateByHql(String hql,Object...args);

    Page<T> findCriteria(Specification<T> spec, Pageable pageable);

    int batchInsert(String sql);
~~~

- 实现类

~~~java
@SuppressWarnings({"unchecked"})
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	//
	private final EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager=em;
	}

	@Override
	public void delete(ID[] ids) {

	}

	@Override
	public Long getTargetId(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return Long.valueOf(query.getSingleResult().toString());
	}

	@Override
	public void updateBySql(String sql, Object... args) {
		Query query = entityManager.createNativeQuery(sql);
		int i = 0;
		for(Object arg:args) {
			query.setParameter(++i,arg);
		}
		query.executeUpdate();
	}

	@Override
	public void updateByHql(String hql, Object... args) {
		Query query = entityManager.createQuery(hql);
		int i = 0;
		for(Object arg:args) {
			query.setParameter(++i,arg);
		}
		query.executeUpdate();
	}

	@Override
	public List<Object[]> listBySQL(String sql) {
		return entityManager.createNativeQuery(sql).getResultList();
	}

	@Override
	public int batchInsert(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}


	public Page<T> find(Class rootCls, CriteriaQuery<T> criteria, int pageNo, int pageSize) {

		//count
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaC = builder.createQuery();
		Root root = criteriaC.from(rootCls);
		criteriaC.select(builder.count(root));
		criteriaC.where(criteria.getRestriction());
		List<Long> totals = entityManager.createQuery(criteriaC).getResultList();
		Long total = 0L;
		for (Long element : totals) {
			total += element == null ? 0 : element;
		}
		//content
		TypedQuery<T> query = entityManager.createQuery(criteria);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<T> content = total > query.getFirstResult() ? query.getResultList() : Collections.<T> emptyList();
		Sort sort = Sort.by(Sort.Direction.DESC, Constant.DEFAULT_SORT);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<T> pageRst = new PageImpl<T>(content, pageable, total);
		return pageRst;

	}

	@Override
	public Page<T> findCriteria(Specification<T> spec, Pageable pageable){
		  return super.findAll(spec,pageable);
	}

}

~~~

### 2.1.3. 抽象实体基类

实例基类，必须要实现一个接口 `Persistable` ，这个接口只定义ID主键。当然我们自己的基类也会定义这个，但是这并不冲突。

~~~java
@MappedSuperclass
public abstract class AbsEntity<ID extends Serializable> extends AbstractEntity<ID> implements Persistable<ID> {

	private static final long serialVersionUID = 1L;

	@Override
	public abstract ID getId();

    /**
     * Sets the id of the entity.
     * @param id the id to set
     */
    public abstract void setId(final ID id);
    //......
}
~~~

### 2.1.4. 抽象Service基类

抽象 **Service**  提供基础业务类的功能。

~~~java
/** Service基类
 * @ClassName BaseService
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/18 11:05
 * @Version 1.0.0
*/
public abstract class BaseService<T extends AbsEntity<?>, ID extends Serializable> {

    protected JpaRepository<T, ID> jpaRepository;

    public BaseService() {}

    /** 重要 **/
    public abstract void setJpaRepository(JpaRepository<T, ID> jpaRepository);

    public boolean retBoolFindByExample(T t){
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }
        Example<T> example = Example.of(t,matcher);
        if(jpaRepository.findAll(example).size()>0){
            return true;
        }
        return false;
    }

    public boolean retBoolSave(T t ){
        try{
            this.save(t);
            return true;
        }catch (RuntimeException re){
            return false;
        }
    }

    public List<T> findByProperty(T t,String propertyName, Object value) {
        try {
            Class<?> cls = t.getClass();
            Field[] fields = cls.getDeclaredFields();
            Method[] methods = cls.getDeclaredMethods();
            for (int i=0;i<fields.length;i++) {
                if(fields[i].getName().equals(propertyName)){
                    String fieldSetName = StringUtils.parSetName(fields[i].getName());
                    for (Method met : methods) {
                        if (fieldSetName.equals(met.getName())) {
                            met.invoke(t,value);
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return findByEntity(t);
    }

    public boolean retBoolDelete(T t) {
        try {
            this.delete(t);
            return true;
        } catch (RuntimeException re) {
            return false;
        }
    }

    public List<T> findByEntity(T t) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }

/*                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("password");*/

        Example<T> example = Example.of(t,matcher);
        return jpaRepository.findAll(example);
    }

    public Page<T> findPageByEntity(int page, int size, T t) {
        size=size==0?10:size;
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }

/*                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("password");*/

        Example<T> example = Example.of(t,matcher);
        return jpaRepository.findAll(example,pageable);
    }

    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public T save(T t) {
        return jpaRepository.save(t);
    }

    public T saveAndFlush(T t) {
        t = save(t);
        jpaRepository.flush();
        return t;
    }


    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(ID id) {
        jpaRepository.delete(findOne(id));
    }

    /**
     * 删除实体
     *
     * @param t 实体
     */
    public void delete(T t) {
        jpaRepository.delete(t);
    }



    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public T findOne(ID id) {
        return jpaRepository.getOne(id);
    }


    /**
     * 实体是否存在
     * @method      exists
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param id                id 主键
     * @return      boolean   存在 返回true，否则false
     * @exception
     * @date        2018/7/3 22:08
     */
    public boolean exists(ID id) {
        return findOne(id)==null?true:false;
    }


    /**
     * 统计实体总数
     * @method      count
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param
     * @return      long
     * @exception
     * @date        2018/7/3 22:07
     */
    public long count() {
        return jpaRepository.count();
    }


    /**
     * 查询所有实体
     * @method      findAll
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param
     * @return      java.utils.List<T>
     * @exception
     * @date        2018/7/3 22:07
     */
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    /**
     * 按照顺序查询所有实体
     * @method      findAll
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param sort
     * @return      java.utils.List<T>
     * @exception
     * @date        2018/7/3 22:06
     */
    public List<T> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }


    /**
     * 分页及排序查询实体
     *
     * @param pageable 分页及排序数据
     * @return
     */
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    public Page<T> findEntityNoCriteria(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return findAll(pageable);
    }

}

~~~

### 2.1.5. 如何使用？

#### 2.1.5.1. 继承实体基类

用 `lombok` 一目了然，对应字段名以及列与属性对应关系，代码整体简洁。

~~~java
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_locations")
public class Location extends AbsEntity<Long> {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "flag")
    private String flag;

    @Column(name = "local_code")
    //省略....
}
~~~

#### 2.1.5.2. 继承repository基类

直接继承就完事，方法命名的书写，有些讲究，这里就不单独说明，有兴趣童鞋自行恶补。

~~~java
public interface LocationRepository extends BaseRepository<Location, Long>,JpaSpecificationExecutor<Location> {

    List<Location> findByLv(int lv);
    //省略....
}
~~~

#### 2.1.5.3. 继承Service基类

需要引入我们 `locationRepository` ，这一步很关键，当然我看有的人将引入的 `Repository` 弄在类泛型中，效果一样。

~~~java
@Service(value="locationService")
@Transactional(readOnly = true)
public class LocationService extends BaseService<Location, Long> {

	private LocationRepository locationRepository;

	@Autowired
	@Qualifier("locationRepository")
	@Override
	public void setJpaRepository(JpaRepository<Location, Long> jpaRepository) {
		this.jpaRepository=jpaRepository;
		this.locationRepository =(LocationRepository)jpaRepository;
	}

	public List<Location> getLocationListByLevel(int lv){
		return locationRepository.findByLv(lv);
	}
}

~~~

#### 2.1.5.4. 启动类

因为我们自定义了工厂类，所以我们的启动类需要将我们工厂类引入进来，所以 `repositoryFactoryBeanClass` 属性需要了解下。
这样子 `springboot` 整合 `JPA` 就完啦。

~~~java
@EnableSwagger2
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"xyz.wongs.drunkard"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//Specify your own factory class
)
public class MoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonApplication.class,args);
    }

}
~~~

 ![EnableJpaRepositories中指定工厂类](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/linux/es/20201218102932.png)

### 2.1.6. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[**Github源码地址**](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-moon)

[**Gitee源码地址**](https://gitee.com/rothschil/drunkard/tree/master/akkad-war3/war3-moon)

## 2.2. 自动刷新MybatisXML

使用Mybatis过程中，很多时候修改了XML文件需要整个项目重新启动，比较耗时，如果没什么业务数据状态还好，有数据状态可就惨啦，所以XML自动线下更新就很有必要。手写一个简单实现，大家参考下。

我的实现思路就是利用一个额外线程扫描`mybatis` XML文件，更新到 `Spring`中的 上下文`ApplicationContext`中。

### 2.2.1. 配置文件

我们定义一套刷新时间和周期频次的配置文件在路径 `persistence-mybatis\mybatis-base\src\main\resources\conf\mybatis-refresh.properties` 中，里面内容如下：

~~~properties
enabled=true
delaySeconds=30
sleepSeconds=10
mappingPath=mapper
~~~

- enabled：是否开启自动刷新
- delaySeconds： 间隔时间
- sleepSeconds： 休眠时间
- mappingPath：XML的路径

核心类需要实现上下文接口 `ApplicationContextAware`。

### 2.2.2. 关键步骤

- @Override重写`setApplicationContext` 方法
- 用静态语句块，初始化配置文件中的相关参数
- @PostConstruct：在构造函数之后对`SqlSessionFactory`进行额外配置
- 启用线程按照频次间隔重复执行上述操作

关键性步骤如下：

~~~java
// 1、从上下文容器获取 SqlSessionFactory
SqlSessionFactory sessionFactory = applicationContext.getBean(SqlSessionFactory.class);
// 2、获取Configuration
Configuration configuration = sessionFactory.getConfiguration();
this.configuration = configuration;
// 3、扫描Locations
mapperLocations = getResource(basePackage,XML_RESOURCE_PATTERN);
// 4、启动线程执行
exeTask();
~~~

核心类在`akkad-base\persistence-mybatis\mybatis-base\src\main\java\xyz\wongs\drunkard\base\persistence\mybatis\loader\MapperAutoRefresh.java` 下，而且行数太长，代码就不贴。

在多线程处理这块有需要注意有一定的线程使用基础，看官自行学习。

### 2.2.3. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-base/persistence-mybatis)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.3. 手写爬虫获取国家统计局行政区划数据

很多地方需要用到 `统计用区划和城乡划分代码` 这块以国家统计局的权威数据为准，但是人家是一个网页。

![国家统计局-统计用区划和城乡划分代码](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201173355.png)

虽然Python解析起来很快，但我还是想用 `Java` 写一套，打发时间也好，无聊也罢，学习学习。

首先要做的就是分析网页的内容特点，进行数据建模和构建框架。

我本机MySQL运行的，图个方便也没用Oracle或者服务器类，一切从简。

~~~sql
CREATE TABLE tb_locations (
  id bigint(20) NOT NULL,
  flag varchar(6) DEFAULT NULL,
  local_code varchar(30) DEFAULT NULL,
  local_name varchar(100) DEFAULT NULL,
  lv int(11) DEFAULT NULL,
  sup_local_code varchar(30) DEFAULT NULL,
  url varchar(60) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
~~~

先说下我的实现思路：自上而下，逐级递归。

统计用区划和城乡可以想象成一个树形结构，主干就是省、直辖市、自治区。逐级解析`html`文本内容，再拼装成完整`URI`路径作为下一级路径解析依据。

这里用到两个技术点：

- Mybatis实现的批量提交
- dom4j解析xml元素

### 2.3.1. 依赖包

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>akkad-war3</artifactId>
        <groupId>xyz.wongs.drunkard</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>war3-area</artifactId>
    <packaging>jar</packaging>
    <dependencies>
        <dependency>
            <groupId>xyz.wongs.drunkard</groupId>
            <artifactId>mybatis-pk-redis</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>com.oracle</groupId>
                    <artifactId>ojdbc6</artifactId>
                </exclusion>
                <exclusion>
                    <artifactId>HikariCP</artifactId>
                    <groupId>com.zaxxer</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.9.2</version>
        </dependency>
        <dependency>
            <groupId>net.sourceforge.htmlunit</groupId>
            <artifactId>neko-htmlunit</artifactId>
            <version>2.30</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpmime</artifactId>
            <version>4.5.5</version>
        </dependency>
        <dependency>
            <groupId>com.gargoylesoftware</groupId>
            <artifactId>htmlunit</artifactId>
            <version>2.3</version>
        </dependency>

        <dependency>
            <groupId>net.sourceforge.htmlunit</groupId>
            <artifactId>htmlunit-core-js</artifactId>
            <version>2.31</version>
        </dependency>
        <dependency>
            <groupId>com.gargoylesoftware</groupId>
            <artifactId>htmlunit-cssparser</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>xalan</groupId>
            <artifactId>xalan</artifactId>
            <version>2.7.2</version>
        </dependency>
        <dependency>
            <groupId>xerces</groupId>
            <artifactId>xercesimpl</artifactId>
            <version>2.11.0</version>
        </dependency>

        <dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>javax.persistence-api</artifactId>
            <version>2.2</version>
        </dependency>
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
        </dependency>

    </dependencies>


    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.2</version>
                <configuration>
                    <configurationFile>${basedir}/src/main/resources/generator/generatorConfig.xml</configurationFile>
                    <overwrite>true</overwrite>
                    <verbose>true</verbose>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
~~~

### 2.3.2. 核心实现代码

~~~java
package xyz.wongs.drunkard.war3.web.area.task.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.service.LocationService;
import xyz.wongs.drunkard.war3.web.util.IdClazzUtils;
import xyz.wongs.drunkard.war3.web.util.AreaCodeStringUtils;
import xyz.wongs.drunkard.war3.web.area.task.ProcessService;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: JsoupProcessServiceImpl
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: <a href="wcngs@qq.com">WCNGS</a>
 * @date: 2017年7月28日 上午11:31:30  *
 * @Copyright: 2017 WCNGS Inc. All rights reserved.
 */
@Slf4j
@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    @Qualifier("locationService")
    LocationService locationService;

    @Override
    public void initLevelOne(String url, Location parentLocation) {
        List<Location> levelOne = null;
        try {
            levelOne = getLevelOneByRoot(url, parentLocation.getLocalCode());
        } catch (IOException e) {
            log.error(" IOException pCode={}", parentLocation.getLocalCode(), e.getMessage(), url);
        }
        save(levelOne);
    }

    @Override
    public boolean initLevelTwo(String url, Location location) {
        try {
            List<Location> secondLevelLocas = getLocationSecondLevel(url, location);
            save(secondLevelLocas);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 初始化省、直辖区、自治区
     *
     * @param url
     * @return void
     * @throws
     * @method intiRootUrl
     * @author WCNGS@QQ.COM
     * @version
     * @date 2018/6/30 23:29
     * @see
     */
    @Override
    public boolean intiRootUrl(String url) {
        try {
            List<Location> rootLocations = getLocationRoot(url, "0");
            save(rootLocations);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Location> getLocationRoot(String url, String pCode) {
        List<Location> locas = new ArrayList<Location>(35);
        try {
            Elements eleProv = getElementsByConnection(url, "provincetr");
            for (Element e : eleProv) {
                Elements eleHerf = e.getElementsByTag("td").select("a[href]");
                if (null == eleHerf || eleHerf.size() == 0) {
                    continue;
                }
                for (Element target : eleHerf) {
                    String urls = target.attributes().asList().get(0).getValue();
                    Location location = Location.builder().id(IdClazzUtils.getId(Location.class))
                            .localCode("0").url(urls).lv(0).localName(target.text())
                            .localCode(urls.substring(0, 2)).build();
                    locas.add(location);
                }
            }
        } catch (IOException e) {
            log.error(" IOException pCode={}", pCode, e.getMessage(), url);
        }
        return locas;
    }

    /**
     * 方法实现说明
     *
     * @param url
     * @param location
     * @return void
     * @throws
     * @method thridLevelResolve
     * @author WCNGS@QQ.COM
     * @version
     * @date 2018/7/1 9:50
     * @see
     */
    @Override
    public void initLevelThrid(String url, Location location) {
        this.initLevelThrid(url, location, "Y");
    }


    /**
     * 方法实现说明
     *
     * @param url
     * @param location
     * @param flag
     * @return void
     * @throws
     * @method thridLevelResolve
     * @author WCNGS@QQ.COM
     * @version
     * @date 2018/7/1 16:24
     * @see
     */
    @Override
    public void initLevelThrid(String url, Location location, String flag) {

        try {
            if (StringUtils.isEmpty(location.getUrl())) {
                return;
            }
            List<Location> thridLevelLocas = getLocation(url, new String[]{"towntr", "href"}, location.getLocalCode(), 3, flag);
            location.setFlag(flag);
            locationService.updateByPrimaryKey(location);
            save(thridLevelLocas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(List<Location> locations) {
        //结果为空，抛出异常
        if (null == locations || locations.isEmpty()) {
            log.error(" target saved is null!");
            return;
        }
        locationService.insertBatchByOn(locations);

    }


    @Override
    public void initLevelFour(String url, List<Location> thridLevelLocas) {
        for (Location le : thridLevelLocas) {
            List<Location> locations = new ArrayList<Location>(12);
            String suffix = new StringBuilder().append(url).append(AreaCodeStringUtils.getUrlStrByLocationCode(le.getLocalCode(), 3)).append(le.getUrl()).toString();
            Elements es = null;
            try {
                es = getElementsByConnection(suffix, "villagetr");
                Location tempLocation = null;
                for (Element e : es) {
                    tempLocation = new Location(e.child(0).text(), e.child(2).text(), le.getLocalCode(), null, 4);
                    tempLocation.setId(IdClazzUtils.getId(Location.class));
                    locations.add(tempLocation);
                }
                le.setFlag("Y");
                locationService.updateByPrimaryKey(le);
                save(locations);
            } catch (IOException e) {
                log.error(" IOException code={},msg={},url={}", le.getLocalCode(), e.getMessage(), suffix);
                int times = AreaCodeStringUtils.getSecond(3);
                try {
                    TimeUnit.SECONDS.sleep(times);
                } catch (InterruptedException interruptedException) {
                    log.error("msg={} ", interruptedException.getMessage());
                }
                continue;
            } catch (Exception e) {
                log.error("Exception code={},msg={}", le.getLocalCode(), e.getMessage());
                continue;
            }
        }
    }


    /**
     * @param url
     * @param location
     * @return
     * @Title: getLocationSecondLevel
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @return: List<Location>
     */
    public List<Location> getLocationSecondLevel(String url, Location location) {
        List<Location> locas = null;
        try {
            locas = new ArrayList<Location>(90);
            //URL地址截取
            //标识位
            boolean flag = false;
            Elements es = getElementsByConnection(url, "countytr");
            if (null == es) {
                log.error(url + " 不能解析！");
                return null;
            }
            Location tempLocation = null;
            for (Element e : es) {
                //针对市辖区 这种无URL的做特殊处理
                if (!flag) {
                    tempLocation = new Location(e.child(0).text(), e.child(1).text(), location.getLocalCode(), null, 2);
                    tempLocation.setId(IdClazzUtils.getId(Location.class));
                    locas.add(tempLocation);
                    //标识位置为TURE
                    flag = true;
                    continue;
                }
                es = e.getElementsByAttribute("href");
                if (es.size() == 0) {
                    tempLocation = new Location(e.child(0).text(), e.child(1).text(), location.getLocalCode(), "", 2);
                    tempLocation.setId(IdClazzUtils.getId(Location.class));
                    locas.add(tempLocation);
                    continue;
                }
                List<Attribute> attrs = es.get(0).attributes().asList();
                tempLocation = new Location(es.get(0).text(), es.get(1).text(), location.getLocalCode(), attrs.get(0).getValue(), 2);
                tempLocation.setId(IdClazzUtils.getId(Location.class));
                locas.add(tempLocation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locas;
    }


    /**
     * @param url
     * @param pCode
     * @return
     * @Title: getLocationOneLevel
     * @Description: 1、获取第一级地市信息
     * 2、第二级区县信息
     * @return: List<Location>
     */
    public List<Location> getLevelOneByRoot(String url, String pCode) throws IOException {

        List<Location> locas = new ArrayList<Location>(20);
        Elements eles = getElementsByConnection(url, "citytr");
        if (null == eles) {
            log.error(url + " 不能解析！");
            return null;
        }
        Location location = null;
        for (Element e : eles) {
            eles = e.getElementsByAttribute("href");
            List<Attribute> attrs = eles.get(0).attributes().asList();
            location = new Location(eles.get(0).text(), eles.get(1).text(), pCode, attrs.get(0).getValue(), 1);
            location.setId(IdClazzUtils.getId(Location.class));
            locas.add(location);
        }
        return locas;
    }


    public List<Location> getLocation(String url, String[] cssClazz, String parentCode, Integer lv, String flag) throws IOException {
        List<Location> locas = new ArrayList<Location>(20);
        Elements eles = getElementsByConnection(url, cssClazz[0]);
        if (null == eles) {
            log.error(url + " 不能解析！");
            return null;
        }
        Location location = null;
        for (Element e : eles) {
            eles = e.getElementsByAttribute(cssClazz[1]);
            List<Attribute> attrs = eles.get(0).attributes().asList();
            location = new Location(eles.get(0).text(), eles.get(1).text(), parentCode, attrs.get(0).getValue(), lv, flag);
            location.setId(IdClazzUtils.getId(Location.class));
            locas.add(location);
        }
        return locas;
    }

    /**
     * 案例
     * <tr class='towntr'>
     * <td><a href='02/340102001.html'>340102001000</a></td>
     * <td><a href='02/340102001.html'>明光路街道</a></td>
     * </tr>
     *
     * @param url
     * @param cssClazz
     * @param parentURLCode
     * @return List<Location>
     * @Title: getLocation
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    public List<Location> getLocation(String url, String[] cssClazz, String parentCode, Integer lv) {
        return getLocation(url, cssClazz, parentCode, lv);
    }


    /**
     * 方法实现说明
     *
     * @param url
     * @param clazzName
     * @return org.jsoup.select.Elements
     * @throws
     * @method getElementss
     * @author WCNGS@QQ.COM
     * @version
     * @date 2018/7/2 11:28
     * @see
     */
    public Elements getElementsByConnection(String url, String clazzName) throws IOException {

        try {
            /** CloseableHttpClient httpclient = HttpClients.createDefault(); **/
            //设置CookieSpecs.STANDARD的cookie解析模式，下面为源码，对应解析格式我给出了备注
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setDefaultRequestConfig(RequestConfig.custom()
                            .setCookieSpec(CookieSpecs.STANDARD).build())
                    .build();
            HttpGet httpget = new HttpGet(url);
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
            RequestConfig config = RequestConfig.custom()
                    //.setProxy(proxy)
                    //设置连接超时 ✔
                    // 设置连接超时时间 10秒钟
                    .setConnectTimeout(10000)
                    // 设置读取超时时间10秒钟
                    .setSocketTimeout(10000)
                    .build();
            httpget.setConfig(config);
            // 执行get请求
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            // 获取返回实体
            String content = EntityUtils.toString(entity, "GBK");
            // ============================= 【Jsoup】 ====================================
            Document doc = Jsoup.parse(content);
            return doc.getElementsByClass(clazzName);
        } catch (ConnectTimeoutException e) {
            log.error(" ConnectTimeoutException URL={},clazzName={},errMsg={}", url, clazzName, e.getMessage());
        }

        return null;
    }

    /**
     * @param locations
     * @return java.lang.String
     * @throws
     * @Description
     * @date 2020/9/9 14:52
     */
    public String appengUrl(List<Location> locations) {
        Iterator<Location> it = locations.iterator();
        String url = "";
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            Location cation = it.next();
            String str = cation.getUrl();
            if (cation.getLv() == 3) {
                sb.append(str);
            } else {
                int i = cation.getUrl().indexOf(Constant.SLASH);
                sb.append(str.substring(0, i)).append(Constant.SLASH).append(sb);
            }
        }
        return url;
    }

}

~~~

### 2.3.3. 单元测试

在单元测试中，自上而下，按个运行测试方法即可。

- initRoot：初始化省、直辖市、自治区的。数量31，速度非常快
- intLevelOne：初始化城市，数量三百多，速度快
- intLevelTwo：初始化县区，数量四千多，速度一般
- intLevelThree：初始化乡镇 街道，数量四万，速度慢
- intLevelFour： 初始化社区村，速度非常慢，需要按照批次执行

同时在运行中，可能会由于服务器拒绝连接，造成无法解析出来地址，这没关系，代码中已经容错这些，继续执行即可！

~~~java
package xyz.wongs.drunkard.task;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import xyz.wongs.drunkard.base.BaseTest;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.service.LocationService;
import xyz.wongs.drunkard.war3.web.util.AreaCodeStringUtils;
import xyz.wongs.drunkard.war3.web.area.task.ProcessService;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ProcessServiceImplTest
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:26
 * @Version 1.0.0
 */
@Slf4j
public class ProcessServiceTest extends BaseTest {

    private static final String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";
    private final static Logger logger = LoggerFactory.getLogger(ProcessServiceTest.class);

    @Autowired
    @Qualifier("processService")
    private ProcessService processService;

    @Autowired
    private LocationService locationService;


    /**
     * 获取所有省，作为Root根节点
     *
     * @return
     * @throws
     * @Description
     * @date 2020/4/30 0:41
     */
    @Test
    public void initRoot() {
        processService.intiRootUrl(URL);
    }


    /**
     * 解析所有省、直辖的城市
     *
     * @return void
     * @throws
     * @Description
     * @date 2020/9/4 22:03
     */
    @Test
    public void intLevelOne() throws Exception {
        city(1);
    }


    public void city(int pageNum) {
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(0, pageNum, 30);
        if (pageInfo.getPages() == 0 || pageInfo.getPageNum() > pageInfo.getPages()) {
            return;
        }
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        while (iter.hasNext()) {
            Location location = iter.next();
            String uls = URL + location.getUrl();
            processService.initLevelOne(uls, location);
            location.setFlag("Y");
            locationService.updateByPrimaryKey(location);
        }
        city(pageNum + 1);
    }


    /**
     * 根据地市，解析并初始化区县
     *
     * @return void
     * @throws
     * @Description
     * @date 2020/9/5 10:21
     */
    @Test
    public void intLevelTwo() throws Exception {
        exet(1);
    }

    public void exet(int pageNum) {
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(1, pageNum, 30);
        if (pageInfo.getPages() == 0 || pageInfo.getPageNum() > pageInfo.getPages()) {
            return;
        }
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        while (iter.hasNext()) {
            Location location = iter.next();
            String url2 = new StringBuilder().append(URL).append(location.getUrl()).toString();
            processService.initLevelTwo(url2, location);
            location.setFlag("Y");
            locationService.updateByPrimaryKey(location);
        }
        exet(pageNum + 1);
    }

    /**
     * 根据区县，解析并初始化乡镇 街道
     *
     * @return
     * @throws
     * @Description
     * @date 2020/4/30 0:27
     */
    @Test
    public void intLevelThree() {
        three(1);
    }

    public void three(int pageNum) {
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(2, pageNum, 100);
        if (pageInfo.getPages() == 0 || pageInfo.getPageNum() > pageInfo.getPages()) {
            return;
        }
        uot++;
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        Location location = null;
        while (iter.hasNext()) {
            location = iter.next();
            String url2 = new StringBuilder().append(URL).append(AreaCodeStringUtils.getUrlStrByLocationCode(location.getLocalCode(), 2)).append(location.getUrl()).toString();
            processService.initLevelThrid(url2, location, "D");
            try {
                int times = AreaCodeStringUtils.getSecond(3);
                TimeUnit.SECONDS.sleep(times);
            } catch (InterruptedException e) {
                log.error("msg={} ", e.getMessage());
            }
        }
        if (uot == COT) {
            return;
        }
        three(pageNum + 1);
    }

    private static int COT = 100;
    private static int uot = 0;

    /**
     * 根据乡镇 街道，解析并初始化社区村
     *
     * @return
     * @Description
     * @throwsOperationImplicitParameterReader
     * @date 2020/4/30 0:27
     */
    @Test
    public void intLevelFour() {
        Location location = new Location();
        location.setLv(3);
        location.setFlag("D");
        village(0, location);
    }

    public void village(int pageNum, Location location) {
        PageInfo<Location> pageInfo = locationService.getLocationsByLvAndFlag(pageNum, 2, location);
        log.error(pageInfo.toString());
        if (pageInfo.getPages() == 0 || pageInfo.getPageNum() > pageInfo.getPages()) {
            return;
        }
        uot++;
        List<Location> locations = pageInfo.getList();
        if (!locations.isEmpty()) {
            processService.initLevelFour(URL, locations);
        }
        if (uot == COT) {
            return;
        }
        village(pageNum + 1, location);
    }

}
~~~

### 2.3.4. 打开浏览器

访问 `http://localhost:9090/region/ip=109.27.45.12` 这是我之前一个例子，用来解析IP地址，获取地域信息的。

![样例响应](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165146.png)

### 2.3.5. 源码地址，如果觉得对你有帮助，请Star

**觉得对你有帮助，请Star**

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-area)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.4. 集成ip2region离线IP地名映射

前段时间因业务需要，客户提出分析外网访问的IP，即针对一些热点区域的IP访问想做到事后预警与分析。
因为我们服务是运行在相对隔离的资源环境中，无法直接去请求外网，于是想到用离线的方式来处理从网关发来的数据。找了下，看到个开源项目

- [Ip2region参考1](https://github.com/lionsoul2014/ip2region)
- [ip2region参考2](https://github.com/hiwepy/ip2region-spring-boot-starter) 

使用起来相对成本较低，本着先用满足需求再说，虽然在性能上并不能满足海量IP的分析，还有提升的空间。

看作者的例子，较为简单，也不多说。先看下我的目录结构吧

~~~
war3-infi
+---src
|   +---main
|   |   +---java
|   |   \---resources
|   |       +---generator
|   |       +---ipdb
|   |   |   |   +---ip2region.db
|   |       +---mapper
|   |       +---application.yml
~~~

- `ip2region.db` 这是需要下载，[下载地址](https://github.com/lionsoul2014/ip2region/tree/master/data)，这里提供Csv、Txt、Db文件三种格式，根据需要自行选择。

- `ipdb`这是我放db库文件的路径，当然可以自定义，只需要在`application.yml` 中配置即可。

- `application.yml` 这个就不多说啦。

~~~yml
server:
  port: 9090

spring:
  redis:
    database: 0
    host: 127.0.0.1
    port: 6379

ip2region:
  external: false
  index-block-size: 4096
  total-header-size: 8192
  location: classpath:ipdb/ip2region.db
~~~

例子如下,`RegionAddress`、`DataBlock` 两种结果返回封装。

~~~java

package xyz.wongs.drunkard.war3.web.controller;


import com.github.hiwepy.ip2region.spring.boot.IP2regionTemplate;
import com.github.hiwepy.ip2region.spring.boot.ext.RegionAddress;
import lombok.extern.slf4j.Slf4j;
import org.nutz.plugins.ip2region.DataBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.framework.limit.RequestLimit;

import java.io.IOException;

/**
 * @ClassName IndexController
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:00
 * @Version 1.0.0
*/
@Slf4j
@RestController
@ResponseResult
public class IndexController {

    @Autowired
    IP2regionTemplate template;

    /** 根据输入IP地址，返回解析后的地址
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @GetMapping(value = "/convert/{ip}")
    public DataBlock convertDataBlock(@PathVariable String ip){
        DataBlock dataBlock = null;
        try {
            dataBlock = template.binarySearch(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBlock;
    }

    /** 根据输入IP地址，返回解析后的地址
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @RequestLimit(maxCount=3)
    @GetMapping(value = "/region/{ip}")
    public RegionAddress convert(@PathVariable String ip){
        RegionAddress regionAddress = null;
        try {
            regionAddress = template.getRegionAddress(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regionAddress;
    }

    @GetMapping(value = "/region/ip={ip}")
    public RegionAddress caseInsensitive(@PathVariable String ip){
        RegionAddress regionAddress = null;
        try {
            regionAddress = template.getRegionAddress(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regionAddress;
    }

}

~~~

### 2.4.1. 打开浏览器

访问 `http://localhost:9090/region/ip=109.27.45.12` 这是我之前一个例子，用来解析IP地址，获取地域信息的。

![样例响应](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165146.png)

### 2.4.2. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-infi)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.5. Http响应内容统一封装

我们在开发`前端`和`后端`进行交互服务过程中，受制于前后端的工作职责明确，在交互协议的定义上理解也较为不同，造成一个项目服务中重复定义交互内容以及编码上重复编写，不利于项目维护。所以基于此，将`后端`按照约定请求URL路径，并传入相关参数，`后端`服务器接收请求，进行业务处理，返回数据给前端，进行再次封装，供前端以及外部调用。

通常情况下我们`后端`返回给`前端`都会采用 `JSON` 的定义，具体如下：

~~~
{
// 返回状态码
code:integer,
// 返回信息描述
message:string,
// 返回值
data:object
}
~~~

- code状态码

在状态码的定义上，在满足业务需求的基础上，避免凌乱，一般业界同行做法就是参考HTTP请求返回的状态码。
具体如 [百度 - HTTP状态码](https://baike.baidu.com/item/HTTP%E7%8A%B6%E6%80%81%E7%A0%81/5053660?fr=aladdin)。

这里我贴出我将我项目中的常用的罗列出来，供大家参考。

~~~java
package xyz.wongs.drunkard.base.message.enums;

/**
 * @ClassName
 * @Description
 * 1000～1999 区间表示参数错误
 * 2000～2999 区间表示用户错误
 * 3000～3999 区间表示接口异常
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:31
 * @Version 1.0.0
 */
public enum ResultCode {

    /** 成功 **/
    SUCCESS(0,"成功"),
    /** 失败 **/
    FAILURE(-1,"失败"),

    EXCEPTION(201, "未知异常"),
    RUNTIME_EXCEPTION(202, "运行时异常"),
    NULL_POINTER_EXCEPTION(203, "空指针异常"),
    CLASS_CAST_EXCEPTION(204, "类型转换异常"),
    IO_EXCEPTION(205, "IO异常"),
    SYSTEM_EXCEPTION(210, "系统异常"),
    NOT_FOUND(404, "Not Found"),

    /**
     * 1000～1999 区间表示参数错误
     */
    PARAMS_IS_INVALID(1001,"参数无效"),
    PARAMS_IS_BANK(1002,"参数为空"),
    PARAMS_TYPE_BIND_ERROR(1003,"参数类型错误"),
    PARAMS_NOT_COMPLETE(1004,"参数缺失"),

    /**
     * 2000～2999 区间表示用户错误
     */
    USER_NOT_LOGGED_IN(2001,"用户未登录，访问路径需要验证"),
    USER_NOT_LOGIN_ERROR(2002,"用户不存在或密码错误"),
    USER_ACCOUNT_FORBIDDEN(2003,"用户被禁用"),
    USER_NOT_EXIST(2004,"用户不存在"),
    USER_HAS_EXISTED(2005,"用户已存在"),
    USER_IS_EXPIRED(2006,"用户账号已过期"),
    USER_FIRST_LANDING(2007, "首次登录"),
    USER_TOKEN_EXPIRED(2008,"Token过期"),
    USER_TOKEN_GENERTATION_FAIL(2009,"生成Token失败"),
    USER_SIGN_VERIFI_NOT_COMPLIANT(2010,"签名校验不合规"),
    USER_PASSWORD_RESET_FAILED(2011, "重置密码失败"),
    USER_UNKONWN_INDENTITY(2012, "未知身份"),
    MANY_USER_LOGINS(2111,"多用户在线"),
    TOO_MANY_PASSWD_ENTER(2112, "密码输入次数过多"),
    VERIFICATION_CODE_INCORECT(2202,"图形验证码不正确"),
    VERIFICATION_CODE_FAIL(2203,"图形验证码生产失败"),

    /**
     * 3000～3999 区间表示接口异常
     */
    API_EXCEPTION(3000, "接口异常"),
    API_NOT_FOUND_EXCEPTION(3002, "接口不存在"),
    API_REQ_MORE_THAN_SET(3003, "接口访问过于频繁，请稍后再试"),
    API_IDEMPOTENT_EXCEPTION(3004, "接口不可以重复提交，请稍后再试"),
    API_PARAM_EXCEPTION(3005, "参数异常"),
    API_PARAM_MISSING_EXCEPTION(3006, "缺少参数"),
    API_METHOD_NOT_SUPPORTED_EXCEPTION(3007, "不支持的Method类型"),
    API_METHOD_PARAM_TYPE_EXCEPTIION(3008, "参数类型不匹配"),

    ARRAY_EXCEPTION(11001, "数组异常"),
    ARRAY_OUT_OF_BOUNDS_EXCEPTION(11002, "数组越界异常"),

    JSON_SERIALIZE_EXCEPTION(30000, "序列化数据异常"),
    JSON_DESERIALIZE_EXCEPTION(30001, "反序列化数据异常"),

    READ_RESOURSE_EXCEPTION(31002, "读取资源异常"),
    READ_RESOURSE_NOT_FOUND_EXCEPTION(31003, "资源不存在异常"),

    DATA_EXCEPTION(32004, "数据异常"),
    DATA_NOT_FOUND_EXCEPTION(32005, "未找到符合条件的数据异常"),
    DATA_CALCULATION_EXCEPTION(32006, "数据计算异常"),
    DATA_COMPRESS_EXCEPTION(32007, "数据压缩异常"),
    DATA_DE_COMPRESS_EXCEPTION(32008, "数据解压缩异常"),
    DATA_PARSE_EXCEPTION(32009, "数据转换异常"),

    ENCODING_EXCEPTION(33006, "编码异常"),
    ENCODING_UNSUPPORTED_EXCEPTION(33006, "编码不支持异常"),

    DATE_PARSE_EXCEPTION(34001, "日期转换异常"),

    MAILE_SEND_EXCEPTION(35001, "邮件发送异常");

    /**
     *
     */
    private Integer code;

    /**
     *
     */
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

~~~

- message内容

这个不解释啦， 就是编码的文字的意义，说清楚就行，没必要太较真，自行脑补。

-  data数据

这就是业务具体数据啦，根据具体业务，内容也不同，这一章节也没必要说。

    这里小结下，我们除了要有需要定义的内容有两块：返回的JSON消息体（这里区分正常响应返回、异常响应返回），还需要一套状态码详细定义；再有我们这里做的是WEB，既然做通用，怎能少拦截器。

摆脱了繁琐的文字，下面开始张罗着贴实现代码啦。

### 2.5.1. 消息体

结合我们定义的状态码，我们返回的消息体主要实现一个 `Serializable`，不要问我为什么。

#### 2.5.1.1. 正常响应

~~~java
package xyz.wongs.drunkard.base.message.response;

import lombok.Data;
import xyz.wongs.drunkard.base.message.enums.ResultCode;

import java.io.Serializable;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:48
 * @Version 1.0.0
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = -4505655308965878999L;

    private Integer code;

    private String message;

    private Object data;

    private Result() {
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
        this.data = data;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }

    /** 返回成功
     * @Description
     * @param
     * @return xyz.wongs.drunkard.base.message.response.R
     * @throws
     * @date 20/11/13 17:15
     */
    public static Result success() {

        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }
    /** 返回成功
     * @Description
     * @param
     * @return xyz.wongs.drunkard.base.message.response.R
     * @throws
     * @date 20/11/13 17:15
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /** 返回失败
     * @Description
     * @param
     * @return xyz.wongs.drunkard.base.message.response.R
     * @throws
     * @date 20/11/13 17:15
     */
    public static Result fail(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /** 返回失败
     * @Description
     * @param
     * @return xyz.wongs.drunkard.base.message.response.R
     * @throws
     * @date 20/11/13 17:15
     */
    public static Result fail(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

}

~~~

#### 2.5.1.2. 异常响应

~~~java
package xyz.wongs.drunkard.base.message.response;

import lombok.Data;
import xyz.wongs.drunkard.base.message.enums.ResultCode;

import java.io.Serializable;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ErrorResult
 * @Description 异常错误的返回信息实体
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 10:42
 * @Version 1.0.0
 */
@Data
public class ErrorResult implements Serializable {

    private static final long serialVersionUID = -4505655308965878999L;

    /**
     * 错误编码
     **/
    private Integer code;
    /**
     * 消息描述
     **/
    private String msg;
    /**
     * 错误
     **/
    private String exception;

    public static ErrorResult fail(ResultCode resultCode, Throwable e, String message) {
        ErrorResult errorResult = ErrorResult.fail(resultCode, e);
        errorResult.setMsg(message);
        return errorResult;
    }

    public static ErrorResult fail(ResultCode resultCode, Throwable e) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(resultCode.getCode());
        errorResult.setMsg(resultCode.getMsg());
        errorResult.setException(e.getClass().getName());
        return errorResult;
    }

    public static ErrorResult fail(Integer code, String message) {
        ErrorResult errorResult = new ErrorResult();
        errorResult.setCode(code);
        errorResult.setMsg(message);
        return errorResult;
    }

}

~~~

这样两个消息体就写完啦。

### 2.5.2. 拦截器

我们这里需要做的就是利用拦截器拦截请求，检查判断是否此请求返回的值需要包装。核心就是判断一个注解`annoation`是否存在方法或类中。

为了演示的完整，我将代码贴完整。

#### 2.5.2.1. Annoation注解

~~~java
/**
 * @ClassName ResponseResult
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/10/30 21:57
 * @Version 1.0.0
*/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
~~~

#### 2.5.2.2. 拦截器

~~~java

package xyz.wongs.drunkard.base.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ResponseResultInterceptor
 * @Description 请求的拦截器
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/10/30 22:08
 * @Version 1.0.0
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {

    private static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }

}

~~~

着十几行代码的核心处理逻辑，就是获取此请求Annoation注解，是否需要返回值包装，并设置一个属性标记，交由下一处理`ResponseResultHandler`来具体封装返回值。

细心的人会发现这里只处置正常成功的内容返回，对于异常的内容并未处置。关于异常处置我理解统一放在一起来编写，这样代码结构性会更好。由此引出下一章节，`全局异常`。

~~~java
package xyz.wongs.drunkard.base.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.response.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ResponseResultHandler
 * @Description 消息返回体
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/10 09:28
 * @Version 1.0.0
*/
@Slf4j
@ControllerAdvice(basePackages = "xyz.wongs.drunkard")
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    private static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";


    /**
     * @Description 判断是否要执行 beforeBodyWrite 方法，true为执行，false不执行，有注解标记的时候处理返回值
     * @param returnType
     * @param converterType
     * @return boolean
     * @throws
     * @date 20/11/13 10:50
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra =(ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        ResponseResult responseResult = (ResponseResult)request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResult==null?false:true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectContentType, Class<? extends HttpMessageConverter<?>> selectConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.error(" ENTER MSG .... Excu");
        if(body instanceof Result){
            return (Result) body;
        }
        return Result.success(body);
    }
}

~~~

#### 2.5.2.3. 全局异常

这里所有的异常都使用到 `ErrorResult` 类。

~~~java
package xyz.wongs.drunkard.base.message.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.response.ErrorResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author WCNGS@QQ.COM
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理Handler
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/9/23 15:03
 * @Version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数校验不通过
     *
     * @param ex
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/9/23 17:53
     * @since
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public ErrorResult handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException msg:{}", ex.getMessage());
        return ErrorResult.fail(ResultCode.PARAMS_IS_INVALID, ex);
    }


    /**
     * 自定义异常
     *
     * @param request
     * @param ex
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @author WCNGS@QQ.COM
     * @See
     * @date 2019/9/23 17:53
     * @since
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(DrunkardException.class)
    @ResponseBody
    public ErrorResult handleWeathertopException(HttpServletRequest request, DrunkardException ex) {
        log.error("WeathertopRuntimeException code:{},msg:{}", ex.getCode(), ex.getMessage());
        return ErrorResult.fail(ex.getCode(), ex.getMessage());
    }

    /**
     * @param e
     * @param request
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @Description 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
     * @date 20/11/13 11:14
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handlerThrowable(Throwable e, HttpServletRequest request) {
        log.error("发生未知异常！原因是: ", e);
        ErrorResult error = ErrorResult.fail(ResultCode.RUNTIME_EXCEPTION, e);
        return error;
    }

    /**
     * @param e
     * @param request
     * @return xyz.wongs.drunkard.base.message.response.ErrorResult
     * @throws
     * @Description 参数校验异常
     * @date 20/11/13 11:14
     */
    @ExceptionHandler(BindException.class)
    public ErrorResult handleBindExcpetion(BindException e, HttpServletRequest request) {
        log.error("发生参数校验异常！原因是：", e);
        ErrorResult error = ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getAllErrors().get(0).getDefaultMessage());
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("发生参数校验异常！原因是：", e);
        ErrorResult error = ErrorResult.fail(ResultCode.API_PARAM_EXCEPTION, e, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return error;
    }
}

~~~

### 2.5.3. 例子

以上虽然将所有代码贴出，这列为凑完整，顺道将写个例子来，写个 `Controller`

~~~java
package xyz.wongs.drunkard.war3.web.controller;


import com.github.hiwepy.ip2region.spring.boot.IP2regionTemplate;
import com.github.hiwepy.ip2region.spring.boot.ext.RegionAddress;
import lombok.extern.slf4j.Slf4j;
import org.nutz.plugins.ip2region.DataBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.framework.limit.RequestLimit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:00
 * @Version 1.0.0
*/
@Slf4j
@RestController
@ResponseResult
public class IndexController {

    @RequestLimit(maxCount=3,second=20)
    @ApplicationLog
    @GetMapping("/test")
    public Map<String, Object> test() {
        HashMap<String, Object> data = new HashMap<>(3);
        data.put("info", "测试成功");
        return data;
    }

    @ApplicationLog
    @GetMapping("/fail")
    public Integer error() {
        // 查询结果数
        int res = 0;
        if( res == 0 ) {
            throw new DrunkardException("没有数据");
        }
        return res;
    }

    @Autowired
    IP2regionTemplate template;

    /** 根据输入IP地址，返回解析后的地址
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @GetMapping(value = "/convert/{ip}")
    public DataBlock convertDataBlock(@PathVariable String ip){
        DataBlock dataBlock = null;
        try {
            dataBlock = template.binarySearch(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataBlock;
    }

    /** 根据输入IP地址，返回解析后的地址
     * @Description
     * @param ip
     * @return xyz.wongs.drunkard.base.message.response.ResponseResult
     * @throws
     * @date 2020/8/17 18:26
     */
    @RequestLimit(maxCount=3)
    @GetMapping(value = "/region/{ip}")
    public RegionAddress convert(@PathVariable String ip){
        RegionAddress regionAddress = null;
        try {
            regionAddress = template.getRegionAddress(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regionAddress;
    }

    @GetMapping(value = "/region/ip={ip}")
    public RegionAddress caseInsensitive(@PathVariable String ip){
        RegionAddress regionAddress = null;
        try {
            regionAddress = template.getRegionAddress(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regionAddress;
    }

}
~~~

访问 `http://localhost:9090/region/ip=109.27.45.12` 这是我之前一个例子，用来解析IP地址，获取地域信息的。

![正常响应](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165146.png)

![异常响应](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165250.png)

### 2.5.4. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-area)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.6. 集成OAuth2


## 2.7. 集成数据校验

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

# 3. FAQ

开发过程中常见问题的解答。