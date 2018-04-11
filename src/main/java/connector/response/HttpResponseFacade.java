package connector.response;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by cong on 2018-04-10.
 */
public class HttpResponseFacade implements HttpServletResponse {

    private HttpResponse response;

    public HttpResponseFacade(HttpResponse response) {
        this.response = response;
    }

    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }

    public String getContentType() {
        return response.getContentType();
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return response.getOutputStream();
    }

    public PrintWriter getWriter() throws IOException {
        return response.getWriter();
    }

    public void setCharacterEncoding(String charset) {
        response.setCharacterEncoding(charset);
    }

    public void setContentLength(int len) {
        response.setContentLength(len);
    }

    public void setContentLengthLong(long len) {
        response.setContentLengthLong(len);
    }

    public void setContentType(String type) {
        response.setContentType(type);
    }

    public void setBufferSize(int size) {
        response.setBufferSize(size);
    }

    public int getBufferSize() {
        return response.getBufferSize();
    }

    public void flushBuffer() throws IOException {
        response.flushBuffer();
    }

    public void resetBuffer() {
        response.resetBuffer();
    }

    public boolean isCommitted() {
        return response.isCommitted();
    }

    public void reset() {
        response.reset();
    }

    public void setLocale(Locale loc) {
        response.setLocale(loc);
    }

    public Locale getLocale() {
        return response.getLocale();
    }

    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    public boolean containsHeader(String name) {
        return response.containsHeader(name);
    }

    public String encodeURL(String url) {
        return response.encodeURL(url);
    }

    public String encodeRedirectURL(String url) {
        return response.encodeRedirectURL(url);
    }

    public String encodeUrl(String url) {
        return response.encodeUrl(url);
    }

    public String encodeRedirectUrl(String url) {
        return response.encodeRedirectUrl(url);
    }

    public void sendError(int sc, String msg) throws IOException {
        response.sendError(sc,msg);
    }

    public void sendError(int sc) throws IOException {
        response.sendError(sc);
    }

    public void sendRedirect(String location) throws IOException {
        response.sendRedirect(location);
    }

    public void setDateHeader(String name, long date) {
        response.setDateHeader(name,date);
    }

    public void addDateHeader(String name, long date) {
        response.addDateHeader(name,date);
    }

    public void setHeader(String name, String value) {
        response.setHeader(name,value);
    }

    public void addHeader(String name, String value) {
        response.addHeader(name,value);
    }

    public void setIntHeader(String name, int value) {
        response.setIntHeader(name,value);
    }

    public void addIntHeader(String name, int value) {
        response.addIntHeader(name,value);
    }

    public void setStatus(int sc) {
        response.setStatus(sc);
    }

    public void setStatus(int sc, String sm) {
        response.setStatus(sc,sm);
    }

    public int getStatus() {
        return response.getStatus();
    }

    public String getHeader(String name) {
        return response.getHeader(name);
    }

    public Collection<String> getHeaders(String name) {
        return response.getHeaders(name);
    }

    public Collection<String> getHeaderNames() {
        return response.getHeaderNames();
    }
}
