package com.example.smilealarmtask;
/**
* �հ�activity�����ڲ��ԣ���ɾ��
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
	protected void onCreate(Bundle savedInstanceState) {// savedInstanceState��������������ϴ��˳�����Ϣ����������Իָ��ϴ��˳���״̬
		super.onCreate(savedInstanceState);
		Log.w("TAG", "blank1"); // ������Ϣ
		setContentView(R.layout.blank);
		Log.w("TAG", "blank2"); // ������Ϣ
	}
}