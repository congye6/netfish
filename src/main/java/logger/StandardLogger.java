package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by cong on 2018-03-08.
 */
public class StandardLogger {

    private static final String INFO="info ";

    private static final String ERROR="error ";

    private static final Logger LOGGER=new FileLogger();

    public static void info(String message){
        log(INFO,message);
    }

    public static void error(String message){
        log(ERROR,message);
    }

    public static void error(String message,Exception e){
        log(ERROR,message+" e:"+e.getMessage());
        e.printStackTrace();
    }

    private static void log(String logLevel,String message){
        System.out.println(message);
        LOGGER.log(message);
    }

}
