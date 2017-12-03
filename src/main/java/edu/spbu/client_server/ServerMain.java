package edu.spbu.client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket s = ss.accept();
            Server server = new Server(s);
            server.readInputStream();
            server.writeOutputStream();
            server.s.close();
        }
    }
}
