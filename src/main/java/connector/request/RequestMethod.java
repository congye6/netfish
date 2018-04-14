package connector.request;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by cong on 2018-04-08.
 */
public enum RequestMethod {


    GET,POST,OPTIONS,HEAD,PUT,DELETE,TRACE,CONNECT,OTHER;

    private static final Map<String,RequestMethod> METHOD_MAP=new HashMap<String,RequestMethod>();

    static {
        METHOD_MAP.put(GET.toString(),GET);
        METHOD_MAP.put(POST.toString(), POST);
        METHOD_MAP.put(OPTIONS.toString(),OPTIONS);
        METHOD_MAP.put(HEAD.toString(),HEAD);
        METHOD_MAP.put(PUT.toString(),PUT);
        METHOD_MAP.put(DELETE.toString(),DELETE);
        METHOD_MAP.put(TRACE.toString(),TRACE);
        METHOD_MAP.put(CONNECT.toString(),CONNECT);
    }

    public boolean isMethod(String method){
        return this.name().equalsIgnoreCase(method);
    }

    public static RequestMethod getRequestMethod(String requestMethod){
        RequestMethod method=METHOD_MAP.get(requestMethod);
        if(method==null)
            return RequestMethod.OTHER;
        return METHOD_MAP.get(requestMethod);
    }

    public static void main(String[] args){
        System.out.println(GET.toString());
    }

}
