package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Программа для запросов клиент-сервер.
 *
 * @author Andrey Shulgin
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String msg = in.readLine();
                    if (msg.contains("?msg=Exit")) {
                        server.close();
                    } else if (msg.contains("?msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    } else {
                        out.write("What".getBytes());
                    }

                    out.flush();
                }
            }
        }
    }
}
