package webresource;

import container.context.Context;
import util.HttpFormatUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by cong on 2018-05-12.
 */
public class StandardRoot implements WebResourceRoot{


    private List<WebResourceSet> resources=new ArrayList<>();

    private Context context;

    public StandardRoot(Context context) {
        this.context = context;
        initResources();
        processLib();
    }

    private void initResources() {
        String docbase= HttpFormatUtil.WEB_ROOT+context.getDocbase();
        DirResourceSet main=new DirResourceSet(docbase,"/",this,"/");
        resources.add(main);
    }

    /**
     * 将lib中的每个jar包作为一个资源集合
     */
    private void processLib(){
        WebResource[] jars=listResources("/webinf/lib");
        for(WebResource jar:jars){
            String jarPath=jar.getURL().toString();
            JarResourceSet jarResourceSet=new JarResourceSet(jarPath,this,"/webinf/classes");
            resources.add(jarResourceSet);
        }
    }

    @Override
    public WebResource getResource(String path) {
        for(WebResourceSet resourceSet:resources){
            WebResource resource=resourceSet.getResource(path);
            if(resource!=null)
                return resource;
        }
        return null;
    }

    @Override
    public WebResource[] getResources(String path) {
        return new WebResource[0];
    }

    @Override
    public WebResource getClassLoaderResource(String path) {
        return getResource("/webinf/classes"+path);
    }

    @Override
    public WebResource[] getClassLoaderResources(String path) {
        return new WebResource[0];
    }

    @Override
    public String[] list(String path) {
        List<String> result=new ArrayList<>();
        for(WebResourceSet resourceSet:resources){
           String[] entries=resourceSet.list(path);
           for(String entry:entries){
               result.add(entry);
           }
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public Set<String> listWebAppPaths(String path) {
        return null;
    }

    @Override
    public WebResource[] listResources(String path) {
        String[] entries=list(path);
        WebResource[] results=new WebResource[entries.length];

        for(int i=0;i<entries.length;i++){
            for(WebResourceSet resourceSet:resources){
                WebResource webResource=resourceSet.getResource(entries[i]);
                if(webResource!=null){
                    results[i]=webResource;
                    break;
                }
            }
        }
        return results;
    }

    @Override
    public void createWebResourceSet(String webAppMount, URL url, String internalPath) {

    }

    @Override
    public void createWebResourceSet(String webAppMount, String base, String archivePath, String internalPath) {

    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }
}
