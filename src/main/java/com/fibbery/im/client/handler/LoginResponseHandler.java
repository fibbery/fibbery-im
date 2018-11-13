package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse response) throws Exception {
        if (response.isSuccess()) {
            System.out.println("---> 客户端：用户" + response.getUsername() + "[" + response.getUserId() + "]成功登录!!!");
            Session session = new Session();
            session.setUserId(response.getUserId());
            session.setUserName(response.getUsername());
            SessionUtils.bindSession(ctx.channel(), session);
        } else {
            System.out.println("---> 客户端：" + "用户登录失败：" + response.getMessage());
        }
    }
}
