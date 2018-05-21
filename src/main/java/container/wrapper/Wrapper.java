package container.wrapper;

import container.Container;

import javax.servlet.Servlet;

/**
 * Created by cong on 2018-04-16.
 */
public interface Wrapper extends Container{

    /**
     * 反射加载一个servlet
     * @return
     */
    public Servlet load();

    /**
     * 分配一个servlet
     * stm servlet从对象池中分配，否则共用一个servlet
     * @return
     */
    public Servlet allocate();

    /**
     * 回收stm servlet
     * 如果不是stm则不处理
     */
    public void recycle(Servlet servlet);

    /**
     * 添加servlet初始化参数
     * @param key
     * @param value
     */
    public void addParam(String key,String value);


}
