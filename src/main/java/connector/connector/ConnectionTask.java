package connector.connector;

import connector.processor.Processor;
import connector.processor.StaticResourceProcessor;
import connector.request.HttpRequest;
import connector.response.HttpResponse;
import connector.response.ResponseLine;
import container.context.Context;
import container.context.ContextMapper;
import container.context.StandardContext;
import container.lifecycle.LifeCycleException;
import container.loader.WebappClassLoader;
import container.loader.WebappLoader;
import container.wrapper.StandardWrapper;
import enumeration.ResponseStatus;
import logger.StandardLogger;
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
                StandardContext context=new StandardContext();

                ContextMapper mapper=new ContextMapper();
                mapper.setContainer(context);
                context.setMapper(mapper);

                context.setDocbase("netfish");

                WebappLoader loader=new WebappLoader();
                loader.setContainer(context);
                context.setLoader(loader);

                try {
                    context.start();
                } catch (LifeCycleException e) {
                    e.printStackTrace();
                }

                context.invoke(request,response);
                response.setResponseLine(new ResponseLine(HttpFormatUtil.HTTP_PROTOCAL, ResponseStatus.OK));
                response.write();
            }else{
                Processor processor=new StaticResourceProcessor();
                processor.process(request,response);
            }
        } catch (IOException e) {
            StandardLogger.error("net io fail:"+e.getMessage());
        }
    }
}
