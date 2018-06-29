package com.jessice.udp2;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.Callable;

import org.omg.CORBA.PUBLIC_MEMBER;

public class clientThread1 implements Callable<String>{
	protected DatagramSocket socket=null;
	//服务器指定
	protected int port;
	protected boolean flag=true;
	protected static String recString=null;

	
	public clientThread1(int port) throws SocketException {
		this.port=port;
		socket=new DatagramSocket();
		
	}

	@Override
	public String call() throws Exception {
		try{
			/*
			 * 发送数据
			 */
			//DatagramSocket socket=new DatagramSocket(port);
			String msg="hello";
			
			byte[] buf=msg.getBytes();
			InetAddress addr=InetAddress.getByName("localhost");
			DatagramPacket packet=new DatagramPacket(buf, buf.length,addr,port);
		//	System.out.println(socket.isClosed());
			socket.send(packet);
			
			
			/*
			 * 接收数据的数据存储
			 */
			packet=new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			 recString=new String(packet.getData());
			System.out.println("---"+recString);
			
		}catch (IOException e) {
			e.printStackTrace();
			
		}finally{
			socket.close();
			}
		return recString;
		
	}
			
}
			
	
	
	
	
	

