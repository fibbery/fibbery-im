package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.request.HeartBeatRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author fibbery
 * @date 2019-01-05
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    public static final int HEART_BEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().scheduleAtFixedRate(() -> {
            System.out.println("客户端发送心跳检测");
            ctx.writeAndFlush(new HeartBeatRequest());
        }, HEART_BEAT_INTERVAL, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
