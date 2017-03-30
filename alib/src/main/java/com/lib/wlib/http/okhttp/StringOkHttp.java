package com.lib.wlib.http.okhttp;

/**
 * Created by txbydev4 on 17/3/7.
 */

public class StringOkHttp extends OkHttp {

    public StringOkHttp() {
        this(null);
    }

    public StringOkHttp(String url) {
        this(url, null);
    }

    public StringOkHttp(String url, String param) {
        super(url);
        this.param = param;
        body(param, TYPE.STRING.getValue());
    }

}
