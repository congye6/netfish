package prototype;

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
        BufferedReader reader=null;
        BufferedWriter writer=null;

        try {
            reader=new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            writer=new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));

            StringBuilder contentBuilder=new StringBuilder();
            while(true){
                String line=reader.readLine();
                Logger.info(line);
                if(line==null||line.equals(""))
                    break;
                contentBuilder.append(line);
            }
            String content="<html><head><title>HTTP响应示例</title></head><body>congye6</body></html>";
            writer.write("HTTP/1.1 200 OK\r\n");
            writer.write("Server:congye6 Server/0.0.1\r\n");
            writer.write("Content-type:text/html;charset=UTF-8\r\n");
            writer.write("Content-Length:"+content.length()+"\r\n");
            writer.write("\r\n");
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            Logger.error("net io fail:"+e.getMessage());
        }finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                Logger.error("close connection fail");
            }
        }
    }
}
