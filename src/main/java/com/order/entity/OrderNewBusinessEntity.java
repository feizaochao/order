package com.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-新业务归属
 * @date 2019/11/22
 */
@ApiModel("订单-新业务信息")
@Entity
@Table(name = "e_order_new_business")
public class OrderNewBusinessEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", dataType = "string")
    private Long id;

    /*网络业务申请单*/
    @Column
    @ApiModelProperty(value = "网络业务申请单", dataType = "string")
    private String requestOrder;

    /*月份*/
    @Column
    @ApiModelProperty(value = "月份", dataType = "string")
    private String month;

    /*受理日期*/
    @Column
    @ApiModelProperty(value = "受理日期", dataType = "string")
    private String date;

    /*对口人*/
    @Column
    @ApiModelProperty(value = "对口人", dataType = "string")
    private String counterpart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestOrder() {
        return requestOrder;
    }

    public void setRequestOrder(String requestOrder) {
        this.requestOrder = requestOrder;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCounterpart() {
        return counterpart;
    }

    public void setCounterpart(String counterpart) {
        this.counterpart = counterpart;
    }
}
