package com.order.entity;

import javax.persistence.*;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 开票信息
 * @date 2019/11/22
 */
@Entity
@Table(name = "e_order_invoice")
public class OrderInvoiceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*是否开票*/
    @Column(nullable = true)
    private int isInvoice;

    /*开票类型*/
    @Column(nullable = true)
    private int type;

    /*开票日期*/
    @Column
    private String invoiceDate;

    /*开票号码*/
    @Column
    private String invoiceNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(int isInvoice) {
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
