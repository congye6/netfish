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
import container.pipeline.StandardPipeline;
import container.session.SessionManager;
import container.session.StandardSessionManager;
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

    private static final int DEFAULT_MAX_IDLE=100000;

    private Socket serverSocket;

    private Context context;

    private boolean keepAlive=true;

    private long lastAliveTime;

    private int maxIdle;

    public ConnectionTask(Socket serverSocket, Context context) {
        this.serverSocket = serverSocket;
        this.context = context;
        maxIdle=DEFAULT_MAX_IDLE;
    }

    public void run() {
        while (keepAlive){
            lastAliveTime=System.currentTimeMillis();
            try {
                HttpRequest request=new HttpRequest();
                request.buildRequest(serverSocket.getInputStream());
                HttpResponse response=new HttpResponse(serverSocket.getOutputStream());
                response.setRequest(request);

                if(request.getURI().startsWith("/servlet/")){
                    request.setContext(context);
                    context.invoke(request,response);
                    response.setResponseLine(new ResponseLine(HttpFormatUtil.HTTP_PROTOCAL, ResponseStatus.OK));
                    response.write();
                }else{
                    Processor processor=new StaticResourceProcessor();
                    processor.process(request,response);
                }

                keepAlive=request.isKeepAlive();
                System.out.println("---------------keep alive="+keepAlive);
            } catch (IOException e) {
                StandardLogger.error("net io fail:"+e.getMessage());
            } catch (Exception e){
                StandardLogger.error("excption",e);
            }

        }
        close();
        System.out.println("---------------------socket finish");
    }

    private void close(){

        try {
            serverSocket.close();
        } catch (IOException e) {
            StandardLogger.error("server socket close error",e);
        }
    }

    public void setMaxIdle(int maxIdle){
        this.maxIdle=maxIdle;
    }

    public boolean checkAlive(){
        long now=System.currentTimeMillis();
        if(now-lastAliveTime>maxIdle){
            keepAlive=false;
            close();
            return false;
        }else{
            return true;
        }
    }
}
