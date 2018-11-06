package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse response) throws Exception {
        if (response.isSuccess()) {
            System.out.println(new Date() + "[" + response.getUsername() + "]已经成功登录！！！");
            Session session = new Session();
            session.setUserId(response.getUserId());
            session.setUserName(response.getUsername());
            SessionUtils.bindSession(ctx.channel(), session);
        }
    }
}
