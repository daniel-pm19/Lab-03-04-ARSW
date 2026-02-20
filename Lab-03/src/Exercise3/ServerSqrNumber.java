package Exercise3;

import java.net.*;
import java.io.*;

public class ServerSqrNumber {

    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e){
            System.err.println("Cound not listen on port: 35000");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e){
            System.err.println("Accept failed");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String inputLine;

        while((inputLine = in.readLine()) != null){
            if(inputLine.equalsIgnoreCase("bye")) break;
            try {
                System.out.println("User message: " + inputLine);
                int number = Integer.parseInt(inputLine);
                System.out.println("Server response: " + (number*number));
                out.println("The squared number is " + (number*number));
            } catch (NumberFormatException e){
                out.println("Error: Invalid number");
            }
        }
        
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    
}
