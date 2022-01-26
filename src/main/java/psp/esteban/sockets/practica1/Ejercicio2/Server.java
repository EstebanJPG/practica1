package psp.esteban.sockets.practica1.Ejercicio2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int PORT=9090;

    public static void main(String[] args) {

        int count =1;
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        boolean listening = true;

        try(ServerSocket serverSocket = new ServerSocket(PORT)){

            File file = new File("log.txt");

            if (!file.exists()) file.createNewFile();

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);

            bufferedWriter.write("ClientNumber" + "\t" + "LocalPort" + "\t"+ "ClientIP" + "\t" + "ClientPort" + "\t"+ "NumberOfCapitalizations");
            bufferedWriter.flush();

            while(true){
                count++;
                executorService.execute(new ServerThread(serverSocket.accept(), fileWriter, "Client"+count));

            }


        } catch (IOException e) {
            System.err.println("Could not listen on port " + PORT);
            System.exit(-1);
        }finally {
            executorService.shutdown();
        }

    }

}
