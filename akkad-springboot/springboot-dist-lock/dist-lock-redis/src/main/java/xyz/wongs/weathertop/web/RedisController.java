package xyz.wongs.weathertop.web;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.bridge.MessageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.weathertop.comp.RedisLockComponent;
import xyz.wongs.weathertop.deno.entity.RedisLock;
import xyz.wongs.weathertop.deno.mapper.RedisLockMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class RedisController {

    @Autowired
    private RedisLockMapper redisLockMapper;

    @Autowired
    private RedisLockComponent redisLockComponent;

    /**
     * 超时时间 5s
     */
    private static final int TIMEOUT = 3;

    @RequestMapping(value = "/seckilling/{key}")
    public Map<String,String> Seckilling(@PathVariable("key") String key){
        //1、加锁
        String value = System.currentTimeMillis() + "";
        if(!redisLockComponent.getLock(key,value,6)){
            throw new DrunkardException(ResultCode.SYSNC_LOCK_MANY_REQ);
        }

        RedisLock redisLock = redisLockMapper.selectByPrimaryKey(1);
        // 2、查询库存，为0则活动结束
        if(redisLock.getCounts()==0){
            throw new DrunkardException(ResultCode.SYSNC_LOCK_NOT_ENOUGH_STOCK);
        }
        //3、减库存
        redisLock.setCounts(redisLock.getCounts()-1);
        redisLockMapper.updateByPrimaryKeySelective(redisLock);
        try{
            Thread.sleep(5000);//模拟减库存的处理时间
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Map<String,String> result = new HashMap<String,String>(){{
            put(Constant.RESULT, Constant.SECKILL);
        }
        };
        //4、释放锁
        redisLockComponent.unlock(key,value);
        return result;
    }
}
