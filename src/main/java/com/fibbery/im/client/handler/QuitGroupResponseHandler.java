package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.response.QuitGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponse msg) throws Exception {
        System.out.println(msg.getMessage());
    }
}
