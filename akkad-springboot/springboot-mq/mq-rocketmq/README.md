# 1. 中间件下载

官网上直接下载，比较简单就不赘述！

# 2. 简介

## 2.1. RocketMQ是什么

![RocketMQ](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/20200805194327.png)

`RocketMQ` 是一个队列模型的消息中间件，具有高性能、高可靠、高实时、分布式特点，具体特性如下：

- Producer、Consumer、队列都可以分布式。
- Producer 向队列轮流发送消息，队列集合称为Topic，Consumer 如果做广播消费，则一个consumer实例消费这个Topic对应的所有队列，如果做集群消费，则多个Consumer 实例平均消费这个topic 对应的队列集合。
- 能够保证严格的消息顺序
- 提供丰富的消息拉取模式
- 高效的订阅者水平扩展能力
- 实时的消息订阅机制
- 亿级消息堆积能力
- 较少的依赖

## 2.2. 概念

### 2.2.1. 消息模型（Message Model）

RocketMQ主要由 Producer、Broker、Consumer 三部分组成，其中Producer 负责生产消息，Consumer 负责消费消息，Broker 负责存储消息。Broker 在实际部署过程中对应一台服务器，每个 Broker 可以存储多个Topic的消息，每个Topic的消息也可以分片存储于不同的 Broker。Message Queue 用于存储消息的物理地址，每个Topic中的消息地址存储于多个 Message Queue 中。ConsumerGroup 由多个Consumer 实例构成。

### 2.2.2. 消息生产者（Producer）

负责生产消息，一般由业务系统负责生产消息。一个消息生产者会把业务应用系统里产生的消息发送到broker服务器。RocketMQ提供多种发送方式，同步发送、异步发送、顺序发送、单向发送。同步和异步方式均需要Broker返回确认信息，单向发送不需要。

### 2.2.3. 消息消费者（Consumer）

负责消费消息，一般是后台系统负责异步消费。一个消息消费者会从Broker服务器拉取消息、并将其提供给应用程序。从用户应用的角度而言提供了两种消费形式：拉取式消费、推动式消费。

### 2.2.4. 主题（Topic）

表示一类消息的集合，每个主题包含若干条消息，每条消息只能属于一个主题，是RocketMQ进行消息订阅的基本单位。

### 2.2.5. 名字服务（Name Server）

名称服务充当路由消息的提供者。生产者或消费者能够通过名字服务查找各主题相应的Broker IP列表。多个Namesrv实例组成集群，但相互独立，没有信息交换。

### 2.2.6. 拉取式消费（Pull Consumer）

Consumer消费的一种类型，应用通常主动调用Consumer的拉消息方法从Broker服务器拉消息、主动权由应用控制。一旦获取了批量消息，应用就会启动消费过程。

### 2.2.7. 推动式消费（Push Consumer）

Consumer消费的一种类型，该模式下Broker收到数据后会主动推送给消费端，该消费模式一般实时性较高。

### 2.2.8. 生产者组（Producer Group）

同一类Producer的集合，这类Producer发送同一类消息且发送逻辑一致。如果发送的是事务消息且原始生产者在发送之后崩溃，则Broker服务器会联系同一生产者组的其他生产者实例以提交或回溯消费。

### 2.2.9. 消费者组（Consumer Group）

同一类Consumer的集合，这类Consumer通常消费同一类消息且消费逻辑一致。消费者组使得在消息消费方面，实现负载均衡和容错的目标变得非常容易。要注意的是，消费者组的消费者实例必须订阅完全相同的Topic。RocketMQ 支持两种消息模式：集群消费（Clustering）和广播消费（Broadcasting）。

### 2.2.10. 普通顺序消息（Normal Ordered Message）

普通顺序消费模式下，消费者通过同一个消费队列收到的消息是有顺序的，不同消息队列收到的消息则可能是无顺序的。

### 2.2.11. 严格顺序消息（Strictly Ordered Message）

严格顺序消息模式下，消费者收到的所有消息均是有顺序的。

## 2.3. 架构设计

### 2.3.1. 技术架构

![技术架构图](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/20200805195736.png)

### 2.3.2. 部署架构

![部署架构图](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/20200805200017.png)

# 3. 安装

由于RocketMQ安装比较简单，在单机这块就省略。

## 3.1. 单机

略！！！！

## 3.2. 集群

### 3.2.1. 集群的方式

- 单 Master：这种方式风险较大，一旦Broker重启或者宕机时，会导致整个服务不可用，不建议线上环境使用。

- 多 Master 模式：一个集群无 Slave，全是 Master，例如 2 个 Master 或者 3 个 Master

    + 优点：配置简单，单个Master 宕机或重启维护对应用无影响，在磁盘配置为 RAID10 时，即使机器宕机不可恢复情况下，由与 RAID10磁盘非常可靠，消息也不会丢（异步刷盘丢失少量消息，同步刷盘一条不丢）。性能最高。

    + 缺点：单台机器宕机期间，这台机器上未被消费的消息在机器恢复之前不可订阅，消息实时性会受到受到影响。

- 多 Master 多 Slave 模式，异步复制：每个 Master 配置一个 Slave，有多对Master-Slave，HA
采用异步复制方式，主备有短暂消息延迟，毫秒级。

    + 优点：即使磁盘损坏，消息丢失的非常少，且消息实时性不会受影响，因为Master 宕机后，消费者仍然可以从 Slave。

    + 缺点：Master 宕机，磁盘损坏情况，会丢失少量消息。

- 多 Master 多 Slave 模式，同步双写：每个 Master 配置一个 Slave，有多对Master-Slave，HA
采用同步双写方式，主备都写成功，向应用返回成功。

    + 优点：数据与服务都无单点，Master宕机情况下，消息无延迟，服务可用性与数据可用性都非常高。

    + 缺点：性能比异步复制模式略低，大约低 10%左右，发送单个消息的 RT 会略高。目前主宕机后，备机不能自动切换为主机，后续会支持自动切换功能

后两种的方式比较复杂，涉及主从同步的问题，非必要的场景，建议采用多Master 这种方式。而我下面的例子也是以 多Master来部署的。

### 3.2.2. 部署

- 服务器环境准备

| 序号 | IP |  角色  | 模式 |
| ---- | ---- | :----: | :----: |
| A |  192.168.244.128  | nameServer1,brokerServer1 | Master1 |
| B |  192.168.244.129  | nameServer2,brokerServer2 | Master2 |

- 修改HOST

在 A、B 两服务器中将HOST文件修改，`vi /etc/hosts`

修改内容如下：

~~~
192.168.244.128 rocketmq-nameserver1
192.168.244.128 rocketmq-master1

192.168.244.129 rocketmq-nameserver2
192.168.244.129 rocketmq-master2
~~~

- 上传文件

在A、B两台机器上传RockerMQ文件，并解压。我这里上传和解压路径在`/usr/local`

~~~
[root@centos8 rocketmq]$ pwd
/usr/local/rocketmq
[root@centos8 rocketmq]$ ll
total 40
drwxr-xr-x. 2 root root    83 Jun 24 02:49 benchmark
drwxr-xr-x. 3 root root  4096 Jun 24 02:02 bin
drwxr-xr-x. 6 root root   211 Jun  2 02:09 conf
drwxr-xr-x. 2 root root  4096 Jun 24 02:49 lib
-rw-r--r--. 1 root root 17336 Jun  2 02:09 LICENSE
-rw-r--r--. 1 root root  1338 Jun  2 02:09 NOTICE
-rw-r--r--. 1 root root  5069 Jun 24 02:02 README.md
[root@centos8 rocketmq]$ 

~~~

- 创建存储路径

在A、B两台机器执行创建路径。

~~~
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store/commitlog
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store/consumequeue
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store/index
~~~

- 修改配置文件

这里贴一个标准配置文件，具体如下：

~~~

// 所属集群名字
brokerClusterName=rocketmq-cluster
//  broker名字，注意此处不同的配置文件填写的不一样
brokerName=broker-a/broker-b ## 需要按机器A/B 区分
// 0 表示 Master，>0 表示 Slave
brokerId=0
//  nameServer地址，分号分割

namesrvAddr=rocketmq-nameserver1:9876;rocketmq-nameserver2:9876
// 在发送消息时，自动创建服务器不存在的topic，默认创建的队列数
defaultTopicQueueNums=4
// 是否允许 Broker 自动创建Topic，建议线下开启，线上关闭
autoCreateTopicEnable=true
// 是否允许 Broker 自动创建订阅组，建议线下开启，线上关闭
autoCreateSubscriptionGroup=true
// Broker 对外服务的监听端口
listenPort=10911
// 删除文件时间点，默认凌晨 4点
deleteWhen=04
// 文件保留时间，默认 48 小时
fileReservedTime=120
// commitLog每个文件的大小默认1G
mapedFileSizeCommitLog=1073741824
//  ConsumeQueue每个文件默认存30W条，根据业务情况调整
mapedFileSizeConsumeQueue=300000
// destroyMapedFileIntervalForcibly=120000
// redeleteHangedFileInterval=120000
// 检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
// 存储路径
storePathRootDir=/usr/local/rocketmq/store
// commitLog 存储路径
storePathCommitLog=/usr/local/rocketmq/store/commitlog
// 消费队列存储路径存储路径
storePathConsumeQueue=/usr/local/rocketmq/store/consumequeue
// 消息索引存储路径
storePathIndex=/usr/local/rocketmq/store/index
// checkpoint 文件存储路径
storeCheckpoint=/usr/local/rocketmq/store/checkpoint
// abort 文件存储路径
abortFile=/usr/local/rocketmq/store/abort
// 限制的消息大小
maxMessageSize=65536
// flushCommitLogLeastPages=4
// flushConsumeQueueLeastPages=2
// flushCommitLogThoroughInterval=10000
// flushConsumeQueueThoroughInterval=60000
// Broker 的角色
//  - ASYNC_MASTER 异步复制Master
//  - SYNC_MASTER 同步双写Master
//  - SLAVE
brokerRole=ASYNC_MASTER
//  刷盘方式
//  - ASYNC_FLUSH 异步刷盘
//  - SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH
//  checkTransactionMessageEnable=false

//  发消息线程池数量
//  sendMessageThreadPoolNums=128
//  拉消息线程池数量
//  pullMessageThreadPoolNums=128
~~~

机器A

~~~
[root@centos8 rocketmq]$ vi/usr/local/rocketmq/conf/2m-noslave/broker-a.properties
~~~

其中`brokerName=broker-a`


在机器B

~~~
[root@centos8 rocketmq]$ vi/usr/local/rocketmq/conf/2m-noslave/broker-a.properties
~~~

其中`brokerName=broker-b`

- 修改日志配置文件

不用说肯定也是A、B两台都要改

~~~
[root@centos8 rocketmq]$ mkdir -p /usr/local/rocketmq/logs
[root@centos8 rocketmq]$ cd /usr/local/rocketmq/conf && sed -i 's#${user.home}#/usr/local/rocketmq#g' *.xml
~~~

- 修改启动参数（A、B）

在`/usr/local/rocketmq/bin` 路径下，找到`runbroker.sh` 和 `runserver.sh`。

我们将这两个文件的`JAVA_OPT` 参数修改下，不然默认情况下，JVM配置是 `8G`。如 `JAVA_OPT="${JAVA_OPT} -server -Xms8g -Xmx8g -Xmn4g"`。

修改后：
~~~
...
JAVA_OPT="${JAVA_OPT} -server -Xms1g -Xmx1g -Xmn512m"
....
~~~

- 启动NameServer（A、B）

~~~
[root@centos8 rocketmq]$ cd /usr/local/rocketmq/bin
[root@centos8 rocketmq]$ nohup sh mqnamesrv &
~~~

![机器A](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/20200805211008.png)

![机器B](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/20200805211039.png)

- 启动BrokerServer（A）

~~~
[root@centos8 rocketmq]$ cd /usr/local/rocketmq/bin
[root@centos8 rocketmq]$ nohup sh mqbroker -c /usr/local/rocketmq/conf/2m-noslave/broker-a.properties &
~~~

- 启动BrokerServer（B）

~~~
[root@centos8 rocketmq]$ cd /usr/local/rocketmq/bin
[root@centos8 rocketmq]$ nohup sh mqbroker -c /usr/local/rocketmq/conf/2m-noslave/broker-b.properties &
~~~

- rocketmq-console安装

[rocketmq-console下载](https://github.com/apache/rocketmq-externals/archive/master.zip)

这个包需要利用maven编译打包。我这里打包一个，放百度云盘上，供下载！


链接: https://pan.baidu.com/s/1hfvzJeyBG7TXnvPHtn3C5Q 

提取码: atkq

最后执行jar文件

~~~
[root@centos8 rocketmq]$ java -jar rocketmq-console-ng-1.0.0.jar
~~~

页面的端口是 8082，刚开始启动有点慢，稍微等会！！

![rocketmq-console界面](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/20200805214518.png)

- 数据清理

~~~
[root@centos8 rocketmq]$ cd /usr/local/rocketmq/bin
[root@centos8 rocketmq]$ sh mqshutdown broker
[root@centos8 rocketmq]$ sh mqshutdown namesrv
~~~

这里需要等待完全停止！

~~~
[root@centos8 rocketmq]$ rm -rf /usr/local/rocketmq/store
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store/commitlog
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store/consumequeue
[root@centos8 rocketmq]$ mkdir /usr/local/rocketmq/store/index
~~~

最终按照以上步骤重启NameServer和BrokerServer即可！

# 4. SpringBoot集成

- POM文件添加依赖

~~~
<dependency>
    <groupId>org.apache.rocketmq</groupId>
    <artifactId>rocketmq-spring-boot-starter</artifactId>
    <version>{换成相应版本}</version>
</dependency>
~~~

- application.yml

~~~
######### 4.1. RocketMQ ##########
rocketmq:
  name-server: 192.168.244.128:9876;192.168.244.129:9876;
  producer:
    group: drunkard
    send-message-timeout: 30000
~~~

- 生产者

~~~
@Slf4j
@RestController
public class RocketMqDemo {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @GetMapping("send/{id}")
    public String send(@PathVariable("id") String id){
        UserVo userVo  = new UserVo(id,"侯征");
        log.warn(JSON.toJSONString(userVo));
        rocketMQTemplate.send("rocket-topic-01", MessageBuilder.withPayload(userVo).build());
        return "SUCESS";
    }
}
~~~

- 消费者

~~~
@Slf4j
@Component
@RocketMQMessageListener(topic = "rocket-topic-01", consumerGroup = "my-rocket-topic-01")
public class UserConsumer implements RocketMQListener<UserVo> {

    @Override
    public void onMessage(UserVo message) {
        log.warn("接受到消息: {}",message.toString());
    }
}

~~~

# 5. 案例下载

![觉得对你有帮助，请Star](https://abram.oss-cn-shanghai.aliyuncs.com/blog/java/drunkard/20201201165747.png)

[**Gitee下载**](https://gitee.com/rothschil/drunkard/tree/master/akkad-springboot/springboot-mq/mq-rocketmq)，希望多多给Star。

[**Github下载**](https://github.com/rothschil/drunkard/tree/master/akkad-springboot/springboot-mq/mq-rocketmq)，希望多多给Star。
