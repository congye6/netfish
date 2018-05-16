package container.loader;

/**
 * Created by cong on 2018-04-24.
 */
public class ResourceEntry {

    /**
     * 最后一次更新的时间戳
     */
    private long lastModified;

    private Class<?> clazz;


    public ResourceEntry(long lastModified, Class<?> clazz) {
        this.lastModified = lastModified;
        this.clazz = clazz;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
