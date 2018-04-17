package container.wrapper;

import container.Container;

import javax.servlet.Servlet;

/**
 * Created by cong on 2018-04-16.
 */
public interface Wrapper extends Container{

    public void load();

    public Servlet allocate();


}
