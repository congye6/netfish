package webresource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Created by cong on 2018-05-11.
 */
public class JarResource implements WebResource{

    private JarEntry resource;

    private JarResourceSet resourceSet;

    public JarResource(JarEntry resource, JarResourceSet resourceSet) {
        this.resource = resource;
        this.resourceSet = resourceSet;
    }

    public long getLastModified() {
        return resource.getLastModifiedTime().toMillis();
    }

    public boolean exists() {
        return true;
    }

    public boolean isDirectory() {
        return resource.isDirectory();
    }

    public boolean isFile() {
        return !resource.isDirectory();
    }

    public boolean delete() {
        return false;
    }

    public String getName() {
        return resource.getName();
    }

    public long getContentLength() {
        return resource.getSize();
    }

    public String getCanonicalPath() {
        return null;
    }

    public boolean canRead() {
        return true;
    }

    public String getWebappPath() {
        return null;
    }

    public InputStream getInputStream() {
        JarFile jarFile=resourceSet.openJarFile();
        try {
            return jarFile.getInputStream(resource);
        } catch (IOException e) {
            return null;
        }
    }

    public byte[] getContent() {
        long len = resource.getSize();

        if (len < 0) {
            // Content is not applicable here (e.g. is a directory)
            return null;
        }

        int size = (int) len;
        byte[] result = new byte[size];

        int pos = 0;
        try (InputStream jisw = getInputStream()) {
            if (jisw == null) {
                // An error occurred, don't return corrupted content
                return null;
            }
            while (pos < size) {
                int n = jisw.read(result, pos, size - pos);
                if (n < 0) {
                    break;
                }
                pos += n;
            }

        } catch (IOException ioe) {
            // Don't return corrupted content
            return null;
        }

        return result;
    }

    public URL getURL() {
        return null;
    }

    public WebResourceRoot getWebResourceRoot() {
        return null;
    }

    public Manifest getManifest() {
        return null;
    }
}
