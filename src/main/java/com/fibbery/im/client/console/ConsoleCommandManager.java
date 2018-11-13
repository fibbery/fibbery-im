package com.fibbery.im.client.console;

import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public class ConsoleCommandManager implements ConsoleCommand {

    public static final ConsoleCommandManager INSTANCE = new ConsoleCommandManager();

    public static final Map<String, ConsoleCommand> consoleCommands = new HashMap<>();

    static {
        consoleCommands.put("login", new LoginConsoleCommand());
        consoleCommands.put("message", new MessageConsoleCommand());
    }


    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入指令：");
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommands.get(command);
        if (consoleCommand != null) {
            boolean notRequireLogin = consoleCommand instanceof LoginConsoleCommand;

            if (!notRequireLogin && !SessionUtils.hasLogin(channel)) {
                System.out.println("用户未登录，请使用login登录");
            } else {
                consoleCommand.exec(scanner, channel);
            }
        }
        exec(scanner, channel);
    }

    private ConsoleCommandManager() {

    }
}
