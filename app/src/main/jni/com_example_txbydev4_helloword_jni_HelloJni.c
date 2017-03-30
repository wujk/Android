#include "com_example_txbydev4_helloword_jni_HelloJni.h"
JNIEXPORT jstring JNICALL Java_com_example_txbydev4_helloword_jni_HelloJni_sayHello
  (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "Hello Jni");
  }
