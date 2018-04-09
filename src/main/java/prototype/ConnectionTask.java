package prototype;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import logger.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by cong on 2018-04-06.
 */
public class ConnectionTask implements Runnable{

    private Socket serverSocket;

    public ConnectionTask(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void run() {
        try {
            HttpRequest request=new HttpRequest();
            request.buildRequest(serverSocket.getInputStream());

            String content="<html><head><title>HTTP响应示例</title></head><body>congye6</body></html>";
            HttpResponse response=new HttpResponse();
            response.setResponseLine("HTTP/1.1",200,"OK");
            response.addHeader("Content-Length",content.length()+"");
            response.addHeader("Content-type","text/html;charset=UTF-8");
            response.setBody(content);
            response.write(serverSocket.getOutputStream());
        } catch (IOException e) {
            Logger.error("net io fail:"+e.getMessage());
        }
    }
}
