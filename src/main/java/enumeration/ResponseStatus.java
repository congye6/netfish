package enumeration;

/**
 * Created by cong on 2018-04-09.
 */
public enum ResponseStatus {


    OK(200,"OK"),NOT_FOUND(404,"File Not Found");

    private int code;

    private String desc;

    private ResponseStatus(int code,String desc){
        this.code=code;
        this.desc=desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
