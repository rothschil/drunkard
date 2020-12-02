
![Drunkard-WONGS](https://img.shields.io/badge/Drunkard-WONGS-blueviolet)
![Drunkard-Version](https://img.shields.io/badge/version-v2.0.0--alpha.2-orange)
![Drunkard-Downloads](https://img.shields.io/badge/Downloads-300-brightgreen)
![Drunkard-Stars](https://img.shields.io/badge/Stars-148-ff69b4)
![Drunkard-size](https://img.shields.io/badge/size-25%20MB-blue)
![Drunkard-license](https://img.shields.io/badge/license-MIT-green)
![Drunkard-coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)
![Java](https://img.shields.io/badge/Java-%3E%3D%201.8-green)
![Maven](https://img.shields.io/badge/Maven-%3E%3D%203.6.3-yellowgreen)
![MySQL](https://img.shields.io/badge/MySQL-%3E%3D%205.7-important)
![Redis](https://img.shields.io/badge/Redis-%3E%3D%204.0-yellowgreen)
![Idea](https://img.shields.io/badge/IDEA-%3E%3D%202020.2-informational)
![SpringBoot](https://img.shields.io/badge/SpringBoot-%3E%3D%202.3.4-druid)
![Druid](https://img.shields.io/badge/druid-%3E%3D%201.2.3-green)
![Mybatis](https://img.shields.io/badge/Mybatis-%3E%3D%203.5.5-red)
![RedisClient](https://img.shields.io/badge/RedisClient-%3E%3D%203.3.0-blue)

<!-- TOC -->

- [1. 项目简介](#1-项目简介)
    - [1.1. 项目结构](#11-项目结构)
    - [1.2. 技术选型](#12-技术选型)
    - [1.3. 测试策略](#13-测试策略)
    - [1.4. 技术架构](#14-技术架构)
    - [1.5. 部署架构](#15-部署架构)
    - [1.6. 外部依赖](#16-外部依赖)
    - [1.7. 环境信息](#17-环境信息)
- [2. 内置功能](#2-内置功能)
    - [2.1. 手写爬虫获取国家统计局行政区划数据](#21-手写爬虫获取国家统计局行政区划数据)
        - [2.1.1. 依赖包](#211-依赖包)
        - [2.1.2. 核心实现代码](#212-核心实现代码)
        - [2.1.3. 单元测试](#213-单元测试)
        - [2.1.4. 打开浏览器](#214-打开浏览器)
        - [2.1.5. 源码地址，如果觉得对你有帮助，请Star](#215-源码地址如果觉得对你有帮助请star)
    - [2.2. 集成ip2region离线IP地名映射](#22-集成ip2region离线ip地名映射)
        - [2.2.1. 打开浏览器](#221-打开浏览器)
        - [2.2.2. 源码地址，如果觉得对你有帮助，请Star](#222-源码地址如果觉得对你有帮助请star)
    - [2.3. Http响应内容统一封装](#23-http响应内容统一封装)
        - [2.3.1. 消息体](#231-消息体)
            - [2.3.1.1. 正常响应](#2311-正常响应)
            - [2.3.1.2. 异常响应](#2312-异常响应)
        - [2.3.2. 拦截器](#232-拦截器)
            - [2.3.2.1. Annoation注解](#2321-annoation注解)
            - [2.3.2.2. 拦截器](#2322-拦截器)
            - [2.3.2.3. 全局异常](#2323-全局异常)
        - [2.3.3. 例子](#233-例子)
        - [2.3.4. 源码地址，如果觉得对你有帮助，请Star](#234-源码地址如果觉得对你有帮助请star)
    - [2.4. 集成OAuth2](#24-集成oauth2)
    - [2.5. 集成数据校验](#25-集成数据校验)
- [3. FAQ](#3-faq)

<!-- /TOC -->
# 1. 项目简介

`drunkard：酒鬼; 醉鬼;`

想做一套后台管理系统，在此期间主要参考的开源项目 `RuoYi`，谁让自己前端知识储备不够，实在是写不出来那样的效果，没办法，只能抄。

本项目是基于SpringBoot开发的脚手架模块，已经集成 `MyBatis` 作为持久层组件，为了方便提供两种不同的主键生成策略，一种是利用 `Redis` ；另一种是利用数据库自身的ID策略，两种方式各有差异，主要针对不同的场景。

## 1.1. 项目结构

## 1.2. 技术选型

SpringBoot、Maven、Jnuit、MySQL、JDK8+

## 1.3. 测试策略

测试类型 | 代码目录 | 测试内容
-- | -- | -- |
单元测试 | src/test/java | 包含核心领域模型（包含领域对象和Factory类）的测试
组件测试 | src/componentTest/java | 用于测试一些核心的组件级对象，比如Repository
API测试 | src/apiTest/java | 模拟客户端调用API

## 1.4. 技术架构

## 1.5. 部署架构

## 1.6. 外部依赖

项目运行时所依赖的外部集成方，比如订单系统会依赖于会员系统；

## 1.7. 环境信息

各个环境的访问方式，数据库连接等；

# 2. 内置功能

## 2.1. 手写爬虫获取国家统计局行政区划数据

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

### 2.1.1. 依赖包

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

### 2.1.2. 核心实现代码

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

### 2.1.3. 单元测试

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

### 2.1.4. 打开浏览器

访问 `http://localhost:9090/region/ip=109.27.45.12` 这是我之前一个例子，用来解析IP地址，获取地域信息的。

![样例响应](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165146.png)

### 2.1.5. 源码地址，如果觉得对你有帮助，请Star

**觉得对你有帮助，请Star**

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-area)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.2. 集成ip2region离线IP地名映射

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
import xyz.wongs.drunkard.war3.limit.RequestLimit;

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

### 2.2.1. 打开浏览器

访问 `http://localhost:9090/region/ip=109.27.45.12` 这是我之前一个例子，用来解析IP地址，获取地域信息的。

![样例响应](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165146.png)

### 2.2.2. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-infi)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.3. Http响应内容统一封装

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

### 2.3.1. 消息体

结合我们定义的状态码，我们返回的消息体主要实现一个 `Serializable`，不要问我为什么。

#### 2.3.1.1. 正常响应

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

#### 2.3.1.2. 异常响应

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

### 2.3.2. 拦截器

我们这里需要做的就是利用拦截器拦截请求，检查判断是否此请求返回的值需要包装。核心就是判断一个注解`annoation`是否存在方法或类中。

为了演示的完整，我将代码贴完整。

#### 2.3.2.1. Annoation注解

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

#### 2.3.2.2. 拦截器

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

#### 2.3.2.3. 全局异常

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

### 2.3.3. 例子

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
import xyz.wongs.drunkard.war3.limit.RequestLimit;

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

### 2.3.4. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-area)

[Gitee源码地址](https://gitee.com/rothschil/drunkard.git)

## 2.4. 集成OAuth2


## 2.5. 集成数据校验

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