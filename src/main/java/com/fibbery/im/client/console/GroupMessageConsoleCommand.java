package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.GroupMessageRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class GroupMessageConsoleCommand implements ConsoleCommand {

    @Override
    public String getAlias() {
        return "group_message";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入你想发送的群组id：");
        long groupId = Long.parseLong(scanner.next());
        System.out.print("输入你想发送的信息：");
        String message = scanner.next();

        GroupMessageRequest request = new GroupMessageRequest();
        request.setGroupId(groupId);
        request.setMessage(message);
        channel.writeAndFlush(request);
    }
}
