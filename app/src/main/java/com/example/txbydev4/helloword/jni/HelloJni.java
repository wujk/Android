package com.example.txbydev4.helloword.jni;
/**
 * Created by txbydev4 on 17/3/27.
 */

public class HelloJni {
    static {
        System.loadLibrary("hello");
    }

    public native String sayHello();
}
