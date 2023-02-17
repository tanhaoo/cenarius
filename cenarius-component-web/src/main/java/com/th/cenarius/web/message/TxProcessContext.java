package com.th.cenarius.web.message;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Aaron
 * @Date: 2023/2/15
 */

@Data
@NoArgsConstructor
public class TxProcessContext implements Serializable {

    private Object arg;

    private RuntimeException exception;

    public TxProcessContext(Object arg) {
        this.arg = arg;
    }
}
