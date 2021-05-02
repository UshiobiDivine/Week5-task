package config;

import parser.HttpParser;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;


public class RequestHandler extends Thread {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            //gets the socket stream
            InputStream inStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //Parses the HTML
            String responsePage = HttpParser.parseHttpRequest(inStream);

            //The response
            final String CRLF = "\n\r";

            String type=null;
            String statusLine = null;

            String str = new String(Files.readAllBytes(Paths.get("src/main/resources/WEB_ROOT/index.html")));
            String js = new String(Files.readAllBytes(Paths.get("src/main/resources/WEB_ROOT/JsonFile.json")));

            if (responsePage.equalsIgnoreCase(str)){
                type = "text/html";
                statusLine="HTTP/1.1 200 OK";
            }else if (responsePage.equalsIgnoreCase(js)){
                type= "application/json";
                statusLine="HTTP/1.1 200 OK";
            }else if (!responsePage.equalsIgnoreCase(str)&&!responsePage.equalsIgnoreCase(js)){
                type = "text/html";
                statusLine="HTTP/1.1 404 Not Found";
            }

            String response =
                            statusLine + CRLF + //Status line
                            "Content-Length: " + responsePage.getBytes(StandardCharsets.UTF_8).length + CRLF +
                            "Content-type: " + type + CRLF +
                            "Server: Simple Java HTTP Server from Divine " + CRLF +
                            "Date: " + new Date() + CRLF + CRLF +   //HEADER

                            responsePage + CRLF + CRLF;

            //gives the response
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));

            inStream.close();
            outputStream.close();
            socket.close();

            //sleep(5000);

        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(message);
        }

        System.out.println("Connection Processing Finished");
    }

}
