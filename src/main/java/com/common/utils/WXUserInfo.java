package com.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/29
 */
@ApiModel("微信用户信息")
public class WXUserInfo {
    @ApiModelProperty(value = "openId", dataType = "string")
    private String openId;
    @ApiModelProperty(value = "别名", dataType = "string")
    private String nickName;
    @ApiModelProperty(value = "性别", dataType = "string")
    private String gender;
    @ApiModelProperty(value = "城市", dataType = "string")
    private String city;
    @ApiModelProperty(value = "省份", dataType = "string")
    private String province;
    @ApiModelProperty(value = "国家", dataType = "string")
    private String country;
    @ApiModelProperty(value = "头像url", dataType = "string")
    private String avatarUrl;
    @ApiModelProperty(value = "用户标识", dataType = "string")
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
