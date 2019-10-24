package hnu.boot.readboy.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import hnu.boot.readboy.entity.User;
import hnu.boot.readboy.mapper.UserMapper;
import hnu.boot.readboy.service.IUserService;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {



}
