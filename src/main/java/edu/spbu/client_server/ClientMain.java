package edu.spbu.client_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 8889);
        Client client = new Client(clientSocket);
        client.writeOutputStream();
        client.readInputStream();
    }
}
