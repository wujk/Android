package com.lib.wlib.frame.net.inter;

import com.lib.wlib.frame.net.model.URLComplete;

public interface URLCallBack {
	
	public void onSucess(URLComplete urlComplete);
	
	public void onFail(URLComplete urlComplete);
  
}
