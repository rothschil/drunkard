package xyz.wongs.drunkard.task.unit;

import org.junit.Test;
import org.springframework.util.Assert;
import xyz.wongs.drunkard.base.BaseTest;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.service.LocationService;

import javax.annotation.Resource;
import java.util.List;

public class LocationCase extends BaseTest {

    @Resource
    private LocationService locationService;

    @Test
    public void findByPoAttr() {
        Location location = new Location();
        location.setLocalName("安徽省");
        List<Location> lists = locationService.findByEntity(location);
        print(lists);
    }

    @Test
    public void findBySql() {
        String sql = "SELECT t.* FROM tb_locations t WHERE t.id=2288662";
        List lists = locationService.listBySql(sql);
        for(int i=0;i<lists.size();i++) {
            Object[] obj = (Object[]) lists.get(i);
            for (int j = 0; j < obj.length; j++) {
            }
        }
    }

    @Test
    public void findByHql() {
        String hql = "FROM Location t WHERE t.id=2288662";
        List lists = locationService.listByHql(hql);
        print(lists);
    }

    public void print(List<?> list){
        Assert.notNull(list,"List must not null");
        list.stream().forEach(o ->
                System.out.println(o.toString()));
    }
}
