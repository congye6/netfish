package connector.request;

import enumeration.ContentType;
import enumeration.Encode;
import logger.Logger;
import util.EncodeUtil;
import util.InputStreamUtil;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by cong on 2018-04-13.
 */
public class ParameterMap {

    private static final String KEY_VALUE_SPLITER="=";

    private static final String PARAMETER_SPLITER="&";

    private Map<String,String> parameterMap;

    private Encode encode;

    public ParameterMap(HttpRequest request){
        encode=Encode.getEncode(request.getCharacterEncoding());
        parameterMap=new HashMap<String, String>();

        String queryString=request.getQueryString();
        if(StringUtil.isNotEmpty(queryString)){
            parse(queryString);
        }

        if(isParamInBody(request)){
            parse(request.getBody());
        }

    }

    public String getParam(String key){
        return parameterMap.get(key);
    }

    private void parse(String str){
        if(StringUtil.isEmpty(str))
            return;
        str=EncodeUtil.encode(str,encode);
        String[] params=str.split(PARAMETER_SPLITER);
        for(String param:params){
            String[] pair=param.split(KEY_VALUE_SPLITER);
            if(pair.length!=2)
                continue;
            parameterMap.put(pair[0],pair[1]);
        }
    }



    private boolean isParamInBody(HttpServletRequest request){
        return RequestMethod.POST.isMethod(request.getMethod())
                && ContentType.ENCODE_URL.isContentType(request.getContentType())&&request.getContentLength()>0;
    }


}
