package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.MessageRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public class MessageConsoleCommand implements ConsoleCommand {

    @Override
    public String getAlias() {
        return "message";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入接受人id:");
        long receiverId = Long.parseLong(scanner.next());
        System.out.print("输入发送的内容：");
        String message = scanner.next();

        MessageRequest request = new MessageRequest();
        request.setReceiverId(receiverId);
        request.setMessage(message);
        channel.writeAndFlush(request);
    }
}
