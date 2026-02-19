package Exercise3;

import java.io.*;
import java.net.*;

public class ClientSqrNumber {
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Dont know about the host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: Localhost");
            System.exit(1);
        }

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while((userInput = stdin.readLine()) != null){
            out.println(userInput);

            if(userInput.equalsIgnoreCase("bye")) break;
            
            System.out.println("Server response: " + in.readLine());
        }

        out.close();
        in.close();
        stdin.close();
        echoSocket.close();
    }
}