package config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer extends Thread {

    private ServerSocket serverSocket;

    //Constructor
    public HttpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        try {
        while (true) {   //The opened port remains opened until it is closed manually or due to any error

            Socket clientSocket = serverSocket.accept();

            System.out.println("Server started.\nListening for connections on port : " + serverSocket.getLocalPort() + " ...\n");

            //Start
            Thread httpConnection = new RequestHandler(clientSocket);
            httpConnection.start();

            System.out.println("Connection accepted " + clientSocket.getInetAddress());
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
