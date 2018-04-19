package enumeration;

/**
 * Created by cong on 2018-04-19.
 */
public enum LogLevel {

    FATAL(0),ERROR(1),WARNING(2),INFO(3),DEBUG(4);


    private int level;

    LogLevel(int level) {
        this.level = level;
    }

    /**
     * 判断日志级别是否更高
     * @param logLevel
     * @return
     */
    public boolean isHigher(LogLevel logLevel){
        if(logLevel==null)
            return true;
        return level<logLevel.level;
    }

    public boolean isLower(LogLevel logLevel){
        return !isHigher(logLevel);
    }
}
