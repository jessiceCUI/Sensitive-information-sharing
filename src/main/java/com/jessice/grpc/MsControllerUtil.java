package com.jessice.grpc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.SocketException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.xml.sax.InputSource;


import com.google.protobuf.util.JsonFormat;

import com.jessice.entity.routingMatrix;
import com.jessice.example.client;
import com.jessice.mscontroller.Matrix;
import com.jessice.mscontroller.MatrixDatabase;
import com.jessice.mscontroller.UUrl;
import com.jessice.tcp.clientTcpThread;
import com.jessice.tcp.serverTcpThread;
import com.jessice.udp.udp0to1Server;
import com.jessice.udp.udp1to0Client;
import com.jessice.udp.udp1to0Server;
import com.jessice.udp.udp2to0Client;
import com.jessice.udp.udp2to0Server;
import com.jessice.udp2.clientThread1;
import com.jessice.udp2.serverThread;

public class MsControllerUtil {
	private static final double COOR_FACTOR=1e7;
	/*
	 * 指定文件的位置处，获取其文件路由
	 */
	public static String getUrl(UUrl location) {
		return location.getUrl();
		
	}
	/*
	 * 指定文件的位置处，获取其文件名
	 */
	public static String getFileid(UUrl location) {
		return location.getFileid();
	}
	/*
	 * 获取默认拓扑图
	 */
	public static URL getDefaultFeaturesFile() {
		return MsControllerServer.class.getResource("msgShare_contr.json");
	}
	/*
	 * 解析json文件
	 */
	public static List<Matrix> parseMatrixs(URL file) throws IOException{
		InputStream input=file.openStream();
		try {
			Reader reader=new InputStreamReader(input,Charset.forName("UTF-8"));
			try {
				MatrixDatabase.Builder database=MatrixDatabase.newBuilder();
				JsonFormat.parser().merge(reader,database);
				return database.getMatrixList();
				/*
				 * JsonFormat：protobuf消息和json文件互相转换的实体类
				 *Parser类： 解析器将JSON解析为protobuf消息。
				 *parser()使用默认配置创建一个{@link Parser}。
				 *merge（）：从JSON解析成protobuf消息。
				 */
			} finally
			{reader.close();}
			
		} finally
		{input.close();}
	}
	
	
	/*
	 * 获取路由的矩阵
	 */
	
	public static String getRecStr(int serverPort) throws SocketException, FileNotFoundException, InterruptedException, ExecutionException{
		String recMsg=null;
		new serverThread(serverPort).start();
		
        ExecutorService exec1 = Executors.newSingleThreadExecutor();
        Future<String> future1=  exec1.submit(new clientThread1(serverPort));
        recMsg=future1.get();
        try {
            System.out.println(recMsg);   
        } catch (Exception e) {
        } finally {
        	exec1.shutdown();
        }
		return recMsg;
	}
	//tcp获取的返回信息
	public static String getRecStr(String ip,int serverPort) throws InterruptedException, ExecutionException{
		String recMsg=null;
		new serverTcpThread(ip,serverPort).start();
		ExecutorService exec1 = Executors.newSingleThreadExecutor();
	    Future<String> future1=exec1.submit(new clientTcpThread(ip,serverPort));
	        recMsg=future1.get();
	        try {
	            System.out.println(recMsg);   
	        } catch (Exception e) {
	        } finally {
	        	exec1.shutdown();
	        }
		
		return recMsg;
	}
	
	
    //根据返回客户端的数据判断矩阵
	public static List<Matrix> parseRoMatrixs(int port) throws IOException, InterruptedException, ExecutionException{
		List<Matrix> list=new ArrayList<Matrix>();
		if(getRecStr(7001).equals("ooook")){
			Matrix	matrix1=Matrix.newBuilder().setMx(0).setMy(1).build();
			list.add(matrix1);
		}
		if(getRecStr(7002).equals("ooook")){
			Matrix matrix2=Matrix.newBuilder().setMx(0).setMy(2).build();
			list.add(matrix2);
		}
		return list;
		
	}
	
	public static List<Matrix> parseRoMatrixs1(int port) throws SocketException, FileNotFoundException, InterruptedException, ExecutionException{
		List<Matrix> list=new ArrayList<Matrix>();
		if(getRecStr(8000).equals("ooook")){
			Matrix	matrix1=Matrix.newBuilder().setMx(1).setMy(0).build();
			list.add(matrix1);
		}
		if(getRecStr(8002).equals("ooook")){
			Matrix matrix2=Matrix.newBuilder().setMx(1).setMy(2).build();
			list.add(matrix2);
		}
		return list;
	}
	
	/*public static List<Matrix> parseRoMatrixs1(int port) throws IOException{
		List<Matrix> list1=new ArrayList<Matrix>();
		if(udp0to1Server.udpMsg0to1().equals("8000向8001发送udp")){
			Matrix matrix3=Matrix.newBuilder().setMx(0).setMy(1).build();
			list1.add(matrix3);
		}
		return list1;
	}*/
	
	public static boolean exists(Matrix matrix){
		return matrix!=null ;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
	
		List<Matrix> lists=parseRoMatrixs(8004);
		System.out.println(lists);
		for(Matrix attribute : lists) {
			  System.out.println(attribute);
			}
		/*String str=getRecStr(4445);
		System.out.println(!str.equals("ooook:"));
		System.out.println("111"+str.equals("ooook"));
		System.out.println(str);
		*/
	}
}
