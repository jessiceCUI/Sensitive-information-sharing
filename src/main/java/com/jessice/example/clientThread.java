package com.jessice.example;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.omg.CORBA.PUBLIC_MEMBER;

public class clientThread extends Thread{
	protected DatagramSocket socket=null;
	//服务器指定
	protected int port;
	protected boolean flag=true;
	protected static String recString=null;

	
	public clientThread(int port) throws SocketException {
		this.port=port;
		socket=new DatagramSocket();
		
	}

	@Override
	public void run() {
		
		
			try{
				/*
				 * 发送数据
				 */
				//DatagramSocket socket=new DatagramSocket(port);
				String msg="hello:  ";
				
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
				String recString=new String(packet.getData());
				System.out.println("---"+recString);
				
			}catch (IOException e) {
				
				e.printStackTrace();
				flag=false;
				socket.close();
			}finally{
				socket.close();}
			
			
		}
		
		

	
	public static String recStr(){
		
	//	return recString;
		return "nihao";
	}
			
}
			
	
	
	
	
	

