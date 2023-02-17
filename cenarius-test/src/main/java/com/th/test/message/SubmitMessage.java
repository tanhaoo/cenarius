package com.th.test.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubmitMessage {

    private int id;

    private String name;

    private String submitDis;
}
