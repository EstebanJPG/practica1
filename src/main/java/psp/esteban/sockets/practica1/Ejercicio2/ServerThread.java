package psp.esteban.sockets.practica1.Ejercicio2;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Locale;

public class ServerThread implements Runnable{

    private Socket socket = null;
    private FileWriter file;
    private String name;

    public ServerThread(Socket socket, FileWriter file, String name) {
        super();
        this.socket = socket;
        this.file=file;
        this.name=name;
    }

    @Override
    public void run() {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            BufferedWriter bwFile = new BufferedWriter(this.file);
            String meString= "Escribe la palabra en minusculas";
            bw.write(meString);
            bw.newLine();
            bw.flush();

            String inputLine;

            do{
                inputLine= br.readLine();
                String respuesta = "Esta es la palabra que has escrito: "+ inputLine.toUpperCase();
                bw.write(respuesta);
                bw.newLine();
                bw.flush();

                bwFile.write(this.name + "\t\t" + socket.getLocalPort() + "\t\t" + socket.getRemoteSocketAddress() + "\t\t" + socket.getPort() + "\t" + inputLine.length() + "\n");
                bwFile.flush();

            }while (!inputLine.equals("QUIT"));

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
