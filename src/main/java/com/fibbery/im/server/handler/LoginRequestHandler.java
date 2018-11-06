package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.utils.SessionUtils;
import com.fibbery.im.utils.UserUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest request) throws Exception {
        String userName = UserUtils.getUserName(request.getUserId());
        System.out.println(new Date() + "[" + userName + "]登录成功！！！");
        Session session = new Session();
        session.setUserId(request.getUserId());
        session.setUserName(userName);
        SessionUtils.bindSession(ctx.channel(), session);

        LoginResponse response = new LoginResponse();
        response.setUserId(request.getUserId());
        response.setUsername(userName);
        response.setSuccess(true);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtils.unbindSession(ctx.channel());
        super.channelInactive(ctx);
    }
}
