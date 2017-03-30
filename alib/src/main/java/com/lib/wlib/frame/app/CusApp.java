package com.lib.wlib.frame.app;

import java.util.LinkedList;
import java.util.List;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import android.app.Activity;

import com.lib.wlib.frame.utils.ImageLoaderTool;
import com.lib.wlib.frame.utils.ImageTool;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CusApp extends LitePalApplication {
	private List<Activity> mActivities = new LinkedList<Activity>();

	public List<Activity> getActivities() {
		return mActivities;
	} 

	public void onDestroy() {
		ImageTool.clearImageCache();
		finishActivities();
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	public void finishActivities() {
		for (Activity activity : mActivities) {
			activity.finish();
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		//CrashHandler.getInstance().init(this);
		ImageLoaderTool.initImageLoader(getApplicationContext());
		Connector.getDatabase();
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		ImageTool.clearImageCache();
		ImageLoader.getInstance().clearMemoryCache();
	}

}
