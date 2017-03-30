package com.lib.wlib.customview;

import java.lang.reflect.Method;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lib.wlib.R;
import com.lib.wlib.frame.utils.L;
import com.lib.wlib.frame.utils.Tools;
import com.lib.wlib.viewhelper.attrbuset.ListViewAttrbuteSet;


public class CustomListView extends FrameLayout implements CustomView{
	private ListViewAttrbuteSet attrbuteSet = new ListViewAttrbuteSet();
	private Object o1, o2;
	private Class<?> listener, adapter;
	private ListView listview;
	private TextView empty;
	private Context context;
	private int resourceId = R.layout.custom_listview;

	public CustomListView(Context context) {
		this(context, null);
	}

	public CustomListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.item_view);
		attrbuteSet.onItemClickListener = a.getString(R.styleable.item_view_onitemclick);
		attrbuteSet.adapter = a.getString(R.styleable.item_view_adapter);
		resourceId = a.getResourceId(R.styleable.item_view_layoutId_2, resourceId);
		a.recycle();
		initView(context);
	}
	
	@SuppressLint("InflateParams") 
	@Override
	public void initView(Context context) {
		LayoutInflater mInflater = LayoutInflater.from(context);
		View view = mInflater.inflate(resourceId, null);
		listview = (ListView) view.findViewById(R.id.custom_listview);
		if(attrbuteSet.onItemClickListener != null)
			addOnItemClickListener(context);
		empty = (TextView) view.findViewById(R.id.empty);
		addView(view);
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
	
	public void setData(Object object){
		setData(object.getClass(), object);
	}
	
	public void setData(Class<?> classType, Object object){
		try {
			System.out.println(Arrays.toString(listener.getMethods()));
			Method method = listener.getMethod("setData", new Class[]{classType});
			if(Tools.isEmpty(method)){
				method.invoke(o1, new Object[]{object});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListView getListview() {
		return listview;
	}

	public TextView getTextView() {
		return empty;
	}
	
	public void setAdapter(ListAdapter adapter){
		listview.setAdapter(adapter);
	}
	
	public ListAdapter getAdapter(){
		return (ListAdapter)o2;
	}
	
	public void setAdapter(Object... object){
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
		if(!Tools.isEmpty(attrbuteSet.adapter)){
			return;
		}
		try {
			adapter = Class.forName(attrbuteSet.adapter);
			L.d(attrbuteSet.adapter+ ":" +  Arrays.toString(classes) + "==========>" + Arrays.toString(adapter.getDeclaredConstructors()));
			o2 = adapter.getDeclaredConstructor(classes).newInstance(objects);
			setAdapter((ListAdapter)o2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setEmpty(){
		listview.setEmptyView(empty);
	}

}
