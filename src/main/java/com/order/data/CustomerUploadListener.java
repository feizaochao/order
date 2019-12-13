package com.order.data;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.order.entity.CustomerEntity;
import com.order.entity.DictValueEntity;
import com.order.repository.DictRepository;
import com.order.service.CustomerService;
import com.qiniu.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/9
 */
public class CustomerUploadListener extends AnalysisEventListener<CustomerEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerUploadListener.class);

    private CustomerService customerService;

    private DictRepository dictRepository;

    public CustomerUploadListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerUploadListener(CustomerService customerService, DictRepository dictRepository) {
        this.customerService = customerService;
        this.dictRepository = dictRepository;
    }

    @Override
    public void invoke(CustomerEntity customerEntity, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据：{}", Json.encode(customerEntity));
        DictValueEntity dictValueEntity = dictRepository.findByDictTypeIdAndName(DictType.PERSIST, customerEntity.getPersistName());
        if(null != dictValueEntity) {
            customerEntity.setPersist(dictValueEntity.getValue());
        }
        customerService.addCustomer(customerEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成");
    }
}
