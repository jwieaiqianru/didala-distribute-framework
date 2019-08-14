package com.didala.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Auther: jiangwei
 * @Date: 2019-08-14 15:45
 * @Description:
 */
public class EchoServer {

    private static final int PORT = 8888;
    public static void main(String[] args) throws InterruptedException {

        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(PORT))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(echoServerHandler);
                        }
                    });
            ChannelFuture sync = bootstrap.bind().sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
