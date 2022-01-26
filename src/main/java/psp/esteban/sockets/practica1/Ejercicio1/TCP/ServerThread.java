package psp.esteban.sockets.practica1.Ejercicio1.TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;


public class ServerThread implements Runnable {

	  private Socket socket = null;

	  
	  
	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//IO STEAMS CREATION
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
			String meString= "Bienvenido al servidor SERENO , La fecha y data actuales son: "+ LocalDateTime.now();
			bw.write(meString);
			bw.newLine();
			bw.flush();
			
			String inputLine= br.readLine();
			
			if(inputLine!=null) {
				System.out.println(inputLine);
				
				 if (inputLine.equals("Bye"))
					 br.close();
						bw.close();
				
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
