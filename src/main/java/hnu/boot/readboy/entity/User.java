package hnu.boot.readboy.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.crazycake.shiro.AuthCachePrincipal;

/**
 * <p>
 * 
 * </p>
 *
 * @author yzw
 * @since 2019-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable, AuthCachePrincipal {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;


    /**
     * 将数据存放到redis
     * @return
     */
    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
