package com.example.smilealarmtask;
/** 
* app���������ʾ
*
* @version 1.0
* @author Liu
* @modified by:
* 12/24ɾ������ʱ������Ӱ�ť�Ͷ�Ӧtextview��Ŀ
*/
import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;


public class SmileAlarmTask extends Activity {

	private Button SysSetting;
	private Button ClockShow;
	String defalutString = "û�л�Ծ����";
	//private Context context;
	Calendar mCalendar = Calendar.getInstance();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ObjectPool.mAlarmHelper = new AlarmHelper(this); // ObjectPool��ֻ������һ��AlarmHelper�Ķ���mAlarmHelper
		//mSet = (Button) findViewById(R.id.mSet);// ��xml��ȡ�ð�ť��id
		SysSetting = (Button) findViewById(R.id.SysSetting);// ��xml��ȡ�ð�ť��id
		ClockShow = (Button) findViewById(R.id.ClockShow);
		setListener();// �����¼�������ȡ�ÿؼ�
		// SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
		// StrTime1 = settings.getString("TIME1", defalutString);
		// setTime1.setText(StrTime1);

	}
	
	public void setListener() {
		/*mSet.setOnClickListener(new View.OnClickListener() {// ��ť�ļ�����������click����Ҫ����onclick
			@Override
			public void onClick(View v) {
				mCalendar.setTimeInMillis(System.currentTimeMillis()); // setTimeInMillis����ʱ��ĺ�������System.currentTimeMillis������ǰ��
																		// һ��������
				int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
				int mMinute = mCalendar.get(Calendar.MINUTE); // ��ȡʱ���Сʱ�ͷ��֣���ʼ��
				new TimePickerDialog(SmileAlarmTask.this, // TimePickerDialogʱ��Ի��򣬵�1����
						new TimePickerDialog.OnTimeSetListener() {// �ڶ�������ʱ��Ի�������
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								mCalendar.setTimeInMillis(System
										.currentTimeMillis());
								mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
								mCalendar.set(Calendar.MINUTE, minute);
								mCalendar.set(Calendar.SECOND, 0);
								mCalendar.set(Calendar.MILLISECOND, 0);
								ObjectPool.mAlarmHelper.openAlarm(1,
										"SmileAlarm", "time out!!",
										mCalendar.getTimeInMillis()); // ������
								setTime1.setText(hourOfDay + "��" + minute);
								// SharedPreferences sp =
								// SmileAlarmTask.this.getSharedPreferences("SP",
								// MODE_PRIVATE);
								// Editor editor = sp.edit();
								// editor.putInt("Hour", hourOfDay);
								// editor.putInt("Minute", minute);
								// ObjectPool.mClockDisplay.changePara(hourOfDay,
								// minute);
								// ObjectPool.mClockDisplay.setTimeText(hourOfDay,
								// minute);
							}
						}, mHour, mMinute, true).show(); // ��345����ture��ʾ24Сʱ��
			}
		});*/

		// Button_setting
		SysSetting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				 Intent intent = new Intent();				 
				 intent.setClass(SmileAlarmTask.this, ClockSetting.class);
				 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 SmileAlarmTask.this.startActivity(intent);
				 
				/*
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						SmileAlarmTask.this);
				builder.setTitle("��ʾ")
						.setIcon(R.drawable.clock)
						// ClockDemo\res\drawable-hdpi�µ��ļ�
						.setMessage("Click!")
						.setPositiveButton("Yeah!",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();
									}
								}).show();	*/
			}
		});
		

		// Clock_show
		ClockShow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SmileAlarmTask.this, ClockDisplay.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);			
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {// Activity�жԡ�BACK�����˼�����¼�������������дonKeyDown����
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showBackDialog(); // ���������showBackDialog����
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/** Give the tip when exit the application. */
	public void showBackDialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ʾ")
				.setIcon(R.drawable.clock)
				// ClockDemo\res\drawable-hdpi�µ��ļ�
				.setMessage("�Ƿ��˳�?")
				.setPositiveButton("sure", // �ı�
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								//TODO save list 
								System.exit(0); // �˳�����
								android.os.Process // �ɹ��˳���Ĳ�����apkֱ���˳���������������
										.killProcess(android.os.Process.myPid());
								dialog.dismiss(); // �Ի�����ʧ
							}
						})
				.setNegativeButton("cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
		AlertDialog ad = builder.create();
		ad.show();// Ϊbuilder�Ķ������£�
		// new AlertDialog.Builder(self).setTitle("����")
		// .setMessage("����Ϣ��").setPositiveButton("ȷ��", null).show();
	}

}