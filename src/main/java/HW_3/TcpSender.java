package HW_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TcpSender {
    private  final int Port = 8099;
    ServerSocket serverSocket ;
    Socket socket;
    File filePath = new File("Alisa.txt");

    TcpSender(){

        try {
            serverSocket = new ServerSocket(Port);
            socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

            outputStream.write(inputStream.read());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        int port = 50001;
        TcpSender sender = new TcpSender();
        UdpServer server = new UdpServer(port);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit((Runnable) sender);
        executorService.submit(server);

    }
}
