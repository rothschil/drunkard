package xyz.wongs.drunkard.domain.addbook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.domain.addbook.entity.RegUserExt;
import xyz.wongs.drunkard.domain.addbook.mapper.RegUserExtMapper;

/**
 * @ClassName RegUserExtService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:14
 * @Version 1.0.0
*/
@Service
@Transactional(readOnly = true)
public class RegUserExtService extends BaseService<RegUserExt, Long> {


	@Autowired
	private RegUserExtMapper regUserExtMapper;



	@Override
	protected BaseMapper<RegUserExt, Long> getMapper() {
		return regUserExtMapper;
	}

}
