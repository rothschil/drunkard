
![Mozilla Add-on](https://img.shields.io/amo/dw/drunkard?color=fedcba&label=drunkard&logo=postwoman&logoColor=rgb&style=for-the-badge)

# 1. 项目简介

`drunkard：酒鬼; 醉鬼;`

想做一套后台管理系统，在此期间主要参考的开源项目 `RuoYi`，谁让自己前端知识储备不够，实在是写不出来那样的效果，没办法，只能抄。

本项目是基于SpringBoot开发的脚手架模块，已经集成 `MyBatis` 作为持久层组件，为了方便提供两种不同的主键生成策略，一种是利用 `Redis` ；另一种是利用数据库自身的ID策略，两种方式各有差异，主要针对不同的场景。

## 1.1. 内置功能

## 1.2. Http响应内容统一封装

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

### 1.2.1. 消息体

结合我们定义的状态码，我们返回的消息体主要实现一个 `Serializable`，不要问我为什么。

#### 1.2.1.1. 正常响应

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

#### 1.2.1.2. 异常响应

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

### 1.2.2. 拦截器

我们这里需要做的就是利用拦截器拦截请求，检查判断是否此请求返回的值需要包装。核心就是判断一个注解`annoation`是否存在方法或类中。

为了演示的完整，我将代码贴完整。

#### 1.2.2.1. Annoation注解

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

#### 1.2.2.2. 拦截器

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

#### 1.2.2.3. 全局异常

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

### 1.2.3. 例子

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

### 1.2.4. 源码地址，如果觉得对你有帮助，请Star

[源码地址](https://github.com/rothschil/drunkard/tree/master/akkad-war3/war3-area)


## 1.3. 集成OAuth2


## 1.4. 集成数据校验

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

## 1.5. 项目结构

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