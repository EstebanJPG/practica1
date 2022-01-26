package psp.esteban.sockets.practica1.Ejercicio3;

import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;

public class Server {

    private static final int PORT= 9090;
    private static final String MACHINE = "localhost";

    public static void main(String[] args) {

        try{
            InetSocketAddress socketAddress = new InetSocketAddress(MACHINE,PORT);
            DatagramSocket datagramSocket= new DatagramSocket(socketAddress);

            while (true){

                //Recibimos para saber a quien enviar
                byte[] messageClient = new byte[40];
                DatagramPacket messageRecibed = new DatagramPacket(messageClient, messageClient.length);
                datagramSocket.receive(messageRecibed);

                // Message received: get sender's address add port information
                InetAddress senderAddr = messageRecibed.getAddress();
                int senderPort = messageRecibed.getPort();

                String mensajeClienteRecives = new String(messageClient, 0, messageRecibed.getLength());

                System.out.println(".... MESSAGE FROM ["
                        + senderAddr.getHostAddress()
                        + senderPort + "] RECEIVED: " + ", "
                        + mensajeClienteRecives);

                if (mensajeClienteRecives.contains("hola")){

                    //Enviamos al cliente

                    String messageEnviar="Que pasa maquina como estas?";
                    byte[] message = messageEnviar.getBytes();

                    DatagramPacket datagramPacket = new DatagramPacket(message, message.length, senderAddr, senderPort);
                    //Enviamos el mensagge alos clientes
                    datagramSocket.send(datagramPacket);

                }else{
                    String messageEnviar="No me has dicho hola :(";
                    byte[] message = messageEnviar.getBytes();

                    DatagramPacket datagramPacket = new DatagramPacket(message, message.length, senderAddr, senderPort);
                    //Enviamos el mensagge alos clientes
                    datagramSocket.send(datagramPacket);
                }
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
