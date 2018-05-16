package webresource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;

/**
 * Created by cong on 2018-05-09.
 * 一个文件组成的资源集合
 */
public class FileResourceSet implements WebResourceSet{

    private WebResourceRoot root;
    private String base;
    private String internalPath = "";
    private String webAppMount;

    public FileResourceSet(WebResourceRoot root, String base, String internalPath, String webAppMount) {
        this.root = root;
        this.base = base;
        this.internalPath = internalPath;
        this.webAppMount = webAppMount;
    }

    public WebResource getResource(String path) {
        return null;
    }

    public String[] list(String path) {
        return new String[0];
    }

    public Set<String> listWebAppPaths(String path) {
        return null;
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
        return null;
    }


}
