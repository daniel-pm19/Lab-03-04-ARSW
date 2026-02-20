package Exercise4;

import java.net.*;
import java.io.*;

public class ServerTrigonometricCalc {

    private static final int PORT = 35000;
    private static String actualFunction = "fun:sin";
    public static void main(String[] args) throws IOException{

        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e){
            System.err.println("Cound not listen on port: " + PORT);
            System.exit(1);            
        }

        Socket clientSocket = null;

        try{
            clientSocket = serverSocket.accept();
        } catch (IOException e){
            System.err.println("Accept failed");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while(((inputLine) = in.readLine()) != null){
            inputLine = inputLine.trim().toLowerCase();

            if(inputLine.equalsIgnoreCase("end")) break;

            System.out.println("User message: " + inputLine);

            if(inputLine.startsWith("fun:")){

                if(inputLine.equals(actualFunction)){
                    out.println("Function already selected");
                } else {

                    String newOperator = changeOperator(inputLine);
                    
                    if(newOperator != null){
                        actualFunction = newOperator;
                        switch (actualFunction) {
                            case "fun:sin":
                                out.println("Function changed to: Sinus");
                                break;
                            case "fun:cos":
                                out.println("Function changed to: Cosinus");
                                break;
                            case "fun:tan":
                                out.println("Function changed to: Tangent");
                                break;
                            default:
                                break;
                        }
                    } else {
                        out.println("Error: Invalid function");
                    }
                }
                
                continue;

            }

            try {
                Double num = Double.parseDouble(inputLine);

                Double result = calculate(num, actualFunction);

                System.out.println("Server response: " + result);
                out.println("Result: " + result);

            } catch (NumberFormatException e){
                out.println("Error: Invalid number");
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static Double calculate(Double number, String function){
        switch (function) {
            case "fun:sin":
                return Math.sin(number);
            case "fun:cos":
                return Math.cos(number);
            case "fun:tan":
                return Math.tan(number);
            default:
                return Math.sin(number);
        }

    }

    public static String changeOperator(String newOperator){
        switch (newOperator) {
            case "fun:sin":
                return  "fun:sin";

            case "fun:cos":
                return "fun:cos";
            
            case "fun:tan":
                return "fun:tan";
        
            default:
                return null;
        }
    }
    
}

