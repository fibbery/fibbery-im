package com.fibbery.im.server;

import com.fibbery.im.protocol.Attributes;
import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.PacketCodec;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author fibbery
 * @date 2018/11/2
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        BasePacket packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequest) {
            LoginRequest request = (LoginRequest) packet;
            System.out.println(new Date() + "用户" + request.getUserName() + "登录成功");
            LoginResponse response = new LoginResponse();
            response.setSuccess(true);
            response.setMessage("登录成功");
            response.setVersion(packet.getVersion());
            //标记登录成功
            ctx.channel().attr(Attributes.USER_NAME).set(request.getUserName());
            ctx.channel().writeAndFlush(PacketCodec.INSTANCE.encode(response));
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " " + ctx.channel().attr(Attributes.USER_NAME).get() + "断开连接");
    }

}
