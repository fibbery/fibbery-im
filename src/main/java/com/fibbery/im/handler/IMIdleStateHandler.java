package com.fibbery.im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author fibbery
 * @date 2019-01-05
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READ_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READ_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READ_IDLE_TIME + "秒内未检测到读，强制关闭连接");
        ctx.channel().close();
    }
}
