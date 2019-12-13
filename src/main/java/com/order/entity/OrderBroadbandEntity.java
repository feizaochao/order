package com.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-宽带信息
 * @date 2019/11/22
 */
@ApiModel("订单-宽带信息")
@Entity
@Table(name = "e_order_broadband")
public class OrderBroadbandEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", dataType = "long")
    private Long id;

    /*运营商*/
    @Column
    @ApiModelProperty(value = "运营商", dataType = "string")
    private String operator;

    /*宽带类型*/
    @Column
    @ApiModelProperty(value = "宽带类型", dataType = "int")
    private int type;

    /*宽带资费*/
    @Column
    @ApiModelProperty(value = "宽带资费", dataType = "double")
    private BigDecimal price;

    /*固话*/
    @Column
    @ApiModelProperty(value = "固话", dataType = "string")
    private String phone;

    /*固话数量*/
    @Column(nullable = true)
    @ApiModelProperty(value = "固话数量", dataType = "int")
    private int phoneNum;

    /*语音资费*/
    @Column
    @ApiModelProperty(value = "语音资费", dataType = "double")
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
