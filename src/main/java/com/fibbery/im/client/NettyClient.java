package com.fibbery.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                .handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) {
            }
        });

        connect(bootstrap, HOST, PORT, MAX_RETRIES);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retries) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
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
}
