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