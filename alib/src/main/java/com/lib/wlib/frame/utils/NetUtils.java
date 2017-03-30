package com.lib.wlib.frame.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/**
 * 跟网络相关的工具类
 */
public class NetUtils {
	private NetUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (null != connectivity) {

			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否是wifi连接
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm == null)
			return false;
		return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

	}

	/**
	 * 判断当前何种网络
	 * 
	 * @param context
	 */
	public static int getNetWorkInfo(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State wifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		State mobile = connectivity.getNetworkInfo(
				ConnectivityManager.TYPE_MOBILE).getState();
		if (wifi == State.CONNECTED) {
			return ConnectivityManager.TYPE_WIFI;
		}
		if (mobile == State.CONNECTED) {
			return ConnectivityManager.TYPE_MOBILE;
		}
		return -1;
	}

	/**
	 * 打开网络设置界面
	 */
	public static void openSetting(Context context) {
		/*
		 * Intent intent = new Intent("/"); ComponentName cm = new
		 * ComponentName("com.android.settings",
		 * "com.android.settings.WirelessSettings"); intent.setComponent(cm);
		 * intent.setAction("android.intent.action.VIEW");
		 * activity.startActivityForResult(intent, 0); Intent intent=new
		 * Intent(); intent.setClassName("com.android.settings",
		 * "com.android.settings.Settings"); activity.startActivity(intent);
		 */
		context.startActivity(new Intent(
				android.provider.Settings.ACTION_WIRELESS_SETTINGS));

	}

}
