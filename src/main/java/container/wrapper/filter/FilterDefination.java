package container.wrapper.filter;

import java.util.*;

/**
 * Created by cong on 2018-05-21.
 * 过滤器定义
 */
public class FilterDefination {

    /**
     * 注释
     */
    private String description;

    /**
     * filter展示的名称
     */
    private String displayName;

    /**
     * 唯一标识filter的名称
     */
    private String filterName;

    /**
     * 类名
     */
    private String filterClass;

    private String largeIcon;

    private String smallIcon;

    /**
     * filter初始化的参数
     */
    private Map<String,String> parameters=new HashMap<>();

    public void addParameter(String key,String value){
        parameters.put(key,value);
    }

    public String getParameter(String key){
        return parameters.get(key);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }
}
