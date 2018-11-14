package com.fibbery.im.codec;

import com.fibbery.im.protocol.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 拦截过滤掉非本类别协议 stateful不可单例
 * @author fibbery
 * @date 2018/11/5
 */
public class ProtocolFilter extends LengthFieldBasedFrameDecoder {

    public static final int LENGTH_FIELD_OFFSET = 7;

    public static final int LENGTH_FIELD_LENGHT = 4;

    public ProtocolFilter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGHT);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUM) {
           //屏蔽非同类协议
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
