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

//���ù��ܣ��������á�������ʽ���á������Ϣ
//        SARing��DefaultMode
public class ClockSetting extends Activity {

	private Button ringSetting;// �������ð�ť
	private Button modeSetting;// ����ģʽ���ð�ť
	private Button infoSoftware;// ��ʾ�����Ϣ
	private TextView copyright;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		ringSetting = (Button) findViewById(R.id.ringSetting);// ��xml��ȡ�ð�ť��id
		modeSetting = (Button) findViewById(R.id.modeSetting);// ��xml��ȡ�ð�ť��id
		infoSoftware = (Button) findViewById(R.id.infoSoftware);// ��xml��ȡ�ð�ť��id
		copyright = (TextView) findViewById(R.id.copyright);
		Log.w("TAG", "��app"); // ������Ϣ???
		setListener();// �����¼�������ȡ�ÿؼ�
	}

	public void setListener() {

		// ������ringSetting��ť��ķ�Ӧ
		ringSetting.setOnClickListener(new View.OnClickListener() {// ��ť�ļ�����������click����Ҫ����onclick
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(ClockSetting.this, RingSetting.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						ClockSetting.this.startActivity(intent);
					}
				});

		// ������modeSetting��ť��ķ�Ӧ
		modeSetting.setOnClickListener(new View.OnClickListener() {// ��ť�ļ�����������click����Ҫ����onclick
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(ClockSetting.this, ModeSetting.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						ClockSetting.this.startActivity(intent);
					}
				});

		// ������infoSoftware��ť��ķ�Ӧ
		infoSoftware.setOnClickListener(new View.OnClickListener() {// ��ť�ļ�����������click����Ҫ����onclick
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
