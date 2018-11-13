package com.fibbery.im.client.console;

import com.fibbery.im.protocol.request.LoginoutRequest;
import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/13
 */
public class LoginoutConsoleCommand implements ConsoleCommand{

    @Override
    public String getAlias() {
        return "login_out";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (SessionUtils.hasLogin(channel)) {
            LoginoutRequest request = new LoginoutRequest();
            channel.writeAndFlush(request);
        } else {
            System.out.println("用户未登录，请尝试使用login登录");
        }
    }
}
