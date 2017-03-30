package com.lib.wlib.intent;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by txbydev4 on 17/1/24.
 */

public class BundleMap implements Parcelable {
    private Map bundle;

    public BundleMap() {
        bundle = new HashMap();
    }

    public BundleMap(Parcel in) {
        in.readMap(bundle, this.getClass().getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(bundle);
    }

    public static final Parcelable.Creator<BundleMap> CREATOR = new Creator<BundleMap>()
    {
        @Override
        public BundleMap[] newArray(int size)
        {
            return new BundleMap[size];
        }

        @Override
        public BundleMap createFromParcel(Parcel in)
        {
            return new BundleMap(in);
        }
    };

    public Map getBundle() {
        return bundle;
    }
}
