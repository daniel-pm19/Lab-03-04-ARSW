package Excercise7;

import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.util.Scanner;

public class ChatApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);


        System.out.print("Enter local port to publish: ");
        int localPort = Integer.parseInt(sc.nextLine());

        LocateRegistry.createRegistry(localPort);
        ChatRemoteImpl localObj = new ChatRemoteImpl();
        Naming.rebind("//localhost:" + localPort + "/chat", localObj);

        System.out.println("Local RMI object published.");


        System.out.print("Enter remote IP: ");
        String remoteIP = sc.nextLine();

        System.out.print("Enter remote port: ");
        int remotePort = Integer.parseInt(sc.nextLine());

        ChatRemote remote = (ChatRemote)
                Naming.lookup("//" + remoteIP + ":" + remotePort + "/chat");

        System.out.println("Connected. Start chatting (type 'exit' to quit)");

        while (true) {
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("exit")) break;
            remote.receiveMessage(msg);
        }

        System.out.println("Chat ended.");
    }
}