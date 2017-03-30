package com.lib.wlib.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by txbydev4 on 17/1/24.
 */

public class IntentTool {

    /**
     * 创建Intent
     * @param action
     * @return
     */
    public static Intent makeIntent(String action) {
        Intent intent = new Intent(action);
        return intent;
    }

    /**
     * 创建Intent
     * @param context
     * @param c
     * @return
     */
    public static Intent makeIntent(Context context, Class<?> c) {
        Intent intent = new Intent(context, c);
        return intent;
    }

    /**
     * 获取Intent
     * @param activity
     * @return
     */
    public static Intent getIntent(Activity activity) {
        return activity.getIntent();
    }

    /**
     * 加入参数
     * @param intent
     * @param key
     * @param value
     * @return
     */
    public static Intent putExtra(Intent intent, String key, Object value) {
        BundleMap data = intent.getParcelableExtra("data");
        if (data == null) {
            data = new BundleMap();
            intent.putExtra("data", data);
        }
        data.getBundle().put(key, value);
        return intent;
    }

    /**
     * 获取参数
     * @param intent
     * @param key
     * @return
     */
    public static Object getExtra(Intent intent, String key) {
        BundleMap data = intent.getParcelableExtra("data");
        if (data == null) {
            data = new BundleMap();
        }
        return data.getBundle().get(key);
    }

}
