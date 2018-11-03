package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.Attributes;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //登录
        LoginRequest request = new LoginRequest();
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        request.setUserName(username);
        request.setPassword(password);
        ctx.channel().attr(Attributes.USER_NAME).set(username);
        ctx.channel().writeAndFlush(request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(new Date() + "用户[" + ctx.channel().attr(Attributes.USER_NAME).get() + "]登录成功");
            LoginUtils.markLogin(ctx.channel());
        }
    }
}
