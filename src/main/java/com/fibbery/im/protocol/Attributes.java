package com.fibbery.im.protocol;

import io.netty.util.AttributeKey;

/**
 * 属性值
 * @author fibbery
 * @date 2018/11/3
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
