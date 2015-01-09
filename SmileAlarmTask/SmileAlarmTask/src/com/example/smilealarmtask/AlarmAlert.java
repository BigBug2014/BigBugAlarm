package com.example.smilealarmtask;

/**
 * Created by Chengbo Wu
 * Play the default ringtone
 * Modified by Qiao Zhang
 * Set specific ringtone and play
 * Enable chosen button of mode
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;  //是project的，因此不能new，只能调用oncreat——最终建立一个AlertDialog
import android.content.DialogInterface;
import android.os.Bundle;//每个extends 了Activity的类中 oncreat方法都需要传递一个bundle 参数 所以必须引进
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent; 
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.RingtoneManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Vibrator;
import android.content.Context;

public class AlarmAlert extends Activity {
	private ImageButton mImageButton1;
	private ImageButton mImageButton2;
	private Button mButton1;
	private Button mButton2;
	private Button mButton3;
	//added by LIU at 12/24
	GlobalVariable application = (GlobalVariable)this.getApplication(); 
	//MediaPlayer mp = new MediaPlayer();
	/*private MediaPlayer mMediaPlayer;
	private void startAlarm(MediaPlayer player)
            throws java.io.IOException, IllegalArgumentException,
                   IllegalStateException {
        final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        // do not play alarms if stream volume is 0
        // (typically because ringer mode is silent).
        if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
            player.setAudioStreamType(AudioManager.STREAM_ALARM);
            player.setLooping(true);
            player.prepare();
            player.start();
        }
    }*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {//savedInstanceState变量，里面包含上次退出的信息，这里面可以恢复上次退出的状态
		super.onCreate(savedInstanceState);
		setContentView(R.layout.unlockchoose);

		/**
 		* 部分添加，实现闹钟响起时删除对应id的list项目
		*
		* @version 12/24
		* @author Liu
		* @modified by:
		* 目前的问题，闹钟永远启动第一个
		*/
		//TODO list中位置为id的map的删除操作,只能在activity中获取list
		Intent intent = getIntent();
		int id =intent.getIntExtra("_id",-1);
		if(id != -1){
			Log.w("TAG","闹钟传参2，id="+id);
			ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = ((GlobalVariable)getApplication()).getList();//取出变量
			Log.w("TAG","闹钟传参3");
			//TODO 删除闹钟id为本地int id的那个list中的map
			int cnt=0;
			int RemovePos = -1;
			for (Map<String, Object> i : list){	
				for (String k : i.keySet())  {//得到map所有的key
					if(k=="_id"){
						int ClockID =  Integer.parseInt(i.get(k).toString());
						if(ClockID == id){Log.w("TAG","ClockID == id =="+id);
							RemovePos = cnt;
						}
					}
				}
				cnt++;
			}
			if(RemovePos != -1){
				list.remove(RemovePos);
			}
			
			//list.remove(id);
			Log.w("TAG","闹钟传参4");
			//ID reset
			Map<String, Object> TempMap = new HashMap<String, Object>();
			Log.w("TAG","闹钟传参5   "+list.size());
			for(int i=0; i<list.size();i++){
				TempMap = list.get(i);
				TempMap.put("title", "闹钟"+(i+1));
				list.set(i, TempMap);
			}
			Log.w("TAG","闹钟传参6");
			((GlobalVariable)getApplication()).setList(list); //保存变量 
		}
		
		
		
		
		
		//-----------------------------------
		mButton1 = (Button) findViewById(R.id.button1);// 从xml中取得按钮的id
		mButton2 = (Button) findViewById(R.id.button2);// 从xml中取得按钮的id
		mButton3 = (Button) findViewById(R.id.button3);// 从xml中取得按钮的id
		//暂时改了做测试用，张乔的结构没有做好交互
		mButton1.setEnabled(true);
		mButton2.setEnabled(false);
		mButton3.setEnabled(false);
		
		if(ModeSetting.SmileEnableFlag){
			mButton1.setEnabled(true);
		}
		else if(ModeSetting.SpeechEnableFlag){
			mButton2.setEnabled(true);
		}
		else if(ModeSetting.TextEnableFlag){
			mButton3.setEnabled(true);
		}
		
		/*new AlertDialog.Builder(AlarmAlert.this)
				.setIcon(R.drawable.clock) 
				.setTitle("SmileAlarm")
				.setMessage("time out!!!")
				.setPositiveButton("Exit",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								System.exit(0);
								android.os.Process
										.killProcess(android.os.Process
												.myPid());  //结束apk
							}
						}).show();*/
		//private Vibrator mVibrator;
	    //private MediaPlayer mMediaPlayer;
		//startAlarm(mMediaPlayer);
		
		RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		//RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE, newUri);
		final MediaPlayer mp = new MediaPlayer();
		try {
			mp.setDataSource(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mp.start();
	    
		/*
		 * liu
		 * 
		 */
		mButton1 = (Button) findViewById(R.id.button1);
		mButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				 Intent intent = new Intent();				 
				 intent.setClass(AlarmAlert.this, FaceUnlock.class);
				 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				 AlarmAlert.this.startActivity(intent);
			}
		});
		
		
		mImageButton1 = (ImageButton) findViewById(R.id.image_Button01 );
		mImageButton1.setOnClickListener(new ImageButton.OnClickListener(){
			public void onClick(View v){
				//mp.stop();
				Intent intent = new Intent();
				intent.setClass(AlarmAlert.this, SmileAlarmTask.class); 
				/* 调用一个新的Activity */ 
				startActivity(intent); 
				/* 关闭原本的Activity */ 
				AlarmAlert.this.finish();

			}
		});
		mImageButton2 = (ImageButton) findViewById(R.id.image_Button02 );
		mImageButton2.setOnClickListener(new ImageButton.OnClickListener(){
			public void onClick(View v){
				mp.stop();

/**
 * Added by changyangyang on 14/12/18.
 */

                Intent intent = new Intent();
                intent.setClass(AlarmAlert.this, DefaultMode.class);
				/* 调用一个新的Activity */
                startActivity(intent);
				/* 关闭原本的Activity */
                AlarmAlert.this.finish();



				
			}
		});
		
		 
		
	}

}
