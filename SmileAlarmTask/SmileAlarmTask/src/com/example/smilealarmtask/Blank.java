package com.example.smilealarmtask;
/**
* 空白activity，用于测试，可删除
*
* @version 1.0
* @author Liu
* @modified by:
* 
*/
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Blank extends Activity {
	protected void onCreate(Bundle savedInstanceState) {// savedInstanceState变量，里面包含上次退出的信息，这里面可以恢复上次退出的状态
		super.onCreate(savedInstanceState);
		Log.w("TAG", "blank1"); // 调试信息
		setContentView(R.layout.blank);
		Log.w("TAG", "blank2"); // 调试信息
	}
}
