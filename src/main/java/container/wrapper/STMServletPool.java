package container.wrapper;

import logger.StandardLogger;

import javax.servlet.Servlet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by cong on 2018-05-19.
 * STM Servlet的对象池
 * 对象池中没有可用对象时阻塞线程
 */
public class STMServletPool {

    private static final int DEFAULT_MAX_SIZE=20;

    private int maxSize;

    private BlockingQueue<Servlet> pool;

    /**
     * 记录已经生成的servlet数量，不能超过maxsize
     */
    private int servletCount=0;

    private Wrapper wrapper;

    public STMServletPool(int maxSize,Wrapper wrapper) {
        this.maxSize = maxSize;
        this.wrapper=wrapper;
        pool=new ArrayBlockingQueue<Servlet>(maxSize);
    }

    public STMServletPool(Wrapper wrapper) {
        this(DEFAULT_MAX_SIZE,wrapper);
    }

    /**
     * 获取servlet对象，如果没有servlet对象则添加
     * 如果添加到最大线程则阻塞线程等待servlet执行完任务
     * @return
     */
    public Servlet get(){
        synchronized (pool){
            if(servletCount<maxSize&&pool.size()==0) {//pool中没有可用servlet且还可以继续添加servlet
                pool.offer(wrapper.load());
                servletCount++;
            }
            return takeFromQueue();
        }
    }


    /**
     * servlet对象执行完任务调用此方法回收
     * @param servlet
     */
    public void recycle(Servlet servlet){
        pool.offer(servlet);
    }

    private Servlet takeFromQueue(){
        try {
            return pool.take();
        } catch (InterruptedException e) {
            StandardLogger.error("servlet对象池获取对象被中断",e);
            return null;
        }
    }

}
