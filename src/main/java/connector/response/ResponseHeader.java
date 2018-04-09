package connector.response;

import logger.Logger;
import util.HttpFormatUtil;
import util.StringUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created by cong on 2018-04-09.
 */
public class ResponseHeader {


    private Map<String,String> headerMap=new HashMap<String,String>();


    public void addHeader(String key,String value){
        if(StringUtil.isEmpty(key))
            return;
        headerMap.put(key,value);
    }

    void write(BufferedWriter bufferedWriter){
        StringBuilder builder=new StringBuilder();
        for(Map.Entry<String,String> entry:headerMap.entrySet()){
            builder.append(entry.getKey());
            builder.append(HttpFormatUtil.HEADER_SPLITER);
            builder.append(entry.getValue());
            builder.append(HttpFormatUtil.LINE_SPLITER);
        }
        try {
            bufferedWriter.write(builder.toString());
        } catch (IOException e) {
            Logger.error("write header fail,e:"+e.getMessage());
        }
    }


}
