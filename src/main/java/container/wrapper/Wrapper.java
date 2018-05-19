package container.wrapper;

import container.Container;

import javax.servlet.Servlet;

/**
 * Created by cong on 2018-04-16.
 */
public interface Wrapper extends Container{

    public Servlet load();

    public Servlet allocate();


}
