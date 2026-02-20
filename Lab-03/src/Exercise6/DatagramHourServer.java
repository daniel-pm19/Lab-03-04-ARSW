package Exercise6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramHourServer {

    DatagramSocket socket;
    private static final int PORT = 4445;

    public DatagramHourServer(){
        try{
            socket = new DatagramSocket(PORT);
        } catch (SocketException e){
            Logger.getLogger(DatagramHourServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void startServer(){
        byte[] buf = new byte[256];
        System.out.println("Server started");

        while(!socket.isClosed()){
            try{
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String dString = new Date().toString();

                buf = dString.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);

                socket.send(packet);

            } catch (SocketException e) {
                Logger.getLogger(DatagramHourServer.class.getName()).log(Level.INFO, "Socket closed, Stopping server...", e);
            } catch (IOException e) {
                Logger.getLogger(DatagramHourServer.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        if(socket != null && !socket.isClosed()) socket.close();

        System.out.println("Server closed");
    }

    public static void main(String[] args){
        DatagramHourServer dhs = new DatagramHourServer();
        dhs.startServer();
    }
}
