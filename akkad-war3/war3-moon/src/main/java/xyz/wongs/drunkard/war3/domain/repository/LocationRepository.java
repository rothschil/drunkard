package xyz.wongs.drunkard.war3.domain.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.wongs.drunkard.base.persistence.jpa.repository.BaseRepository;
import xyz.wongs.drunkard.war3.domain.entity.Location;


public interface LocationRepository extends BaseRepository<Location, Long>,JpaSpecificationExecutor<Location> {

}