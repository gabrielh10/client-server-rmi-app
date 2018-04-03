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
    private ServerSocket serverSock;
    String path = "";

   public Servidor() throws RemoteException {
       super();
   }


    public void run(){
    /*
        try {
  //          while(true){




                Socket servicoSock = serverSock.accept();
                Thread tServico = new Servico(servicoSock);
                tServico.start();
                //System.out.println("Abriu thread do servico");
      //      }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
        */
    }

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
            Servico servico = new Servico();
            Registry registry = LocateRegistry.createRegistry(9999);
            registry.bind("ServicoDownload", servico);

    }

}
