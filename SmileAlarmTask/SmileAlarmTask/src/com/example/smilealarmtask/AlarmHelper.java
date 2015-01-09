package com.example.smilealarmtask;
/**
* 普通类，内置各种全局函数，可以通过objcetpool中的对象进行调用
* openalarm控制开启闹钟服务线程
* closealarm控制关闭闹钟服务线程
*
* @version 1.0
* @author Liu
* @modified by:
* 12/21 修改closealarm，实现闹钟服务删除
* 12/24 修改openalarm，增加传参功能，传递至callalarm（是一个broadcastreceiver）中
*/
import android.app.AlarmManager;//"提醒"，系统级别的提示服务，特定时刻广播制定的intent

//set设置闹钟setRepeating设置重复闹钟setlnexactRepeating设置间隔不规律重复闹钟
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHelper {

	private Context c;
	private AlarmManager mAlarmManager;

	public AlarmHelper(Context c) {
		this.c = c;
		mAlarmManager = (AlarmManager) c
				.getSystemService(Context.ALARM_SERVICE); // 返回alarmmanager的对象
	}// 后台service由SystemServer开启，getSystemService是activity的一个方法

	public void openAlarm(int id, String title, String content, long time) {
		Intent intent = new Intent();
		intent.putExtra("_id", id);
		intent.putExtra("title", title);
		intent.putExtra("content", content);// 传两个string进去
		// intent.putExtra用来传递参数
		// putExtra("A",B)中，AB为键值对，第一个参数为键名，第二个参数为键对应的值
		intent.setClass(c, CallAlarm.class); // 通过setClass启动一个Activity，参数一为当前Package的context，参数二为你要打开的Activity的类名
		PendingIntent pi = PendingIntent.getBroadcast(c, id, intent,PendingIntent.FLAG_UPDATE_CURRENT);
		
		// PendingIntent intent= PendingIntent.getBroadcast(Context context, int
		// requestCode, Intent intent, int flags)
		//int FLAG_CANCEL_CURRENT：如果该PendingIntent已经存在，则在生成新的之前取消当前的。
		//int FLAG_NO_CREATE：如果该PendingIntent不存在，直接返回null而不是创建一个PendingIntent.
		//int FLAG_ONE_SHOT:该PendingIntent只能用一次，在send()方法执行后，自动取消。
		//int FLAG_UPDATE_CURRENT：如果该PendingIntent已经存在，则用新传入的Intent更新当前的数据。
		mAlarmManager.set(AlarmManager.RTC_WAKEUP, time, pi);
		// mAlarmManager.set(AlarmManager.RTC_WAKEUP, 1000, pi);
		// set设置一次性闹钟set(int type，long startTime，PendingIntent pi)；
		// AlarmManager.RTC_WAKEUP表示闹钟在睡眠状态下会唤醒系统并执行提示功能，该状态下闹钟使用绝对时间，状态值为0；
	}// //PendingIntent这个类用于处理即将发生的事情。比如在通知Notification中用于跳转页面，但不是马上跳转。

	//public void functest() {
	//	Log.w("TAG", "textfunctionAlarmHelper"); // 调试信息
	//}

	public void closeAlarm(int id, String title, String content) {
		Intent intent = new Intent();
		intent.putExtra("_id", id);
		intent.putExtra("title", title);
		intent.putExtra("content", content);
		intent.setClass(c, CallAlarm.class);
		PendingIntent pi = PendingIntent.getBroadcast(c, id, intent, 0);
		mAlarmManager.cancel(pi);
	}
}