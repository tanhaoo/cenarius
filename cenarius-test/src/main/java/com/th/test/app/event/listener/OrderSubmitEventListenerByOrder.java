package com.th.test.app.event.listener;

import com.th.cenarius.event.EventListener;
import com.th.test.app.event.OrderSubmitEventByOrder;
import com.th.test.domain.Order;

import org.springframework.stereotype.Component;

/**
 * @Author: Aaron
 * @Date: 2022/3/3
 */
@Component
public class OrderSubmitEventListenerByOrder implements EventListener<OrderSubmitEventByOrder> {

    @Override
    public void onEvent(OrderSubmitEventByOrder event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(event.getTarget());
    }
}
