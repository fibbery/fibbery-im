package com.fibbery.im.server.handler;

import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author fibbery
 * @date 2018/11/5
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (SessionUtils.hasLogin(ctx.channel())) {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }
}


