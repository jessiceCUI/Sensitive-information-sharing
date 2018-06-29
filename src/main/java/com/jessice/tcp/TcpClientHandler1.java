package com.jessice.tcp;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.SimpleChannelInboundHandler;  
 
  
public class TcpClientHandler1 extends SimpleChannelInboundHandler<Object> implements Callable<String>{  
	private static final Logger LOGGER = LoggerFactory.getLogger(TcpClientHandler1.class);
	
    @Override  
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {  
    	LOGGER.info("client接收到服务器返回的消息:" + msg);  
    }

	@Override
	public String call() throws Exception {
		
		return null;
	}  
}  