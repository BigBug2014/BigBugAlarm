package com.example.smilealarmtask;

import android.app.*;
import android.os.Bundle;
import android.util.Log;
//import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.widget.TimePicker;
import android.content.*;

//设置功能：铃声设置、解锁方式设置、软件信息
//        SARing、DefaultMode
public class ClockSetting extends Activity {

	private Button ringSetting;// 铃声设置按钮
	private Button modeSetting;// 解锁模式设置按钮
	private Button infoSoftware;// 显示软件信息
	private TextView copyright;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		ringSetting = (Button) findViewById(R.id.ringSetting);// 从xml中取得按钮的id
		modeSetting = (Button) findViewById(R.id.modeSetting);// 从xml中取得按钮的id
		infoSoftware = (Button) findViewById(R.id.infoSoftware);// 从xml中取得按钮的id
		copyright = (TextView) findViewById(R.id.copyright);
		Log.w("TAG", "打开app"); // 调试信息???
		setListener();// 设置事件监听，取得控件
	}

	public void setListener() {

		// 监听到ringSetting按钮后的反应
		ringSetting.setOnClickListener(new View.OnClickListener() {// 按钮的监听。监听到click后需要调用onclick
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(ClockSetting.this, RingSetting.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						ClockSetting.this.startActivity(intent);
					}
				});

		// 监听到modeSetting按钮后的反应
		modeSetting.setOnClickListener(new View.OnClickListener() {// 按钮的监听。监听到click后需要调用onclick
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(ClockSetting.this, ModeSetting.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						ClockSetting.this.startActivity(intent);
					}
				});

		// 监听到infoSoftware按钮后的反应
		infoSoftware.setOnClickListener(new View.OnClickListener() {// 按钮的监听。监听到click后需要调用onclick
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(ClockSetting.this, InfoSoftware.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						ClockSetting.this.startActivity(intent);
					}
				});

	}

}
