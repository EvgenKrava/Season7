package lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean work = true;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {
            pw.write("Welcome to calculator" + System.lineSeparator());
            while (work) {
                try {
                    pw.write("Input task:" + System.lineSeparator());
                    pw.flush();
                    String request = br.readLine().replace(" ", "");
                    if ("exit".equals(request)) {
                        work = false;
                        break;
                    }
                    pw.write(request + " = ");
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
                } catch (Exception e) {
                    e.printStackTrace();
                    work = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
