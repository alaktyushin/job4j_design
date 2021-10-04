package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private static int port = 9000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(port)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String str;
                    while (in.ready()) {
                        str = in.readLine();
                        LOG.info(str);
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
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
       }
    }
}