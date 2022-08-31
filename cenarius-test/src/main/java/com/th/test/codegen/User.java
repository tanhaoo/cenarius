package com.th.test.codegen;

import com.th.cenarius.codegen.processor.dto.GenDto;
import com.th.cenarius.codegen.processor.dto.IgnoreDto;

/**
 * @Author: Aaron
 * @Date: 2022/8/31
 */
@GenDto(packageName = "com.th.test.dto")
public class User {

    private String username;

    private String password;

    @IgnoreDto
    private String keyWord;

    public void test() {

    }

}
