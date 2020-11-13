package lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Task3 implements Runnable {
    private final int port;
    private final String host;
    private boolean work = true;

    public Task3(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void exit() {
        work = false;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Client started");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
                while (work) {
                    Thread.sleep(2000);
                    String sign = null;
                    double rand = Math.random();
                    if (rand <= 0.25) {
                        sign = "+";
                    } else if (rand > 0.25 && rand <= 0.5) {
                        sign = "/";
                    } else if (rand > 0.5 && rand <= 0.75) {
                        sign = "*";
                    } else if (rand > 0.75 && rand <= 1) {
                        sign = "-";
                    }
                    pw.write((String.format("%.2f", Math.random() * 100) + sign + String.format("%.2f", Math.random() * 100) + System.lineSeparator()));
                    pw.flush();
                    System.out.println(br.readLine());
                }
            }
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
