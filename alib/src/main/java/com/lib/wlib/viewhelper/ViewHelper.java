package com.lib.wlib.viewhelper;

import java.util.LinkedList;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;

import com.lib.wlib.customview.CustomGridView;
import com.lib.wlib.customview.EditTextView;
import com.lib.wlib.customview.RLListview;
import com.lib.wlib.customview.TextTextView;
import com.lib.wlib.frame.view.CustomListView;


public class ViewHelper {
	private Activity activity;
	private CustomListView listview;
	private RLListview rlListview;
	private CustomGridView gridview;
	private ViewGroup group;
	private LinkedList<EditTextView> editTextViews = new LinkedList<EditTextView>();
	private LinkedList<TextTextView> textTextViews = new LinkedList<TextTextView>();

	public ViewHelper(Activity activity) {
		super();
		this.activity = activity;
	}
	
	public View getView(int parentId, int resourceId){
		group = (ViewGroup) activity.findViewById(parentId);
		return getView(group, resourceId);
		
	}
	
	public View getView(ViewGroup group, int resourceId){
		XmlPullParser parser = activity.getResources().getXml(resourceId);
		AttributeSet attributes = Xml.asAttributeSet(parser);
		try {
			int event = parser.getEventType();			
			while(event != XmlPullParser.END_DOCUMENT){
				switch(event){
				case XmlPullParser.START_TAG:
					if(parser.getName().equals("root"))
						break;
					try {
						Class<?> cl = Class.forName(parser.getName());
						Object o = cl.getConstructor(Context.class, AttributeSet.class).newInstance(activity, attributes);
						group.addView((View)o);
						if(o instanceof CustomListView)
							listview = (CustomListView)o;
						if(o instanceof CustomGridView)
							gridview = (CustomGridView)o;
						if(o instanceof RLListview)
							rlListview = (RLListview) o;
						if(o instanceof EditTextView)
							editTextViews.addLast((EditTextView)o);						
						if(o instanceof TextTextView)
							textTextViews.addLast((TextTextView)o);						
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
				event = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return group;
	}

	public CustomListView getListview() {
		if(listview == null){
			throw new RuntimeException("listview为null");
		}
		return listview;
	}

	public CustomGridView getGridview() {
		if(gridview == null){
			throw new RuntimeException("gridview为null");
		}
		return gridview;
	}
	
	public RLListview getRlListview(){
		if(rlListview == null){
			throw new RuntimeException("rlListview为null");
		}
		return rlListview;
	}
	
	public LinkedList<EditTextView> getEditTextViews(){
		return editTextViews;
	}
	public LinkedList<TextTextView> getTextTextViews(){
		return textTextViews;
	}
	
	public ViewGroup getRootView(){
		if(group == null){
			throw new RuntimeException("RootView为null");
		}
		return group;
	}			
	

}
