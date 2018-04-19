package util;

import enumeration.Encode;
import logger.StandardLogger;

import java.io.UnsupportedEncodingException;

/**
 * Created by cong on 2018-04-13.
 */
public class EncodeUtil {


    /**
     * 将字符串按某种编码格式编码
     * 编码失败则返回原字符串
     * @param source
     * @param encode
     * @return
     */
    public static String encode(String source, Encode encode){
        try {
            return new String(source.getBytes(encode.getCode()),encode.getCode());
        } catch (UnsupportedEncodingException e) {
            StandardLogger.error("encode str:"+source+" fail,encode:"+encode.getCode(),e);
        }
        return source;
    }

}
