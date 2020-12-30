//package xyz.wongs.drunkard.war3.domain.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import xyz.wongs.drunkard.base.persistence.jpa.service.BaseService;
//import xyz.wongs.drunkard.war3.domain.entity.FileInfo;
//import xyz.wongs.drunkard.war3.domain.repository.FileInfoRepository;
//
//import java.util.List;
//
///**
// * @ClassName LocationService
// * @Description
// * @author WCNGS@QQ.COM
// * @Github <a>https://github.com/rothschil</a>
// * @date 2020/9/9 16:11
// * @Version 1.0.0
//*/
//@Service(value="fileInfoService")
//@Transactional(readOnly = true)
//public class FileInfoService extends BaseService<FileInfo, Long> {
//
//	private FileInfoRepository fileInfoRepository;
//
//	@Autowired
//	@Qualifier("fileInfoRepository")
//	@Override
//	public void setJpaRepository(JpaRepository<FileInfo, Long> jpaRepository) {
//		this.jpaRepository=jpaRepository;
//		this.fileInfoRepository =(FileInfoRepository)jpaRepository;
//	}
//
//	@Transactional(readOnly = false)
//	public void insert(List<FileInfo> lists){
//		fileInfoRepository.batchInsert(lists);
//	}
//}
