package com.fibbery.im.server;

import com.fibbery.im.protocol.Attributes;
import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.PacketCodec;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.request.MessageRequest;
import com.fibbery.im.protocol.response.LoginResponse;
import com.fibbery.im.protocol.response.MessageResponse;
import com.fibbery.im.utils.LoginUtils;
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
            //标记用户名字以及成功登录标记
            ctx.channel().attr(Attributes.USER_NAME).setIfAbsent(request.getUserName());
            LoginUtils.markLogin(ctx.channel());
            ctx.channel().writeAndFlush(PacketCodec.INSTANCE.encode(response));
        } else if (packet instanceof MessageRequest) {
            MessageRequest request = (MessageRequest) packet;
            System.out.println(new Date() + " 接收客户端消息 >> " + request.getMessage());

            MessageResponse response = new MessageResponse();
            response.setMessage("[服务端消息]" + ((MessageRequest) packet).getMessage());
            response.setVersion(packet.getVersion());
            ctx.channel().writeAndFlush(PacketCodec.INSTANCE.encode(response));
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " " + ctx.channel().attr(Attributes.USER_NAME).get() + "断开连接");
    }

}
