package HW_3;

import java.io.IOException;
import java.net.*;

public class UdpServer implements Runnable {

    private final int ClientPort;

    public UdpServer(int clientPort) {
        this.ClientPort = clientPort;
    }

    @Override
    public void run() {
        try (DatagramSocket serverSocket = new DatagramSocket(50000)) {
            for (int i = 0; i < 3; i++) {
                String message = "Message number " + i;
                DatagramPacket datagramPacket = new DatagramPacket(
                        message.getBytes(),
                        message.length(),
                        InetAddress.getLocalHost(),
                        ClientPort
                );
                serverSocket.send(datagramPacket);

            }
        } catch (SocketException | UnknownHostException  e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}