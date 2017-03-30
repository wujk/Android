package com.lib.wlib.metadata;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

/**
 * Created by txbydev4 on 17/3/3.
 */

public class MetaDataHelper {

    /**
     * 获取Application节点的meta-data
     * @param context
     * @param key
     * @return
     */
    public Object getApplicationInfo(Context context, String key) {
        try{
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.get(key);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取Activity节点的meta-data
     * @param activity
     * @param key
     * @return
     */
    public Object getctivityInfo(Activity activity, String key) {
        try{
            ActivityInfo info=activity.getPackageManager().getActivityInfo(activity.getComponentName(), PackageManager.GET_META_DATA);
            return info.metaData.get(key);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取Service节点的meta-data
     * @param context
     * @param clazz
     * @param key
     * @return
     */
    public Object geterviceInfo(Context context, Class<?> clazz, String key) {
        try{
            ComponentName cn=new ComponentName(context, clazz);
            ServiceInfo info=context.getPackageManager().getServiceInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.get(key);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取Receiver节点的meta-data
     * @param context
     * @param clazz
     * @param key
     * @return
     */
    public Object getApplicationInfo(Context context, Class<?> clazz, String key) {
        try{
            ComponentName cn=new ComponentName(context, clazz);
            ActivityInfo info=context.getPackageManager().getReceiverInfo(cn, PackageManager.GET_META_DATA);
            return info.metaData.get(key);
        } catch (Exception e) {
            return null;
        }

    }

}
