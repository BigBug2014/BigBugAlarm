package com.example.smilealarmtask;
/**
* BroadcastReceiver�࣬��pengdingintent���õ�ʱ�䵽�����������

*
* @version 1.0
* @author Liu
* @modified by:
* 12/24 ���մ����id����
* 12/24 ���ǲ���һ��activity�����ܻ�ȡglobalvariable�е�ȫ�ֱ�������˽�id����AlarmAlert��activity����
* 	Open Declaration Application android.app.Activity.getApplication()�����getApplication�Ǵ�activity�м̳еķ���
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.os.Bundle;
//import�˹㲥broadcast
import android.util.Log;

public class CallAlarm extends BroadcastReceiver { // ��BroadcastReceiver���յ��㲥�󣬾ͻ�ȥִ��OnReceive()����
	@Override
	// ͨ��overrideʵ��������Ҫ�Ĺ���
	public void onReceive(Context context, Intent intent) {
		// ���µ�activity����AlarmAlert
		int id = intent.getIntExtra("_id", -1);
		if(id != -1){  
			Log.w("TAG","���Ӵ���1��id="+id);
			intent.putExtra("_id", id);
		}
		intent.setClass(context, AlarmAlert.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//������ intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);Ҳ�ǿ��Ե�
		context.startActivity(intent);
	}
}
// ע�⣺������BroadcastReceiver����Ҫ��AndroidManifest.xml��ע�᣺
// <receiver android:name=".MyReceiver">
// <intent-filter>
// <action android:name= "com.Reminder.MyReceiver" />
// </intent-filter>
// </receiver>