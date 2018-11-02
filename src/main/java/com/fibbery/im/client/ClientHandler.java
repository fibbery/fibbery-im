package com.fibbery.im.client;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.PacketCodec;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author fibbery
 * @date 2018/11/2
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //登录
        LoginRequest request = new LoginRequest();
        request.setUserName(UUID.randomUUID().toString());
        request.setPassword(UUID.randomUUID().toString());
        ByteBuf data = PacketCodec.INSTANCE.encode(request);
        ctx.channel().writeAndFlush(data);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        BasePacket packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponse) {
            LoginResponse response = (LoginResponse) packet;
            if (response.isSuccess()) {
                System.out.println(new Date() + " 用户成功登录");
            } else {
                System.out.println(new Date() + " 用户登录失败");
            }
        }
    }
}
