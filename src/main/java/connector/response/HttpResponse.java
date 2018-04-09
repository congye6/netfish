package connector.response;

import connector.request.HttpRequest;
import enumeration.ResponseStatus;
import logger.Logger;
import util.HttpFormatUtil;
import util.StringUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * Created by cong on 2018-04-09.
 */
public class HttpResponse implements ServletResponse{

    private HttpRequest request;

    private ResponseLine responseLine;

    private ResponseHeader responseHeader=new ResponseHeader();




    public void addHeader(String key,String value){
        responseHeader.addHeader(key,value);
    }


    public void write(OutputStream outputStream){
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));

        File file=new File(HttpFormatUtil.WEB_ROOT,"StringUtil.java");
        if(file.exists()){
            responseHeader.addHeader("Content-Length",file.length()+"");
            responseLine=new ResponseLine(HttpFormatUtil.HTTP_PROTOCAL,ResponseStatus.OK);
            responseLine.write(bufferedWriter);
            responseHeader.write(bufferedWriter);
            
            try {
                bufferedWriter.write(HttpFormatUtil.LINE_SPLITER);
                FileReader reader=new FileReader(file);
                char[] bytes=new char[(int)file.length()];
                reader.read(bytes);
                bufferedWriter.write(bytes);
                bufferedWriter.flush();
            } catch (IOException e) {
                Logger.error(e.getMessage());
            }
        }else{
            ResponseLine responseLine=new ResponseLine(HttpFormatUtil.HTTP_PROTOCAL, ResponseStatus.NOT_FOUND);
            responseLine.write(bufferedWriter);

            String body="File Not Found";
            responseHeader.addHeader("Content-Length",body.length()+"");
            responseHeader.write(bufferedWriter);
            try {
                bufferedWriter.write(HttpFormatUtil.LINE_SPLITER);
                bufferedWriter.write(body);
                bufferedWriter.flush();
            } catch (IOException e) {
                Logger.error("not found page error,e:"+e.getMessage());
            }
        }
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public String getContentType() {
        return null;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return null;
    }

    public void setCharacterEncoding(String charset) {

    }

    public void setContentLength(int len) {

    }

    public void setContentLengthLong(long len) {

    }

    public void setContentType(String type) {

    }

    public void setBufferSize(int size) {

    }

    public int getBufferSize() {
        return 0;
    }

    public void flushBuffer() throws IOException {

    }

    public void resetBuffer() {

    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {

    }

    public void setLocale(Locale loc) {

    }

    public Locale getLocale() {
        return null;
    }
}
