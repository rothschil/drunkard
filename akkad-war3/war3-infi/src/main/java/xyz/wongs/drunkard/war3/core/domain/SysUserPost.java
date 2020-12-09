package xyz.wongs.drunkard.war3.core.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author WCNGS@QQ.COM
 * @ClassName SysUserPost
 * @Description 用户和岗位关联
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/9 10:52
 * @Version 1.0.0
 */
public class SysUserPost {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("postId", getPostId())
                .toString();
    }
}
