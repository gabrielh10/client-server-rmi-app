package serverside;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServicoI extends Remote {
   byte[] downloadFile(String name) throws IOException;
   String welcomeMSG() throws RemoteException;
   ArrayList<String> listFiles() throws RemoteException;

}
