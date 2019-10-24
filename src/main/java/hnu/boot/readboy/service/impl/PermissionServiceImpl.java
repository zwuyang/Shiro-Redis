package hnu.boot.readboy.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import hnu.boot.readboy.entity.Permission;
import hnu.boot.readboy.mapper.PermissionMapper;
import hnu.boot.readboy.service.IPermissionService;

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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
