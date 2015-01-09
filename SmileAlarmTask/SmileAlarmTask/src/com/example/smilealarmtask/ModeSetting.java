package com.example.smilealarmtask;

import java.util.ArrayList;  
import java.util.List;  
  
import android.os.Bundle;  
import android.app.Activity;  
import android.app.AlertDialog;  
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;  
import android.view.View;  
import android.widget.CheckBox;  
import android.widget.CompoundButton;  
import android.widget.Toast;
import android.view.View.OnClickListener;
  
public class ModeSetting extends Activity {  
      
    //声明组件  
    private CheckBox cb1,cb2,cb3;  
      
    //声明一个集合  
    private List<CheckBox> checkBoxs=new ArrayList<CheckBox>();  
    CompoundButton.OnCheckedChangeListener listener;
    //声明传递出去的模式标识
    public static boolean SmileEnableFlag;
    public static boolean SpeechEnableFlag;
    public static boolean TextEnableFlag;
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.modesetting);  
          
        //获取组件  
        cb1 = (CheckBox) findViewById(R.id.checkBox1);  
        cb2 = (CheckBox) findViewById(R.id.checkBox2);  
        cb3 = (CheckBox) findViewById(R.id.checkBox3);   
          
        //默认选项  
        cb1.setChecked(true);   
          
        //注册事件  
        cb1.setOnCheckedChangeListener(listener);  
        cb2.setOnCheckedChangeListener(listener);  
        cb3.setOnCheckedChangeListener(listener);  
 
        //把四个组件添加到集合中去  
        checkBoxs.add(cb1);  
        checkBoxs.add(cb2);  
        checkBoxs.add(cb3);  
 
    }  
  
    //初始化menu键，没有进行实际操作，可以后加功能
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        //getMenuInflater().inflate(R.menu.main, menu);  //调用men健
        return true;  
    }  
      
    public void getValues(View v) {  
  
        String content = "";  
  
        for (CheckBox cbx : checkBoxs) {  
            if (cbx.isChecked()) {  
                content += cbx.getText() + "\n";  
            }  
        }  
  
        if ("".equals(content)) {  
            content = "Nothing！" + "\n" + "请返回并选择至少一种解锁闹钟方式";  
        }  
        
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage(content)
        		.setTitle("选择的解锁方式为")  
                .setPositiveButton("确定", 
                		new DialogInterface.OnClickListener() {                	
                	        public void onClick(DialogInterface dialog,
							    int which) {
                	        	Intent intent = new Intent();
                				intent.setClass(ModeSetting.this, ClockSetting.class);
                				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                				ModeSetting.this.startActivity(intent);
                				dialog.dismiss();
					        }                	
                        })
                .setNegativeButton("返回",
                		new DialogInterface.OnClickListener() {
                	        public void onClick(DialogInterface dialog,
						        int which) {
					        dialog.dismiss();
				        }
                	
                }).show();  
  
    }  
  
    class OnCheckBoxClickListener implements OnClickListener {  
        
        @Override  
        public void onClick(View view) {  
            CheckBox checkBox = (CheckBox) view;  
            if (checkBox.getId() == R.id.checkBox1) {  
            	if (checkBox.isChecked()) {  
            		SmileEnableFlag = true; 
                } else {  
                	SmileEnableFlag = false;  
                } 
            } else if (checkBox.getId() == R.id.checkBox2) {  
            	if (checkBox.isChecked()) {  
            		SpeechEnableFlag = true; 
                } else {  
                	SpeechEnableFlag = false;  
                } 
            } else if (checkBox.getId() == R.id.checkBox3) {  
            	if (checkBox.isChecked()) {  
            		TextEnableFlag = true; 
                } else {  
                	TextEnableFlag = false;  
                } 
            }  
  
        }  
  
    }  
    
    //要不要写利用BACK键返回上一级？
  
}  
