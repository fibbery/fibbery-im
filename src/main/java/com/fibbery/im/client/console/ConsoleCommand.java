package com.fibbery.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
