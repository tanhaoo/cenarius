package com.th.cenarius.lock.utils;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Author: Aaron
 * @Date: 2022/2/15
 */
public class SpELUtils {

    /**
     * @param expressionString  EL表达式
     * @param method            被执行方法
     * @param args              参数
     * @param c                 期望返回类型
     * @param <T>
     * @return
     */
    public static <T> T parseExpression(String expressionString, Method method, Object[] args, Class<T> c) {
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArr = discoverer.getParameterNames(method);
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < paramNameArr.length; ++i) {
            context.setVariable(paramNameArr[i], args[i]);
        }

        return parser.parseExpression(expressionString).getValue(context, c);
    }

    /**
     * 通过类名加属性名称直接返回对应值,类型嵌套同理
     * 例:   class OrderItem->{Integer id,class A a->{B b}}
     *       #OrderItem.id
     *       #OrderItem.a.b
     * @param expressionString
     * @param args
     * @return
     */
    public static Object parseExpression(String expressionString, Object args) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable(args.getClass().getSimpleName(), args);
        return parser.parseExpression(expressionString).getValue(context);
    }
}
