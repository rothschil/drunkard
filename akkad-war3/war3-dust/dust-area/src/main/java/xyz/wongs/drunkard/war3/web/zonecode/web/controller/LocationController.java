//package xyz.wongs.drunkard.war3.web.zonecode.web.controller;
//
//
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//
//import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;
//import xyz.wongs.drunkard.war3.web.domain.area.service.LocationService;
//import xyz.wongs.drunkard.war3.web.utils.ZoneCodeStringUtils;
//import xyz.wongs.drunkard.war3.web.zonecode.task.ProcessService;
//
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.SynchronousQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@RequestMapping(value = "/locations")
//public class LocationController {
//
//    private static final String TARGET_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/";
//
//    @Autowired
//    @Qualifier("locationService")
//    private LocationService locationService;
//
//    @Autowired
//    @Qualifier("processService")
//    private ProcessService processService;
//
//    /**
//     *
//     * @Title: getLocationListByLevel
//     * @Description: 请求参数在URL中，需要在 @ApiImplicitParam 中加上 "paramType="path""
//     * @param lv
//     * @return  List<LocationEntity>
//     */
//    @RequestMapping(value = "/{lv}", method = RequestMethod.GET)
//    public List<Location> getLocationListByLevel(@PathVariable(value = "lv") Integer lv) {
//
//        List<Location> r = locationService.getLocationListByLevel(lv);
//        return r;
//    }
//
//    @RequestMapping(value = "id={id}", method = RequestMethod.GET)
//    public List<Location> getLocationListByLevel(@PathVariable Long id) {
//        List<Location> r = locationService.getLocationListById(id);
//        return r;
//    }
//
//    @RequestMapping(value="/intiLevelToFirst",method= RequestMethod.GET)
//    public void intiLevelToFirst(){
//        List<Location> root = locationService.getLocationListByLevel(0);
//        for (Location location: root) {
//            processService.initLevelOne(TARGET_URL+location.getUrl(),location);
//        }
//    }
//
//    public void secondLevelResolve(int pageNumber) {
//        //初始化0
//        try {
//            PageInfo<Location> pages = locationService.getLocationsByLevel(1,pageNumber);
//            if(pages.getPageNum()!=0){
//                List<Location> lists = pages.getList();
//                if(lists.isEmpty()){
//                    return ;
//                }
//                Iterator<Location> iter = lists.iterator();
//                while(iter.hasNext()){
//                    Location location = iter.next();
//                    String url2 = new StringBuilder().append(TARGET_URL).append(location.getUrl()).toString();
//                    processService.initLevelTwo(url2,location);
//                    locationService.updateLocationFlag("Y",location.getId());
//                }
//            }
//
//
//
//            int times = new Random().nextInt(10000);
//            Thread.sleep(times);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RequestMapping(value="/intiLevelToSecond",method= RequestMethod.GET)
//    public void intiLevelToSecond(){
//        int totalPages = locationService.getLocationCountsByLevel(1);
//
//        for (int i = 1; i <= totalPages; i++) {
//            secondLevelResolve(i);
//        }
//    }
//
//    /**
//     * @Description
//     * @param
//     * @return void
//     * @throws
//     * @date 2020/9/9 14:48
//     */
//    @RequestMapping(value="/intiLevelToThrid",method= RequestMethod.GET)
//    public void intiLevelToThrid(){
//
//        int totalPages = locationService.getLocationCountsByLevel(2);
//        ExecutorService fixedThreadPool = new ThreadPoolExecutor(10,15,5, TimeUnit.SECONDS,new SynchronousQueue<>());
//
//        for (int i = 1; i <= totalPages; i++) {
//            int ii = i;
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
////                    thridLevelResolve(ii);
//                    PageInfo<Location> pages = locationService.getLocationsByLevel(2,ii);
//                    if(pages.getPageNum()!=0) {
//                        List<Location> lists = pages.getList();
//                        if (lists.isEmpty()) {
//                            return;
//                        }
//                        Iterator<Location> iter = lists.iterator();
//                        while (iter.hasNext()) {
//                            Location location = iter.next();
//                            String url2 = new StringBuilder().append(TARGET_URL).append(ZoneCodeStringUtils.getUrlStrByLocationCode(location.getLocalCode(), 2)).append(location.getUrl()).toString();
//                            processService.initLevelThrid(url2, location);
//
//                        }
//                    }
//                    try {
//                        int times = new Random().nextInt(30000);
//                        Thread.sleep(times);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//
//        }
//    }
//
//    public void thridLevelResolve(int pageNumber){
//        PageInfo<Location> pages = locationService.getLocationsByLevel(2,pageNumber);
//        if(pages.getPageNum()!=0) {
//            List<Location> lists = pages.getList();
//            if (lists.isEmpty()) {
//                return;
//            }
//            Iterator<Location> iter = lists.iterator();
//            while (iter.hasNext()) {
//                Location location = iter.next();
//                String url2 = new StringBuilder().append(TARGET_URL).append(ZoneCodeStringUtils.getUrlStrByLocationCode(location.getLocalCode(), 2)).append(location.getUrl()).toString();
//                processService.initLevelThrid(url2, location);
//
//            }
//        }
//    }
//
//
//
//    @RequestMapping(value="/intiLevelToFourth",method= RequestMethod.GET)
//    public void intiLevelToFourth(){
//        for(int i=0;i<5;i++){
//            processService.getLocationThrid();
//        }
//    }
//
//}
//
