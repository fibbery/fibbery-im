package com.fibbery.im.utils;

import com.fibbery.im.protocol.Attributes;
import com.fibbery.im.protocol.Session;
import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * @author fibbery
 * @date 2018/11/5
 */
public class SessionUtils {

    public static final HashMap<Long, Channel> userChannelMap = new HashMap<>();

    public static void bindSession(io.netty.channel.Channel channel, Session session) {
        userChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            userChannelMap.remove(channel.attr(Attributes.SESSION).get().getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(Long userId) {
        return userChannelMap.get(userId);
    }
}
