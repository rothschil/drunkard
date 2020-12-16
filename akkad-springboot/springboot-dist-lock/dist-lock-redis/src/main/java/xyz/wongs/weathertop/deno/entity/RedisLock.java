package xyz.wongs.weathertop.deno.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisLock extends AbstractEntity<Integer> {
    private Integer id;
    private Integer counts;

}