package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.JoinGroupRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class JoinGroupConsoleCommand implements ConsoleCommand{
    @Override
    public String getAlias() {
        return "join_group";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入你需要加入的群组：");
        long groupId = Long.parseLong(scanner.next());

        JoinGroupRequest request = new JoinGroupRequest();
        request.setGroupId(groupId);
        channel.writeAndFlush(request);
    }
}
