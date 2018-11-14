package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.request.MessageRequest;
import com.fibbery.im.protocol.response.MessageResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/3
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {

        MessageResponse response = new MessageResponse();
        response.setSenderId(SessionUtils.getSession(ctx.channel()).getUserId());
        response.setSenderName(SessionUtils.getSession(ctx.channel()).getUserName());
        response.setReceiverId(msg.getReceiverId());
        response.setMessage(msg.getMessage());
        Channel channel = SessionUtils.getChannel(msg.getReceiverId());
        if (channel != null) {
            channel.writeAndFlush(response);
        } else {
            System.out.println(">>>> 服务端：" + "用户" + msg.getReceiverId() + "不在线！！！");
        }
    }
}
