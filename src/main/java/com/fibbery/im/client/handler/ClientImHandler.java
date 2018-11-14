package com.fibbery.im.client.handler;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@ChannelHandler.Sharable
public class ClientImHandler extends SimpleChannelInboundHandler<BasePacket> {

    public static final ClientImHandler INSTANCE = new ClientImHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends BasePacket>> handlerMap;

    private ClientImHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(Command.LOGIN_RESPONSE_COMMAND, LoginResponseHandler.INSTANCE);
        handlerMap.put(Command.LOGIN_OUT_RESPONSE_COMMAND, LoginoutResponseHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_RESPONSE_COMMAND, MessageResponseHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_RESPONSE_COMMAND, CreateGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_RESPONSE_COMMAND, GroupMessageResponseHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_RESPONSE_COMMAND, QuitGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_RESPONSE_COMMAND, JoinGroupResponseHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_RESPONSE_COMMAND, ListGroupResponseHandler.INSTANCE);
    }


    @Override

    protected void channelRead0(ChannelHandlerContext ctx, BasePacket msg) throws Exception {
        SimpleChannelInboundHandler<? extends BasePacket> handler = handlerMap.get(msg.getCommand());
        if (handler != null) {
            handler.channelRead(ctx, msg);
        }
    }
}
