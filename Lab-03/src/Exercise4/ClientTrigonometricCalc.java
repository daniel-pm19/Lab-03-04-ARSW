package Exercise4;

import java.io.*;
import java.net.*;

public class ClientTrigonometricCalc {

    private static final int PORT = 35000;
    public static void main(String[] args) throws IOException{

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", PORT);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Dont know about the host");
            System.exit(1);
        } catch (IOException e){
            System.err.println("Couldn't get I/O for the connection to: Localhost");
            System.exit(1);
        }

        System.out.println("Trigonometric Functions Calculator");
        System.out.println();
        System.out.println("Your default function is sin");
        System.out.println("To change it, just type fun:function");
        System.out.println("To finish the connection type :end ");
        System.out.println();


        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        while((userInput = stdin.readLine()) != null){
            out.println(userInput);

            if(userInput.equalsIgnoreCase("end")) break;

            System.out.println(in.readLine());
        }

        System.out.println("Connection ended");

        out.close();
        in.close();
        stdin.close();
        echoSocket.close();
    }

    
}