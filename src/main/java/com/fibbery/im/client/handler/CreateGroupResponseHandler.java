package com.fibbery.im.client.handler;

import com.alibaba.fastjson.JSON;
import com.fibbery.im.protocol.response.CreateGroupResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponse> {

    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponse msg) throws Exception {
        System.out.println("你加入了群组 " + msg.getGroupId() + ", 群组成员有：" + JSON.toJSONString(msg.getUsers().values()));
    }
}
