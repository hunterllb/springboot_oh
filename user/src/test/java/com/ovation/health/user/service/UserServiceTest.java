package com.ovation.health.user.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ovation.health.user.UserApplicationTests;
import com.ovation.health.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * UserServiceTest
 * </p>
 *
 * @package: com.ovation.health.user.service
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/21 17:15
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
@Slf4j
public class UserServiceTest extends UserApplicationTests {
    @Autowired
    private UserService userService;

    /**
     * 测试Mybatis-Plus 新增
     */
    @Test
    public void testSave() {
        String salt = IdUtil.fastSimpleUUID();
        User testSave3 = User.builder().code(IdUtil.simpleUUID()).name("testSave3").passw(SecureUtil.md5("123456" + salt)).address( salt).email("testSave3@xkcoding.com").tel("17300000003").bloodType("1").build();
        boolean save = userService.save(testSave3);
        Assert.assertTrue(save);
        log.error("【测试id回显#testSave3.getId()】= {}", testSave3.getId());
    }

    /**
     * 测试Mybatis-Plus 批量新增
     */
    @Test
    public void testSaveList() {
        List<User> userList = Lists.newArrayList();
        for (int i = 4; i < 14; i++) {
            String salt = IdUtil.fastSimpleUUID();
            User user = User.builder().name("testSave" + i).passw(SecureUtil.md5("123456" + salt)).address(salt).email("testSave" + i + "@xkcoding.com").tel("1730000000" + i).bloodType("1").build();
            userList.add(user);
        }
        boolean batch = userService.saveBatch(userList);
        Assert.assertTrue(batch);
        List<String> ids = userList.stream().map(User::getId).collect(Collectors.toList());
        log.debug("【userList#ids】= {}", ids);
    }

    /**
     * 测试Mybatis-Plus 删除
     */
    @Test
    public void testDelete() {
        boolean remove = userService.removeById(1L);
        Assert.assertTrue(remove);
        User byId = userService.getById(1L);
        Assert.assertNull(byId);
    }

    /**
     * 测试Mybatis-Plus 修改
     */
    @Test
    public void testUpdate() {
        User user = userService.getById(1L);
        Assert.assertNotNull(user);
        user.setName("MybatisPlus修改名字");
        boolean b = userService.updateById(user);
        Assert.assertTrue(b);
        User update = userService.getById(1L);
        Assert.assertEquals("MybatisPlus修改名字", update.getName());
        log.debug("【update】= {}", update);
    }

    /**
     * 测试Mybatis-Plus 查询单个
     */
    @Test
    public void testQueryOne() {
        User user = userService.getById(1L);
        Assert.assertNotNull(user);
        log.debug("【user】= {}", user);
    }

    /**
     * 测试Mybatis-Plus 查询全部
     */
    @Test
    public void testQueryAll() {
        List<User> list = userService.list(new QueryWrapper<>());
        Assert.assertTrue(CollUtil.isNotEmpty(list));
        log.debug("【list】= {}", list);
    }

    /**
     * 测试Mybatis-Plus 分页排序查询
     */
    @Test
    public void testQueryByPageAndSort() {
        initData();
        int count = userService.count(new QueryWrapper<>());
        Page<User> userPage = new Page<>(1, 5);
        userPage.setDesc("id");
        IPage<User> page = userService.page(userPage, new QueryWrapper<>());
        Assert.assertEquals(5, page.getSize());
        Assert.assertEquals(count, page.getTotal());
        log.debug("【page.getRecords()】= {}", page.getRecords());
    }

    /**
     * 测试Mybatis-Plus 自定义查询
     */
    @Test
    public void testQueryByCondition() {
        initData();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "Save1").or().eq("phone_number", "17300000001").orderByDesc("id");
        int count = userService.count(wrapper);
        Page<User> userPage = new Page<>(1, 3);
        IPage<User> page = userService.page(userPage, wrapper);
        Assert.assertEquals(3, page.getSize());
        Assert.assertEquals(count, page.getTotal());
        log.debug("【page.getRecords()】= {}", page.getRecords());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        testSaveList();
    }
}
