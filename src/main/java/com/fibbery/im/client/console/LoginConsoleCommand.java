package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.LoginRequest;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入登录用户名字：");
        String userName = scanner.nextLine();

        LoginRequest request = new LoginRequest();
        request.setUserName(userName);
        request.setPassword("pwd");
        channel.writeAndFlush(request);
        //wait to login
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
        }
    }
}
