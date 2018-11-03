package com.fibbery.im.utils;


import com.fibbery.im.protocol.Attributes;
import io.netty.channel.Channel;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginUtils {

    public static void markLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN).get() != null;
    }
}
