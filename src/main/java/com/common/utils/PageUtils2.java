package com.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/11/27
 */
@ApiModel("分页对象")
public class PageUtils2<T> implements Serializable {

    @ApiModelProperty(value = "总记录数", dataType = "int")
    private int totalCount;
    @ApiModelProperty(value = "每页记录数", dataType = "int")
    private int pageSize;
    @ApiModelProperty(value = "总页数", dataType = "int")
    private int totalPage;
    @ApiModelProperty(value = "当前页数", dataType = "int")
    private int currPage;
    @ApiModelProperty(value = "列表数据", dataType = "list")
    private List<T> list;

    /**
     * 分页
     * @param totalCount
     * @param pageSize
     * @param totalPage
     * @param list
     */
    public PageUtils2(int totalCount, int pageSize, int totalPage, List<T> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
