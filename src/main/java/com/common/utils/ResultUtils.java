package com.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/11/27
 */
@ApiModel("API接口返回信息")
public class ResultUtils<T> implements Serializable {
    @ApiModelProperty(value = "状态码", dataType = "int")
    private int code;
    @ApiModelProperty(value = "返回信息", dataType = "string")
    private String msg;
    @ApiModelProperty(value = "数据", dataType = "object")
    private Object data;

    public ResultUtils(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultUtils(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultUtils ok() {
        return new ResultUtils(200, "");
    }

    public static ResultUtils success(String msg, Object data) {
        return new ResultUtils(200, "", data);
    }

    public static ResultUtils error(int code, String msg) {
        return new ResultUtils(code, msg);
    }
}
