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
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入收件人id以及发送内容：");
        long receiverId = Long.parseLong(scanner.next());
        String message = scanner.next();

        MessageRequest request = new MessageRequest();
        request.setReceiverId(receiverId);
        request.setMessage(message);
    }
}
