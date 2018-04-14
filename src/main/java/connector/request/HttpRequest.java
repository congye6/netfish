package connector.request;

import connector.cookie.CookieParser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.Principal;
import java.util.*;

/**
 * Created by cong on 2018-04-08.
 */
public class HttpRequest implements HttpServletRequest {

    private static final String COOKIE_SESSION_ID="JSESSIONID";

    private static final String LINE_SPLITER="\r\n";

    private RequestLine requestLine;

    private RequestHeader requestHeader=new RequestHeader();

    private String body;

    private SocketInputStream socketInputStream;

    private List<Cookie> cookies;

    private ParameterMap parameterMap;

    private boolean isRequestedSessionIdFromCookie=false;

    private String requestedSessionId;


    public void buildRequest(InputStream inputStream) {
        this.socketInputStream=new SocketInputStream(inputStream);
        this.requestLine=new RequestLine(socketInputStream.getRequestLine());
        this.requestHeader=new RequestHeader();
        for(String header:socketInputStream.getRequestHeaders()){
            requestHeader.addHeader(header);
        }
        cookies= new CookieParser().parse(requestHeader.getHeader(RequestHeaderKey.COOKIE));
        Cookie sessionId=getCookie(COOKIE_SESSION_ID);
        if(sessionId!=null){
            isRequestedSessionIdFromCookie=true;
            requestedSessionId=sessionId.getValue();
        }

        body=new String(socketInputStream.getBody(getContentLength()));
        parameterMap=new ParameterMap(this);
    }

    @Override
    public String toString(){
        String result=requestLine.toString()+LINE_SPLITER+requestHeader.toString()+LINE_SPLITER+body;
        return result;
    }

    public String getBody(){
        return body;
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
        return requestHeader.getHeader(RequestHeaderKey.ACCEPT_ENCODING);
    }

    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {

    }

    public int getContentLength() {
        return Integer.parseInt(requestHeader.getHeader(RequestHeaderKey.CONTENT_LENGTH));
    }

    public long getContentLengthLong() {
        return getContentLength();
    }

    public String getContentType() {
        return requestHeader.getHeader(RequestHeaderKey.CONTENT_TYPE);
    }

    public ServletInputStream getInputStream() throws IOException {
        return null;
    }

    public String getParameter(String name) {
        return parameterMap.getParam(name);
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
        return requestLine.getProtocal();
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
        return requestHeader.getHeader(RequestHeaderKey.HOST);
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

    public String getAuthType() {
        return requestHeader.getHeader(RequestHeaderKey.AUTHORIZATION);
    }

    public Cookie[] getCookies() {
        return cookies.toArray(new Cookie[cookies.size()]);
    }

    /**
     * 查询cookie
     * @return
     */
    private Cookie getCookie(String name){
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }

    public long getDateHeader(String name) {
        return 0;
    }

    public String getHeader(String name) {
        return requestHeader.getHeader(name);
    }

    public Enumeration<String> getHeaders(String name) {
        return null;
    }

    public Enumeration<String> getHeaderNames() {
        return null;
    }

    public int getIntHeader(String name) {
        return Integer.parseInt(requestHeader.getHeader(name));
    }

    public String getMethod() {
        return requestLine.getMethod().name();
    }

    public String getPathInfo() {
        return null;
    }

    public String getPathTranslated() {
        return null;
    }

    public String getContextPath() {
        return null;
    }

    public String getQueryString() {
        return requestLine.getQueryString();
    }

    public String getRemoteUser() {
        return null;
    }

    public boolean isUserInRole(String role) {
        return false;
    }

    public Principal getUserPrincipal() {
        return null;
    }

    public String getRequestedSessionId() {
        return requestedSessionId;
    }

    public String getRequestURI() {
        return null;
    }

    public StringBuffer getRequestURL() {
        return null;
    }

    public String getServletPath() {
        return null;
    }

    public HttpSession getSession(boolean create) {
        return null;
    }

    public HttpSession getSession() {
        return null;
    }

    public String changeSessionId() {
        return null;
    }

    public boolean isRequestedSessionIdValid() {
        return false;
    }

    public boolean isRequestedSessionIdFromCookie() {
        return isRequestedSessionIdFromCookie;
    }

    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        return false;
    }

    public void login(String username, String password) throws ServletException {

    }

    public void logout() throws ServletException {

    }

    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    public Part getPart(String name) throws IOException, ServletException {
        return null;
    }

    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        return null;
    }


}
