package psp.esteban.sockets.practica1.Ejercicio3;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    private static final int PORT= 9090;
    private static final String MACHINE = "localhost";

    public static void main(String[] args) {

        try{
            DatagramSocket datagramSocket= new DatagramSocket();
            System.out.println("Escribe el mensaje a enviar");
            Scanner sc = new Scanner(System.in);
            //Sendind mensage al server
            String messageToServer = sc.next();
            DatagramPacket datagramPacket = new DatagramPacket(messageToServer.getBytes(), messageToServer.getBytes().length, InetAddress.getByName(MACHINE), PORT);
            datagramSocket.send(datagramPacket);


            //Recibimos el mensaje del Server
            byte[] messageServer = new byte[100];
            datagramPacket= new DatagramPacket(messageServer, messageServer.length);
            datagramSocket.receive(datagramPacket);

            System.out.println("Mensage recibido server: " + new String(messageServer, 0, datagramPacket.getLength()));
            datagramSocket.close();



        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
