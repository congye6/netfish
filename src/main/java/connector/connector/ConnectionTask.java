package connector.connector;

import connector.processor.ServletProcessor;
import connector.processor.Processor;
import connector.processor.StaticResourceProcessor;
import connector.request.HttpRequest;
import connector.response.HttpResponse;
import logger.Logger;

import java.io.*;
import java.net.Socket;

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

            HttpResponse response=new HttpResponse(serverSocket.getOutputStream());
            response.setRequest(request);

            if(request.getURI().startsWith("/servlet/")){
                Processor processor=new ServletProcessor();
                processor.process(request,response);
            }else{
                Processor processor=new StaticResourceProcessor();
                processor.process(request,response);
            }
        } catch (IOException e) {
            Logger.error("net io fail:"+e.getMessage());
        }
    }
}
