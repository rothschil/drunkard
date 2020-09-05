package xyz.wongs.drunkard.task;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import xyz.wongs.drunkard.base.BaseTest;
import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;
import xyz.wongs.drunkard.war3.web.domain.area.service.LocationService;
import xyz.wongs.drunkard.war3.web.utils.ZoneCodeStringUtils;
import xyz.wongs.drunkard.war3.web.zonecode.task.ProcessService;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class ProcessServiceImplTest extends BaseTest {

    private static final String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/";
    private final static Logger logger = LoggerFactory.getLogger(ProcessServiceImplTest.class);

    @Autowired
    @Qualifier("processService")
    private ProcessService processService;

    @Autowired
    private LocationService locationService;


    /** 获取所有省，作为Root根节点
     * @Description
     * @return
     * @throws
     * @date 2020/4/30 0:41
    */
    @Test
    public void initRoot(){
        processService.intiRootUrl(url);
    }


    /** 解析所有省直辖的城市
     * @Description
     * @return void
     * @throws
     * @date 2020/9/4 22:03
     */
    @Test
    public void intLevelOne() throws Exception {
        city(1);
    }


    public void city(int pageNum){
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(0,pageNum,30);
        if(pageInfo.getPages()==0 || pageInfo.getPageNum()>pageInfo.getPages()){
            return;
        }
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        while(iter.hasNext()){
            Location location = iter.next();
            String uls =  url+location.getUrl();
            processService.initLevelOne(uls,location);
            location.setFlag("Y");
            locationService.updateByPrimaryKey(location);
        }
        city(pageNum+1);
    }


    /** 根据地市，解析并初始化区县
     * @Description
     * @return void
     * @throws
     * @date 2020/9/5 10:21
     */
    @Test
    public void intLevelTwo() throws Exception {
        exet(1);
    }

    public void exet(int pageNum){
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(1,pageNum,30);
        if(pageInfo.getPages()==0 || pageInfo.getPageNum()>pageInfo.getPages()){
            return;
        }
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        while(iter.hasNext()){
            Location location = iter.next();
            String url2 = new StringBuilder().append(url).append(location.getUrl()).toString();
            processService.initLevelTwo(url2,location);
            location.setFlag("Y");
            locationService.updateByPrimaryKey(location);
        }
        exet(pageNum+1);
    }

    /** 根据区县，解析并初始化乡镇 街道
     * @Description
     * @return
     * @throws 
     * @date 2020/4/30 0:27
    */
    @Test
    public void intLevelThree(){
        three(1);
    }

    public void three(int pageNum){
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(2,pageNum,100);
        if(pageInfo.getPages()==0 || pageInfo.getPageNum()>pageInfo.getPages()){
            return;
        }
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        Location location = null;
        while(iter.hasNext()){
            location = iter.next();
            String url2 = new StringBuilder().append(url).append(ZoneCodeStringUtils.getUrlStrByLocationCode(location.getLocalCode(), 2)).append(location.getUrl()).toString();
            processService.thridLevelResolve(url2, location, "D");
            try {
                int times = new Random().nextInt(2000);
                Thread.sleep(times);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        three(pageNum+1);
    }


    /** 根据乡镇 街道，解析并初始化社区村
     * @Description
     * @return
     * @throws
     * @date 2020/4/30 0:27
     */
    @Test
    public void intLevelFour(){
        village(1);
    }

    public void village(int pageNum){
        PageInfo<Location> pageInfo = locationService.getLocationsByLv(3,pageNum,100);
        if(pageInfo.getPages()==0 || pageInfo.getPageNum()>pageInfo.getPages()){
            return;
        }
        List<Location> locations = pageInfo.getList();
        Iterator<Location> iter = locations.iterator();
        Location location = null;

        while(iter.hasNext()){
            location = iter.next();
            String url2 = new StringBuilder().append(url).append(ZoneCodeStringUtils.getUrlStrByLocationCode(location.getLocalCode(), 2)).append(location.getUrl()).toString();
            processService.thridLevelResolve(url2, location, "T");
            try {
                int times = new Random().nextInt(2000);
                Thread.sleep(times);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        village(pageNum+1);
    }

}