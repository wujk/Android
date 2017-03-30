package com.lib.wlib.customview;

import java.lang.reflect.Method;
import java.util.Arrays;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.lib.wlib.R;
import com.lib.wlib.frame.utils.Tools;
import com.lib.wlib.viewhelper.attrbuset.RLListViewAttrbuteSet;


public class RLListview extends FrameLayout implements CustomView{
	private RLListViewAttrbuteSet attrbuteSet = new RLListViewAttrbuteSet();
	private Object o1, o2, o3, o4, model;
	private Class<?> listener, adapter, refresh, load;
	private ListView listview;
	private RefreshableView refreshableView;
	private Context context;
	private int resourceId = R.layout.drop_down_listview;
	private boolean needLoad = true;
	private int from = 0; 

	public RLListview(Context context) {
		this(context, null);
	}

	public RLListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.item_view);
		attrbuteSet.onItemClickListener = a.getString(R.styleable.item_view_onitemclick);
		attrbuteSet.adapter = a.getString(R.styleable.item_view_adapter);
		attrbuteSet.onLoad = a.getString(R.styleable.item_view_onLoad);
		attrbuteSet.onRefresh = a.getString(R.styleable.item_view_onRefresh);
		needLoad = a.getBoolean(R.styleable.item_view_load, needLoad);
		resourceId = a.getResourceId(R.styleable.item_view_layoutId_2, resourceId);
		from = a.getInt(R.styleable.item_view_from, from);
		a.recycle();
		initView(context);
	}

	@Override
	public void initView(Context context) {
		LayoutInflater mInflater = LayoutInflater.from(context);
		View view = mInflater.inflate(resourceId, null);
		listview = (ListView) view.findViewById(R.id.listview);
		refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);
		refreshableView.setNeedLoad(needLoad);
		addOnItemClickListener(context);
		setOnload();
		setOnRefresh();
		addView(view);
	}
	
	private void setOnRefresh() {
		if(!Tools.isEmpty(attrbuteSet.onRefresh))
			return;
		try {
			refresh = Class.forName(attrbuteSet.onRefresh);
			o3 = refresh.getDeclaredConstructor(new Class[]{Context.class, RLListview.class}).newInstance(new Object[]{context, this});
			refreshableView.setOnRefreshListener(((RefreshableView.PullToRefreshListener)o3), from);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void setOnload() {
		if(!Tools.isEmpty(attrbuteSet.onLoad))
			return;
		try {
			load = Class.forName(attrbuteSet.onLoad);
			o4 = load.getDeclaredConstructor(new Class[]{Context.class, RLListview.class}).newInstance(new Object[]{context, this});
			refreshableView.setOnLoadListener(((RefreshableView.PullToLoadListener)o4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addOnItemClickListener(Context context){
		if(!Tools.isEmpty(attrbuteSet.onItemClickListener))
			return;
		try {
			listener = Class.forName(attrbuteSet.onItemClickListener);
			o1 = listener.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
			addOnItemClickListener(((OnItemClickListener)o1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addOnItemClickListener(OnItemClickListener o){
		listview.setOnItemClickListener(o);
	}
	
	public void setOnItemData(Object object){
		setOnItemData(object.getClass(), object);
	}
	
	public void setOnItemData(Class<?> classType, Object object){
		if(listener == null){
			return;
		}
		try {
			Method method = listener.getMethod("setData", new Class[]{classType});
			if(Tools.isEmpty(method)){
				method.invoke(o1, new Object[]{object});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setOnLoadData(Object object){
		setOnLoadData(object.getClass(), object);
	}
	
	public void setOnLoadData(Class<?> classType, Object object){
		
		try {
			if(Tools.isEmpty(refresh)){
				Method method = refresh.getMethod("setData", new Class[]{classType});
				if(Tools.isEmpty(method)){
					method.invoke(o3, new Object[]{object});
				}
			}
			if(Tools.isEmpty(load)){
				Method method = load.getMethod("setData", new Class[]{classType});
				if(Tools.isEmpty(method)){
					method.invoke(o4, new Object[]{object});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setAdapter(ListAdapter adapter){
		if(needLoad){
			listview.addFooterView(refreshableView.getFooterView());
			listview.setAdapter(adapter);
			listview.removeFooterView(refreshableView.getFooterView());
		}else{
			listview.setAdapter(adapter);
		}
	}
	
	public ListAdapter getAdapter(){
		return (ListAdapter)o2;
	}
	
	public void setAdapter(Object... object){
		model = object[0];
		Class<?>[] classes = new Class<?>[object.length + 1];
		classes[0] = Context.class;
		Object[] objects = new Object[object.length + 1];
		objects[0] = context;
		for (int i = 1; i <= object.length; i++) {
			classes[i] = object[i - 1].getClass();
			objects[i] = object[i - 1];
		}
		setAdapter(classes, objects);
	}
	
	public void setAdapter(Class<?>[] classes, Object[] objects){
		if(!Tools.isEmpty(attrbuteSet.adapter))
			return;
		try {
			adapter = Class.forName(attrbuteSet.adapter);
			System.out.println(attrbuteSet.adapter);
			System.out.println(Arrays.toString(adapter.getDeclaredConstructors()));
			o2 = adapter.getDeclaredConstructor(classes).newInstance(objects);
			System.out.println(o2.getClass().getName());
			setAdapter((ListAdapter)o2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListView getListview() {
		return listview;
	}

	public RefreshableView getRefreshableView() {
		return refreshableView;
	}
	
	public void refresh(){
		refreshableView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				refreshableView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				refreshableView.refresh();
			}
		});
	}
	
	public Object getModel(){
		return model;
	}
	
	public void hideHead(){
		refreshableView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout() {
				refreshableView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				refreshableView.finishRefreshing();
			}
		});
	}

}
