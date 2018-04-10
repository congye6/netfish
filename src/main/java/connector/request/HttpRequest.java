package connector.request;

import logger.Logger;
import util.StringUtil;

import javax.servlet.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by cong on 2018-04-08.
 */
public class HttpRequest implements ServletRequest{

    private static final String LINE_SPLITER="\r\n";

    private RequestLine requestLine;

    private RequestHeader requestHeader=new RequestHeader();

    private String body;


    public void buildRequest(InputStream inputStream) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        try {
            //请求行
            String requestLine=reader.readLine();
            this.requestLine=new RequestLine(requestLine);
            //请求头
            while(true){
                String headerLine=reader.readLine();
                if(StringUtil.isEmpty(headerLine))
                    break;
                requestHeader.addHeader(headerLine);
            }
            requestHeader.initSpecialHead();
            //body
            StringBuilder bodyBuilder=new StringBuilder();
            for(int i=0;i<requestHeader.getContentLength();i++){
                int readChar=reader.read();
                bodyBuilder.append((char)readChar);
            }

            body=bodyBuilder.toString();
        } catch (IOException e) {
            Logger.error("analyze request fail:"+e.getMessage());
        }
    }

    @Override
    public String toString(){
        String result=requestLine.toString()+LINE_SPLITER+requestHeader.toString()+LINE_SPLITER+body;
        return result;
    }

    public String getURI(){
        return requestLine.getPath();
    }

    public Object getAttribute(String name) {
        return null;
    }

    public Enumeration<String> getAttributeNames() {
        return null;
    }

    public String getCharacterEncoding() {
        return null;
    }

    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return 0;
    }

    public long getContentLengthLong() {
        return 0;
    }

    public String getContentType() {
        return null;
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String name) {
        return null;
    }

    public Enumeration<String> getParameterNames() {
        return null;
    }

    public String[] getParameterValues(String name) {
        return new String[0];
    }

    public Map<String, String[]> getParameterMap() {
        return null;
    }

    public String getProtocol() {
        return null;
    }

    public String getScheme() {
        return null;
    }

    public String getServerName() {
        return null;
    }

    public int getServerPort() {
        return 0;
    }

    public BufferedReader getReader() throws IOException {
        return null;
    }

    public String getRemoteAddr() {
        return null;
    }

    public String getRemoteHost() {
        return null;
    }

    public void setAttribute(String name, Object o) {

    }

    public void removeAttribute(String name) {

    }

    public Locale getLocale() {
        return null;
    }

    public Enumeration<Locale> getLocales() {
        return null;
    }

    public boolean isSecure() {
        return false;
    }

    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }

    public String getRealPath(String path) {
        return null;
    }

    public int getRemotePort() {
        return 0;
    }

    public String getLocalName() {
        return null;
    }

    public String getLocalAddr() {
        return null;
    }

    public int getLocalPort() {
        return 0;
    }

    public ServletContext getServletContext() {
        return null;
    }

    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    public boolean isAsyncStarted() {
        return false;
    }

    public boolean isAsyncSupported() {
        return false;
    }

    public AsyncContext getAsyncContext() {
        return null;
    }

    public DispatcherType getDispatcherType() {
        return null;
    }
}
