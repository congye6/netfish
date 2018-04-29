package connector.connector;

import container.context.Context;

import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by cong on 2018-04-06.
 */
public class ConnectionHandler {


    private static final Executor CONNECT_THREAD_POOL= Executors.newCachedThreadPool();

    private KeepAliveMonitor keepAliveMonitor=new KeepAliveMonitor();

    public ConnectionHandler() {
        Thread thread=new Thread(keepAliveMonitor);
        thread.start();
    }

    public void connect(Socket socket, Context context){
        ConnectionTask connectionTask=new ConnectionTask(socket,context);
        keepAliveMonitor.addTask(connectionTask);
        CONNECT_THREAD_POOL.execute(connectionTask);
    }
}
