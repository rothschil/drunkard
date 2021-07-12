package xyz.wongs.drunkard.task;

import org.junit.Test;
import org.lionsoul.ip2region.Ip2regionSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.wongs.drunkard.BaseTest;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * @author WCNGS@QQ.COM
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date
 * @Version 1.0.0
 **/
public class Ip2RegionAutoConfigurationTest extends BaseTest{

    @Autowired
    private Ip2regionSearcher ip2regionSearcher;

    @Test
    public void testIp2RegionAutoConfiguration() {
        assertNotNull(ip2regionSearcher);
    }

    @Test
    public void testSearch() throws IOException {
        String region = ip2regionSearcher.search("101.105.35.57").toString();
        System.out.println("Region: "+region);
        region = ip2regionSearcher.search("118.28.185.144", Ip2regionSearcher.ALGORITHM.BINARY_SEARCH).toString();
        System.out.println("Region: "+region);
        assertNotNull(region);
    }

    @Test
    public void testDataSplit() throws IOException {
        ip2regionSearcher = ip2regionSearcher.search("100.10.3.50");
        System.out.println(ip2regionSearcher.toString());
        System.out.println(ip2regionSearcher.getCity()+"-"+ip2regionSearcher.getProvince());
    }
}
