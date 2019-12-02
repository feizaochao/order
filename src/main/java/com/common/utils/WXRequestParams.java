package com.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/11/29
 */
@ApiModel("微信登录请求参数")
public class WXRequestParams {
    @ApiModelProperty(value = "登录凭证", dataType = "string")
    private String code;
    @ApiModelProperty(value = "", dataType = "string")
    private String encryptedData;
    @ApiModelProperty(value = "", dataType = "string")
    private String iv;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }
}
