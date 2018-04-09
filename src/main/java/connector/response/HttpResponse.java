package connector.response;

import logger.Logger;
import util.HttpFormatUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by cong on 2018-04-09.
 */
public class HttpResponse {

    private ResponseLine responseLine;

    private ResponseHeader responseHeader=new ResponseHeader();

    private String body;

    public void setResponseLine(String protocal, int statusCode, String statusDesc){
        responseLine=new ResponseLine(protocal,statusCode,statusDesc);
    }

    public void addHeader(String key,String value){
        responseHeader.addHeader(key,value);
    }

    public void setBody(String body){
        this.body=body;
    }

    public void write(OutputStream outputStream){
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
        responseLine.write(bufferedWriter);
        responseHeader.write(bufferedWriter);
        try {
            bufferedWriter.write(HttpFormatUtil.LINE_SPLITER);
            bufferedWriter.write(body);
            bufferedWriter.flush();
        } catch (IOException e) {
            Logger.error("write body:"+body+" fail,e:"+e.getMessage());
        }
    }

}
