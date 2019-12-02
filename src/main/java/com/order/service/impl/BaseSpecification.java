package com.order.service.impl;

import com.common.utils.Query;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/2
 */
public class BaseSpecification implements Specification {
    private Query query;

    public BaseSpecification(Query query) {
        this.query = query;
    }
    
    @Override
    public Predicate toPredicate(Root root, CriteriaQuery qy, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        String fieldName = (String) query.get("fieldName");
        if(null != fieldName && !"".equals(fieldName)) {
            Predicate predicate = cb.like(root.<String>get(fieldName), "%" + fieldName + "%");
            predicates.add(predicate);
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
