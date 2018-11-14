package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.response.LoginoutResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/13
 */
public class LoginoutResponseHandler extends SimpleChannelInboundHandler<LoginoutResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginoutResponse msg) throws Exception {
        if (msg.isSuccess()) {
            String userName = SessionUtils.getSession(ctx.channel()).getUserName();
            System.out.println("用户" + userName + "登录退出!");
        }
        SessionUtils.unbindSession(ctx.channel());
        System.out.println();
    }
}
