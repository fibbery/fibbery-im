package com.fibbery.im.protocol;

import io.netty.util.AttributeKey;

/**
 * 属性值
 * @author fibbery
 * @date 2018/11/3
 */
public interface Attributes {
    /**登录标志*/
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    /**用户名属性*/
    AttributeKey<String> USER_NAME = AttributeKey.newInstance("username");
}
