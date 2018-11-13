package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.CreateGroupRequest;
import com.google.common.collect.Lists;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author fibbery
 * @date 2018/11/13
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    @Override
    public String getAlias() {
        return "create_group";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入需要组群的人员id，用,分割：");
        String idString = scanner.next();
        List<Long> userIds = Lists.newArrayList(idString.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());

        CreateGroupRequest request = new CreateGroupRequest();
        request.setUserIds(userIds);
        channel.writeAndFlush(request);
    }
}
