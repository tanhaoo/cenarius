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
public class SubmitMessageProcessor implements TxMessageProcessor<SubmitMessage> {

    @Override
    public String getTopic() {
        return "submit_topic";
    }

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(SubmitMessage message, TxProcessContext context) {
        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(SubmitMessage message) {
        return RocketMQLocalTransactionState.COMMIT;
    }
}
