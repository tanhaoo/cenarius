package com.th.test.pattern.pipeline.coupon.generate;

import com.th.cenarius.web.common.pipeline.ContextHandler;

import org.springframework.stereotype.Component;

import java.util.HashMap;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Aaron
 * @Date: 2022/11/25
 */
@Component
@Slf4j
public class CouponInstanceCreator implements ContextHandler<CouponBuildContext> {

    @Override
    public boolean handle(CouponBuildContext context) {

        HashMap<Integer, String> dictionary = createDictionary(context.getDictionary());

        StringBuilder codeBuilder = generateCouponCode(dictionary, context.getPartLen() * context.getPartCount(), context.getCurIndex());

        insertSeparator(context.getPartLen(), context.getSeparator(), codeBuilder);

        context.getCurCodeBuilder().append(codeBuilder);

        return true;
    }

    private void insertSeparator(int partLen, String separator, StringBuilder code) {
        int count = 0;
        for (int i = partLen; i < code.length() - count; i += partLen) {
            code.insert(i + (count++), separator);
        }
    }

    private HashMap<Integer, String> createDictionary(String dict) {
        String[] split = dict.split("");
        HashMap<Integer, String> dictionary = new HashMap<>(26);
        for (int i = 0; i < split.length; i++) {
            dictionary.put(i, split[i]);
        }
//        log.info("Current dictionary: {}", dictionary);
        return dictionary;
    }

    public StringBuilder generateCouponCode(HashMap<Integer, String> dict, int len, double cur) {
        StringBuilder curCode = calcCouponCode(dict, cur);
        // digits requirements
        if (curCode.length() < len) {
            int count = len - curCode.length();
            for (int j = 0; j < count; j++) {
                curCode.insert(j, dict.get(0));
            }
        }
        return curCode;
    }

    public StringBuilder calcCouponCode(HashMap<Integer, String> dict, double val) {
        int radix = dict.size();
        StringBuilder res = new StringBuilder();

        while (val != 0) {
            res.append(dict.get((int) val % radix));
            val = Math.floor(val / radix);
        }
        return res.reverse();
    }

}
