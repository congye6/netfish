package webresource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * Created by cong on 2018-05-11.
 */
public class JarResourceSet implements WebResourceSet{

    private String jarPath;

    private WebResourceRoot webResourceRoot;

    private JarFile archive;

    private Map<String,JarEntry> jarEntries;

    private String webappAmount;

    public JarResourceSet(String jarPath, WebResourceRoot webResourceRoot, String webappAmount) {
        this.jarPath = jarPath;
        this.webResourceRoot = webResourceRoot;
        this.webappAmount = webappAmount;
        this.jarEntries=getArchiveEntries();
    }

    public WebResource getResource(String path) {

        if(!path.startsWith(webappAmount))
            return null;

        String pathInJar=path.substring(webappAmount.length());
        if(pathInJar.startsWith("/"))
            pathInJar=pathInJar.substring(1);

        return new JarResource(jarEntries.get(pathInJar),this);
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

    }

    public URL getBaseUrl() {
        return null;
    }


    private Map<String,JarEntry> getArchiveEntries() {
        Map<String,JarEntry> archiveEntries=new HashMap<>();

        JarFile jarFile = null;
        archiveEntries = new HashMap<>();
        try {
            jarFile = openJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                archiveEntries.put(entry.getName(), entry);
            }
        } finally {
            if (jarFile != null) {
                closeJarFile();
            }
        }

        return archiveEntries;

    }


    private JarEntry getArchiveEntry(String pathInArchive) {
        JarFile jarFile = null;
        try {
            jarFile = openJarFile();
            return jarFile.getJarEntry(pathInArchive);
        } finally {
            if (jarFile != null) {
                closeJarFile();
            }
        }
    }

    JarFile openJarFile(){
        if(archive!=null)
            return archive;
        try {
            archive=new JarFile(jarPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return archive;
    }

    private void closeJarFile(){
        if(archive==null)
            return;
        try {
            archive.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        archive=null;
    }

}
