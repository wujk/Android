package com.lib.wlib.frame.activity.impl;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;

import com.lib.wlib.frame.activity.inter.TActivity;
import com.lib.wlib.frame.activity.inter.TitleWrapper;
import com.lib.wlib.frame.activity.util.SavedInstanceStateUtil;
import com.lib.wlib.frame.app.CusApp;
import com.lib.wlib.frame.net.impl.NetWorkImpl;
import com.lib.wlib.frame.utils.IntentTool;


public abstract class JActionBarActivity extends ActionBarActivity implements
        TActivity, TitleWrapper {
    
	protected HashMap<String, Object> bundle // 从上个页面传递来的map集合，用于存放参数
			, param; // 传递到下个页面的参数放入该map
	protected ActionBar ab;
	protected NetWorkImpl netWork;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initOther();
		super.onCreate(savedInstanceState);
		SavedInstanceStateUtil.getBundle(bundle, savedInstanceState);
		if(hasTitle()){
			ab = getSupportActionBar();
			setActionBar();
		}else{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		createView();
	}

	
	
	protected void initOther(){
		((CusApp)getApplication()).getActivities().add(this);
		param = new HashMap<String, Object>();
		bundle = IntentTool.getParameter(this);
		netWork = new NetWorkImpl(this, NetWorkImpl.POST);
	}

	/**
	 * 创建视图 如果getLayoutId()<= 0，则手动创建，否则就通过layoutId创建
	 */
	private void createView() {
		if (hasTitle() && hasCustomTitle()) {
			setCustomActionBar();
		}
		int layoutId = getLayoutId();
		if (layoutId > 0) { 
			setContentView(layoutId);
		}
		initContentView(); //初始化控件
	}

	private void setCustomActionBar() {
		ab.setDisplayShowCustomEnabled(true);
		int layoutId = getTitleViewId();
		if (layoutId > 0) {
			ab.setCustomView(layoutId);
		}else{
			ab.setCustomView(createTitleView());
		}
		initTitleView();
	}
	
	@Override
	public abstract void initTitleView();
	
	 //该方法中对actionbar设置
	//ab.setDisplayShowHomeEnabled(false);
	//ab.setDisplayShowTitleEnabled(false);
	//ab.setDisplayHomeAsUpEnabled(false);
	//ab.setDisplayUseLogoEnabled(false);
	protected abstract void setActionBar();
	
	@Override
	public abstract boolean hasCustomTitle();
	
	@Override
	public boolean hasTitle(){
		return true;
	}

	/***********************重写下面两个中的一个*********************/
	//代码创建title视图
	protected abstract View createTitleView();

	//xml创建title视图
	@Override
	public abstract int getTitleViewId();
	
	/****************重写上面两个中的一个****************************/

	@Override
	public abstract int getLayoutId();
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		((CusApp)getApplication()).getActivities().remove(this);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		SavedInstanceStateUtil.stayBundle(outState, this);
	}

}
