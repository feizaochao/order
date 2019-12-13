package com.order.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 开票信息
 * @date 2019/11/22
 */
@ApiModel("订单-开票信息")
@Entity
@Table(name = "e_order_invoice")
public class OrderInvoiceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", dataType = "long")
    private Long id;

    /*是否开票*/
    @Column(nullable = true)
    @ApiModelProperty(value = "是否开票", dataType = "int")
    private String isInvoice;

    /*开票类型*/
    @Column(nullable = true)
    @ApiModelProperty(value = "开票类型", dataType = "int")
    private int type;

    /*开票日期*/
    @Column
    @ApiModelProperty(value = "开票日期", dataType = "string")
    private String invoiceDate;

    /*开票号码*/
    @Column
    @ApiModelProperty(value = "开票号码", dataType = "string")
    private String invoiceNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }
}
