package xyz.wongs.drunkard.domain.addbook.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.constant.Constants;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.drunkard.base.utils.security.Md5Utils;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUser;
import xyz.wongs.drunkard.domain.addbook.mapper.RegisterUserMapper;

import java.util.List;
import java.util.Random;

/**
 * @ClassName RegUserService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:14
 * @Version 1.0.0
*/
@Service
@Transactional(readOnly = true)
public class RegisterUserService extends BaseService<RegisterUser, Long> {


	@Autowired
	private RegisterUserMapper registerUserMapper;


	/** 保存用户，并且指定Salt和密码，密文存储
	 * @Description
	 * @param registerUser
	 * @return java.lang.Long
	 * @throws
	 * @date 2020/8/15 23:24
	 */
	@Transactional(readOnly = false)
	public Long save(RegisterUser registerUser){
		String salt = StringUtils.getRandomString(16);
		String passWord = Md5Utils.getSaltMD5(StringUtils.getRandomString(7),salt);
		registerUser.setSat(salt);
		registerUser.setPassWord(passWord);
		registerUser.setStatus(Constants.STATUS_EFF);
		return super.insert(registerUser);
	}

	@Override
	protected BaseMapper<RegisterUser, Long> getMapper() {
		return registerUserMapper;
	}

	public List<RegisterUser> selectByRegUser(RegisterUser regUser){
		return registerUserMapper.selectByRegUser(regUser);
	}

}
