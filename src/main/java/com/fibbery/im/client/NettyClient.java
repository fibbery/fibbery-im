package com.fibbery.im.client;

import com.fibbery.im.client.handler.LoginResponseHandler;
import com.fibbery.im.client.handler.MessageResponseHandler;
import com.fibbery.im.codec.PacketDecoder;
import com.fibbery.im.codec.PacketEncoder;
import com.fibbery.im.codec.ProtocolFilter;
import com.fibbery.im.protocol.request.LoginRequest;
import com.fibbery.im.protocol.request.MessageRequest;
import com.fibbery.im.utils.SessionUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public class NettyClient {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    private static final int MAX_RETRIES = 5;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();

        bootstrap.group(boss).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new ProtocolFilter());
                ch.pipeline().addLast(new PacketEncoder());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginResponseHandler());
                ch.pipeline().addLast(new MessageResponseHandler());
            }
        });

        connect(bootstrap, HOST, PORT, MAX_RETRIES);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retries) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retries == 0) {
                System.out.println("连接失败，无重试次数");
            } else {
                int order = (MAX_RETRIES - retries) + 1;
                int delay = 1 << order;
                System.out.println("客户端将会在" + delay + "秒之后第" + order + "次尝试连接服务器");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retries - 1),
                        delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                //已经登录的情况进行发消息操作
                if (SessionUtils.hasLogin(channel)) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("发送用户id:");
                    long userId = Long.parseLong(scanner.nextLine());
                    System.out.println("发送内容:");
                    String message = scanner.nextLine();

                    MessageRequest request = new MessageRequest();
                    request.setMessage(message);
                    request.setReceiverId(userId);
                    channel.writeAndFlush(request);
                } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("输入用户id:");
                    long userId = Long.parseLong(scanner.nextLine());

                    LoginRequest request = new LoginRequest();
                    request.setUserId(userId);
                    request.setPassword("pwd");
                    channel.writeAndFlush(request);

                    //wait for login
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    /**
     * 重新登录
     * @param channel
     */
    private static void reLogin(Channel channel) {

    }
}
