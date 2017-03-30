package com.lib.wlib.frame.model;

import java.util.HashMap;

import com.lib.wlib.frame.utils.Tools;

public class BaseModel {
	
	public HashMap<String, Object> toMap(){
		return Tools.toMap(this);
	}

}
