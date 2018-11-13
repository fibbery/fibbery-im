package com.fibbery.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public interface ConsoleCommand {

    String getAlias();

    void exec(Scanner scanner, Channel channel);
}
