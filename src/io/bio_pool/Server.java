package io.bio_pool;

import io.bio.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static int PORT = 8765;

    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
        ) {
            System.out.println("Server start");
            HandlerExecutorPool executorPool=new HandlerExecutorPool(50,100);
            Socket socket=null;
            while (true){
                socket=serverSocket.accept();
                executorPool.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
