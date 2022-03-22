package com.ovation.health.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ovation.health.user.entity.User;

/**
 * <p>
 * UserService
 * </p>
 *
 * @package: com.ovation.health.user.service
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/21 17:10
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
public interface UserService extends IService<User> {
    /**
     * 添加 User
     *
     * @param user 用户
     */
    void addOrUpdate(User user);

    boolean save(User user);

    User get(Long id);

    void delete(Long id);
}
