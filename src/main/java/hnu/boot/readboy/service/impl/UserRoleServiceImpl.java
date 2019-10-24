package hnu.boot.readboy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import hnu.boot.readboy.entity.UserRole;
import hnu.boot.readboy.mapper.UserRoleMapper;
import hnu.boot.readboy.service.IUserRoleService;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzw
 * @since 2019-10-23
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
