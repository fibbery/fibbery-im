package com.fibbery.im.server.handler;

import com.fibbery.im.protocol.Session;
import com.fibbery.im.protocol.request.ListGroupRequest;
import com.fibbery.im.protocol.response.ListGroupResponse;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@ChannelHandler.Sharable
public class ListGroupRequestHandler extends SimpleChannelInboundHandler<ListGroupRequest> {

    public static final ListGroupRequestHandler INSTANCE = new ListGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupRequest msg) throws Exception {
        ChannelGroup group = SessionUtils.getGroup(msg.getGroupId());
        if (group == null) {
            ListGroupResponse response = new ListGroupResponse();
            response.setSuccess(false);
            response.setMessage("群组不存在");
            return;
        }

        if (!group.contains(ctx.channel())) {
            ListGroupResponse response = new ListGroupResponse();
            response.setSuccess(false);
            response.setMessage("该用户不在群组中");
            return;
        }

        List<Session> sessions = group.stream().map(SessionUtils::getSession).collect(Collectors.toList());
        ListGroupResponse response = new ListGroupResponse();
        response.setSuccess(true);
        response.setGroupId(msg.getGroupId());
        response.setUsers(sessions.stream().collect(Collectors.toMap(Session::getUserId, Session::getUserName)));
        group.writeAndFlush(response);
    }
}
