package com.fibbery.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.request.CreateGroupRequest;
import com.fibbery.im.protocol.response.CreateGroupResponse;
import com.fibbery.im.utils.IDUtils;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.HashMap;
import java.util.List;

/**
 * @author fibbery
 * @date 2018/11/13
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequest msg) throws Exception {
        List<Long> userIds = msg.getUserIds();
        userIds.add(SessionUtils.getSession(ctx.channel()).getUserId());

        //create group
        ChannelGroup group = new DefaultChannelGroup(ctx.executor());
        HashMap<Long, String> users = new HashMap<>(userIds.size());
        for (Long userId : userIds) {
            Channel channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                group.add(channel);
                Session session = SessionUtils.getSession(channel);
                users.put(session.getUserId(), session.getUserName());
            }
        }

        //response
        CreateGroupResponse response = new CreateGroupResponse();
        response.setGroupId(IDUtils.generateID());
        response.setUsers(users);
        group.writeAndFlush(response);
        System.out.println(">>> 服务端: 群组创建成功,群号：" + response.getGroupId() + ", 群组成员：" + JSON.toJSONString(users.values()));

        //bing
        SessionUtils.bindGroup(response.getGroupId(), group);


    }
}
