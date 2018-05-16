package webresource;

import java.io.InputStream;
import java.net.URL;
import java.util.jar.Manifest;

/**
 * Represents a file or directory within a web application. It borrows heavily
 * from {@link org.apache.catalina.WebResource}.
 */
public interface WebResource {
    /**
     * @return {@link java.io.File#lastModified()}.
     */
    long getLastModified();

    /**
     * @return {@link java.io.File#exists()}.
     */
    boolean exists();


    /**
     * @return {@link java.io.File#isDirectory()}.
     */
    boolean isDirectory();

    /**
     * @return {@link java.io.File#isFile()}.
     */
    boolean isFile();

    /**
     * @return {@link java.io.File#delete()}.
     */
    boolean delete();

    /**
     * @return {@link java.io.File#getName()}.
     */
    String getName();

    /**
     * @return {@link java.io.File#length()}.
     */
    long getContentLength();

    /**
     * @return {@link java.io.File#getCanonicalPath()}.
     */
    String getCanonicalPath();

    /**
     * @return {@link java.io.File#canRead()}.
     */
    boolean canRead();

    /**
     * @return The path of this resource relative to the web application root. If the
     * resource is a directory, the return value will end in '/'.
     */
    String getWebappPath();


    /**
     * Obtain an InputStream based on the contents of this resource.
     *
     * @return  An InputStream based on the contents of this resource or
     *          <code>null</code> if the resource does not exist or does not
     *          represent a file
     */
    InputStream getInputStream();

    /**
     * @return the cached binary content of this resource.
     */
    byte[] getContent();


    /**
     * @return a URL to access the resource or <code>null</code> if no such URL
     * is available or if the resource does not exist.
     */
    URL getURL();

    /**
     * @return a reference to the WebResourceRoot of which this WebResource is a
     * part.
     */
    WebResourceRoot getWebResourceRoot();


    /**
     * @return the manifest associated with this resource or @null if none.
     *
     * @see java.util.jar.JarFile#getManifest()
     */
    Manifest getManifest();
}