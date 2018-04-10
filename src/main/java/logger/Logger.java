package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by cong on 2018-03-08.
 */
public class Logger {

    private static final String INFO="info ";

    private static final String ERROR="error ";

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
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("log",true));
            bufferedWriter.append('\n');
            bufferedWriter.append(logLevel);
            bufferedWriter.append(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
