package connector.connector;

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
        try {
            if(serverSocket==null)
                serverSocket=new ServerSocket(PORT);
            StandardLogger.info("create server socker,port:"+PORT);

            while(!isStop){
                Socket socket=serverSocket.accept();
                connectionHandler.connect(socket);
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
