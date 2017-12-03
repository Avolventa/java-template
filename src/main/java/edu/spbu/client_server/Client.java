package edu.spbu.client_server;

import java.net.*;
import java.io.*;

public class Client {

    private Socket s;
    private InputStream is;
    private OutputStream os;

    public Client(Socket s) throws IOException {
        this.s = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
    }

    public void writeOutputStream() throws IOException {
        String str = "GET /MyFile.html HTTP/1.1\r\n\r\n";
        os.write(str.getBytes());
        os.flush();
    }

    public void readInputStream() throws IOException{

    }
}
