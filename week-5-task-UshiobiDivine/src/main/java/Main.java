import config.HttpServer;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {

        int port = 8088;

        ServerSocket server = new ServerSocket(port);
        Thread httpServerOne = new HttpServer(server);
        httpServerOne.start();
    }
}
