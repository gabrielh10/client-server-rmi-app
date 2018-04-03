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

    private static final String home= "C:\\Users\\gabri\\Pictures\\cliente\\";
    public void run(){
        /*      try {
            //configurando a porta para comunicação 9999 e anexando canais de comunicação
            Socket socket = new Socket("localhost", 9999);


            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());


            //criando os buffers cliente
            System.out.println("Criou os buffers cliente");
            Scanner scan = new Scanner(System.in);

  //          while(true) {

                System.out.println("Instanciou scanner");
                String options = in.readLine();
                //recebo o resultado do número de arquivos
                int numeroArquivos = Integer.valueOf(in.readLine());
                int i = 0;
                while (i < numeroArquivos) {
                    options += in.readLine() + "\n";
                    i++;
                }
                System.out.println("aqui");
                System.out.println(options);


                //digite  a opcao escolhida do arquivo
                String resposta = scan.nextLine();
                home += resposta;
                out.println(resposta);

                //resposta do servidor
                String resultado = in.readLine();
                System.out.println(resultado);

                //criar arquivo
                byte[] data = (byte[]) is.readObject();


                FileOutputStream fos = new FileOutputStream(home);
                fos.write(data);

  //          }
        //        fos.close();
    //            socket.close();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
*/
    }


    public static void main (String[] args) throws IOException, NotBoundException, InterruptedException {
  //      Cliente tCliente = new Cliente();
  //      tCliente.start();

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
        //ClienteGui clienteGui =   new ClienteGui();
    }
}
