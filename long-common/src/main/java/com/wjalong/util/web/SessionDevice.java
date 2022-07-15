package com.wjalong.util.web;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chendanping
 * @date 2020/8/17  9:41
 */
@Data
public class SessionDevice implements Serializable {
    private static final long serialVersionUID = 2747744818561875748L;

    private String aesKey;
    private String publicKey;
}
