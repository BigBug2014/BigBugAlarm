package com.example.smilealarmtask;

import android.app.*;
//import android.content.DialogInterface;
import android.os.Bundle;
//import android.util.Log;
import android.view.KeyEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.TimePicker;

//显示软件信息
public class InfoSoftware extends Activity{

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.softwareinfo);
	}
	
	/**通过BACK键返回上一级页面*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//Activity中对“BACK”后退键后的事件监听处理，重写onKeyDown方法
		return super.onKeyDown(keyCode, event);
	}
	
}
