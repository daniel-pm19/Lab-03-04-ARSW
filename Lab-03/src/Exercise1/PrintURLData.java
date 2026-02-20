package Exercise1;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PrintURLData {
    public static void main (String[] args){
        
        try {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the URL: ");
            String newURL = sc.nextLine();

            URL enteredURL = new URL(newURL);
            
            System.out.println( enteredURL + " Data Results");
            System.out.println("");
            System.out.println("URL Protocol: " + enteredURL.getProtocol());
            System.out.println("URL Authority: " + enteredURL.getAuthority());
            System.out.println("URL Host: " + enteredURL.getHost());
            System.out.println("URL Port: " + enteredURL.getPort());
            System.out.println("URL Path: " + enteredURL.getPath());
            System.out.println("URL Query: " + enteredURL.getQuery());
            System.out.println("URL File: " + enteredURL.getFile());
            System.out.println("URL Reference: " + enteredURL.getRef());

        } catch (MalformedURLException e){

            e.printStackTrace();

        }
    }

}