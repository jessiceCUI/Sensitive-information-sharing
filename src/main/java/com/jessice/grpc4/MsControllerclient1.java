package com.jessice.grpc4;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.IOException;
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

public class MsControllerclient1 {
	private static final Logger logger=Logger.getLogger(MsControllerclient1.class.getName());
	private final ManagedChannel channel;
	private final MsgShareControllerBlockingStub blockingStub;
	private final MsgShareControllerStub asyncStub;
	
	private static int[][] routeMatrix=new int[4][4];
	
	private Random random=new Random();
	private TestHelper testHelper;
	
	public MsControllerclient1(String host,int port) {
		this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
	}
	
	public MsControllerclient1(ManagedChannelBuilder<?> channelBuilder) {
		channel=channelBuilder.build();
		blockingStub=MsgShareControllerGrpc.newBlockingStub(channel);
		asyncStub=MsgShareControllerGrpc.newStub(channel);	
	}
	
	public void shutdown() throws InterruptedException{
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	public void getMatrix(String url,String filename){
		info("@@@@ getmatrix: url={0} filename={1}",url,filename);
		
		UUrl request=UUrl.newBuilder().setUrl(url).setFileid(filename).build();
		System.out.println(request);
		Matrix matrix;
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
			}return;
		}
		if(MsControllerUtil.exists(matrix)){
			
			info("found matrix called at '['{0},{1}']' at {2}, {3}",
					matrix.getMx(),
					matrix.getMy(),
					MsControllerUtil.getUrl(matrix.getUrlLocation()),
					//matrix.getUrlLocation().getUrl(),
					MsControllerUtil.getFileid(matrix.getUrlLocation()));
		}else{
			info("found no matrix at {0},{1}",
					MsControllerUtil.getUrl(matrix.getUrlLocation()),
					MsControllerUtil.getFileid(matrix.getUrlLocation()));
		}
	}
	
	public void listMatrixs(RectangleUrl request){
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		List<Matrix> matrixs;
		try {
			matrixs=MsControllerUtil.parseMatrixs(MsControllerUtil.getDefaultFeaturesFile());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		MsControllerclient1 client=new MsControllerclient1("localhost",8880);
		try {
			client.getMatrix("http://192.168.0.1", "no_4");
			
			routeMatrix[0][0]=1;
			
		} 
		finally{
			client.shutdown();
		}
	
	System.out.println(routeMatrix[0][0]);
	}

	
	
	
	
	
	
	private void Warnings(String string, String string2) {
		logger.log(Level.WARNING,string,string2);
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
