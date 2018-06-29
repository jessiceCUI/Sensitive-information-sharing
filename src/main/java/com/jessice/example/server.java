package com.jessice.example;

import java.io.FileNotFoundException;
import java.net.SocketException;

public class server {
	public static void main(String[] args) throws SocketException, FileNotFoundException {
		//开启服务器的4445端口
		new serverThread(4445).start();
		new serverThread(4446).start();
	}
}
