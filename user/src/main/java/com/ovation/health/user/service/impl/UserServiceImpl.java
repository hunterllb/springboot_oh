package com.ovation.health.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ovation.health.user.entity.User;
import com.ovation.health.user.mapper.UserMapper;
import com.ovation.health.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * UserServiceImpl
 * </p>
 *
 * @package: com.ovation.health.user.service.impl
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/21 17:11
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
@Service
@Slf4j
@DS("slave")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 类上 @DS("slave") 代表默认从库，在方法上写 @DS("master") 代表默认主库
     *
     * @param user 用户
     */
    @DS("master")
    @CachePut(value = "user", key = "#user.id")
    @Override
    public void addOrUpdate(User user) {
        baseMapper.insert(user);
    }

    /**
     * @description: 存储用户内容
     * @param: user
     * @return: boolean
     * @author 6E ALIENWARE
     * @date: 2022/3/21 20:33
     */
    @DS("master")
    @CachePut(value = "user", key = "#user.id")
    @Override
    public boolean save(User user){
        return baseMapper.insert(user) > 0;
    }

    /**
     * @description: 获取用户信息
     * @param: id
     * @return:
     * @author 6E ALIENWARE
     * @date: 2022/3/21 20:33
     **/
    @Cacheable(value = "user", key = "#id")
    @Override
    public User get(Long id){
        return baseMapper.selectById(id);
    }

    /**
     * @description: 删除用户信息
     * @param: id
     * @return: void
     * @author 6E ALIENWARE
     * @date: 2022/3/21 22:49
     **/
    @CacheEvict(value = "user", key = "#id")
    @Override
    public void delete(Long id){
        baseMapper.deleteById(id);
    }
}
