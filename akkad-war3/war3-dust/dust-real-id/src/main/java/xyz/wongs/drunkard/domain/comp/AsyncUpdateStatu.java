package xyz.wongs.drunkard.domain.comp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.domain.RealContast;
import xyz.wongs.drunkard.domain.real.mapper.RelCertIdMapper;

import java.util.ArrayList;
import java.util.List;

/** 异步批量写入
 * @ClassName AsyncAddBookComp
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:07
 * @Version 1.0.0
*/
@Component
public class AsyncUpdateStatu {

    @Autowired
    private RelCertIdMapper relCertIdMapper;

    /** 异步方法
     * @Description
     * @param registerUser
     * @return void
     * @throws
     * @date 2020/9/9 15:07
     */
    @Async
    public void excuStatueBatch(List<Long> isRealList, List<Long> notRealList){
        if(!isRealList.isEmpty()){
            relCertIdMapper.updateBatch(RealContast.REAL_STATUS_Y,isRealList);
        }
        if(!notRealList.isEmpty()){
            relCertIdMapper.updateBatch(RealContast.REAL_STATUS_N,notRealList);
        }
    }
}
