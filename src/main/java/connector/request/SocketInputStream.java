package connector.request;

import connector.request.cookie.CookieParser;
import logger.Logger;
import util.StringUtil;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cong on 2018-04-14.
 */
public class SocketInputStream {

    private String requestLine;

    private List<String> requestHeaders=new ArrayList<String>();

    private char[] body;

    private BufferedReader reader;

    public SocketInputStream(InputStream inputStream){
       reader=new BufferedReader(new InputStreamReader(inputStream));
        try {
            //请求行
            requestLine=reader.readLine();

            //请求头
            while(true){
                String headerLine=reader.readLine();
                if(StringUtil.isEmpty(headerLine))
                    break;
                requestHeaders.add(headerLine);
            }


        } catch (IOException e) {
            Logger.error("analyze request fail:"+e.getMessage());
        }
    }

    public String getRequestLine() {
        return requestLine;
    }

    public List<String> getRequestHeaders() {
        return requestHeaders;
    }

    public char[] getBody(int length) {
        //body
        body= new char[length];
        try {
            reader.read(body,0,length);
        } catch (IOException e) {
            Logger.error("read body fail",e);
        }
        return body;
    }
}
