package com.order.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 字典类型
 * @date 2019/12/10
 */
@Entity
@Table(name = "e_dict_type")
public class DictTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Transient
    private List<DictValueEntity> dictValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DictValueEntity> getDictValues() {
        return dictValues;
    }

    public void setDictValues(List<DictValueEntity> dictValues) {
        this.dictValues = dictValues;
    }
}
