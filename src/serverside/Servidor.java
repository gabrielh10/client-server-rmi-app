package serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor extends Thread {

   public Servidor() throws RemoteException {
       super();
   }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
            Servico servico = new Servico();
            Registry registry = LocateRegistry.createRegistry(9999);
            registry.bind("ServicoDownload", servico);

    }

}
