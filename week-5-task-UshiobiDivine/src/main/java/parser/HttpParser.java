package parser;

import utils.FileLocator;

import java.io.InputStream;
import java.util.Scanner;

public class HttpParser {

    private InputStream stream;

    public HttpParser(InputStream stream) {
        this.stream = stream;
    }

    //Parses the request and maps the request to the right file
    public static String parseHttpRequest(InputStream stream){
        Scanner inputStream = new Scanner(stream);

        String[] statusLine = inputStream.nextLine().split(" "); // TODO handle ex

        if (statusLine[0].equals("GET") || statusLine[0].equals("HEAD")) {
            String content;
            if (statusLine[1].endsWith("/")) {
                content = FileLocator.getHtmlPath();
                return content;
            } else if (statusLine[1].endsWith("/json")) {
                content = FileLocator.getJsonPath();
                return content;
            } else if (!statusLine[1].endsWith("/") || !statusLine[1].endsWith("/json")) {
                content = FileLocator.getNotAllowed();
                return content;
            }
        }
        return null;
    }
}
