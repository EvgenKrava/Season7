package lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Task1 {
    private int port;
    private boolean work = true;

    public Task1(int port) {
        this.port = port;
    }

    public void stop() {
        work = false;
    }

    public void start() throws IOException {
        while (work) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            System.out.println("Have request!");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
                pw.write("Welcome to simple calculator" + System.lineSeparator());
                pw.flush();
                while (work) {
                    pw.write("Enter your task:" + System.lineSeparator());
                    pw.flush();
                    String request = br.readLine();
                    if ("exit".equals(request)) {
                        work = false;
                        break;
                    }
                    request = request.replace(" ", "");
                    if (request.matches("(-?\\d+\\.?\\d*)\\+(-?\\d+\\.?\\d*)")) {
                        String[] added = request.split("\\+");
                        pw.write(Double.parseDouble(added[0])
                                + Double.parseDouble(added[1])
                                + System.lineSeparator());
                    } else if (request.matches("(-?\\d+\\.?\\d*)-(-?\\d+\\.?\\d*)")) {
                        String[] added = request.split("-");
                        pw.write(Double.parseDouble(added[0])
                                - Double.parseDouble(added[1])
                                + System.lineSeparator());
                    } else if (request.matches("(-?\\d+\\.?\\d*)\\*-?(\\d+\\.?\\d*)")) {
                        String[] added = request.split("\\*");
                        pw.write(Double.parseDouble(added[0])
                                * Double.parseDouble(added[1])
                                + System.lineSeparator());
                    } else if (request.matches("(-?\\d+\\.?\\d*)/(-?\\d+\\.?\\d*)")) {
                        String[] added = request.split("/");
                        pw.write(Double.parseDouble(added[0])
                                / Double.parseDouble(added[1])
                                + System.lineSeparator());
                    } else {
                        pw.write("Incorrect input" + System.lineSeparator());
                    }
                    pw.flush();
                }
            } catch (Exception e) {

                work = false;
            }

        }
    }
}
