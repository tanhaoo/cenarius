package com.th.cola.app.order;

import com.alibaba.fastjson.JSON;
import com.th.cola.api.order.ISalesOrderService;
import com.th.cola.domain.order.event.SalesOrderCreateEventHandler;
import com.th.cola.domain.order.model.SalesOrder;
import com.th.cola.domain.order.pipeline.config.PipelineConfig;
import com.th.cola.domain.order.pipeline.filter.OrderSavingFilter;
import com.th.cola.domain.order.service.SalesOrderDomainServiceImpl;
import com.th.cola.dto.SalesOrderAddCmd;
import com.th.cola.infra.delivery.config.InfraConfiguration;
import com.th.cola.infra.delivery.gateway.DeliveryGatewayImpl;
import com.th.cola.infra.order.gateway.SalesOrderGatewayImpl;
import com.th.cola.infra.order.gateway.SalesOrderPipelineGatewayImpl;
import com.th.cola.infra.product.gateway.ProductGatewayImpl;
import com.th.cola.infra.promotion.gateway.PromotionGatewayImpl;
import com.th.cola.order.SalesOrderServiceImpl;
import com.th.cola.order.convertor.SalesOrderAppConvertorImpl;
import com.th.extension.ExtensionExecutor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @Author: Aaron
 * @Date: 2023/5/25
 */
@ExtendWith(SpringExtension.class)
@Import({
        SalesOrderServiceImpl.class,
        SalesOrderAppConvertorImpl.class,
        SalesOrderCreateEventHandler.class,
        SalesOrderDomainServiceImpl.class,
        PipelineConfig.class,
        SalesOrderPipelineGatewayImpl.class,
        SalesOrderGatewayImpl.class,
        PromotionGatewayImpl.class,
        DeliveryGatewayImpl.class,
        ProductGatewayImpl.class,
        OrderSavingFilter.class,
})
public class SalesOrderServiceImplTest {

    @Resource
    private ISalesOrderService salesOrderService;

    @MockBean
    private ExtensionExecutor extensionExecutor;

    @MockBean
    private InfraConfiguration configuration;

    private List<SalesOrder> orders = new ArrayList<>();

    private SalesOrderAddCmd cmd = new SalesOrderAddCmd();

    @BeforeEach
    public void initData() {
//        for (int i = 0; i < 50; i++) {
//            orders.add(new SalesOrder(String.valueOf(i),
//                    "order detail " + i,
//                    BigDecimal.valueOf(new Random().nextDouble() * 100),
//                    "operator00",
//                    Lists.newArrayList()));
//        }
        cmd = new SalesOrderAddCmd("Order Desc 01", new BigDecimal("100"), "operator00", false, new ArrayList<>());

        for (int i = 0; i < 10; i++) {
            SalesOrderAddCmd.Product product = new SalesOrderAddCmd.Product(
                    "SPU " + i,
                    "SKU " + i,
                    BigDecimal.valueOf(new Random().nextDouble() * 100),
                    BigDecimal.valueOf(new Random().nextDouble() * 100));
            cmd.getItems().add(new SalesOrderAddCmd.SalesOrderItem(
                    "Item ID " + i,
                    product,
                    (long) new Random().nextInt(100),
                    BigDecimal.valueOf(new Random().nextDouble() * 100),
                    BigDecimal.valueOf(new Random().nextDouble() * 100))
            );
        }
    }

    @Test
    public void testSubmitOrder() {
        System.out.println(JSON.toJSONString(cmd));
        salesOrderService.submitOrder(cmd);
    }

    @Test
    public void test() {

        System.out.println(JSON.toJSONString(cmd));

        List<String> collect = cmd.getItems().stream().map(SalesOrderAddCmd.SalesOrderItem::getItemId).collect(Collectors.toList());
        collect.replaceAll(val -> "123");
        System.err.println(collect);
        System.err.println(cmd.getItems());
    }
}
