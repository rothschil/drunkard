package xyz.wongs.drunkard;

import com.github.zheng93775.mlock.MLock;
import org.junit.Test;

import java.util.Random;

public class TestCaseLnpdb extends BaseUnit {

    private MLock mLock;

    @Test
    public void test() throws InterruptedException {
        mLock = new MLock("DailyJob");
        Thread thread1 = startThread(1);
        Thread thread2 = startThread(2);
        thread1.join();
        thread2.join();
    }

    private Thread startThread(int number) {
        Random random = new Random();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean lockResult = false;
                for (int i = 1; i < 10; i++) {
                    try {
                        lockResult = mLock.tryLock();
                        if (lockResult == false) {
                            int ms = random.nextInt(10000);
                            Thread.sleep(ms);
                            continue;
                        }
                        int ms = random.nextInt(10000);
                        Thread.sleep(ms);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        mLock.unlock();
                        int ms = random.nextInt(5000);
                        try {
                            Thread.sleep(ms);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
        return thread;
    }
}
