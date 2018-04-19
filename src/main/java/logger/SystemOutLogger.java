package logger;

/**
 * Created by cong on 2018-04-19.
 */
public class SystemOutLogger extends BaseLogger{
    public void log(String message) {
        System.out.println(message);
    }
}
