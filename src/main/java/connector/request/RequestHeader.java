package connector.request;

import util.StringUtil;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by cong on 2018-04-08.
 */
public class RequestHeader {

    private static final String HEADER_SPLITER=":";

    private static final int INDEX_OF_KEY=0;

    private static final int INDEX_OF_VALUE=1;

    private static final String CONTENT_LENGTH_KEY="Content-Length";

    private Map<String,String> headerMap=new HashMap<String,String>();



    private int contentLength;

    public String getHeader(String key){
        return headerMap.get(key);
    }

    public void addHeader(String headerLine){
        if(StringUtil.isEmpty(headerLine))
            return;
        String[] params=headerLine.split(HEADER_SPLITER);
        headerMap.put(params[INDEX_OF_KEY],params[INDEX_OF_VALUE].trim());
    }

    public void initSpecialHead(){
        String length=headerMap.get(CONTENT_LENGTH_KEY);
        if(length==null)
            return;
        contentLength=Integer.parseInt(length);
    }

    @Override
    public String toString(){
        String result="";
        for(String key:headerMap.keySet()){
            result=result+"\n"+key+HEADER_SPLITER+headerMap.get(key);
        }
        return result;
    }

    public int getContentLength() {
        return contentLength;
    }

}
