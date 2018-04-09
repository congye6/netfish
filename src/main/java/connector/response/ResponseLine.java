package connector.response;

import enumeration.ResponseStatus;
import logger.Logger;
import util.HttpFormatUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by cong on 2018-04-09.
 */
public class ResponseLine {

    private String protocal;

    private int statusCode;

    private String statusDesc;

    public ResponseLine(String protocal, ResponseStatus status) {
        this.protocal = protocal;
        this.statusCode = status.getCode();
        this.statusDesc = status.getDesc();
    }

    void write(BufferedWriter writer){
        StringBuilder builder=new StringBuilder();
        try {
            builder.append(protocal);
            builder.append(HttpFormatUtil.SPACE_SPLITER);
            builder.append(statusCode);
            builder.append(HttpFormatUtil.SPACE_SPLITER);
            builder.append(statusDesc);
            builder.append(HttpFormatUtil.LINE_SPLITER);
            writer.write(builder.toString());
        } catch (IOException e) {
            Logger.error("write response fail:"+e.getMessage());
        }
    }

    public void setProtcal(String protcal) {
        this.protocal = protcal;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
