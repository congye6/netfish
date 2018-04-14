package enumeration;

import java.util.*;

/**
 * Created by cong on 2018-04-13.
 */
public enum Encode {


    UTF8("UTF-8"),ISO("ISO-8859-1"),GBK("GBK"),UNICODE("UNICODE");

    private static final Map<String,Encode> ENCODE_MAP=new HashMap<String,Encode>();

    static {
        ENCODE_MAP.put(UTF8.code,UTF8);
        ENCODE_MAP.put(ISO.code,ISO);
        ENCODE_MAP.put(GBK.code,GBK);
        ENCODE_MAP.put(UNICODE.code,UNICODE);
    }

    Encode(String encode){
        this.code=encode;
    }

    private String code;

    public String getCode() {
        return code;
    }

    public static Encode getEncode(String code){
        Encode encode=ENCODE_MAP.get(code);
        if(encode==null)
            return UTF8;
        return encode;
    }


}
