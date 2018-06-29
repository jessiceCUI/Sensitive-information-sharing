package com.jessice.tcp;

import java.util.concurrent.Callable;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class clientTcpThread implements Callable<String>{
	private static final Logger LOGGER = LoggerFactory.getLogger(TcpClient.class);
   //服务器的ip和端口
	public static String HOST ;  
    public static int PORT ;  
  
    public static Bootstrap bootstrap;  
    public static Channel channel;  
    
    public clientTcpThread(String host,int port) {
		this.HOST=host;
		this.PORT=port;
		bootstrap = getBootstrap();
		channel = getChannel(host, port);
	}
    /** 
     * 初始化Bootstrap 
     */  
    public static final Bootstrap getBootstrap() {  
        EventLoopGroup group = new NioEventLoopGroup();  
        Bootstrap b = new Bootstrap();  
        b.group(group).channel(NioSocketChannel.class);  
        b.handler(new ChannelInitializer<Channel>() {  
            @Override  
            protected void initChannel(Channel ch) throws Exception {  
                ChannelPipeline pipeline = ch.pipeline();  
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));  
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));  
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));  
                pipeline.addLast("handler", new TcpClientHandler());  
            }  
        });  
        b.option(ChannelOption.SO_KEEPALIVE, true);  
        return b;  
    }  
  
    public static final Channel getChannel(String host, int port) {  
        Channel channel = null;  
        try {  
            channel = bootstrap.connect(host, port).sync().channel();  
        } catch (Exception e) {  
        	LOGGER.error("连接Server(IP{},PORT{})失败", host, port, e);  
            return null;  
        }  
        return channel;  
    }  
  
    public static void sendMsg(String msg) throws Exception {  
        if (channel != null) {  
            channel.writeAndFlush(msg).sync(); 
            LOGGER.debug("发送成功");
        } else {  
        	LOGGER.warn("消息发送失败,连接尚未建立!");  
        }  
    }
	@Override
	public String call() throws Exception {
		try {  
            long t0 = System.nanoTime();  
           clientTcpThread.sendMsg("hello");
            long t1 = System.nanoTime();  
            LOGGER.info("time used:{}", t1 - t0);  
        } catch (Exception e) {  
        	LOGGER.error("main err:", e);  
        }  
		
		
		return null;
	}  
    
  

	
}
