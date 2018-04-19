package logger;

import container.Container;
import container.lifecycle.LifeCycle;
import container.lifecycle.LifeCycleException;
import container.lifecycle.LifeCycleListener;
import container.lifecycle.LifeCycleUtil;
import enumeration.LogLevel;

import java.util.List;

/**
 * Created by cong on 2018-04-19.
 * 日志记录的基类
 * log（message）方法需要子类实现
 */
public abstract class BaseLogger implements Logger,LifeCycle{

    protected Container container;

    protected LifeCycleUtil lifeCycle;

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

    public void addLifeCycleListener(LifeCycleListener listener) {
        lifeCycle.addListener(listener);
    }

    public List<LifeCycleListener> getLifeCycleListeners() {
        return lifeCycle.getListeners();
    }

    public void removeLifeCycleListener(LifeCycleListener listener) {
        lifeCycle.removeListener(listener);
    }

    public void start() throws LifeCycleException {

    }

    public void stop() throws LifeCycleException {

    }
}
