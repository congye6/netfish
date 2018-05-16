package webresource;

import java.io.InputStream;
import java.net.URL;
import java.util.Set;

/**
 * Represents a set of resources that are part of a web application. Examples
 * include a directory structure, a resources JAR and a WAR file.
 */
public interface WebResourceSet{
    /**
     * Obtain the object that represents the resource at the given path. Note
     * the resource at that path may not exist.
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The object that represents the resource at the given path
     */
    WebResource getResource(String path);

    /**
     * Obtain the list of the names of all of the files and directories located
     * in the specified directory.
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The list of resources. If path does not refer to a directory
     *          then a zero length array will be returned.
     */
    String[] list(String path);

    /**
     * Obtain the Set of the web applications pathnames of all of the files and
     * directories located in the specified directory. Paths representing
     * directories will end with a "/" character.
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The Set of resources. If path does not refer to a directory
     *          then an empty set will be returned.
     */
    Set<String> listWebAppPaths(String path);

    /**
     * Create a new directory at the given path.
     *
     * @param path  The path for the new resource to create relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  <code>true</code> if the directory was created, otherwise
     *          <code>false</code>
     */
    boolean mkdir(String path);

    /**
     * Create a new resource at the requested path using the provided
     * InputStream.
     *
     * @param path      The path to be used for the new Resource. It is relative
     *                  to the root of the web application and must start with
     *                  '/'.
     * @param is        The InputStream that will provide the content for the
     *                  new Resource.
     * @param overwrite If <code>true</code> and the resource already exists it
     *                  will be overwritten. If <code>false</code> and the
     *                  resource already exists the write will fail.
     *
     * @return  <code>true</code> if and only if the new Resource is written
     */
    boolean write(String path, InputStream is, boolean overwrite);

    void setRoot(WebResourceRoot root);

    /**
     * Obtain the base URL for this set of resources. One of the uses of this is
     * to grant read permissions to the resources when running under a security
     * manager.
     *
     * @return The base URL for this set of resources
     */
    URL getBaseUrl();




}
