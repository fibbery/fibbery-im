package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.response.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) throws Exception {
        System.out.println(new Date() + "收到用户[" + msg.getSenderId() + "]的来信：" + msg.getMessage());
    }
}
