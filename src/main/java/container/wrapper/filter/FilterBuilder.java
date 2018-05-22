package container.wrapper.filter;

import container.context.Context;
import container.loader.Loader;
import container.loader.WebappLoader;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Enumeration;

/**
 * Created by cong on 2018-05-21.
 */
public class FilterBuilder implements FilterConfig{


    private Context context;

    private FilterDefination filterDefination;

    private Filter filter;


    public FilterBuilder(Context context, FilterDefination filterDefination) {
        this.context = context;
        this.filterDefination = filterDefination;
    }

    /**
     * 创建一个filter并初始化
     * @return
     */
    public Filter getFilter(){
        if(filter!=null)
            return filter;
        Loader loader=context.getLoader();//TODO 加载netfish自带的filter
        Class<Filter> clazz=loader.load(filterDefination.getFilterClass());
        try {
            filter=clazz.newInstance();
            filter.init(this);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return filter;
    }


    @Override
    public String getFilterName() {
        return filterDefination.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return context.getServletContext();
    }

    @Override
    public String getInitParameter(String name) {
        return filterDefination.getParameter(name);s
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return null;
    }
}
