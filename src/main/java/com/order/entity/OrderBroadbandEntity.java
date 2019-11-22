package com.order.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-宽带信息
 * @date 2019/11/22
 */
@Entity
@Table(name = "e_order_broadband")
public class OrderBroadbandEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*运营商*/
    @Column
    private String operator;

    /*宽带类型*/
    @Column
    private String type;

    /*宽带资费*/
    @Column
    private BigDecimal price;

    /*固话*/
    @Column
    private String phone;

    /*固话数量*/
    @Column(nullable = true)
    private int phoneNum;

    /*语音资费*/
    @Column
    private BigDecimal voiceTariff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public BigDecimal getVoiceTariff() {
        return voiceTariff;
    }

    public void setVoiceTariff(BigDecimal voiceTariff) {
        this.voiceTariff = voiceTariff;
    }
}
