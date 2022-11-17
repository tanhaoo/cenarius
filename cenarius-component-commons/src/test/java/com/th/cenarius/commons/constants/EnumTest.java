package com.th.cenarius.commons.constants;

import com.google.common.collect.Lists;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @Author: Aaron
 * @Date: 2022/9/13
 */

public class EnumTest {

    @Test
    public void baseEnumTest() {
        Lists.newArrayList();
        FeeTypeEnum of = FeeTypeEnum.of(2);
        System.out.println(of);
    }

}
