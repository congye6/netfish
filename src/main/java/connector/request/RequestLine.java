package connector.request;

import util.StringUtil;

/**
 * Created by cong on 2018-04-08.
 */
public class RequestLine {

    private static final int NUM_OF_PARAM=3;

    private static final int INDEX_OF_METHOD=0;

    private static final int INDEX_OF_PATH=1;

    private static final int INDEX_OF_PROTOCAL=2;

    /**
     * 将uri与参数分开
     */
    private static final String QUERY_STRING_SPLITER="?";

    private RequestMethod method;

    private String path;

    private String protocal;

    private String queryString;

    public RequestLine(String requestLine){
        if(StringUtil.isEmpty(requestLine))
            return;
        String[] params=requestLine.split(" ");
        if(params.length!=NUM_OF_PARAM)
            return;

        method=RequestMethod.getRequestMethod(params[INDEX_OF_METHOD]);
        path=params[INDEX_OF_PATH];
        parseQueryString(path);
        protocal=params[INDEX_OF_PROTOCAL];
    }

    private void parseQueryString(String path){
        if(!path.contains(QUERY_STRING_SPLITER))
            return;
        String[] strs=path.split(QUERY_STRING_SPLITER);
        if(strs.length!=2)
            return;
        this.queryString=strs[1];
        this.path=strs[0];
    }

    public String getQueryString(){
        return queryString;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocal() {
        return protocal;
    }

    @Override
    public String toString(){
        String result=method+" "+path+" "+protocal;
        return result;
    }
}
