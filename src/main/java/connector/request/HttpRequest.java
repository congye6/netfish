package connector.request;

import logger.Logger;
import util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cong on 2018-04-08.
 */
public class HttpRequest {

    private static final String LINE_SPLITER="\r\n";

    private RequestLine requestLine;

    private RequestHeader requestHeader=new RequestHeader();

    private String body;


    public void buildRequest(InputStream inputStream) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        try {
            //请求行
            String requestLine=reader.readLine();
            this.requestLine=new RequestLine(requestLine);
            //请求头
            while(true){
                String headerLine=reader.readLine();
                if(StringUtil.isEmpty(headerLine))
                    break;
                requestHeader.addHeader(headerLine);
            }
            requestHeader.initSpecialHead();
            //body
            StringBuilder bodyBuilder=new StringBuilder();
            for(int i=0;i<requestHeader.getContentLength();i++){
                int readChar=reader.read();
                bodyBuilder.append((char)readChar);
            }

            body=bodyBuilder.toString();
        } catch (IOException e) {
            Logger.error("analyze request fail:"+e.getMessage());
        }
    }

    @Override
    public String toString(){
        String result=requestLine.toString()+LINE_SPLITER+requestHeader.toString()+LINE_SPLITER+body;
        return result;
    }

}
