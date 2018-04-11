package connector.connector;

import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by cong on 2018-04-06.
 */
public class ConnectionHandler {


    private static final Executor CONNECT_THREAD_POOL= Executors.newCachedThreadPool();



    public void connect(Socket socket){
        ConnectionTask connectionTask=new ConnectionTask(socket);
        CONNECT_THREAD_POOL.execute(connectionTask);
    }
}
