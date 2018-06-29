package com.jessice.tcp;

import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.SimpleChannelInboundHandler;  
  
public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {  
	private static final Logger LOGGER = LoggerFactory.getLogger(TcpClientHandler.class);
	
    @Override  
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {  
    	
    	LOGGER.info("client接收到服务器返回的消息:" + msg);  
    }
    
	  public static String getMsg(){
		  String recMsg="ok";
	  	System.out.println(recMsg);
	  	
		return recMsg;
	  }

}  