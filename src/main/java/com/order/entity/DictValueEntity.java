package com.order.entity;

import javax.persistence.*;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 字典值
 * @date 2019/12/10
 */
@Entity
@Table(name = "e_dict_value")
public class DictValueEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long dictTypeId;

    @Column
    private String name;

    @Column
    private int value;

    @Transient
    private String dictTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(Long dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }
}
