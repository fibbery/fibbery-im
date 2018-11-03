package com.fibbery.im.codec;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author fibbery
 * @date 2018/11/3
 */
public class PacketEncoder extends MessageToByteEncoder<BasePacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, BasePacket msg, ByteBuf out) throws Exception {
        PacketCodec.INSTANCE.encode(msg, out);
    }
}
