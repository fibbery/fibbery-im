package com.fibbery.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public class NettyServer {

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {

                    }
                });

        bind(bootstrap, HOST, PORT);
    }

    private static void bind(ServerBootstrap bootstrap, String host, int port) {
        bootstrap.bind(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("服务器成功启动，主机地址：" + host + ", 端口为：" + port);
            } else {
                bind(bootstrap, host, port + 1);
            }
        });
    }
}
