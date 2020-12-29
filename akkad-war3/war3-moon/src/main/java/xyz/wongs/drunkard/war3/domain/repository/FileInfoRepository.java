package xyz.wongs.drunkard.war3.domain.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.wongs.drunkard.base.persistence.jpa.repository.BaseRepository;
import xyz.wongs.drunkard.war3.domain.entity.FileInfo;


public interface FileInfoRepository extends BaseRepository<FileInfo, Long>,JpaSpecificationExecutor<FileInfo> {
}