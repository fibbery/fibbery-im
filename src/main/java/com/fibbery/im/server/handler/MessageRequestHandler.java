package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.request.MessageRequest;
import com.fibbery.im.protocol.response.MessageResponse;
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
        System.out.println(new Date() + " 接收到客户端消息 >> " + msg.getMessage());

        MessageResponse response = new MessageResponse();
        response.setVersion(msg.getVersion());
        response.setMessage("[服务端消息]");
        ctx.channel().writeAndFlush(response);
    }
}
