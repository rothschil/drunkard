package xyz.wongs.drunktard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunktard.base.persistence.mybatis.entity.BaseEntityAbstract;

@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUser extends BaseEntityAbstract<Long> {
    private Long id;

    private String status;

    private byte[] nickName;

    private byte[] uCode;

    private byte[] uMobile;

    private byte[] email;

    private byte[] uPwd;

    private byte[] sat;

}