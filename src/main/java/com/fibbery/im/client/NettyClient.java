package com.fibbery.im.client;

import com.fibbery.im.client.handler.LoginResponseHandler;
import com.fibbery.im.client.handler.MessageResponseHandler;
import com.fibbery.im.codec.PacketDecoder;
import com.fibbery.im.codec.PacketEncoder;
import com.fibbery.im.protocol.request.MessageRequest;
import com.fibbery.im.utils.LoginUtils;
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
                if (LoginUtils.hasLogin(channel)) {
                    System.out.println("输入信息发送到服务端：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    MessageRequest request = new MessageRequest();
                    request.setVersion((byte) 1);
                    request.setMessage(line);
                    channel.writeAndFlush(request);
                } else {
                    reLogin(channel);
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
