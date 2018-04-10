package connector.response;

import logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by cong on 2018-04-10.
 */
public class OutputBuffer {


    private ByteArrayOutputStream bufferStream=new ByteArrayOutputStream();


    public OutputStream getBufferStream(){
        return bufferStream;
    }

    public void writeTo(OutputStream outputStream){
        try {
            bufferStream.writeTo(outputStream);
        } catch (IOException e) {
            Logger.error("write buffer to response fail",e);
        }
    }

    public int size(){
        return bufferStream.size();
    }

}
