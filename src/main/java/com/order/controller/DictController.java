package com.order.controller;

import com.common.utils.PageParams;
import com.common.utils.PageUtils;
import com.common.utils.Query;
import com.common.utils.ResultUtils;
import com.order.entity.DictTypeEntity;
import com.order.entity.DictValueEntity;
import com.order.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/10
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @PostMapping("/add")
    public ResultUtils addDict(@RequestBody DictValueEntity dict) {
        return dictService.addDict(dict);
    }

    @PostMapping("/edit")
    public ResultUtils editDict(@RequestBody DictValueEntity dict) {
        return dictService.editDict(dict);
    }

    @GetMapping("/delete")
    public ResultUtils deleteDict(Long id) {
        return dictService.deleteDict(id);
    }

    @PostMapping("/list")
    public ResultUtils queryList(@RequestBody PageParams params) {
        Query query = new Query(params.toMap());
        PageUtils pageUtils = dictService.queryList(query);
        return ResultUtils.success("", pageUtils);
    }

    @GetMapping("/one")
    public ResultUtils queryOne(Long id) {
        return dictService.queryOne(id);
    }

    @GetMapping("/type")
    public ResultUtils queryTypeList() {
        List<DictTypeEntity> list = dictService.queryDictTypeList();
        return ResultUtils.success("", list);
    }

    @GetMapping("/value")
    public ResultUtils queryValueList(Long dictTypeId) {
        List<DictValueEntity> list = dictService.queryDictValueList(dictTypeId);
        return ResultUtils.success("", list);
    }
}
