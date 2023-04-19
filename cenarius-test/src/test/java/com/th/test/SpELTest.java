package com.th.test;

import com.th.cenarius.lock.utils.SpELUtils;
import com.th.test.app.service.OrderService;
import com.th.test.domain.Order;
import com.th.test.domain.OrderItem;
import com.th.test.domain.OrderPrice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * @Author: Aaron
 * @Date: 2023/4/10
 */
@ExtendWith(SpringExtension.class)
public class SpELTest {

    @Test
    public void testReflectCollection() throws NoSuchMethodException {
        String expressionString = "#order.codes";
        Method method = OrderService.class.getMethod("submit", Order.class);

        Order testOrder = new Order();
        testOrder.setCodes(new ArrayList<>(Arrays.asList(99, 1, 100, 2)));

        Collection collection = SpELUtils.parseExpression(expressionString, method, new Object[]{testOrder}, Collection.class);

        System.out.println(collection);
    }

    @Test
    public void testVariable() {
        Order order = new Order();
        order.setOrderPrice(new OrderPrice(88));

        OrderItem orderItem = new OrderItem();
        orderItem.setId(2);

        StandardEvaluationContext context = new StandardEvaluationContext();
        ExpressionParser parser = new SpelExpressionParser();

        context.setVariable("order", order);
        context.setVariable("orderItem", orderItem);

        String expression = "#order.orderPrice.price * #orderItem.id";
        Integer value = parser.parseExpression(expression).getValue(context, Integer.class);

        System.out.println(value);
    }

    @Test
    public void testRootObject() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        ExpressionParser parser = new SpelExpressionParser();

        context.setRootObject(this);

        Object thisObj = parser.parseExpression("#this").getValue(context);
        Object thisRoot = parser.parseExpression("#root").getValue(context);

        System.out.println(thisObj);
        System.out.println(thisRoot);
    }

    @Test
    public void testBeanResolver() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        Order order = new Order();
        order.setId(55);

        factory.registerSingleton("test", order);

        StandardEvaluationContext context = new StandardEvaluationContext();
        ExpressionParser parser = new SpelExpressionParser();

        context.setBeanResolver(new BeanFactoryResolver(factory));

        Integer id = parser.parseExpression("@test.id").getValue(context, Integer.class);

        System.out.println(id);
    }

    @Test
    public void testTKey() {
        ExpressionParser parser = new SpelExpressionParser();

        String expression = "T(Math).random()";
        Double value = parser.parseExpression(expression).getValue(Double.class);

        String dateExpression = "new java.util.Date().toInstant()";
        Instant instant = parser.parseExpression(dateExpression).getValue(Instant.class);

        // 这边#this就是指带传进去的instant实例，把它作为rootObject
        String dateClassExpression = "T(java.util.Date).from(#this)";
        Date date = parser.parseExpression(dateClassExpression).getValue(instant, Date.class);

        System.out.println(value);
        System.out.println(instant);
        System.out.println(date);

    }

    @Test
    public void testCollection() {
        ExpressionParser parser = new SpelExpressionParser();

        Collection list = parser.parseExpression("{4,5,6,7}").getValue(Collection.class);
        System.out.println(list);

        Integer first = parser.parseExpression("#this[0]").getValue(list, Integer.class);
        System.out.println(first);
    }

    @Test
    public void testTemplate() {
        SpelExpressionParser parser = new SpelExpressionParser();

        ParserContext context = new TemplateParserContext("%{", "}");
        Expression expression = parser.parseExpression("Hello, My name is %{#name}, age is %{#age}", context);

        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name", "Aaron");
        evaluationContext.setVariable("age", 23);

        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);

    }


}
