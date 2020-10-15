package org.noear.solon.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.*;

/**
 * 通用上下文，空对象
 *
 * @author noear
 * @since 1.0
 * */
public class XContextEmpty extends XContext {
    public static XContext create(){
        return new XContextEmpty();
    }

    public XContextEmpty(){
        sessionStateInit(new XSessionState() {
            @Override
            public String sessionId() {
                return null;
            }

            @Override
            public Object sessionGet(String key) {
                return sessionMap().get(key);
            }

            @Override
            public void sessionSet(String key, Object val) {
                sessionMap().put(key,val);
            }
        });
    }




    private Map<String,Object> sessionMap = null;
    public Map<String,Object> sessionMap(){
        if(sessionMap == null){
            sessionMap = new HashMap<>();
        }

        return sessionMap;
    }


    @Override
    public Object request() {
        return null;
    }

    @Override
    public String ip() {
        return null;
    }

    @Override
    public String method() {
        return null;
    }

    @Override
    public String protocol() {
        return null;
    }

    @Override
    public URI uri() {
        return null;
    }

    @Override
    public String path() {
        return null;
    }

    @Override
    public String url() {
        return null;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public String contentType() {
        return null;
    }

    @Override
    public InputStream bodyAsStream() throws IOException {
        return null;
    }

    @Override
    public String[] paramValues(String key) {
        return new String[0];
    }

    @Override
    public String param(String key) {
        return paramMap().get(key);
    }

    @Override
    public String param(String key, String def) {
        return paramMap().getOrDefault(key, def);
    }

    private XMap paramMap = null;
    @Override
    public XMap paramMap() {
        if(paramMap == null){
            paramMap = new XMap();
        }
        return paramMap;
    }

    @Override
    public Map<String, List<String>> paramsMap() {
        return null;
    }

    @Override
    public List<XFile> files(String key) throws Exception {
        return null;
    }

    @Override
    public String cookie(String key) {
        return cookieMap().get(key);
    }

    @Override
    public String cookie(String key, String def) {
        return cookieMap().getOrDefault(key,def);
    }

    XMap cookieMap = null;
    @Override
    public XMap cookieMap() {
        if(cookieMap == null){
            cookieMap = new XMap();
        }
        return cookieMap;
    }

    private XMap headerMap = null;
    @Override
    public XMap headerMap() {
        if(headerMap == null){
            headerMap = new XMap();
        }
        return headerMap;
    }

    @Override
    public Object response() {
        return null;
    }

    @Override
    protected void contentTypeDoSet(String contentType) {

    }

    @Override
    public void output(byte[] bytes) {

    }

    @Override
    public void output(InputStream stream) {

    }

    @Override
    public OutputStream outputStream() {
        return null;
    }

    @Override
    public void headerSet(String key, String val) {
        headerMap().put(key,val);
    }

    @Override
    public void headerAdd(String key, String val) {
        headerMap().put(key,val);
    }

    @Override
    public void cookieSet(String key, String val, String domain, String path, int maxAge) {
        cookieMap().put(key,val);
    }

    @Override
    public void redirect(String url) {

    }

    @Override
    public void redirect(String url, int code) {

    }

    private int status = 0;
    @Override
    public int status() {
        return status;
    }

    @Override
    public void statusSet(int status) {
        this.status = status;
    }

    @Override
    public void flush() throws IOException{

    }
}
