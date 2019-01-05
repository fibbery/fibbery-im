package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.request.HeartBeatRequest;
import com.fibbery.im.protocol.response.HeartBeatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2019-01-05
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequest msg) throws Exception {
        System.out.println("回复心跳");
        ctx.writeAndFlush(new HeartBeatResponse());
    }
}
