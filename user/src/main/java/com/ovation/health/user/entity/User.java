package com.ovation.health.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * User
 * </p>
 *
 * @package: com.ovation.health.user.entity
 * @description: TODO
 * @author: 6E ALIENWARE
 * @date: Created in 2022 2022/3/21 11:46
 * @version: V1.0
 * @modified: 6E ALIENWARE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("pub_user")
public class User implements Serializable {
    private static final long serialVersionUID = -1840831686851699943L;

    private String code;
    private String name;
    private String email;
    private String tel;
    private String idCard;
    private String birthDate;
    private String sex;
    private String address;
    private String bloodType;
    private String hight;
    private String weight;
    private String passw;
    private String regtime;
    private String equCode;
    private String isvalid;
    private String userImage;
    private String memo;
    private String id;
    private String regionCode;
    private String isFormal;
    private String nimToken;
    private String unionid;
    private String habAddress;
    private String birthAddress;
    private String weixin;
    private String bcgCode;
}
