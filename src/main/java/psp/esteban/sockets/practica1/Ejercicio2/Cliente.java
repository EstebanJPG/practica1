package psp.esteban.sockets.practica1.Ejercicio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 9090;

        try(Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));){

            BufferedReader stdIN = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;
            String fromServer;

            while ((fromServer = in.readLine()) !=null){
                System.out.println(fromServer);
                fromUser = stdIN.readLine();

                if (fromUser.equals("QUIT")){
                    break;
                }

                if (fromUser!=null){
                    out.println(fromUser);
                }

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
