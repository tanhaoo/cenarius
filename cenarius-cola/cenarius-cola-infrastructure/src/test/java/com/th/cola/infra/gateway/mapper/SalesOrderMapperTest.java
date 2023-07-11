//package com.th.cola.infra.gateway.mapper;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
//import com.th.cola.config.mybatis.MyBatisAutoConfiguration;
//import com.th.cola.domain.gateway.ISalesOrderGateway;
//import com.th.cola.domain.model.SalesOrder;
//import com.th.cola.infra.convertor.SalesOrderConvertorImpl;
//import com.th.cola.infra.gateway.SalesOrderGateway;
//import com.th.cola.infra.gateway.database.dataobject.SalesOrderDO;
//import com.th.cola.infra.gateway.database.mapper.SalesOrderMapper;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.math.BigDecimal;
//
//import javax.annotation.Resource;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
///**
// * @Author: Aaron
// * @Date: 2023/7/24
// */
//@MybatisPlusTest
//@ContextConfiguration(classes = {MyBatisAutoConfiguration.class})
//@Import({SalesOrderGateway.class, SalesOrderConvertorImpl.class})
//@ActiveProfiles("test")
//public class SalesOrderMapperTest {
//
//    @Resource
//    private SalesOrderMapper salesOrderMapper;
//
//    @Resource
//    private ISalesOrderGateway salesOrderGateway;
//
//    private final SalesOrderDO salesOrderDO = new SalesOrderDO();
//
//    private String id;
//
//    @BeforeEach
//    public void init() {
//        salesOrderDO.setFreight(new BigDecimal("9.99"));
//        salesOrderDO.setTotalAmount(new BigDecimal("19.99"));
//        salesOrderDO.setDescription("Description: It's a sales order.");
//        salesOrderDO.setOperateUser("Aaron");
//        salesOrderMapper.insert(salesOrderDO);
//
//        id = salesOrderDO.getId();
//
//        for (int i = 0; i < 100; i++) {
//            SalesOrderDO copy = new SalesOrderDO();
//            copy.setFreight(new BigDecimal(i + "9.99"));
//            copy.setTotalAmount(new BigDecimal(i + "19.99"));
//            copy.setDescription("Description: It's a sales order." + i);
//            copy.setOperateUser("Aaron");
//            salesOrderMapper.insert(copy);
//        }
//    }
//
//    @Test
//    public void insertSalesOrder() {
//        SalesOrderDO result = salesOrderMapper.selectById(id);
//
//        assertEquals(result.getDescription(), salesOrderDO.getDescription());
//        assertEquals(result.getFreight(), salesOrderDO.getFreight());
//        assertEquals(result.getTotalAmount(), salesOrderDO.getTotalAmount());
//        assertEquals(result.getOperateUser(), salesOrderDO.getOperateUser());
//
//        System.out.println(result);
//    }
//
//    @Test
//    public void updateSalesOrder() {
//        SalesOrderDO result = salesOrderMapper.selectById(id);
//
//        result.setTotalAmount(new BigDecimal("88.88"));
//        result.setFreight(new BigDecimal("18.88"));
//        result.setDescription("Update Description: It's a sales order.");
//        result.setOperateUser("Update Aaron");
//
//        int executeRes = salesOrderMapper.updateById(result);
//        SalesOrderDO update = salesOrderMapper.selectById(id);
//
//        assertEquals(update.getDescription(), result.getDescription());
//        assertEquals(update.getFreight(), result.getFreight());
//        assertEquals(update.getTotalAmount(), result.getTotalAmount());
//        assertEquals(update.getOperateUser(), result.getOperateUser());
//
//        System.out.println("executeRes " + executeRes);
//        System.out.println(update);
//    }
//
//    @Test
//    public void testGetListByPage() {
//        IPage<SalesOrder> listByPage = salesOrderGateway.getListByPage(new Page(1, 20), null);
//        System.err.println(JSON.toJSONString(listByPage, true));
//    }
//
//    @Test
//    public void testGetListByPageAndFreightLe50() {
//        QueryWrapper<SalesOrderDO> wrapper = new QueryWrapper<>();
//        wrapper.lambda()
//                .le(SalesOrderDO::getFreight, new BigDecimal("50"));
//
//        IPage<SalesOrder> listByPage = salesOrderGateway.getListByPage(new Page(1, 20), wrapper);
//        System.err.println(JSON.toJSONString(listByPage, true));
//    }
//
//
//}
