package com.order.service;

import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.ResultUtils;
import com.order.entity.DictTypeEntity;
import com.order.entity.DictValueEntity;

import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/10
 */
public interface DictService {

    ResultUtils addDict(DictValueEntity dict);

    ResultUtils editDict(DictValueEntity dict);

    ResultUtils deleteDict(Long id);

    PageUtils queryList(Query query);

    ResultUtils queryOne(Long id);

    List<DictTypeEntity> queryDictTypeList();

    List<DictValueEntity> queryDictValueList(Long dictTypeId);
}
