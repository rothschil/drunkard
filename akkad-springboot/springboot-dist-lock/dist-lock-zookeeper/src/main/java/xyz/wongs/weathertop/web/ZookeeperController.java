package xyz.wongs.weathertop.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.weathertop.conf.DistributedLockByCurator;

@RestController
@Slf4j
public class ZookeeperController {

    @Autowired
    private DistributedLockByCurator distributedLockByZookeeper;

    private final static String PATH = "test";

    @GetMapping("/lock10")
    public void getLock1() {
        Boolean acquire = distributedLockByZookeeper.acquireDistributedLock(PATH);
        try {
            if(acquire) {
                log.error("I am lock1，i am updating resource……！！！");
                Thread.sleep(2000);
            } else{
                throw new DrunkardException(ResultCode.SYNC_LOCK_FAILURE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            distributedLockByZookeeper.releaseDistributedLock(PATH);
        }
    }

    @GetMapping("/lock11")
    public void getLock11() {
        Boolean acquire = distributedLockByZookeeper.acquireDistributedLock(PATH);
        try {
            if(acquire) {
                log.error("I am lock11，i am updating resource……！！！");
                Thread.sleep(3000);
            } else{
                throw new DrunkardException(ResultCode.SYNC_LOCK_FAILURE);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            distributedLockByZookeeper.releaseDistributedLock(PATH);
        }
    }
}
