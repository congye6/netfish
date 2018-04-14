package util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cong on 2018-04-14.
 */
public class InputStreamUtil {


    public static byte[] read(InputStream inputStream,int contentLength){
        if(contentLength<=0||inputStream==null)
            return null;
        byte[] buffer=new byte[contentLength];

        int offset=0;
        while (offset<contentLength){
            try {
                int readNum=inputStream.read(buffer,offset,contentLength-offset);
                if(readNum<0){
                    inputStream.close();
                    return buffer;
                }else{
                    offset+=readNum;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }


}
