package psp.esteban.sockets.practica1.Ejercicio1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server {

	private static int PORT=9090;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		 
		 boolean listening = true;
		 
		 try (ServerSocket serverSocket = new ServerSocket(PORT)) { 
	        	
	        	
	        	while (listening) {
	            	
	            	executorService.execute(new ServerThread(serverSocket.accept()));
	            	
	       
		        }
	        
		    } catch (IOException e) {
	            System.err.println("Could not listen on port " + PORT);
	            System.exit(-1);
	            
	        
	        }finally {
	        	executorService.shutdown();
			}
		
		
		
	}

}
