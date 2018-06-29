package com.jessice.example;

import java.net.SocketException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class client2 {
	public static void main(String[] args) throws SocketException, InterruptedException {
		//发送给服务器的4445端口
	//	new clientThread(4445).start();
		//System.out.println("hello");
		//new clientThread(4445).join();
		
		//System.out.println("++"+clientThread.recStr());
		
		ExecutorService exec = Executors.newSingleThreadExecutor();
		
        Future<String> future =  exec.submit(new clientThread1(4446));
        
        try {
            System.out.println(future.get());
        } catch (Exception e) {

        } finally {
        	exec.shutdown();
        }
        

        ExecutorService exec1 = Executors.newSingleThreadExecutor();
        Future<String> future1=  exec1.submit(new clientThread1(4445));
        
        try {
            System.out.println(future1.get());
        } catch (Exception e) {

        } finally {
        	exec1.shutdown();
        }
	}

}
