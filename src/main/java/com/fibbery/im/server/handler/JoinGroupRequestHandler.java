package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.request.JoinGroupRequest;
import com.fibbery.im.protocol.response.JoinGroupResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequest msg) throws Exception {
        ChannelGroup group = SessionUtils.getGroup(msg.getGroupId());
        if (group == null) {
            JoinGroupResponse response = new JoinGroupResponse();
            response.setSuccess(false);
            response.setMessage("群组不存在");
            return;
        }

        group.add(ctx.channel());
        JoinGroupResponse response = new JoinGroupResponse();
        response.setSuccess(true);
        response.setMessage("用户[" + SessionUtils.getSession(ctx.channel()).getUserName() + "]加入群组[" + msg.getGroupId() + "]");
        group.writeAndFlush(response);
    }
}
