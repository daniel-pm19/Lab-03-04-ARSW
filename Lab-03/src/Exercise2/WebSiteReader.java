package Exercise2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class WebSiteReader {
    public static void main(String[] args){
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the URL: ");
            String newURL = sc.nextLine();

            URL enteredURL = new URL(newURL);
            List<String> result = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(enteredURL.openStream()))){
                String inputLine = null;
                
                while((inputLine = reader.readLine()) != null){
                    result.add(inputLine);
                    System.out.println(inputLine);
                }

            } catch (IOException x){
                System.err.println(x);
            }

            try(BufferedWriter writer = new BufferedWriter(new FileWriter("./src/Excercise2/result.html"))) {
                for(String l : result){
                    writer.write(l);
                    writer.newLine();
                }
            } catch (IOException y){
                System.err.println(y);
            }


        } catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
}
