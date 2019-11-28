package com.order.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-收费信息
 * @date 2019/11/22
 */
@ApiModel("订单-收费信息")
@Entity
@Table(name = "e_order_charge")
public class OrderChargeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", dataType = "long")
    private Long id;

    /*每周期收费金额*/
    @Column
    @ApiModelProperty(value = "每周期收费金额", dataType = "double")
    private BigDecimal chargeAmount;

    /*收费日期*/
    @Column
    @ApiModelProperty(value = "收费日期", dataType = "string")
    private String chargeDate;

    /*下期续费日期*/
    @Column
    @ApiModelProperty(value = "下期续费日期", dataType = "string")
    private String nextChargeDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getNextChargeDate() {
        return nextChargeDate;
    }

    public void setNextChargeDate(String nextChargeDate) {
        this.nextChargeDate = nextChargeDate;
    }
}
