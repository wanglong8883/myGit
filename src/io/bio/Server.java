package io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static int PORT=8765;

    public static void main(String[] args) {
//        ServerSocket serverSocket=null;
//        try {
//            serverSocket=new ServerSocket(PORT);
//            System.out.println("server start ....");
//            Socket socket=serverSocket.accept();
//            new Thread (new ServerHandler(socket)).start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if (serverSocket != null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            serverSocket=null;
//        }
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("server start ....");
            Socket socket=serverSocket.accept();
            new Thread (new ServerHandler(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
