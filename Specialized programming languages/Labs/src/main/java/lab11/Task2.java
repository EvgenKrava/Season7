package lab11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Task2 {
    private int port;
    private boolean work = true;

    public Task2(int port) {
        this.port = port;
    }

    public void exit() {
        work = false;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started");
            while (work) {
                Socket socket = serverSocket.accept();
                System.out.println("Have new connection: " + socket);
                Thread handler = new Thread(new Handler(socket));
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
