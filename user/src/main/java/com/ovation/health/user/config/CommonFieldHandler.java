package com.ovation.health.user.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * CommonFieldHandler
 * </p>
 *
 * @package: com.ovation.health.user.config
 * @description: TODO
 * @author: llb
 * @date: Created in 2022/3/21 11:44
 * @version: V1.0
 * @modified: llb
 */
@Slf4j
@Component
public class CommonFieldHandler implements MetaObjectHandler {

    /**
        * @description: 数据库添加自动填写字段
        * @param: metaObject
        * @return: void
        * @author llb
        * @date: 2022/3/21 11:44
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("update_time", System.currentTimeMillis(), metaObject);
        this.setFieldValByName("Check_Time", new Date(), metaObject);
    }

    /**
        * @description: 数据库更新自动填写字段
        * @param: metaObject
        * @return: void
        * @author llb
        * @date: 2022/3/21 11:44
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("update_time", System.currentTimeMillis(), metaObject);
    }
}
