package com.th.cenarius.web.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */
@RocketMQTransactionListener
@Slf4j
public class DefaultRocketMQTransactionDispatcher<T> implements RocketMQLocalTransactionListener {

    @Resource
    private List<TxMessageProcessor<T>> processors;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            TxMessageProcessor<T> processor = getMatchProcessor(processors, message);
            T txMessage = getMatchMessage(message, processor.getMessageType());

            TxProcessContext context = (TxProcessContext) o;

            log.info("Start execute transaction,message type: {}, message: {}, context: {} ", txMessage.getClass().getSimpleName(), txMessage, context);
            return processor.executeLocalTransaction(txMessage, context);

        } catch (Exception ex) {
            log.error("Error occurred in executeLocalTransaction.", ex);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }


    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        try {
            TxMessageProcessor<T> processor = getMatchProcessor(processors, message);
            T txMessage = getMatchMessage(message, processor.getMessageType());

            log.info("Check local transaction,message type: {}, message: {}", txMessage.getClass().getSimpleName(), txMessage);
            return processor.checkLocalTransaction(txMessage);
        } catch (Exception ex) {
            log.error("Error occurred in checkLocalTransaction.", ex);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    public TxMessageProcessor getMatchProcessor(List<TxMessageProcessor<T>> processors, Message message) {
        String topic = (String) message.getHeaders().get(TxMessageConst.ROCKETMQ_TOPIC);
        String messageType = (String) message.getHeaders().get(TxMessageConst.MESSAGE_TYPE);

        return processors.stream()
                .filter(processor -> processor.getMessageType().getName().equals(messageType))
                .filter(processor -> processor.getTopic().equals(topic))
                .findFirst()
                .orElseThrow();
    }

    private T getMatchMessage(Message message, Class<T> msgType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        final String json = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        return mapper.readValue(json, msgType);
    }

}
