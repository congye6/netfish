package connector.connector;

import connector.processor.ServletProcessor;
import connector.processor.Processor;
import connector.processor.StaticResourceProcessor;
import connector.request.HttpRequest;
import connector.response.HttpResponse;
import connector.response.ResponseLine;
import container.Container;
import container.context.Context;
import container.context.ContextMapper;
import container.context.StandardContext;
import container.wrapper.StandardWrapper;
import enumeration.ResponseStatus;
import logger.Logger;
import util.HttpFormatUtil;

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
                Context context=new StandardContext();
                context.addServletMapping("/servlet/CookieServlet",new StandardWrapper("/servlet/CookieServlet"));
                ContextMapper mapper=new ContextMapper();
                mapper.setContainer(context);
                context.setMapper(mapper);
                context.invoke(request,response);
                response.setResponseLine(new ResponseLine(HttpFormatUtil.HTTP_PROTOCAL, ResponseStatus.OK));
                response.write();
            }else{
                Processor processor=new StaticResourceProcessor();
                processor.process(request,response);
            }
        } catch (IOException e) {
            Logger.error("net io fail:"+e.getMessage());
        }
    }
}
