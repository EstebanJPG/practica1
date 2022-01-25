package psp.esteban.sockets.practica1.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 9090;

        try (Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            BufferedReader stdIN = new BufferedReader(new InputStreamReader(System.in));
            String fromUser;
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);

                if (fromServer.equals("Bye."))
                    break;

                fromUser = stdIN.readLine();

                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }


        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}
