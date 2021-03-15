package com.xxLiuxx.ucenterservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Yuchen Liu
 */

@ApiModel(value = "register object", description = "register object")
public class RegisterVo {
    @ApiModelProperty(value = "nickname")
    private String nickname;

    @ApiModelProperty(value = "mobile")
    private String mobile;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "verify code")
    private String code;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
