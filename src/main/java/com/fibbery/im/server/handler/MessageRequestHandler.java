package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.request.MessageRequest;
import com.fibbery.im.protocol.response.MessageResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {

        MessageResponse response = new MessageResponse();
        response.setSenderId(SessionUtils.getSession(ctx.channel()).getUserId());
        response.setReceiverId(msg.getReceiverId());
        response.setMessage(msg.getMessage());
        Channel channel = SessionUtils.getChannel(msg.getReceiverId());
        if (channel != null) {
            channel.writeAndFlush(response);
        } else {
            System.out.println(new Date() + "用户[" + msg.getReceiverId() + "]不在线");
        }
    }
}
