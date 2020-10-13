package xyz.wongs.drunktard.task;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.wongs.drunkard.base.BaseTest;
import xyz.wongs.drunkard.domain.real.service.RelCertIdService;

public class RealTest extends BaseTest {

    @Autowired
    RelCertIdService relCertIdService;

    @Test
    public void testOm(){
        relCertIdService.realId2();
    }
}
