package container.loader;

import logger.Logger;
import logger.StandardLogger;
import util.HttpFormatUtil;
import util.StringUtil;
import webresource.WebResource;
import webresource.WebResourceRoot;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;


/**
 * Created by cong on 2018-04-19.
 */
public class WebappClassLoader extends URLClassLoader implements Reloader{

    private List<String> repositories=new ArrayList<String>();

    private WebResourceRoot resources;

    private static final String CLASS_SUFFIX=".class";

    private Map<String,ResourceEntry> cachedClass=new HashMap<String,ResourceEntry>();

    private Map<String,Long> jarModifiedTimes;

    public WebappClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    public WebappClassLoader() {
        super(new URL[0]);
    }

    public void addRepository(String repository) {
        repositories.add(repository);
        URL url=getUrl(repository);
        if(url!=null)
            addURL(url);

    }

    public List<String> getRepositories() {
        return new ArrayList<String>(repositories);
    }

    /**
     * todo 检查class文件是否被修改
     * @return
     */
    public boolean modified() {
        //检查class文件是否被修改
        for(Map.Entry<String,ResourceEntry> entry:cachedClass.entrySet()){
            WebResource resource=resources.getResource(getPath(entry.getKey()));
            if(!resource.exists())
                return true;
            if(resource.getLastModified()!=entry.getValue().getLastModified())
                return true;
        }

        WebResource[] jars=resources.listResources("/webinf/lib");
        if(jarModifiedTimes==null){
            jarModifiedTimes=new HashMap<>();
            for(WebResource jar:jars){
                jarModifiedTimes.put(jar.getName(),jar.getLastModified());
            }
            return false;
        }else{
            for(WebResource jar:jars){
                Long lastModified=jarModifiedTimes.get(jar.getName());
                if(lastModified==null||lastModified!=jar.getLastModified())
                    return true;
            }
            return false;
        }

    }


    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        ResourceEntry resourceEntry=cachedClass.get(name);
        Class<?> clazz=null;
        if(resourceEntry!=null){
            StandardLogger.info("----------------find clazz from cache");
            return resourceEntry.getClazz();
        }


        WebResource resource=resources.getClassLoaderResource(getPath(name));
        if(resource!=null){
            byte[] content=resource.getContent();
            clazz=defineClass(name,content,0,content.length);
            cachedClass.put(name,new ResourceEntry(resource.getLastModified(),clazz));
            StandardLogger.info("----------------find clazz from child");
            return clazz;
        }

        //父类方法查找
        clazz=super.findClass(name);
        StandardLogger.info("----------------find clazz from parent");
        return clazz;
    }

    private String getPath(String name){
        return "/"+name+CLASS_SUFFIX;
    }

    private URL getUrl(String repository){
        File classPath=new File(HttpFormatUtil.WEB_ROOT+ File.separator+repository);

        try {
            String repositoryPath=classPath.getCanonicalFile()+ File.separator;
            URL url=new URL("file",null,repositoryPath);
            return  url;
        } catch (IOException e) {
            StandardLogger.error("get url fail",e);
        }

        return null;
    }

    public WebResourceRoot getResources() {
        return resources;
    }

    public void setResources(WebResourceRoot resources) {
        this.resources = resources;
    }
}
