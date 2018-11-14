package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.QuitGroupRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/14
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public String getAlias() {
        return "quit_group";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("请输入需要退出的群id：");
        long groupId = Long.parseLong(scanner.next());

        QuitGroupRequest request = new QuitGroupRequest();
        request.setGroupId(groupId);
        channel.writeAndFlush(request);
    }
}
