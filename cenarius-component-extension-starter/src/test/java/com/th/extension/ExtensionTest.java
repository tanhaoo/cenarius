package com.th.extension;

import com.th.extension.order.IOrderServiceExtPt;
import com.th.extension.order.OrderLocalServiceImpl;
import com.th.extension.order.OrderRemoteServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @Author: Aaron
 * @Date: 2023/5/31
 */
@ExtendWith(SpringExtension.class)
@Import({
        OrderLocalServiceImpl.class,
        OrderRemoteServiceImpl.class,
        ExtensionBootstrap.class,
        ExtensionRepository.class,
        ExtensionExecutor.class
})
public class ExtensionTest {

    @Resource
    private ExtensionExecutor extensionExecutor;

    @Test
    public void testEquals() {

        ExtensionCoordinate localIOrderService = new ExtensionCoordinate(IOrderServiceExtPt.class.getName(), "Local");
        ExtensionCoordinate copyLocalIOrderService = new ExtensionCoordinate(IOrderServiceExtPt.class.getName(), "Local");
        ExtensionCoordinate remoteIOrderService = new ExtensionCoordinate(IOrderServiceExtPt.class.getName(), "Remote");
        ExtensionCoordinate localOrderLocalServiceImpl = new ExtensionCoordinate(OrderLocalServiceImpl.class.getName(), "Local");

        System.err.println(localIOrderService.toString());

        assertEquals(localIOrderService, copyLocalIOrderService);
        assertNotEquals(localIOrderService, localOrderLocalServiceImpl);
        assertNotEquals(localIOrderService, remoteIOrderService);
    }

    @Test
    public void testExecuteVoidSuccess() {
        extensionExecutor.executeVoid(IOrderServiceExtPt.class, "Local", IOrderServiceExtPt::say);
        extensionExecutor.executeVoid(IOrderServiceExtPt.class, "Remote", IOrderServiceExtPt::say);
    }

    @Test
    public void testExecuteVoidFailure() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> extensionExecutor.executeVoid(IOrderServiceExtPt.class, "Local1", IOrderServiceExtPt::say)
        );
        assertEquals(ex.getMessage(),
                "Can not find extension with ExtensionPoint: " + IOrderServiceExtPt.class + " BizScenario: Local1");
    }

    @Test
    public void testExecuteSuccess() {
        BigDecimal localSummary = extensionExecutor.execute(IOrderServiceExtPt.class, "Local", IOrderServiceExtPt::summary);
        BigDecimal remoteSummary = extensionExecutor.execute(IOrderServiceExtPt.class, "Remote", IOrderServiceExtPt::summary);

        assertEquals(localSummary.compareTo(new BigDecimal("10")), 0);
        assertEquals(remoteSummary.compareTo(new BigDecimal("5")), 0);
    }

    @Test
    public void testExecuteFailure() {
        BigDecimal localSummary = extensionExecutor.execute(IOrderServiceExtPt.class, "Local", IOrderServiceExtPt::summary);
        BigDecimal remoteSummary = extensionExecutor.execute(IOrderServiceExtPt.class, "Remote", IOrderServiceExtPt::summary);

        assertNotEquals(localSummary.compareTo(new BigDecimal("5")), 0);
        assertNotEquals(remoteSummary.compareTo(new BigDecimal("10")), 0);
    }

    @Test
    public void testExecuteFailureByInvalidBizId() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> extensionExecutor.executeVoid(IOrderServiceExtPt.class, "Local1", IOrderServiceExtPt::summary)
        );
        assertEquals(ex.getMessage(),
                "Can not find extension with ExtensionPoint: " + IOrderServiceExtPt.class + " BizScenario: Local1");
    }
}
