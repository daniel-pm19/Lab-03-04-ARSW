package Excercise7;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatRemote extends Remote {
    void receiveMessage(String message) throws RemoteException;
}