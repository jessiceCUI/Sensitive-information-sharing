package com.jessice.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpServerHandler1 extends SimpleChannelInboundHandler<Object> {  
	private static final Logger LOGGER = LoggerFactory.getLogger(TcpServerHandler1.class);
	
    @Override  
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {  
    	LOGGER.info("SERVER接收到消息:" + msg);  
        ctx.channel().writeAndFlush("server accepted msg:" + msg);  
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
    	LOGGER.warn("exceptionCaught!", cause);  
        ctx.close();  
    }  
}