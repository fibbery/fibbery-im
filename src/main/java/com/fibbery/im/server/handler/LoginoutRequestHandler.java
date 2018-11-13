package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.request.LoginoutRequest;
import com.fibbery.im.protocol.response.LoginoutResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/13
 */
public class LoginoutRequestHandler extends SimpleChannelInboundHandler<LoginoutRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginoutRequest msg) throws Exception {
        if (SessionUtils.hasLogin(ctx.channel())) {
            String userName = SessionUtils.getSession(ctx.channel()).getUserName();
            System.out.println(">>> 服务端 ：用户" + userName + "退出登录!!!");

            LoginoutResponse response = new LoginoutResponse();
            response.setSuccess(true);
            ctx.channel().writeAndFlush(response);
        }
    }
}
