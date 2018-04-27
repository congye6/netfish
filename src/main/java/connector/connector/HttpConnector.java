package connector.connector;

import container.context.ContextMapper;
import container.context.StandardContext;
import container.lifecycle.LifeCycleException;
import container.loader.WebappLoader;
import container.session.SessionManager;
import container.session.StandardSessionManager;
import logger.StandardLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cong on 2018-04-06.
 */
public class HttpConnector implements Runnable{


    private static final int PORT=8088;

    private ServerSocket serverSocket;

    private ConnectionHandler connectionHandler=new ConnectionHandler();

    private boolean isStop=false;

    public void run(){

        StandardContext context=new StandardContext();
        ContextMapper mapper=new ContextMapper();
        mapper.setContainer(context);
        context.setMapper(mapper);

        context.setDocbase("netfish");

        WebappLoader loader=new WebappLoader();
        loader.setContainer(context);
        context.setLoader(loader);

        SessionManager sessionManager=new StandardSessionManager();
        context.setSessionManager(sessionManager);
        sessionManager.setContainer(context);

        try {
            context.start();
        } catch (LifeCycleException e) {
            e.printStackTrace();
        }

        try {
            if(serverSocket==null)
                serverSocket=new ServerSocket(PORT);
            StandardLogger.info("create server socker,port:"+PORT);

            while(!isStop){
                Socket socket=serverSocket.accept();
                connectionHandler.connect(socket,context);
            }

        } catch (IOException e) {
            StandardLogger.error(e.getMessage());
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    StandardLogger.error("close server socket fail");
                }
                serverSocket=null;
                StandardLogger.info("server socket is closed");
            }
        }
    }

    public static void main(String[] args){
        new HttpConnector().run();
    }

}
