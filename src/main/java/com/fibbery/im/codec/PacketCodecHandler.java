package com.fibbery.im.codec;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, BasePacket> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket msg, List<Object> out) throws Exception {
        ByteBuf buff = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(msg, buff);
        out.add(buff);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode(msg));
    }
}
