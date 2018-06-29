package com.netty.work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netty.handler.FileUploadServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;


public class FileUploadServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServer.class);
	public static final int PORT = 7766;
    public void bind(int PORT) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(
            		new ChannelInitializer<Channel>() {

                @Override
                protected void initChannel(Channel ch) throws Exception {
                	System.out.println("有客户端连接上来::"+ch.localAddress().toString());
                    ch.pipeline().addLast(new ObjectEncoder());
                    ch.pipeline().addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(null))); 
                    ch.pipeline().addLast(new FileUploadServerHandler());
                }
            });
            ChannelFuture f = b.bind(PORT).sync();
            System.out.println("file server 等待连接");
            LOGGER.debug("服务端口为:" + PORT);
            f.channel().closeFuture().sync();
            System.out.println("file end");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port =  PORT;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try {
            new FileUploadServer().bind(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   // public static final int FILE_PORT = 9991;
}
