package enumeration;

/**
 * Created by cong on 2018-04-15.
 */
public enum Protocal {


    HTTP11("HTTP/1.1"),HTTP10("HTTP/1.0"),HTTP09("HTTP/0.9");

    private String value;

    Protocal(String value) {
        this.value = value;
    }

    private boolean isProtocal(String value){
        return this.value.equalsIgnoreCase(value);
    }
}
