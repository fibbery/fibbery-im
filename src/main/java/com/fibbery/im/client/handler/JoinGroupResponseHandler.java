package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.response.JoinGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponse msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(msg.getMessage());
        } else {
            System.out.println("加入群组失败，原因：" + msg.getMessage());
        }
    }
}
