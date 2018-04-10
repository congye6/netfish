package connector.processor;

import connector.request.HttpRequest;
import connector.response.HttpResponse;
import connector.response.ResponseLine;
import enumeration.ResponseStatus;
import util.HttpFormatUtil;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by cong on 2018-04-09.
 */
public class DynamicResourceProcessor implements Processor{


    public void process(HttpRequest request, HttpResponse response) {
        File classPath=new File(HttpFormatUtil.WEB_ROOT);
        try {
            response.setResponseLine(new ResponseLine(HttpFormatUtil.HTTP_PROTOCAL, ResponseStatus.OK));

            URL url=new URL("file",null,classPath.getCanonicalFile()+File.separator);
            URLClassLoader classLoader=new URLClassLoader(new URL[]{url});

            int index=request.getURI().lastIndexOf('/');
            String servletName=request.getURI().substring(index+1);
            Class servletClazz=classLoader.loadClass(servletName);
            Servlet servlet=(Servlet)servletClazz.newInstance();
            servlet.service(request,response);
            response.write();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }
}
