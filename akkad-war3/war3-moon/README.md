<!-- TOC -->

- [1. 背景](#1-背景)
- [2. 实现思路](#2-实现思路)
- [3. 功能实现](#3-功能实现)
    - [3.1. 数据表设计](#31-数据表设计)
    - [3.2. 实体类三部曲](#32-实体类三部曲)
        - [3.2.1. 实体类](#321-实体类)
        - [3.2.2. Mapper](#322-mapper)
        - [3.2.3. XML文件](#323-xml文件)
        - [3.2.4. Service](#324-service)
    - [3.3. 核心类](#33-核心类)
- [4. 问题](#4-问题)
- [5. 问题分析](#5-问题分析)
- [6. 优化思路](#6-优化思路)
    - [6.1. 异步批量写库](#61-异步批量写库)
        - [6.1.1. 定义实际操作接口](#611-定义实际操作接口)
        - [6.1.2. 定义队列](#612-定义队列)
        - [6.1.3. 核心方法改造](#613-核心方法改造)
    - [6.2. 异步获取MD5](#62-异步获取md5)
        - [6.2.1. 异步线程定义](#621-异步线程定义)
        - [6.2.2. 核心方法改造](#622-核心方法改造)
- [7. 小结](#7-小结)
- [8. 源码地址，如果觉得对你有帮助，请Star](#8-源码地址如果觉得对你有帮助请star)

<!-- /TOC -->

# 1. 背景

这些天整理孩子们的图片时候， 发现我 `iCloud` 自动下载以及 `Onedrive` 自动备份还有本身随机拷贝的文件散落在磁盘每个目录，也怪我手懒没认真整理，这时候扎进去梳理头绪很让人头大。对其他一些图片管理软件不熟悉，只能自己想办法，谁让我们弄代码的，我第一时间想到就是利用 `Java` 遍历，主要是其他软件我也不会，在脑海中整理下思路，等到代码写完发现执行效率太慢，不敢想象，怎么这么慢，这是我写的代码吗？严重鄙视它，好好静下心逐行分析发现好些地方完全可以拆分出来异步执行，充分利用当前操作系统多核的优势。

本章节知识点主要有：

- 线程池 `ThreadPoolExecutor`
- 阻塞队列 `LinkedBlockingQueue`

# 2. 实现思路

输入一个文件夹，循环递归文件夹以及子文件夹。然后挨个将文件名、大小、文件路径、文件后缀名、文件校验MD5等记录关系型数据库中。

# 3. 功能实现

一切准备妥当，我准备在开发环境弄。

## 3.1. 数据表设计

表的结构很简单，数据库主键生成策略我就用自增长模式，没必要太复杂。

~~~sql
CREATE TABLE tb_file_info (
  id bigint(9) NOT NULL AUTO_INCREMENT,
  file_name varchar(500) DEFAULT NULL,
  file_path varchar(500) DEFAULT NULL,
  file_size bigint(9) DEFAULT NULL,
  suffix_name varchar(30) DEFAULT NULL,
  md5 varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=12824 DEFAULT CHARSET=utf8;
~~~

## 3.2. 实体类三部曲

持久层我选择 `Mybatis` 来集成，也没太多必要弄分页，需求分析下来主要瓶颈在遍历读取文件再数据库上。

作为 `JAVA` 面向对象的基础，除了注意几个实体基类，那是我基础的封装，再有就是这里批量写入的方式，其他内容真的没什么可以说的。

### 3.2.1. 实体类

这里需要注意下，这里的 `AbstractEntity` 是我基类。

~~~java
package xyz.wongs.drunkard.war3.moon.entity;

import lombok.*;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo extends AbstractEntity<Long> {

    private Long id;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private String suffixName;

    private String md5;
}
~~~

### 3.2.2. Mapper

这里需要注意下，这里的 `BaseMapper` 是我基类。

~~~java
package xyz.wongs.drunkard.war3.moon.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;

import java.util.List;

public interface FileInfoMapper extends BaseMapper<FileInfo,Long> {
    int deleteByPrimaryKey(Long id);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    void batchInsert(List<FileInfo> lists);
}
~~~

### 3.2.3. XML文件

~~~java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="xyz.wongs.drunkard.war3.moon.mapper.FileInfoMapper" >
  <resultMap id="BaseResultMap" type="xyz.wongs.drunkard.war3.moon.entity.FileInfo" >
    <id column="id" property="id" jdbcType="BIGINT" />
    <result column="file_name" property="fileName" jdbcType="VARCHAR" />
    <result column="file_path" property="filePath" jdbcType="VARCHAR" />
    <result column="file_size" property="fileSize" jdbcType="BIGINT" />
    <result column="suffix_name" property="suffixName" jdbcType="VARCHAR" />
    <result column="md5" property="md5" jdbcType="VARCHAR" />
  </resultMap>
  <sql id="Base_Column_List" >
    id, file_name, file_path, file_size, suffix_name, md5
  </sql>


  <insert id="batchInsert" parameterType="java.util.List">
    insert into tb_file_info (id, file_name, file_path,
    file_size, suffix_name, md5
    ) values
    <foreach collection="list" item="item" index="index" separator=",">
      (#{item.id,jdbcType=BIGINT}, #{item.fileName,jdbcType=VARCHAR}, #{item.filePath,jdbcType=VARCHAR},
      #{item.fileSize,jdbcType=BIGINT}, #{item.suffixName,jdbcType=VARCHAR}, #{item.md5,jdbcType=VARCHAR}
      )
    </foreach>
  </insert>

  <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long" >
    select 
    <include refid="Base_Column_List" />
    from tb_file_info
    where id = #{id,jdbcType=BIGINT}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
    delete from tb_file_info
    where id = #{id,jdbcType=BIGINT}
  </delete>
  <insert id="insert" parameterType="xyz.wongs.drunkard.war3.moon.entity.FileInfo" >
    insert into tb_file_info (id, file_name, file_path, 
      file_size, suffix_name, md5
      )
    values (#{id,jdbcType=BIGINT}, #{fileName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, 
      #{fileSize,jdbcType=BIGINT}, #{suffixName,jdbcType=VARCHAR}, #{md5,jdbcType=VARCHAR}
      )
  </insert>
  <insert id="insertSelective" parameterType="xyz.wongs.drunkard.war3.moon.entity.FileInfo" >
    insert into tb_file_info
    <trim prefix="(" suffix=")" suffixOverrides="," >
      <if test="id != null" >
        id,
      </if>
      <if test="fileName != null" >
        file_name,
      </if>
      <if test="filePath != null" >
        file_path,
      </if>
      <if test="fileSize != null" >
        file_size,
      </if>
      <if test="suffixName != null" >
        suffix_name,
      </if>
      <if test="md5 != null" >
        md5,
      </if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
      <if test="id != null" >
        #{id,jdbcType=BIGINT},
      </if>
      <if test="fileName != null" >
        #{fileName,jdbcType=VARCHAR},
      </if>
      <if test="filePath != null" >
        #{filePath,jdbcType=VARCHAR},
      </if>
      <if test="fileSize != null" >
        #{fileSize,jdbcType=BIGINT},
      </if>
      <if test="suffixName != null" >
        #{suffixName,jdbcType=VARCHAR},
      </if>
      <if test="md5 != null" >
        #{md5,jdbcType=VARCHAR},
      </if>
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="xyz.wongs.drunkard.war3.moon.entity.FileInfo" >
    update tb_file_info
    <set >
      <if test="fileName != null" >
        file_name = #{fileName,jdbcType=VARCHAR},
      </if>
      <if test="filePath != null" >
        file_path = #{filePath,jdbcType=VARCHAR},
      </if>
      <if test="fileSize != null" >
        file_size = #{fileSize,jdbcType=BIGINT},
      </if>
      <if test="suffixName != null" >
        suffix_name = #{suffixName,jdbcType=VARCHAR},
      </if>
      <if test="md5 != null" >
        md5 = #{md5,jdbcType=VARCHAR},
      </if>
    </set>
    where id = #{id,jdbcType=BIGINT}
  </update>
  <update id="updateByPrimaryKey" parameterType="xyz.wongs.drunkard.war3.moon.entity.FileInfo" >
    update tb_file_info
    set file_name = #{fileName,jdbcType=VARCHAR},
      file_path = #{filePath,jdbcType=VARCHAR},
      file_size = #{fileSize,jdbcType=BIGINT},
      suffix_name = #{suffixName,jdbcType=VARCHAR},
      md5 = #{md5,jdbcType=VARCHAR}
    where id = #{id,jdbcType=BIGINT}
  </update>
</mapper>
~~~

### 3.2.4. Service

这里需要注意下，这里的 `AbstractService` 是我基类。

~~~java
package xyz.wongs.drunkard.war3.moon.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.AbstractService;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.mapper.FileInfoMapper;
import java.util.List;

/**
 * @ClassName FileInfoService
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 16:11
 * @Version 1.0.0
*/
@Service(value="fileInfoService")
@Transactional(readOnly = true)
public class FileInfoService extends AbstractService<FileInfo, Long> {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    protected BaseMapper<FileInfo, Long> getMapper() {
        return fileInfoMapper;
    }

	@Transactional(readOnly = false)
	public void insert(List<FileInfo> lists){
        fileInfoMapper.batchInsert(lists);
	}
}
~~~

## 3.3. 核心类

核心处理类，这是一个 `bean`，结合 `Junit`做测试，所以标明为 `@Component`。

- 判断是否是文件夹
- 判断文件后缀名字是图片格式
- 批量写入数据库中

![20201230140531](https://abram.oss-cn-shanghai.aliyuncs.com/blog/thread/20201230140531.png)

~~~java
package xyz.wongs.drunkard.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.file.FileUtil;
import xyz.wongs.drunkard.base.utils.security.Md5Utils;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ResultCode 定义的接口状态码
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/12/28 17:21
 * @Version 1.0.0
 */
@Component
@Slf4j
public class RunFileTask {

    @Autowired
    public FileInfoService fileInfoService;

    public void run(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            return;
        }
        listFiles(file);
    }

    public void listFiles(File file){
        File[] files = file.listFiles();
        List<FileInfo> lists = new ArrayList<FileInfo>();
        for (File fl : files) {
            // 1、文件夹就递归
            if(fl.isDirectory()){
                listFiles(fl);
                continue;
            }
            String suffixName = FileUtil.getSuffix(fl);
            // 2、只要文件后缀名字是图片的
            if(!ImageConst.LIST_SUFFIX.contains(suffixName.toUpperCase())){
                continue;
            }
            String fileName = FileUtil.getName(fl);
            float size = fl.length();
            String filePath = FileUtil.getAbsolutePath(fl);
            FileInfo fileInfo = FileInfo.builder().fileName(fileName).filePath(filePath).fileSize(size).suffixName(suffixName)
                    .md5(Md5Utils.getMd5(fl)).build();
            lists.add(fileInfo);
        }
        // 3、批量写入数据库中
        if(!lists.isEmpty()){
            fileInfoService.insert(lists);
        }
    }

}

~~~

# 4. 问题

最终执行起来，好家伙，差不多 `4000` 多文件，发现执行耗时超过 `15` 分钟，时间有点长，一时间我还很难以接受，但是总能把事情解决，具体解决的过程完美与否暂时没考虑。

![20201230140612](https://abram.oss-cn-shanghai.aliyuncs.com/blog/thread/20201230140612.png)

.......
.......

这一夜，注定是漫长的，让人无法忍受，一点也不完美。

怎么能执行这么久，不科学，能不能想个办法优化执行时间，一定有。

# 5. 问题分析

文件信息在写库、以及获取 `MD5` 消耗资源过多，所以较其他逻辑要耗时些，文件单次处理大概只要在 `100` 毫秒内，但是写库以及生成文件校验值差不多又要 `120` 毫秒，两个业务步骤加起来整体耗时差不多就要 `220` 多毫秒。

![20201230140426](https://abram.oss-cn-shanghai.aliyuncs.com/blog/thread/20201230140426.png)

# 6. 优化思路

根据直觉将任务的实现改为异步并行处理，主要体现在以下两个地方：

- 将最后批量写库，这个地方改为异步队列方式
- 针对文件生成 `MD5` 这个地方本来比较耗费资源，能否也改为异步

## 6.1. 异步批量写库

弄个队列，让它处理需要批量入库的集合，并且这个队列要是阻塞队列。

### 6.1.1. 定义实际操作接口

~~~java
package xyz.wongs.drunkard.task.hadler;

import xyz.wongs.drunkard.base.aop.pojo.OperationLog;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import java.security.PrivateKey;
import java.util.List;

public interface IntfFileInfoHandler {

    /**  这里也就是我们实现QueueTaskHandler的处理接口
     * @Description
     * @return
     * @throws 
     * @date 20/11/19 17:09
    */
    void processData();
}

~~~

`FileInfoHandler` 实现 `IntfFileInfoHandler` 接口，并且有一个成员变量，用于接收需要批量入库的集合。

~~~java
package xyz.wongs.drunkard.task.hadler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.task.hadler.IntfFileInfoHandler;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;
import java.util.List;

@Data
@Slf4j
@Component("fileInfoHandler")
public class FileInfoHandler implements IntfFileInfoHandler {

    private List<FileInfo> lists;

    @Autowired
    private FileInfoService fileInfoService;

    public void processData(){
        fileInfoService.insert(lists);
    }
}

~~~

### 6.1.2. 定义队列

~~~java
package xyz.wongs.drunkard.task.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.task.hadler.IntfFileInfoHandler;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/** 异步文件写入队列
 * @ClassName FileInfoQueue
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/13 16:14
 * @Version 1.0.0
*/
@Slf4j
@Component
public class FileInfoQueue {

    private final LinkedBlockingQueue<IntfFileInfoHandler> queue = new LinkedBlockingQueue<IntfFileInfoHandler>(500);

    /**
     * 线程池
     */
    private ExecutorService service = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    /**
     * 检查服务是否运行
     */
    private volatile boolean running = true;

    /**
     * 线程状态
     */
    private Future<?> threadStatus = null;

    @PostConstruct
    public void init(){
        threadStatus = service.submit(
                new Runnable() {
                    @Override
                    public void run() {
                        while(running){
                            try {
                                // 队列中不存在元素 则不处理
                                if(!queue.isEmpty()){
                                    IntfFileInfoHandler taskHandler = queue.take();
                                    taskHandler.processData();
                                }
                            } catch (InterruptedException e) {
                                log.error("服务停止，退出", e);
                                running = false;
                            }
                        }
                    }
                });
    }

    @PreDestroy
    public void destory() {
        running = false;
        service.shutdownNow();
    }

    public void activeService() {
        running = true;
        if (service.isShutdown()) {
            service = Executors.newSingleThreadExecutor();
            init();
            log.info("线程池关闭，重新初始化线程池及任务");
        }
        if (threadStatus.isDone()) {
            init();
            log.info("线程池任务结束，重新初始化任务");
        }
    }

    public boolean addQueue(IntfFileInfoHandler taskHandler){
        if(!running){
            log.warn("service is stop");
            return false;
        }

        boolean isFull = queue.offer(taskHandler);
        if(!isFull){
            log.warn("添加任务到队列失败");
        }
        return isFull;
    }

    public boolean empty(){
        return queue.isEmpty();
    }

}

~~~

### 6.1.3. 核心方法改造

~~~java
package xyz.wongs.drunkard.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.file.FileUtil;
import xyz.wongs.drunkard.base.utils.security.Md5Utils;
import xyz.wongs.drunkard.task.hadler.impl.FileInfoHandler;
import xyz.wongs.drunkard.task.queue.FileInfoQueue;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName RunFileTask
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/30 12:58
 * @Version 1.0.0
*/
@Component
@Slf4j
public class RunFileTask {

    public static final String THREAD_NAME ="RUN_FILE_NAME";

    @Autowired
    public FileInfoService fileInfoService;

    @Autowired
    private FileInfoQueue fileInfoQueue;

    @Autowired
    private FileInfoHandler fileInfoHandler;

    public void run(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            return;
        }
        listFiles(file);
    }

    public void listFiles(File file){
        File[] files = file.listFiles();
        List<FileInfo> lists = new ArrayList<FileInfo>();
        for (File fl : files) {
            if(fl.isDirectory()){
                listFiles(fl);
                continue;
            }
            String suffixName = FileUtil.getSuffix(fl);
            if(!ImageConst.LIST_SUFFIX.contains(suffixName.toUpperCase())){
                continue;
            }
            long size = fl.length();
            String filePath = FileUtil.getAbsolutePath(fl);
            try {
                FileInfo fileInfo = FileInfo.builder().fileName(fileName).filePath(filePath).fileSize(size).suffixName(suffixName)
                        .md5(DigestUtils.md5Hex(new FileInputStream(fl))).build();
                lists.add(fileInfo);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        if(!lists.isEmpty()){
            fileInfoHandler.setLists(lists);
            fileInfoQueue.addQueue(fileInfoHandler);
        }
    }
}

~~~

通过这次改造，执行耗时差不多在 `580` 秒，比之前的 `15 分钟` 减少了一大截，终于露出一丝微笑。

## 6.2. 异步获取MD5

思路通过一个有返回值的线程处理 `MD5`，我想到了 `Callable`。

`FileSizeThread` 实现 `Callable` 接口，并且通过构造函数来指明需要处理的文件。

### 6.2.1. 异步线程定义

~~~java
package xyz.wongs.drunkard.task.thread;

import org.apache.commons.codec.digest.DigestUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/** 异步获取文件名
 * @ClassName FileSizeThread
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/30 13:16
 * @Version 1.0.0
*/
public class FileSizeThread implements Callable<String> {

    private File file;

    public FileSizeThread(){

    }

    public FileSizeThread(File file){
        this.file = file;
    }

    @Override
    public String call() throws Exception {
        try {
            return DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }
    }
}

~~~

### 6.2.2. 核心方法改造

~~~java
package xyz.wongs.drunkard.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.file.FileUtil;
import xyz.wongs.drunkard.base.utils.security.Md5Utils;
import xyz.wongs.drunkard.base.utils.thread.ThreadPoolUtils;
import xyz.wongs.drunkard.task.hadler.impl.FileInfoHandler;
import xyz.wongs.drunkard.task.queue.FileInfoQueue;
import xyz.wongs.drunkard.task.thread.FileSizeThread;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName RunFileTask
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/30 12:58
 * @Version 1.0.0
*/
@Component
@Slf4j
public class RunFileTask {

    public static final String THREAD_NAME ="RUN_FILE_NAME";

    @Autowired
    public FileInfoService fileInfoService;

    @Autowired
    private FileInfoQueue fileInfoQueue;

    @Autowired
    private FileInfoHandler fileInfoHandler;

    private ThreadPoolExecutor executor = ThreadPoolUtils.doCreate(1,1,THREAD_NAME);

    public void run(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            return;
        }
        listFiles(file);
    }

    public void listFiles(File file){
        File[] files = file.listFiles();
        List<FileInfo> lists = new ArrayList<FileInfo>();
        for (File fl : files) {
            if(fl.isDirectory()){
                listFiles(fl);
                continue;
            }
            String suffixName = FileUtil.getSuffix(fl);
            if(!ImageConst.LIST_SUFFIX.contains(suffixName.toUpperCase())){
                continue;
            }
            
            Future<String> result = executor.submit(new FileSizeThread(fl));
            String fileName = FileUtil.getName(fl);
            long size = fl.length();
            String filePath = FileUtil.getAbsolutePath(fl);
            try {
                FileInfo fileInfo = FileInfo.builder().fileName(fileName).filePath(filePath).fileSize(size).suffixName(suffixName)
                        .md5(result.get()).build();
                lists.add(fileInfo);
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
            }
        }
        if(!lists.isEmpty()){
            fileInfoHandler.setLists(lists);
            fileInfoQueue.addQueue(fileInfoHandler);
        }
    }
}

~~~

通过这次改造，执行耗时差不多在 `314` 秒，比之前的 `580` 秒又减少了，这样的结果是在线程池开启一个线程处理结果，将核心线程数调制到 `3`，最大线程数调整到 `5` ，我这里执行结果是 `294` 秒，线程池的设置根据实际物理机器还有机器的 `CPU` 负荷来设置。

# 7. 小结

我这里将所有处理耗时统计了下做成一张表格，方便大家对比。

序号 | 实现 | 耗时(秒) |
- | - | - |
1 | 普通方法基本实现，无任何异步操作，全是阻塞模式 | 1758
2 | 将写库的操作改为以队列方式，进行异步批量处理 | 508
3 | 在 2 上将获取 `MD5` 改为异步，核心线程池设置为 `1`  | 314
4 | 在 3 上将核心线程数设置为 `3`，最大线程数设置为 `5`  | 294

可以看到简单的几步可以将性能提升的好几倍，这就是多线程带来的收获。

# 8. 源码地址，如果觉得对你有帮助，请Star

![觉得对你有帮助，请Star](https://img-blog.csdnimg.cn/img_convert/c256592943f0dad054561a6a6bbe419c.png)

[Github源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-moon)

[Gitee源码地址](https://gitee.com/rothschil/drunkard/tree/master/akkad-war3/war3-moon)
