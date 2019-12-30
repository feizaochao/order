package com.order.data;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.order.entity.*;
import com.order.repository.AreaRepository;
import com.order.repository.DictRepository;
import com.order.service.OrderService;
import com.qiniu.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/9
 */
public class OrderUploadListener extends AnalysisEventListener<OrderData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderUploadListener.class);

    private OrderService orderService;

    private DictRepository dictRepository;

    private AreaRepository areaRepository;

    public OrderUploadListener(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderUploadListener(OrderService orderService, DictRepository dictRepository) {
        this.orderService = orderService;
        this.dictRepository = dictRepository;
    }

    public OrderUploadListener(OrderService orderService, DictRepository dictRepository, AreaRepository areaRepository) {
        this.orderService = orderService;
        this.dictRepository = dictRepository;
        this.areaRepository = areaRepository;
    }

    @Override
    public void invoke(OrderData orderData, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据：{}", Json.encode(orderData));

        // 订单信息
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(orderData.getOrderNo());
        orderEntity.setOwnerProject(orderData.getOwnerProject());
        DictValueEntity dictValueEntity = dictRepository.findByDictTypeIdAndName(DictType.TRADE_STATUS, orderData.getStatus());
        if(null != dictValueEntity) {
            orderEntity.setStatus(dictValueEntity.getValue());
        }
        orderEntity.setIsUseNetwork(orderData.getIsUseNetWork());
        orderEntity.setAreaNumber(orderData.getAreaNumber());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(orderData.getCustomerName());
        customerEntity.setContact(orderData.getContact());
        customerEntity.setMallNo(orderData.getMailNo());
        customerEntity.setLicenseNo(orderData.getLicenseNo());
        customerEntity.setLicenseAddress(orderData.getLicenseAddress());
        dictValueEntity = dictRepository.findByDictTypeIdAndName(DictType.PERSIST, orderData.getPersist());
        if(null != dictValueEntity) {
            customerEntity.setPersist(dictValueEntity.getValue());
        }
        customerEntity.setAddress(orderData.getAddress());
        customerEntity.setRemarks(orderData.getRemarks());
        AreaEntity areaEntity = areaRepository.findByAreaName(orderData.getAreaName());
        if(areaEntity != null) {
            orderEntity.setAreaId(areaEntity.getId());
            orderEntity.setAreaName(areaEntity.getAreaName());

            customerEntity.setAreaId(areaEntity.getId());
            customerEntity.setAreaName(areaEntity.getAreaName());
        }
        orderEntity.setCustomer(customerEntity);

        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setSiteName(orderData.getSiteName());
        contractEntity.setContractNo(orderData.getContractNo());
        contractEntity.setPartyA(orderData.getPartyA());
        contractEntity.setPartyB(orderData.getPartyB());
        contractEntity.setContractStartTime(orderData.getContractStartTime());
        contractEntity.setContractEndTime(orderData.getContractEndTime());
        contractEntity.setContractAmount(orderData.getContractAmount());
        contractEntity.setElectricityFee(orderData.getElectricityFee());
        contractEntity.setStartTime(orderData.getStartTime());
        contractEntity.setEndTime(orderData.getEndTime());
        contractEntity.setElectricityCharge(orderData.getElectricityCharge());
        contractEntity.setElectricitySubmitType(orderData.getElectricitySubmitType());
        contractEntity.setElectricityPaid(orderData.getElectricityPaid());
        contractEntity.setPaidTime(orderData.getPaidTime());
        orderEntity.setContract(contractEntity);

        OrderBroadbandEntity broadbandEntity = new OrderBroadbandEntity();
        broadbandEntity.setOperator(orderData.getOperator());
        dictValueEntity = dictRepository.findByDictTypeIdAndName(DictType.BROADBAND_TYPE, orderData.getType());
        if(null != dictValueEntity) {
            broadbandEntity.setType(dictValueEntity.getValue());
        }
        broadbandEntity.setPrice(orderData.getPrice());
        broadbandEntity.setPhone(orderData.getPhone());
        broadbandEntity.setPhoneNum(orderData.getPhoneNum());
        broadbandEntity.setVoiceTariff(orderData.getVoiceTariff());
        orderEntity.setBroadband(broadbandEntity);

        OrderChargeEntity chargeEntity = new OrderChargeEntity();
        chargeEntity.setChargeAmount(orderData.getChargeAmount());
        chargeEntity.setChargeDate(orderData.getChargeDate());
        chargeEntity.setNextChargeDate(orderData.getNextChargeDate());
        orderEntity.setCharge(chargeEntity);

        OrderInvoiceEntity invoiceEntity = new OrderInvoiceEntity();
        invoiceEntity.setIsInvoice(orderData.getIsInvoice());
        dictValueEntity = dictRepository.findByDictTypeIdAndName(DictType.INVOICE_TYPE, orderData.getInvoiceType());
        if(null != dictValueEntity) {
            invoiceEntity.setType(dictValueEntity.getValue());
        }
        invoiceEntity.setInvoiceDate(orderData.getInvoiceDate());
        invoiceEntity.setInvoiceNum(orderData.getInvoiceNum());
        orderEntity.setInvoice(invoiceEntity);

        OrderNewBusinessEntity newBusinessEntity = new OrderNewBusinessEntity();
        newBusinessEntity.setRequestOrder(orderData.getRequestOrder());
        newBusinessEntity.setMonth(orderData.getMonth());
        newBusinessEntity.setDate(orderData.getDate());
        newBusinessEntity.setCounterpart(orderData.getCounterpart());
        orderEntity.setNewBusiness(newBusinessEntity);

        OrderOperatorEntity operatorEntity = new OrderOperatorEntity();
        operatorEntity.setProductNum(orderData.getProductNum());
        dictValueEntity = dictRepository.findByDictTypeIdAndName(DictType.OPERATOR_ACCOUNT_TYPE, orderData.getAccountStatus());
        if(null != dictValueEntity) {
            operatorEntity.setAccountStatus(dictValueEntity.getValue());
        }
        operatorEntity.setInternetAccount(orderData.getInternetAccount());
        operatorEntity.setBroadband(orderData.getBroadband());
        operatorEntity.setPhone(orderData.getOperatorPhone());
        operatorEntity.setPaymentCycle(orderData.getPaymentCycle());
        operatorEntity.setPaymentDate(orderData.getPaymentDate());
        operatorEntity.setInvoiceDate(orderData.getOperatorInvoiceDate());
        operatorEntity.setContact(orderData.getOperatorContact());
        operatorEntity.setRenewContract(orderData.getRenewContract());
        operatorEntity.setPrimaryCharge(orderData.getPrimaryCharge());
        orderEntity.setOperator(operatorEntity);

        orderService.addOrder(orderEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成");
    }
}
