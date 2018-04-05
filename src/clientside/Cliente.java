package clientside;

import serverside.ServicoI;

import java.io.*;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente extends Thread{
    //Seta a pasta onde os downloads serao armazenados
    private static final String home= "C:\\Users\\gabri\\Pictures\\cliente\\";


    public static void main (String[] args) throws IOException, NotBoundException, InterruptedException {

        Registry registry = LocateRegistry.getRegistry("localhost", 9999);
        ServicoI servico = (ServicoI) registry.lookup("ServicoDownload");

        while(true) {
            System.out.println(servico.welcomeMSG());

            System.out.println("Dos arquivos a seguir, qual deseja baixar ?");
            ArrayList<String> files = servico.listFiles();
            for (String file:files) System.out.println(file);

            Scanner scan = new Scanner(System.in);
            String arquivo = scan.nextLine();

            if(files.contains(arquivo)) {
                byte[] serializedFile = servico.downloadFile(arquivo);
                FileOutputStream fos = new FileOutputStream(home + arquivo);
                fos.write(serializedFile);
            }else{
                System.out.println("The specified file doesn't exist on the server");
            }
            currentThread().sleep(2000);
        }

    }
}
