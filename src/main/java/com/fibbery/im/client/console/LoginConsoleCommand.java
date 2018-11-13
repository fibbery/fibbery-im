package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public String getAlias() {
        return "login";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (SessionUtils.hasLogin(channel)) {
            System.out.println("你已经登录，不需要重新登录！如果需要重新登录，请先logout!");
            return;
        }

        System.out.print("输入登录用户名字：");
        String userName = scanner.next();

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
