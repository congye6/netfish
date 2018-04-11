package connector.response;

import connector.request.HttpRequest;
import enumeration.ResponseStatus;
import logger.Logger;
import util.HttpFormatUtil;
import util.StringUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by cong on 2018-04-09.
 */
public class HttpResponse implements HttpServletResponse {

    private HttpRequest request;

    private ResponseLine responseLine;

    private ResponseHeader responseHeader=new ResponseHeader();


    private OutputStream outputStream;

    /**
     * 将用户的输出缓存
     */
    private OutputBuffer buffer=new OutputBuffer();


    public HttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }



    public void write(){
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream));
        responseLine.write(writer);
        responseHeader.addHeader("Content-Length",buffer.size()+"");
        responseHeader.write(writer);
        try {
            writer.write(HttpFormatUtil.LINE_SPLITER);
            writer.flush();
            buffer.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendStaticResource(){
        BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
        String fileName=request.getURI();
        File file=new File(HttpFormatUtil.WEB_ROOT,fileName);
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

    public void setResponseLine(ResponseLine responseLine) {
        this.responseLine = responseLine;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void addCookie(Cookie cookie) {

    }

    public boolean containsHeader(String name) {
        return responseHeader.containsHeader(name);
    }

    public String encodeURL(String url) {
        return null;
    }

    public String encodeRedirectURL(String url) {
        return null;
    }

    public String encodeUrl(String url) {
        return null;
    }

    public String encodeRedirectUrl(String url) {
        return null;
    }

    public void sendError(int sc, String msg) throws IOException {

    }

    public void sendError(int sc) throws IOException {

    }

    public void sendRedirect(String location) throws IOException {

    }

    public void setDateHeader(String name, long date) {
        responseHeader.addHeader(name,date+"");
    }

    public void addDateHeader(String name, long date) {
        responseHeader.addHeader(name,date+"");
    }

    public void setHeader(String name, String value) {
        responseHeader.addHeader(name,value);
    }

    public void addHeader(String key, String value){
        responseHeader.addHeader(key,value);
    }

    public void setIntHeader(String name, int value) {
        responseHeader.addHeader(name,value+"");
    }

    public void addIntHeader(String name, int value) {
        responseHeader.addHeader(name,value+"");
    }

    public void setStatus(int sc) {
        responseLine.setStatusCode(sc);
    }

    public void setStatus(int sc, String sm) {
        responseLine.setStatusCode(sc);
        responseLine.setStatusDesc(sm);
    }

    public int getStatus() {
        return responseLine.;
    }

    public String getHeader(String name) {
        return null;
    }

    public Collection<String> getHeaders(String name) {
        return null;
    }

    public Collection<String> getHeaderNames() {
        return null;
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
        return new PrintWriter(buffer.getBufferStream(),true);
    }

    public void setCharacterEncoding(String charset) {
        responseHeader.addHeader(ResponseHeaderKey.CONTENT_ENCODING,charset);
    }

    public void setContentLength(int len) {
        responseHeader.addHeader(ResponseHeaderKey.CONTENT_LENGTH,len+"");
    }

    public void setContentLengthLong(long len) {
        responseHeader.addHeader(ResponseHeaderKey.CONTENT_LENGTH,len+"");
    }

    public void setContentType(String type) {
        responseHeader.addHeader(ResponseHeaderKey.CONTENT_TYPE,type);
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
