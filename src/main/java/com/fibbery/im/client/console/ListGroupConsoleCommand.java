package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.ListGroupRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class ListGroupConsoleCommand implements ConsoleCommand{
    @Override
    public String getAlias() {
        return "list_group";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入你需要查询的群组：");
        long groupId = Long.parseLong(scanner.next());

        ListGroupRequest request = new ListGroupRequest();
        request.setGroupId(groupId);
        channel.writeAndFlush(request);
    }
}
