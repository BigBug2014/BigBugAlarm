package com.example.smilealarmtask;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.content.Intent;

public class TimeHandler extends Activity {
	@Override  
	   public void onCreate(Bundle savedInstanceState) {  
       super.onCreate(savedInstanceState);  
       final TimeHandler handler=new TimeHandler();
       Runnable runnable=new Runnable(){
    	   
    	   public void run(){
    		   //To do
    		   	Intent intent = new Intent();
				intent.setClass(TimeHandler.this, AlarmAlert.class); 
				/* 调用一个新的Activity */ 
				startActivity(intent); 
				/* 关闭原本的Activity */ 
				TimeHandler.this.finish();
    		   //
    		  // handler.postDelayed(TimeHandler.this,20000);
    	   }
    	   
       };
      // handler.postDelayed(runnable, 20000);//
	}
}
