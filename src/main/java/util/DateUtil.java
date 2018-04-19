package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cong on 2018-04-19.
 */
public class DateUtil {

    private static final String DATE_FOMART="yyyy-MM-dd";

    /**
     * 获取timestamp的日期
     * @param timestamp
     * @return
     */
    public static String getDate(long timestamp){
        return getFormatString(timestamp,DATE_FOMART);
    }

    private static String getFormatString(long timestamp,String format){
        Date date=new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}
