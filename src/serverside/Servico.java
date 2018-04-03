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
  /*  public Servico(Socket socket)  {
        this.socket = socket;
    }*/
    public Servico() throws RemoteException {
        super();
    }

 /*   public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //transferencia de file
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Criou os buffers");

 //           while(true) {

                out.println(("Deseja baixar qual arquivo das opções a seguir?"));
                ArrayList<String> arrayList = listDirFiles(target);

                out.println(arrayList.size());
                for (String caminho : arrayList) {
                    out.println(caminho);
                }

                String resposta = in.readLine();
                //processa,verificando se possue o arquivo especificado
                if (arrayList.contains(resposta)) {
                    target += resposta;
                    System.out.println(resposta + " requisitado!");
                    out.println("Você escolheu baixar o arquivo " + resposta + "," +
                            "requisição confirmada!");
                } else {
                    System.out.println(resposta + " pedido de documento inexistente!");
                    out.println("Arquivo não encontrado!");
                }

                Path file = Paths.get(target);
                byte[] data = Files.readAllBytes(file);


                os.writeObject(data);
 //           }
     //     socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
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
