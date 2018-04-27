package util;

import logger.StandardLogger;

/**
 * Created by cong on 2018-04-24.
 */
public class ThreadUtil {

    public static void sleep(int sleepTime){
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            StandardLogger.error("thread sleep fail",e);
        }
    }

}
