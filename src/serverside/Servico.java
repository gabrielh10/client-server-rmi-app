package serverside;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Servico extends UnicastRemoteObject implements ServicoI {
    private Socket socket;
    private static final String target = "C:\\Users\\gabri\\Pictures\\server\\";

    public Servico() throws RemoteException {
        super();
    }


    //Exibição de arquivos no diretorio
    private ArrayList<String> listDirFiles(String path){
        ArrayList<String> names = new ArrayList<String>();
        File dir = new File(path);
        for(File i : dir.listFiles()){
            if(i.isFile()){
                names.add(i.getName());
            }
        }
        return names;
    }

    public byte[] downloadFile(String name) throws IOException {
        Path file = Paths.get(target+name);
        byte[] data = Files.readAllBytes(file);
        return data;
    }
    public String welcomeMSG() throws RemoteException{
        return "Hello, you're connected to the server now !";
    }
    public ArrayList<String> listFiles() throws RemoteException{
        return listDirFiles(target);
    }
}
