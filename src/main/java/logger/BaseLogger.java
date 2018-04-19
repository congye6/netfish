package logger;

import container.Container;
import enumeration.LogLevel;

/**
 * Created by cong on 2018-04-19.
 * 日志记录的基类
 * log（message）方法需要子类实现
 */
public abstract class BaseLogger implements Logger{

    protected Container container;

    /**
     * 日志级别，比这级别低的日志不记录
     */
    protected LogLevel logLevel=LogLevel.INFO;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container=container;
    }

    public String getInfo() {
        return null;
    }

    public LogLevel getLevel() {
        return logLevel;
    }

    public void setLevel(LogLevel level) {
        this.logLevel=level;
    }

    public void log(String message, Exception e) {
        message=message+"\n"+e.getMessage();
        log(message);
    }

    public void log(String message, LogLevel logLevel) {
        if(this.logLevel.isHigher(logLevel))
            return;
        log(message);
    }

    public void log(String message, LogLevel logLevel, Exception e) {
        if(this.logLevel.isHigher(logLevel))
            return;
        log(message,e);
    }
}
