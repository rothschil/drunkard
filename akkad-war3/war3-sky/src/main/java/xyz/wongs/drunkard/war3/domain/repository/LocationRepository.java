package xyz.wongs.drunkard.war3.domain.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.wongs.drunkard.base.persistence.jpa.repository.BaseRepository;
import xyz.wongs.drunkard.war3.domain.entity.Location;

import java.util.List;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/8 - 15:18
 * @Version 1.0.0
 */
public interface LocationRepository extends BaseRepository<Location, Long>,JpaSpecificationExecutor<Location> {

    List<Location> findByLv(int lv);

}