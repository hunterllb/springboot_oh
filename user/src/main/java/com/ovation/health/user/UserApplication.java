package com.ovation.health.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * UserApplication
 * </p>
 *
 * @package: com.ovation.health.user
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/21 11:46
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
@SpringBootApplication
@MapperScan(basePackages = "com.ovation.health.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
