package xyz.wongs.drunkard.service.quanmin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.entity.quanmin.InformSms;
import xyz.wongs.drunkard.mapper.quanmin.InformSmsMapper;

@Slf4j
@Service
@Transactional(readOnly = true)
public class InformSmsService extends BaseService<InformSms, Long> {


    @Autowired
    private InformSmsMapper informSmsMapper;

    @Override
    protected BaseMapper<InformSms, Long> getMapper() {
        return informSmsMapper;
    }





}
