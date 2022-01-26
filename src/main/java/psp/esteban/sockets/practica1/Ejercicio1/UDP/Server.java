package psp.esteban.sockets.practica1.Ejercicio1.UDP;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Server {

    private static final int PORT= 9090;
    private static final String MACHINE = "localhost";

    public static void main(String[] args) throws IOException {

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

               System.out.println(".... MESSAGE FROM ["
                        + senderAddr.getHostAddress()
                        + senderPort + "] RECEIVED: " + ", "
                        + new String(messageClient, 0, messageRecibed.getLength()));


               //Enviamos al cliente

                String messageEnviar="BIENVENIDO AL SERVIDOR SERENO. La fecha y data actual son: " + LocalDateTime.now();
                byte[] message = messageEnviar.getBytes();

                DatagramPacket datagramPacket = new DatagramPacket(message, message.length, senderAddr, senderPort);
                //Enviamos el mensagge alos clientes
                datagramSocket.send(datagramPacket);

            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
