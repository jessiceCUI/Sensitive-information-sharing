package com.jessice.udp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class serverThread extends Thread{
	protected DatagramSocket socket=null;
	//服务器的端口指定
	protected int port;
	protected BufferedReader in=null;
	protected boolean mqueue=true;
	
	public serverThread(String name,int port) throws SocketException{
		super(name);
		this.port=port;
		socket=new DatagramSocket(port);
		try {
			in=new BufferedReader(new FileReader("D:\\myeclipse2015-offline\\workplace\\grpc4\\src\\main\\java\\com\\jessice\\example\\one.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("could not");
			e.printStackTrace();
		}
	
	}
	
	public serverThread(int port) throws SocketException, FileNotFoundException {
		this("serverThread",port);
	}
	
	public void run(){
		while(mqueue){
			try {
				/*
				 * 接收数据
				 */
				byte[] buf=new byte[256];
				DatagramPacket packet=new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received1=new String(packet.getData());
				System.out.println("服务器端口"+port+"  ---->:   "+received1);
			
				String str=null;
				
					/*
					 *发送数据
					 */
					//str=getNextQuotes();
					//buf=str.getBytes();
					if(received1==null)
						{str="false";
						}else{str="ooook";}
					
					byte[] buf1=new byte[256];
					buf1=str.getBytes();
					InetAddress addr=packet.getAddress();
					int port=packet.getPort();
					DatagramPacket packet1=new DatagramPacket(buf1, buf1.length,addr,port);
					socket.send(packet1);
				}
				
			 catch (IOException e) {
				e.printStackTrace();
				mqueue=false;
			}
			
		}
		socket.close();
	}
	
	protected String getNextQuotes() throws IOException{
		String value=null;
		if((value=in.readLine())==null){
			in.close();
			mqueue=false;
			value="no more";
		}
		return value;
	}

}
