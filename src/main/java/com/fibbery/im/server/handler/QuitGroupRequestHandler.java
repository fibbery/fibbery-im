package com.fibbery.im.server.handler;

import com.fibbery.im.client.handler.QuitGroupResponseHandler;
import com.fibbery.im.protocol.request.QuitGroupRequest;
import com.fibbery.im.protocol.response.QuitGroupResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequest> {

    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequest msg) throws Exception {
        ChannelGroup group = SessionUtils.getGroup(msg.getGroupId());
        if (group == null) {
            QuitGroupResponse response = new QuitGroupResponse();
            response.setSuccess(false);
            response.setMessage("群组不存在");
            ctx.channel().writeAndFlush(response);
            return;
        }

        if (!group.contains(ctx.channel())) {
            QuitGroupResponse response = new QuitGroupResponse();
            response.setSuccess(false);
            response.setMessage("当前用户不在此群组");
            ctx.channel().writeAndFlush(response);
            return;
        }

        QuitGroupResponse response = new QuitGroupResponse();
        response.setSuccess(true);
        response.setGroupId(msg.getGroupId());
        String userName = SessionUtils.getSession(ctx.channel()).getUserName();
        response.setMessage("用户" + userName + "从群组[" + msg.getGroupId() + "]退出");
        group.writeAndFlush(response);
        group.remove(ctx.channel());



    }
}
