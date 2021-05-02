package parser;

import static org.junit.jupiter.api.Assertions.*;
import config.HttpServer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


class HttpParserTest {

    @Test
    void parseHttpRequest() {
        String htmlResponse = HttpParser.parseHttpRequest(generateTestCase());
        String jsonResponse = HttpParser.parseHttpRequest(generateTestCaseTwo());
        String errorResponse = HttpParser.parseHttpRequest(generateTestCaseThree());

        try {
            assertTrue(htmlResponse.startsWith("<!DOCTYPE html>"));
            assertTrue(htmlResponse.equalsIgnoreCase(new String(Files.readAllBytes(Paths.get("src/main/resources/WEB_ROOT/index.html")))));
            assertTrue(jsonResponse.equalsIgnoreCase(new String(Files.readAllBytes(Paths.get("src/main/resources/WEB_ROOT/JsonFile.json")))));
            assertTrue(errorResponse.equalsIgnoreCase(new String(Files.readAllBytes(Paths.get("src/main/resources/WEB_ROOT/not_found.html")))));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private InputStream generateTestCase(){
        String rawData = "GET / HTTP/1.1\r\n" +
                "Host: localhost:9000\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Sec-Fetch-Site: none";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.UTF_8));
        return inputStream;

    }

    private InputStream generateTestCaseTwo(){
        String rawData = "GET /json HTTP/1.1\r\n" +
                "Host: localhost:9000\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Sec-Fetch-Site: none";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.UTF_8));
        return inputStream;

    }

    private InputStream generateTestCaseThree(){
        String rawData = "GET /something_else HTTP/1.1\r\n" +
                "Host: localhost:9000\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.72 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "Sec-Fetch-Site: none";

        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.UTF_8));
        return inputStream;

    }
}