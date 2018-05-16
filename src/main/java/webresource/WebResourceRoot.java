package webresource;
import container.context.Context;

import java.net.URL;
import java.util.Set;
public interface WebResourceRoot {
    /**
     * Obtain the object that represents the resource at the given path. Note
     * that the resource at that path may not exist. If the path does not
     * exist, the WebResource returned will be associated with the main
     * WebResourceSet.
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The object that represents the resource at the given path
     */
    WebResource getResource(String path);

    /**
     * Obtain the objects that represent the resource at the given path. Note
     * that the resource at that path may not exist. If the path does not
     * exist, the WebResource returned will be associated with the main
     * WebResourceSet. This will include all matches even if the resource would
     * not normally be accessible (e.g. because it was overridden by another
     * resource)
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The objects that represents the resource at the given path
     */
    WebResource[] getResources(String path);

    /**
     * Obtain the object that represents the class loader resource at the given
     * path. WEB-INF/classes is always searched prior to searching JAR files in
     * WEB-INF/lib. The search order for JAR files will be consistent across
     * subsequent calls to this method until the web application is reloaded. No
     * guarantee is made as to what the search order for JAR files may be.
     *
     * @param path  The path of the class loader resource of interest relative
     *              to the the root of class loader resources for this web
     *              application.
     *
     * @return  The object that represents the class loader resource at the
     *          given path
     */
    WebResource getClassLoaderResource(String path);

    /**
     * Obtain the objects that represent the class loader resource at the given
     * path. Note that the resource at that path may not exist. If the path does
     * not exist, the WebResource returned will be associated with the main
     * WebResourceSet. This will include all matches even if the resource would
     * not normally be accessible (e.g. because it was overridden by another
     * resource)
     *
     * @param path  The path for the class loader resource of interest relative
     *              to the root of the class loader resources for the web
     *              application. It must start with '/'.
     *
     * @return  The objects that represents the class loader resources at the
     *          given path
     */
    WebResource[] getClassLoaderResources(String path);

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
     * directories will end with a '/' character.
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The Set of resources. If path does not refer to a directory
     *          then null will be returned.
     */
    Set<String> listWebAppPaths(String path);

    /**
     * Obtain the list of all of the WebResources in the specified directory.
     *
     * @param path  The path for the resource of interest relative to the root
     *              of the web application. It must start with '/'.
     *
     * @return  The list of resources. If path does not refer to a directory
     *          then a zero length array will be returned.
     */
    WebResource[] listResources(String path);


    /**
     * Creates a new {@link WebResourceSet} for this {@link WebResourceRoot}
     * based on the provided parameters.
     *
     * @param type          The type of {@link WebResourceSet} to create
     * @param webAppMount   The path within the web application that the
     *                          resources should be published at. It must start
     *                          with '/'.
     * @param url           The URL of the resource (must locate a JAR, file or
     *                          directory)
     * @param internalPath  The path within the resource where the content is to
     *                          be found. It must start with '/'.
     */
    void createWebResourceSet(String webAppMount, URL url,
            String internalPath);

    /**
     * Creates a new {@link WebResourceSet} for this {@link WebResourceRoot}
     * based on the provided parameters.
     *
     * @param webAppMount   The path within the web application that the
     *                          resources should be published at. It must start
     *                          with '/'.
     * @param base          The location of the resources
     * @param archivePath   The path within the resource to the archive where
     *                          the content is to be found. If there is no
     *                          archive then this should be <code>null</code>.
     * @param internalPath  The path within the archive (or the resource if the
     *                          archivePath is <code>null</code> where the
     *                          content is to be found. It must start with '/'.
     */
    void createWebResourceSet( String webAppMount,
            String base, String archivePath, String internalPath);


    /**
     * @return the web application this WebResourceRoot is associated with.
     */
    Context getContext();

    /**
     * Set the web application this WebResourceRoot is associated with.
     *
     * @param context the associated context
     */
    void setContext(Context context);

}
