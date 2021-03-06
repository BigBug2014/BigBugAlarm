package com.example.smilealarmtask;
/**
* BroadcastReceiver类，在pengdingintent设置的时间到达后闹钟响起

*
* @version 1.0
* @author Liu
* @modified by:
* 12/24 接收传入的id变量
* 12/24 这是不是一个activity，不能获取globalvariable中的全局变量，因此将id传入AlarmAlert（activity）中
* 	Open Declaration Application android.app.Activity.getApplication()，因此getApplication是从activity中继承的方法
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.os.Bundle;
//import了广播broadcast
import android.util.Log;

public class CallAlarm extends BroadcastReceiver { // 当BroadcastReceiver接收到广播后，就会去执行OnReceive()方法
	@Override
	// 通过override实现我们需要的功能
	public void onReceive(Context context, Intent intent) {
		// 打开新的activity——AlarmAlert
		int id = intent.getIntExtra("_id", -1);
		if(id != -1){  
			Log.w("TAG","闹钟传参1，id="+id);
			intent.putExtra("_id", id);
		}
		intent.setClass(context, AlarmAlert.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//或者是 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);也是可以的
		context.startActivity(intent);
	}
}
// 注意：创建完BroadcastReceiver后，需要在AndroidManifest.xml中注册：
// <receiver android:name=".MyReceiver">
// <intent-filter>
// <action android:name= "com.Reminder.MyReceiver" />
// </intent-filter>
// </receiver>