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

//��ʾ�����Ϣ
public class InfoSoftware extends Activity{

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.softwareinfo);
	}
	
	/**ͨ��BACK��������һ��ҳ��*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {//Activity�жԡ�BACK�����˼�����¼�����������дonKeyDown����
		return super.onKeyDown(keyCode, event);
	}
	
}
