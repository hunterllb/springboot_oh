package com.ovation.health.user.job;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>
 * UserQuertz
 * </p>
 *
 * @package: com.ovation.health.user.job
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/22 14:00
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
@Component
@EnableScheduling
public class UserQuertz {

    @Scheduled(cron = "0/2 * * * * ?")
    public void testUserQuertz() {
        System.out.println("testUserQuertz 执行时间: {}" + DateUtil.now());
    }
}
