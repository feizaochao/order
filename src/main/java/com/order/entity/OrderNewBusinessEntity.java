package com.order.entity;

import javax.persistence.*;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-新业务归属
 * @date 2019/11/22
 */
@Entity
@Table(name = "e_order_new_business")
public class OrderNewBusinessEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*网络业务申请单*/
    @Column
    private String requestOrder;

    /*月份*/
    @Column
    private String month;

    /*受理日期*/
    @Column
    private String date;

    /*对口人*/
    @Column
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
