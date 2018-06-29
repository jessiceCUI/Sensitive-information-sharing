package com.jessice.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerServiceDefinition;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import com.jessice.entity.routingMatrix;
import com.jessice.example.clientThread;
import com.jessice.example.clientThread1;
import com.jessice.example.serverThread;

import com.jessice.mscontroller.Matrix;
import com.jessice.mscontroller.MsgShareControllerGrpc;
import com.jessice.mscontroller.RectangleUrl;
import com.jessice.mscontroller.UUrl;
import com.jessice.mscontroller.UrlNote;
import com.jessice.mscontroller.UrlSummary;


public class MsControllerServer {
	private static final Logger logger=Logger.getLogger(MsControllerServer.class.getName());
	private final int port;
	private final Server server;
	
	public MsControllerServer(ServerBuilder<?> serverBuilder,int port,List<Matrix> matrixs){
		this.port=port;
		server=serverBuilder.addService(new MsControllerService(matrixs)).build();
	}
	
	public MsControllerServer(int port) throws IOException, InterruptedException, ExecutionException{
		this(ServerBuilder.forPort(port),port,MsControllerUtil.parseRoMatrixs(7000));
	}
	
	public void start() throws IOException{
		server.start();
		logger.info("server started,listening on  "+port);
	}
	
	private void blockUntilShutdown() throws InterruptedException{
		if(server!=null){
			server.awaitTermination();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {

		MsControllerServer server=new MsControllerServer(8880);
	
		server.start();
		
		server.blockUntilShutdown();
		
		
	}
	
	private static class MsControllerService extends MsgShareControllerGrpc.MsgShareControllerImplBase{
		//private final Collection<Matrix> matrixs;
		private final ConcurrentHashMap<UUrl, List<UrlNote>> urlnotes=
				new ConcurrentHashMap<UUrl, List<UrlNote>>();
		/*
		 * 路由矩阵信息
		 */
		private final List<Matrix> routingmatrix;
		
		 public MsControllerService(List<Matrix> matrixs) {
			this.routingmatrix=matrixs;
			
		}
		
		 @Override
		public void getMatrix(UUrl request,
				StreamObserver<Matrix> responseObserver) {
			 
			try {
				responseObserver.onNext(checkMatrix(request));
			} catch (IOException e) {
				e.printStackTrace();
			}
			responseObserver.onCompleted();
			 }
		
	
		
		
		@Override
		public void listMatrixs(RectangleUrl request,
				StreamObserver<Matrix> responseObserver) {
			
			for (Matrix matrix : routingmatrix) {
		        if (!MsControllerUtil.exists(matrix)) {
		          continue;
		        }  
		      responseObserver.onNext(matrix);
		     
			}
			 responseObserver.onCompleted();
		    }
			
		@Override
		public StreamObserver<UUrl> recordUrl(
				StreamObserver<UrlSummary> responseObserver) {
			return super.recordUrl(responseObserver);
		}
		@Override
		public StreamObserver<UrlNote> urlChat(
				StreamObserver<UrlNote> responseObserver) {
			return super.urlChat(responseObserver);
		}
	
		/*private Matrix checkMatrix(UUrl location){
			for(Matrix matrix:matrixs){
				if(matrix.getUrlLocation().getUrl().equals(location.getUrl())
					&& matrix.getUrlLocation().getFileid().equals(location.getFileid())){
					
				//	System.out.println("666 "+matrix);
					
					return matrix;
				}
			}
			return Matrix.newBuilder().setMx(0).setMy(0).setUrlLocation(location).build();
			
		}*/
		
		private Matrix checkMatrix(UUrl location) throws IOException{
			for(Matrix matrix:routingmatrix){
				return matrix;
			}
			return Matrix.newBuilder().setMx(0).setMy(0).build();
			
			
		}
		
	}
	

}
