package com.fibbery.im.client.console;

import com.fibbery.im.utils.SessionUtils;
import io.netty.channel.Channel;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author fibbery
 * @date 2018/11/8
 */
public class ConsoleCommandManager implements ConsoleCommand {

    public static final ConsoleCommandManager INSTANCE = new ConsoleCommandManager();

    public static final Map<String, ConsoleCommand> consoleCommands = new HashMap<>();

    static {
        Reflections reflections = new Reflections("com.fibbery");

        //载入序列化方式
        Set<Class<? extends ConsoleCommand>> clazzes = reflections.getSubTypesOf(ConsoleCommand.class);
        for (Class<? extends ConsoleCommand> clazz : clazzes) {
            boolean isAbstrace = Modifier.isAbstract(clazz.getModifiers());
            if (isAbstrace) {
                continue;
            }
            if (clazz == ConsoleCommandManager.class) {
                continue;
            }
            try {
                ConsoleCommand command = clazz.newInstance();
                consoleCommands.put(command.getAlias(), command);
            } catch (Exception e) {
                System.err.println(clazz.getSimpleName() + "无空构造，不进行录入");
            }
        }
    }


    @Override
    public String getAlias() {
        return "manager";
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
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
