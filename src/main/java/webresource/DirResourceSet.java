package webresource;

import util.StringUtil;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cong on 2018-05-09.
 */
public class DirResourceSet implements WebResourceSet{

    private String base;

    private String internalPath;

    private WebResourceRoot root;

    private String webappMount;

    private File filebase;

    public DirResourceSet(String base, String internalPath, WebResourceRoot root, String webappMount) {
        this.base = base;
        this.internalPath = internalPath;
        this.root = root;
        this.webappMount = webappMount;
        filebase=new File(base+webappMount);
    }

    public WebResource getResource(String path) {

        if(StringUtil.isEmpty(path)||!path.startsWith(webappMount))
            return null;

        File file=new File(base+path);
        if(file==null||!file.exists())
            return null;
        return new FileResource(file,root,path);
    }

    public String[] list(String path) {

        if(StringUtil.isEmpty(path)||!path.startsWith(webappMount))
            return new String[0];
        File file=new File(base+path);
        if(file==null||!file.exists())
            return new String[0];

        return file.list();
    }

    public Set<String> listWebAppPaths(String path) {
        Set<String> paths=new HashSet<String>();
        if(StringUtil.isEmpty(path)||!path.startsWith(webappMount))
            return paths;
        File file=new File(base+path);
        if(file==null||!file.exists())
            return paths;
        File[] files=file.listFiles();
        for(File entry:files){
            StringBuilder builder=new StringBuilder();
            builder.append(path);
            if(!path.endsWith("/"))
                builder.append("/");
            builder.append(entry.getName());
            if(entry.isDirectory())
                builder.append("/");
            paths.add(builder.toString());
        }
        return paths;
    }

    public boolean mkdir(String path) {
        return false;
    }

    public boolean write(String path, InputStream is, boolean overwrite) {
        return false;
    }

    public void setRoot(WebResourceRoot root) {
        this.root=root;
    }

    public URL getBaseUrl() {
        try {
            return filebase.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
