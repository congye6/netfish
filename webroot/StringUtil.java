package util;

/**
 * Created by cong on 2018-04-08.
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        return str==null||str.length()==0;
    }

    public static boolean isNotEmpty(String str){
        return str!=null&&str.length()!=0;
    }
}
