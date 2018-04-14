package enumeration;

/**
 * Created by cong on 2018-04-13.
 */
public enum ContentType {

    ENCODE_URL("application/x-www-form-urlencoded"),
    FORM_DATA("multipart/form-data"),
    JSON("application/json"),
    XML("text/xml");

    private String value;

    ContentType(String value){
        this.value=value;
    }

    public boolean isContentType(String value){
        return this.value.equals(value);
    }

    public String getValue() {
        return value;
    }
}
