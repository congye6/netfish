package logger;

import util.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by cong on 2018-04-19.
 */
public class FileLogger extends BaseLogger{

    private static final String FILE_NAME_PRE="netfish-log-";

    private static final String FILE_NAME_POST=".log";

    private static final String LOG_DIR="log";

    public void log(String message) {

        File logDir=new File(LOG_DIR);
        if(!logDir.exists())
            logDir.mkdir();
        String fileName=LOG_DIR+"/"+FILE_NAME_PRE+ DateUtil.getDate(System.currentTimeMillis())+FILE_NAME_POST;
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
