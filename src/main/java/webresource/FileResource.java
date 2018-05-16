package webresource;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Manifest;

/**
 * Created by cong on 2018-05-09.
 */
public class FileResource implements WebResource{

    private File resource;

    private WebResourceRoot root;

    private String webappPath;

    public FileResource(File resource, WebResourceRoot root, String webappPath) {
        this.resource = resource;
        this.root = root;
        this.webappPath = webappPath;
    }

    public long getLastModified() {
        return resource.lastModified();
    }

    public boolean exists() {
        return resource.exists();
    }

    public boolean isDirectory() {
        return resource.isDirectory();
    }

    public boolean isFile() {
        return resource.isFile();
    }

    public boolean delete() {
        return resource.delete();
    }

    public String getName() {
        return resource.getName();
    }

    public long getContentLength() {
        if(resource.isDirectory())
            return -1;
        return resource.length();
    }

    public String getCanonicalPath() {
        try {
            return resource.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean canRead() {
        return resource.canRead();
    }

    public String getWebappPath() {
        return webappPath;
    }

    public InputStream getInputStream() {
        try {
            return new FileInputStream(resource);
        } catch (FileNotFoundException fnfe) {
            return null;
        }
    }

    public byte[] getContent() {
        int size = (int) resource.length();
        byte[] result = new byte[size];

        int pos = 0;
        try (InputStream is = new FileInputStream(resource)) {
            while (pos < size) {
                int n = is.read(result, pos, size - pos);
                if (n < 0) {
                    break;
                }
                pos += n;
            }
        } catch (IOException ioe) {
            return null;
        }

        return result;
    }


    public URL getURL() {
        if(!resource.exists())
            return null;

        try {
            return resource.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }


    public WebResourceRoot getWebResourceRoot() {
        return root;
    }

    public Manifest getManifest() {
        return null;
    }
}
