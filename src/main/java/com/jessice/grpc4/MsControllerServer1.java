package com.jessice.grpc4;

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
import java.util.logging.Logger;

import com.jessice.mscontroller.Matrix;
import com.jessice.mscontroller.MsgShareControllerGrpc;
import com.jessice.mscontroller.RectangleUrl;
import com.jessice.mscontroller.UUrl;
import com.jessice.mscontroller.UrlNote;
import com.jessice.mscontroller.UrlSummary;

public class MsControllerServer1 {
	private static final Logger logger=Logger.getLogger(MsControllerServer1.class.getName());
	private final int port;
	private final Server server;
	
	public MsControllerServer1(ServerBuilder<?> serverBuilder,int port,List<Matrix> matrixs){
		this.port=port;
		server=serverBuilder.addService(new MsControllerService(matrixs)).build();
	}
	
	public MsControllerServer1(int port) throws IOException, InterruptedException, ExecutionException{
		this(ServerBuilder.forPort(port),port,MsControllerUtil.parseRoMatrixs1(8001));
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
		MsControllerServer1 server=new MsControllerServer1(8881);
		server.start();
		server.blockUntilShutdown();
	}
	
	private static class MsControllerService extends MsgShareControllerGrpc.MsgShareControllerImplBase{
		private final Collection<Matrix> matrixs;
		private final ConcurrentHashMap<UUrl, List<UrlNote>> urlnotes=
				new ConcurrentHashMap<UUrl, List<UrlNote>>();
		public MsControllerService(Collection<Matrix> matrixs) {
			this.matrixs=matrixs;
		}
		@Override
		public void getMatrix(UUrl request,
				StreamObserver<Matrix> responseObserver) {
			responseObserver.onNext(checkMatrix(request));
			responseObserver.onCompleted();
		}
		@Override
		public void listMatrixs(RectangleUrl request,
				StreamObserver<Matrix> responseObserver) {
			for (Matrix matrix : matrixs) {
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
	
		private Matrix checkMatrix(UUrl location){
			for(Matrix matrix:matrixs){
				if(matrix.getUrlLocation().getUrl().equals(location.getUrl())
					&& matrix.getUrlLocation().getFileid().equals(location.getFileid())){
					
					//System.out.println("666 "+matrix);
					
					return matrix;
				}
			}
			return Matrix.newBuilder().setMx(0).setMy(0).setUrlLocation(location).build();
		
		}
	}
	

}
