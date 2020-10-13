package xyz.wongs.drunkard.domain.real.service;


import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.domain.RealContast;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.domain.comp.AsyncUpdateStatu;
import xyz.wongs.drunkard.domain.real.entity.RelCertId;
import xyz.wongs.drunkard.domain.real.mapper.RelCertIdMapper;
import xyz.wongs.drunkard.domain.real.vo.ReqVo;
import xyz.wongs.drunkard.domain.real.vo.RespHeadVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName RelCertIdService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/17 11:17
 * @Version 1.0.0
*/
@Slf4j
@Service(value="relCertIdService")
@Transactional(readOnly = true)
public class RelCertIdService extends BaseService<RelCertId, Long> {

	@Autowired
	private RelCertIdMapper relCertIdMapper;

	@Autowired
	private AsyncUpdateStatu asyncUpdateStatu;

	private static int SIZE = 50;

	@Override
	protected BaseMapper<RelCertId, Long> getMapper() {
		return relCertIdMapper;
	}

	public List<RelCertId> getList2(){
		return relCertIdMapper.getList2(SIZE);
	}

	public PageInfo<RelCertId> getRelCertIdByPage(int pageNum, int pageSize){
		PageInfo<RelCertId> pageInfo =null;
		try {
			PageHelper.startPage(pageNum,pageSize);
			RelCertId relCertId = new RelCertId();
			List<RelCertId> relCertIds = relCertIdMapper.getList2(SIZE);
			pageInfo = new PageInfo<>(relCertIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageInfo;
	}

	public void realId2(){
		int count = relCertIdMapper.getCount();
		int cls = new Double(Math.ceil(count/SIZE)).intValue();
		for (int i = 0; i < cls; i++) {
			List<RelCertId> relCertIds = relCertIdMapper.getList2(SIZE);
			if(!relCertIds.isEmpty()){
				intf(relCertIds);
			}
			log.error(" Time "+ i);
		}
	}

	public void realId(){
		realId(1);
	}

	private void realId(int pageNum){
		int i =0;
		PageInfo<RelCertId> pageInfo = getRelCertIdByPage(pageNum,SIZE);
		if(pageInfo.getPages()==0 || pageInfo.getPageNum()>pageInfo.getPages()){
			return;
		}
		List<RelCertId> relCertIds = pageInfo.getList();
		if(!relCertIds.isEmpty()){
			intf(relCertIds);
		}
		i++;
		realId(pageNum+1);
	}

	/**
	 * @Description
	 * @param relCertIds
	 * @return void
	 * @throws
	 * @date 2020/9/17 16:18
	 */
	public void intf(List<RelCertId> relCertIds) {
		List<Long> isRealList = new ArrayList<>();
		List<Long> notRealList = new ArrayList<>();
		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpPost http = getHttpclient();
			BasicResponseHandler handler = new BasicResponseHandler();
			for (RelCertId relCertId: relCertIds) {
				String json = RelCertIdService.returnVo(relCertId);
				StringEntity entity = new StringEntity(json, RealContast.UTF8);
				entity.setContentEncoding(RealContast.UTF8);
				entity.setContentType(RealContast.APPLICATION_JSON);
				http.setEntity(entity);
				// 执行Post请求
				String response = httpclient.execute(http,handler);
				RespHeadVo respHeadVo = JSONUtil.toBean(response, RespHeadVo.class);
				if(respHeadVo.getResultObject()!=null && respHeadVo.getResultObject().getResultCode().equals(RealContast.STATUS)){
					isRealList.add(relCertId.getId());
				} else {
					notRealList.add(relCertId.getId());
				}
			}
			asyncUpdateStatu.excuStatueBatch(isRealList,notRealList);
			log.error(" 执行完毕  数量={}",relCertIds.size());
		} catch (ConnectTimeoutException e) {
			log.error("连接超时");
		} catch (IOException e) {
			log.error("IO异常超时");
		}
	}

	/**
	 * @Description
	 * @param
	 * @return org.apache.http.client.methods.HttpPost
	 * @throws
	 * @date 2020/9/17 16:55
	 */
	public static HttpPost getHttpclient() {
		HttpPost http = new HttpPost(RealContast.REAL_URL);
		http.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
		RequestConfig config = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
		http.setConfig(config);
		return http;
	}

	/**
	 * @Description
	 * @param relCertId
	 * @return java.lang.String
	 * @throws
	 * @date 2020/9/17 16:18
	 */
	public static String returnVo(RelCertId relCertId){
		ReqVo reqVo = new ReqVo();
		reqVo.setRequestObjects(new ReqVo.RequestObjectsBean(relCertId.getCertId(),relCertId.getCertName()));
		return JSONUtil.parse(reqVo).toString();
	}
}
