package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.Attributes;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest msg) throws Exception {
        System.out.println(new Date() + " " + msg.getUserName() + "登录成功！！！");
        LoginUtils.markLogin(ctx.channel());
        ctx.channel().attr(Attributes.USER_NAME).set(msg.getUserName());

        LoginResponse response = new LoginResponse();
        response.setVersion(msg.getVersion());
        response.setSuccess(true);
        ctx.writeAndFlush(response);
    }
}
