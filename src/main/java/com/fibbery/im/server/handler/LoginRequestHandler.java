package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest request) throws Exception {
        if (StringUtils.isEmpty(request.getUserName())) {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("用户名不能为空");
            ctx.writeAndFlush(response);
            return;
        }

        if (!SessionUtils.hasLogin(ctx.channel())) {
            System.out.println(">>>> 服务端： [" + request.getUserName() + "]登录成功！！！");
            Session session = new Session();
            session.setUserId(System.currentTimeMillis());
            session.setUserName(request.getUserName());
            SessionUtils.bindSession(ctx.channel(), session);

            LoginResponse response = new LoginResponse();
            response.setUserId(session.getUserId());
            response.setUsername(session.getUserName());
            response.setSuccess(true);
            ctx.writeAndFlush(response);
        }else {
            LoginResponse response = new LoginResponse();
            response.setSuccess(false);
            response.setMessage("已经登录，无需重复登录!!!");
            ctx.writeAndFlush(response);
        }

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtils.unbindSession(ctx.channel());
        super.channelInactive(ctx);
    }
}
