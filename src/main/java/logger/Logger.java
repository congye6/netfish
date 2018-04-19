package logger;

import container.Container;
import enumeration.LogLevel;

/**
 * Created by cong on 2018-04-19.
 */
public interface Logger {


    public Container getContainer();

    public void setContainer(Container container);

    public String getInfo();

    public LogLevel getLevel();

    public void setLevel(LogLevel level);

    public void log(String message);

    public void log(String message,Exception e);

    public void log(String message,LogLevel logLevel);

    public void log(String message,LogLevel logLevel,Exception e);

}
