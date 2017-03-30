package com.lib.wlib.customview;

import java.util.Arrays;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.lib.wlib.R;
import com.lib.wlib.viewhelper.attrbuset.ViewPagerAttrbuteSet;


public class ImageViewPager extends FrameLayout implements CustomView, OnCheckedChangeListener, OnPageChangeListener{
	private ViewPagerAttrbuteSet attrbuteSet = new ViewPagerAttrbuteSet();
	private ViewPager pager;
	private RadioGroup group;
	private Class<?> adapter;
	private Object o;
	private int[] ids = {R.id.rb1, R.id.rb2, R.id.rb3};
	private Context context;

	public ImageViewPager(Context context) {
		super(context, null);
	}

	public ImageViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.image_view_pager);
		attrbuteSet.adapter = a.getString(R.styleable.image_view_pager_pager_adapter);
		a.recycle();
		initView(context);
	}

	@SuppressLint("InflateParams") @Override
	public void initView(Context context) {
		LayoutInflater mInflater = LayoutInflater.from(context);
		View view = mInflater.inflate(R.layout.image_view_pager, null);
		pager = (ViewPager) view.findViewById(R.id.viewPager);
		group = (RadioGroup) view.findViewById(R.id.rd);
		group.setOnCheckedChangeListener(this);
		pager.setCurrentItem(0);
		pager.setOnPageChangeListener(this);
		setAdapter();
		addView(view);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		for (int i = 0; i < ids.length; i++) {
			if(checkedId == ids[i]){
				pager.setCurrentItem(i);
			}
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int arg0) {
		((RadioButton)group.findViewById(ids[arg0])).setChecked(true);
	}
	
	private void setAdapter(PagerAdapter adapter){
		pager.setAdapter(adapter);
	}
	
	private void setAdapter(){
		try {
			adapter = Class.forName(attrbuteSet.adapter);
			System.out.println(attrbuteSet.adapter);
			System.out.println(Arrays.toString(adapter.getDeclaredConstructors()));
			o = adapter.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
			setAdapter((PagerAdapter)o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
