package com.fibbery.im.client.handler;

import com.alibaba.fastjson.JSON;
import com.fibbery.im.protocol.response.ListGroupResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@ChannelHandler.Sharable
public class ListGroupResponseHandler extends SimpleChannelInboundHandler<ListGroupResponse> {

    public static final ListGroupResponseHandler INSTANCE = new ListGroupResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupResponse msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println("群组[" + msg.getGroupId() + "]成员有" + JSON.toJSONString(msg.getUsers().values()));
        } else {
            System.out.println(msg.getMessage());
        }
    }
}
