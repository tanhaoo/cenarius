package com.th.test;

import com.th.test.codegen.User;
import com.th.test.dto.UserDTO;

import org.junit.jupiter.api.Test;

/**
 * @Author: Aaron
 * @Date: 2022/8/31
 */
public class UnitTest {


    @Test
    public void testGenerateCode(){
        User user = new User();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("122");
        System.out.println(userDTO.getUsername());
    }
}
