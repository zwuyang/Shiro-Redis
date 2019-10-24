package hnu.boot.readboy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("userRole")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userid;

    private Integer roleid;


}
