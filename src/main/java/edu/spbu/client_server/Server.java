package edu.spbu.client_server;

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.net.Socket;

public class Server {
    public Socket s;
    private InputStream is;
    private OutputStream os;
    private String fileName;

    public Server(Socket s) throws IOException {
        this.s = s;
        this.is = s.getInputStream();
        this.os = s.getOutputStream();
        this.fileName = "";
    }

    public void readInputStream() throws IOException {
        Scanner scan = new Scanner(is);
        String str = scan.next();
        if (str.equals("GET")) {
            str = scan.next();
            fileName = str.substring(1, str.length() - 1);
        } else {
            System.out.println("Strange string input");
        }

    }

    public void writeOutputStream() throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            String s = new String(Files.readAllBytes(Paths.get(fileName)));
            String response = "HTTP/1.1 200 OK\r\n" + "Content-Type:text/html\r\n\r\n" + s;
            os.write(response.getBytes());
        } else {
            os.write("<html><h2>404</h2></html>".getBytes());
            os.flush();
        }
    }
}
