package xyz.wongs.drunkard.war3.moon.entity;

import lombok.*;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo extends AbstractEntity<Long> {

    private Long id;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private String suffixName;

    private String md5;
}