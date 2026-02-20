package Excercise7;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class ChatRemoteImpl extends UnicastRemoteObject implements ChatRemote {

    protected ChatRemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("[RECEIVED] " + message);
    }
}