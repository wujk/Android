package com.lib.wlib.http.okhttp;

import com.lib.jlib.util.ObjUtil;

import java.io.File;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by txbydev4 on 17/3/6.
 */

public class OkHttp {
    protected OkHttpClient client = null;
    protected String url;
    protected int code;
    protected String param;
    protected String body;
    protected String message;
    protected Map<String, Object> paramBody;
    protected Request request;
    protected Response response;
    protected Request.Builder builder;

    public enum TYPE {
        STRING(MediaType.parse("text/plain")), JSON(MediaType.parse("application/json; charset=utf-8")), STREAM(MediaType.parse("text/x-markdown; charset=utf-8"));
        private MediaType value;
        private TYPE() {}
        private TYPE(MediaType value) {}

        public MediaType getValue() {
            return value;
        }

        public void setValue(MediaType value) {
            this.value = value;
        }
    }

    public OkHttp() {
        this(null);
    }

    public OkHttp(String url) {
        client = new OkHttpClient();
        builder = new Request.Builder();
        url(url);
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public String getMessage() {
        return message;
    }

    public Response getResponse() {
        return response;
    }

    /**
     * url赋值
     * @param url
     */
    public OkHttp url(String url) {
        this.url = url;
        builder.url(url);
        return this;
    }

    /**
     * 构建返回Request对象
     * @return
     */
    public Request getRequest() {
        request = builder.build();
        return request;
    }

    /**
     * 构建post请求对象
     * @param body
     * @param type
     */
    public OkHttp body(Object body, MediaType type) {
        if(ObjUtil.checkClass(String.class, body)) {
            builder.post(RequestBody.create(type, (String) body));
        } else if (ObjUtil.checkClass(File.class, body)) {
            builder.post(RequestBody.create(type, (File) body));
        }
        return this;
    }

    /**
     * 创建Request对象
     * @param url
     * @param body
     * @param type
     * @return
     */
    public Request makeRequest(String url, Object body, MediaType type) {
        url(url);
        body(body, type);
        return getRequest();
    }


    public void paramBody(Map<String, Object> paramBody) {
        this.paramBody = paramBody;
    }

    /**
     * 同步get
     * @return
     */
    public Response get() {
        return get(null);
    }

    /**
     * 同步get
     * @return
     */
    public Response get(String url) {
        url(ObjUtil.isEmpty(url) ? this.url : url);
        try {
            Response response = client.newCall(getRequest()).execute();
            this.code = response.code();
            this.body = response.body().string();
            this.message = response.message();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 同步post
     * @return
     */
    public Response syhpost() {
        return syhpost(getRequest());
    }

    /**
     * 同步post
     * @param request
     * @return
     */
    public Response syhpost(Request request) {
        try {
            Response response = client.newCall(request).execute();
            this.code = response.code();
            this.body = response.body().string();
            this.message = response.message();
            return response;
        } catch (Exception e) {
            return null;
        }
    }

}
