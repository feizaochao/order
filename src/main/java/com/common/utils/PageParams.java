package com.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/11/27
 */
@ApiModel("分页请求数据")
public class PageParams {
    @ApiModelProperty(value = "当前页", dataType = "int", example = "1")
    private int page;
    @ApiModelProperty(value = "每页记录数", dataType = "int", example = "10")
    private int limit;
    @ApiModelProperty(value = "字段名", dataType = "string")
    private String fieldName;
    @ApiModelProperty(value = "搜索值", dataType = "string")
    private String keyword;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("page", getPage());
        params.put("limit", getLimit());
        params.put("fieldName", getFieldName());
        params.put("keyword", getKeyword());
        return params;
    }
}
