package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLocator {

    private static final String HTMLPATH = "src/main/resources/WEB_ROOT/index.html";
    private static final String JSONPATH = "src/main/resources/WEB_ROOT/JsonFile.json";
    private static final String NOT_ALLOWED = "src/main/resources/WEB_ROOT/not_found.html";

    private InputStream inputStream;

    public FileLocator(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    //Gets the path of various file and read them into a string
    public static String getHtmlPath(){
        String content = null;
        try {
            content= new String(Files.readAllBytes(Paths.get(HTMLPATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String getJsonPath(){
        String content = null;
        try {
            content= new String(Files.readAllBytes(Paths.get(JSONPATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String getNotAllowed(){
        String content = null;
        try {
            content= new String(Files.readAllBytes(Paths.get(NOT_ALLOWED)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

}
