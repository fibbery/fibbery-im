package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.request.GroupMessageRequest;
import com.fibbery.im.protocol.response.GroupMessageResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequest msg) throws Exception {
        ChannelGroup group = SessionUtils.getGroup(msg.getGroupId());
        if (group == null) {
            GroupMessageResponse response = new GroupMessageResponse();
            response.setSuccess(false);
            response.setMessage("群组不存在");
            ctx.channel().writeAndFlush(response);
        }

        if (!group.contains(ctx.channel())) {
            GroupMessageResponse response = new GroupMessageResponse();
            response.setSuccess(false);
            response.setMessage("该用户不存在群组中");
            ctx.channel().writeAndFlush(response);
        }

        Session sesson = SessionUtils.getSession(ctx.channel());
        GroupMessageResponse response = new GroupMessageResponse();
        response.setSuccess(true);
        response.setMessage(msg.getMessage());
        response.setGroupId(msg.getGroupId());
        response.setSenderId(sesson.getUserId());
        response.setSenderName(sesson.getUserName());
        group.writeAndFlush(response);
    }
}
