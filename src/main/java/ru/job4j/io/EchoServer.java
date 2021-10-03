package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str;
                    while (in.ready()) {
                        str = in.readLine();
                        System.out.println(str);
                        if (str.contains("?msg=Exit")) {
                            out.write("Bye-bye!".getBytes());
                            server.close();
                            break;
                        }
                        if (str.contains("?msg=Hello")) {
                            out.write("Hello, dear friend!".getBytes());
                        } else {
                            out.write("What?".getBytes());
                        }
                        break;
                    }
                    out.flush();
                }
            }
        }
    }
}