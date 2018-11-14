package com.fibbery.im.server.handler;

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
public class ServerImHandler extends SimpleChannelInboundHandler<BasePacket> {

    public static final ServerImHandler INSTANCE = new ServerImHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends BasePacket>> handlerMap;

    private ServerImHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(Command.LOGIN_OUT_REQUEST_COMMAND, LoginoutRequestHandler.INSTANCE);
        handlerMap.put(Command.MESSAGE_REQUEST_COMMAND, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST_COMMAND, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST_COMMAND, GroupMessageRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST_COMMAND, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST_COMMAND, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_REQUEST_COMMAND, ListGroupRequestHandler.INSTANCE);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BasePacket msg) throws Exception {
        SimpleChannelInboundHandler handler = handlerMap.get(msg.getCommand());
        if (handler != null) {
            handler.channelRead(ctx, msg);
        }
    }
}
