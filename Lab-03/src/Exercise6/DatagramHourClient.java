package Exercise6;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DatagramHourClient {

    private static final int PORT = 4445;
    private static final String SERVER_HOST = "127.0.0.1";
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            byte[] buf = new byte[256];
            socket.setSoTimeout(2000);
            InetAddress address = InetAddress.getByName(SERVER_HOST);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if(socket != null && !socket.isClosed()) socket.close();
            }));

            byte[] recvBuf = new byte[256];
            String lastKnown = "no time yet";

            while(true){

                long loopStart = System.currentTimeMillis();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);

                try{
                    socket.send(packet);
                } catch (IOException e ){
                    Logger.getLogger(DatagramHourClient.class.getName()).log(Level.WARNING, "Failed to send request", e);
                }

                DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
                try{
                    socket.receive(recvPacket);
                    String received = new String(recvPacket.getData(), 0, recvPacket.getLength());
                    lastKnown = received;
                } catch (SocketTimeoutException e){

                } catch (IOException e){
                    Logger.getLogger(DatagramHourClient.class.getName()).log(Level.WARNING, "Receive failed", e);
                }

                System.out.println("Date: " + lastKnown);

                long elapsed = System.currentTimeMillis() - loopStart;
                long sleep = 5000 - elapsed;
                if(sleep > 0){
                    try{
                        Thread.sleep(sleep);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
            
        } catch (SocketException e) {
            Logger.getLogger(DatagramHourClient.class.getName()).log(Level.SEVERE, null, e);
        } catch (UnknownHostException e) {
            Logger.getLogger(DatagramHourClient.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    
}
