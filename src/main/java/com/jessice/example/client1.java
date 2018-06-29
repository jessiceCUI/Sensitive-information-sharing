package com.jessice.example;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class client1 {
	public static void main(String[] args) throws IOException {
	/*if(args.length!=1){
			System.out.println("usage");
			System.out.println(args.length);
			return;
		}*/
		/*
		 * 发送数据的数据包
		 */
		DatagramSocket socket=new DatagramSocket(7000);
		byte[] buf=new byte[256];
		InetAddress addr=InetAddress.getByName("localhost");
		DatagramPacket packet=new DatagramPacket(buf, buf.length,addr,4446);
		socket.send(packet);
		
		/*
		 * 接收数据的数据存储
		 */
		packet=new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		String received=new String(packet.getData());
		System.out.println("---"+received);
		socket.close();
		
		
	}

	
}
