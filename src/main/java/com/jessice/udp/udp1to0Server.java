package com.jessice.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class udp1to0Server {

	/*public static void main(String[] args) throws IOException {
		System.out.println("接收端7000启动....");

		//1.建立UDP的Socket服务；使用DatagramSocket
		DatagramSocket ds=new DatagramSocket(7000);
		//2.创建一个数据包，用于存储接收到的数据
		byte [] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf, buf.length);


		//3.receive数据
		ds.receive(dp);


		//4.通过数据包对象的方法，解析其中的数据，比如地址，端口，内容等
		String ip=dp.getAddress().getHostAddress();
		int port=dp.getPort();
		String text=new String (dp.getData(),0,dp.getLength());


		//5.关闭Socket服务
		System.out.println(ip+"::"+port+"::"+text);
		ds.close();
		}*/
	
	 public static String udpMsg1to0() throws IOException{
		System.out.println("接收端7000--1启动....");

		//1.建立UDP的Socket服务；使用DatagramSocket
		DatagramSocket ds=new DatagramSocket(7000);
		//2.创建一个数据包，用于存储接收到的数据
		byte [] buf=new byte[1024];
		DatagramPacket dp=new DatagramPacket(buf, buf.length);


		//3.receive数据
		ds.receive(dp);


		//4.通过数据包对象的方法，解析其中的数据，比如地址，端口，内容等
		String ip=dp.getAddress().getHostAddress();
		int port=dp.getPort();
		String text=new String (dp.getData(),0,dp.getLength());


		//5.关闭Socket服务
		System.out.println(ip+"::"+port+"::"+text);
		ds.close();
		
		return text;
		
	}
		

}
