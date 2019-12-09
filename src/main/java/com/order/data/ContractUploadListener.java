package com.order.data;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.order.entity.ContractEntity;
import com.order.service.ContractService;
import com.qiniu.util.Json;
import net.sf.json.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/9
 */
public class ContractUploadListener extends AnalysisEventListener<ContractEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractUploadListener.class);

    private ContractService contractService;

    public ContractUploadListener(ContractService contractService) {
        this.contractService = contractService;
    }

    @Override
    @Transactional
    public void invoke(ContractEntity contractEntity, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据：{}", Json.encode(contractEntity));
        contractService.addContract(contractEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成");
    }
}
