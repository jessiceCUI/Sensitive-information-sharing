package com.jessice.grpc4;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.mockito.internal.debugging.WarningsCollector;

import com.google.api.Logging;
import com.google.common.annotations.VisibleForTesting;
import com.google.protobuf.Message;
import com.jessice.mscontroller.Matrix;
import com.jessice.mscontroller.MsgShareControllerGrpc;
import com.jessice.mscontroller.RectangleUrl;
import com.jessice.mscontroller.MsgShareControllerGrpc.MsgShareControllerBlockingStub;
import com.jessice.mscontroller.MsgShareControllerGrpc.MsgShareControllerFutureStub;
import com.jessice.mscontroller.MsgShareControllerGrpc.MsgShareControllerStub;
import com.jessice.mscontroller.UUrl;

public class MsControllerclient {
	private static final Logger logger=Logger.getLogger(MsControllerclient.class.getName());
	private final ManagedChannel channel;
	private final MsgShareControllerBlockingStub blockingStub;
	private final MsgShareControllerStub asyncStub;
	
	private static int[][] routeMatrix=new int[4][4];
	
	private Random random=new Random();
	private TestHelper testHelper;
	
	public MsControllerclient(String host,int port) {
		this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
	}
	
	public MsControllerclient(ManagedChannelBuilder<?> channelBuilder) {
		channel=channelBuilder.build();
		blockingStub=MsgShareControllerGrpc.newBlockingStub(channel);
		asyncStub=MsgShareControllerGrpc.newStub(channel);	
	}
	
	public void shutdown() throws InterruptedException{
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	public Matrix getMatrix(String url,String filename){
		info("@@@@ getmatrix: url={0} filename={1}",url,filename);
		
		UUrl request=UUrl.newBuilder().setUrl(url).setFileid(filename).build();
		System.out.println(request);
		Matrix matrix = null;
		try {
			matrix=blockingStub.getMatrix(request);
			System.out.println(matrix);
	
			if(testHelper!=null){
				testHelper.onMessage(matrix);
			}
		} catch (Exception e) {
			Warnings("RPC failes:{0}","error");
			if(testHelper!=null){
				testHelper.onRpcError(e);
			}
			
			return matrix;
		}
		if(MsControllerUtil.exists(matrix)){
			
			info("found matrix called at '['{0},{1}']' at {2}, {3}",
					matrix.getMx(),
					matrix.getMy(),
					MsControllerUtil.getUrl(matrix.getUrlLocation()),
					//matrix.getUrlLocation().getUrl(),
					MsControllerUtil.getFileid(matrix.getUrlLocation()));
			
			routeMatrix[matrix.getMx()][matrix.getMy()]=1;
		
		}else{
			info("found no matrix at {0},{1}",
					MsControllerUtil.getUrl(matrix.getUrlLocation()),
					MsControllerUtil.getFileid(matrix.getUrlLocation()));
		}
		return matrix;
	}
	
	public void listMatrixs(String url1,String id1,String url2,String id2){
		
		 info("*** ListFeatures: url1={0} id1={1} url2={2} id2={3}",url1,url2,id1,id2 );

		 RectangleUrl request1 =
				 RectangleUrl.newBuilder()  //设置矩形的两个对角上的分别的经度和纬度
			            .setLo(UUrl.newBuilder().setUrl(url1).setFileid(id1).build())
			            .setHi(UUrl.newBuilder().setUrl(url2).setFileid(id2).build()).build();
			    Iterator<Matrix> matrixs;
			    try {
			    	matrixs = blockingStub.listMatrixs(request1);
			      System.out.println("aaaa"+matrixs);
			      for (int i = 1; matrixs.hasNext(); i++) {
			    	  Matrix matrix = matrixs.next();
			    	 // info("Result #" + i + ": {0}", matrix);
			    	  System.out.println("????"+matrix);
			  
			    	  routeMatrix[matrix.getMx()][matrix.getMy()]=1;
			    	 
			        
			        if (testHelper != null) {
			          testHelper.onMessage(matrix);
			          System.out.println("----"+matrix);
			        }
			      }
			     
			    } catch (StatusRuntimeException e) {
			      warning("RPC failed: {0}", e.getStatus());
			      if (testHelper != null) {
			        testHelper.onRpcError(e);
			      }
			    }
			  }

		
	
	
	public static void main(String[] args) throws InterruptedException {
		List<Matrix> matrixs;
		try {
			matrixs=MsControllerUtil.parseMatrixs(MsControllerUtil.getDefaultFeaturesFile());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		MsControllerclient client=new MsControllerclient("localhost",8880);
		try {
			//client.getMatrix("http://192.168.0.1", "no_4");
			client.listMatrixs("http://192.168.0.1", "no_4", "http://192.168.0.1", "no_3");
		} 
		finally{
			client.shutdown();
		}

		MsControllerclient client1=new MsControllerclient("localhost",8881);
		try {
			client1.listMatrixs("http://192.168.0.1", "no_7", "http://192.168.0.1", "no_8");
		} finally{
			client1.shutdown();
		}
		
		/*MsControllerclient client2=new MsControllerclient("localhost",8882);
		try {
			client2.getMatrix("http://192.168.0.1", "no_3");
		} 
		finally{
			client2.shutdown();
		}
		
		MsControllerclient client3=new MsControllerclient("localhost",8883);
		try {
			client3.getMatrix("http://192.168.0.1", "no_3");
		} 
		finally{
			client3.shutdown();
		}
	*/
		for(int m=0;m<routeMatrix.length;m++){    //注意a.length，外层是遍历一维数组，二维数组的遍历就是在一维数组的基础上
            for(int j=0;j<routeMatrix[m].length;j++){    //里层是遍历一维数组的元素
           	
           	 System.out.print(routeMatrix[m][j]+"  "); //将数组的元素输出
   	        
            }
            System.out.println();
   }
	

	}

	
	
	
	
	
	
	private void Warnings(String string, String string2) {
		logger.log(Level.WARNING,string,string2);
	}
	
	private void warning(String msg, Object... params) {
	    logger.log(Level.WARNING, msg, params);
	  }

	private void Warning(String msg,Object...params){
		logger.log(Level.WARNING,msg,params);
	}
	
	private void info(String msg,Object...params) {
		logger.log(Level.INFO,msg,params);
		/*
		 * Log():用一组对象参数记录消息。如果记录器当前对给定消息级别启用，则创建相应的LogRecord并将其转发给所有注册的输出处理程序对象。
		 * 
		 */
		
	}

	
	@VisibleForTesting
	  interface TestHelper {
	    /**
	     * Used for verify/inspect message received from server.
	     */
	    void onMessage(Message message);

	    /**
	     * Used for verify/inspect error received from server.
	     */
	    void onRpcError(Throwable exception);
	  }

	  @VisibleForTesting
	  void setTestHelper(TestHelper testHelper) {
	    this.testHelper = testHelper;
	  }
}
