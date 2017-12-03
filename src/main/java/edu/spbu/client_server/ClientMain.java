package edu.spbu.client_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 8080);
        Client client = new Client(s);
        client.writeOutputStream();
        client.readInputStream();
    }
}
