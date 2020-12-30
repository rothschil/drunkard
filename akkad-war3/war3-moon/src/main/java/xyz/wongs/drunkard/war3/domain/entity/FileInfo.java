//package xyz.wongs.drunkard.war3.domain.entity;
//
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import xyz.wongs.drunkard.base.persistence.jpa.entity.AbsEntity;
//
//import javax.persistence.*;
//
///**
// * @ClassName FileInfo
// * @Description
// * @author WCNGS@QQ.COM
// * @Github <a>https://github.com/rothschil</a>
// * @date 20/12/28 17:16
// * @Version 1.0.0
//*/
//@EqualsAndHashCode(callSuper=false)
//@Builder(toBuilder=true)
//@Data
//@Entity
//@Table(name="tb_file_info")
//public class FileInfo extends AbsEntity<Long> {
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "file_name")
//    private String fileName;
//
//    @Column(name = "file_path")
//    private String filePath;
//
//    @Column(name = "file_size")
//    private float fileSize;
//
//    @Column(name = "suffix_name")
//    private String suffixName;
//
//    @Column(name = "md5")
//    private String md5;
//
//}
