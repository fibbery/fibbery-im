package com.fibbery.im.utils;

import com.fibbery.im.protocol.Attributes;
import com.fibbery.im.protocol.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fibbery
 * @date 2018/11/5
 */
public class SessionUtils {
    /**
     * 存储用户session信息
     */
    private static final Map<Long, Channel> USER_SESSIONS = new ConcurrentHashMap<>();

    /**
     * 存储用户群组信息
     */
    private static final Map<Long, ChannelGroup> USER_GROUPS = new ConcurrentHashMap<>();

    public static void bindSession(io.netty.channel.Channel channel, Session session) {
        USER_SESSIONS.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if (hasLogin(channel)) {
            USER_SESSIONS.remove(channel.attr(Attributes.SESSION).get().getUserId());
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
        return USER_SESSIONS.get(userId);
    }

    public static void bindGroup(long groupId, ChannelGroup group) {
        USER_GROUPS.put(groupId, group);
    }
}
