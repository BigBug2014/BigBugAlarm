package com.example.smilealarmtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.util.Log;

public class GlobalVariable extends Application { 
    /**
     * �����쳣����һЩ���淶�Ĵ����о�������Activity������Service���ж������ྲ̬��Ա���ԡ����������ܻ��������Ī������� null
     * pointer�쳣��
     */ 
   
    /**
     * �쳣������Java��������������ջ��ƻ���������û�б����õĶ�������ԡ����ڴ治��ʱ����������������մ��ں�̨��Activity��
     * Service��ռ�õ��ڴ档��Ӧ���ٴ�ȥ���þ�̬���Ի�����ʱ�򣬾ͻ����null pointer�쳣
     */ 
   
    /**
     * ����쳣��Application������Ӧ���У�ֻҪ���̴��ڣ�Application�ľ�̬��Ա�����Ͳ��ᱻ���գ��������null pointer�쳣
     */ 
    private ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int ClockID = 0;
    @Override 
    public void onCreate() { 
        // TODO Auto-generated method stub 
        super.onCreate(); 
		//Log.w("TAG", "list��ʼ��"); // ������Ϣ
		//Map<String, Object> item = new HashMap<String, Object>();
		//item.put("image", R.drawable.clock);
		//item.put("title", "adassakjf");
		//item.put("info", getString(R.string.clock_setted));
		//this.list.add(item);
		//setList(list);
    } 
    //���÷�ʽ
   //((HelloApplication)getApplication()).setGlobalVariable(10);
    //int valiable=((HelloApplication)getApplication()).getGlobalVariable();
    public int getClockID(){
    	return this.ClockID++;
    }
    public void setClockID(){
    	this.ClockID--;
    }
    public ArrayList<Map<String, Object>> getList() {  
        return list; 
    } 
    public void setList(ArrayList<Map<String, Object>> list) { 
    	if(list != null){
    		this.list = list; 
    	}
    	else{
    		Log.w("TAG", "Set List Failed!");
    	}
    } 
}