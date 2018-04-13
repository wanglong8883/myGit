package io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                PrintWriter out =new PrintWriter(this.socket.getOutputStream(),true)
        ) {
            String body=null;
            while (true){
                body=in.readLine();
                if(body==null)
                    break;
                System.out.println("Server: "+body);
                out.println("服务器回送相应的数据");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null) {
                try {
                    socket.close();
                    System.out.println("socket is close");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket=null;
        }
    }
}
