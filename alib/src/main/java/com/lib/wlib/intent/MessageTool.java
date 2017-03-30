package com.lib.wlib.intent;

import android.os.Bundle;
import android.os.Message;

/**
 * Created by txbydev4 on 17/3/24.
 */

public class MessageTool {

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public static Message putExtra(String key, Object value) {
        Message msg = new Message();
        return putExtra(msg, key, value);
    }
    /**
     * 构建Message
     * @param msg
     * @param key
     * @param value
     * @return
     */
    public static Message putExtra(Message msg, String key, Object value) {
        Bundle bundle = msg.getData();
        if (bundle == null) {
            bundle = new Bundle();
            msg.setData(bundle);
        }
        BundleMap data = bundle.getParcelable("data");
        if (data == null) {
            data = new BundleMap();
            bundle.putParcelable("data", data);
        }
        data.getBundle().put(key, value);
        return msg;
    }
}
