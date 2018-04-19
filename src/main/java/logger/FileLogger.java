package logger;

import util.DateUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by cong on 2018-04-19.
 */
public class FileLogger extends BaseLogger{

    private static final String FILE_NAME_PRE="netfish-log-";

    public void log(String message) {
        String fileName=FILE_NAME_PRE+ DateUtil.getDate(System.currentTimeMillis());
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(fileName,true));
            bufferedWriter.append('\n');
            bufferedWriter.append(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
