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

    private RequestMethod method;

    private String path;

    private String protocal;

    public RequestLine(String requestLine){
        if(StringUtil.isEmpty(requestLine))
            return;
        String[] params=requestLine.split(" ");
        if(params.length!=NUM_OF_PARAM)
            return;

        method=RequestMethod.getRequestMethod(params[INDEX_OF_METHOD]);
        path=params[INDEX_OF_PATH];
        protocal=params[INDEX_OF_PROTOCAL];
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
