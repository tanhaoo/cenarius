package com.th.test.message;

import com.th.cenarius.web.message.TxMessageProcessor;
import com.th.cenarius.web.message.TxProcessContext;

import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */
@Component
@Slf4j
public class ConfirmMessageProcessor implements TxMessageProcessor<ConfirmMessage> {

    @Override
    public String getTopic() {
        return "confirm_topic";
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(ConfirmMessage message, TxProcessContext context) {
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(ConfirmMessage message) {
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
