package com.jessice.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class udp1to0Client extends Thread{
	public static void main(String[] args) throws IOException {
		System.out.println("发送端7001启动....");
	//1.建立UDP的Socket服务；使用DatagramSocket
		DatagramSocket ds=new DatagramSocket(7001); //指定发送端端口


		//2.创建数据包；使用DatagramPacket，确定目的地址
		byte [] buf=new String("7001向7000发送udp").getBytes();
		DatagramPacket dp =
		new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"),7000);
		//指定接收端端口


		//3.发送数据包；send()
		ds.send(dp);
		 
		// 4.关闭Socket服务
		ds.close();
		}
	

	@Override
	public void run() {
		System.out.println("发送端7001启动....");
		//1.建立UDP的Socket服务；使用DatagramSocket
		DatagramSocket ds = null;
		try {
			ds = new DatagramSocket(7001);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //指定发送端端口


		//2.创建数据包；使用DatagramPacket，确定目的地址
		byte [] buf=new String("7001向7000发送udp").getBytes();
		DatagramPacket dp = null;
		try {
			dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"),7000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//指定接收端端口


		//3.发送数据包；send()
		try {
			ds.send(dp);
		} catch (IOException e) {

			e.printStackTrace();
		}
		 
		// 4.关闭Socket服务
		ds.close();
	
	}

  
  
	


}
