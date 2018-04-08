package prototype;

import logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by cong on 2018-04-06.
 */
public class ServerSocketService {


    private static final int PORT=8088;

    private ServerSocket serverSocket;

    private ConnectionHandler connectionHandler=new ConnectionHandler();

    private boolean isStop=false;

    public void start(){
        try {
            if(serverSocket==null)
                serverSocket=new ServerSocket(PORT);
            Logger.info("create server socker,port:"+PORT);

            while(!isStop){
                Socket socket=serverSocket.accept();
                connectionHandler.connect(socket);
            }

        } catch (IOException e) {
            Logger.error(e.getMessage());
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    Logger.error("close server socket fail");
                }
                serverSocket=null;
                Logger.info("server socket is closed");
            }
        }
    }

    public static void main(String[] args){
        new ServerSocketService().start();
    }

}
