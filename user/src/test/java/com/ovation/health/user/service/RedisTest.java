package com.ovation.health.user.service;

import com.ovation.health.user.UserApplicationTests;
import com.ovation.health.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <p>
 * RedisTest
 * </p>
 *
 * @package: com.ovation.health.user.service
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/21 22:07
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
@Slf4j
public class RedisTest extends UserApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Test
    private void get() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");

        String key = "ovation:user:1";

        redisCacheTemplate.opsForValue().set(key, new User());
        User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.debug("[user] = {}", user);
    }
}
