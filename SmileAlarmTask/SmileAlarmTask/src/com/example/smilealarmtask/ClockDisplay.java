package com.example.smilealarmtask;
/**
* �Ѿ����õ�����listview�б�����ʾ
*
* @version 1.0
* @author Liu
* @modified by:
* 12/24�����ӹ㲥���̵�id������listÿһ����Ŀ��id��ϵ����������GlobalVariable��
* 12/25����������Ŀ��������رչ���,��list������int id��long time������ʵ�����ӵĹرպ����¿���
*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TimePicker;
import android.widget.Toast;
//*********************************
//created by Liu 
//last modification at 12/21
//********************************
public class ClockDisplay extends Activity {
	Calendar mCalendar = Calendar.getInstance();
	private ListView mListView = null;
	private Button SysSetting;
	//boolean isOnUsing = true;
	// MyListAdapter myAdapter = null;
	GlobalVariable application = (GlobalVariable)this.getApplication(); 
	//ArrayList<Map<String, Object>> list = application.getList();//ȡ������ 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		SysSetting = (Button) findViewById(R.id.newClock);// ��xml��ȡ�ð�ť��id
		mListView = (ListView) findViewById(R.id.MyListView);
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = ((GlobalVariable)getApplication()).getList();//ȡ������ 
		SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.mylist,
				new String[] { "image" ,"title" ,"info" ,"onUse"}, new int[] { R.id.ItemImage, R.id.ClockTitle ,R.id.info ,R.id.OnUsing});
		mListView.setAdapter(adapter);
		//----------������ã��̰�--�޸�
		mListView.setOnItemClickListener(new OnItemClickListener() {
	        @Override  
	        public void onItemClick(AdapterView<?> parent, View view, int position, //����,?��ʾ��ȷ����java���͡�  
	                long id) {  
	            // �����view��������list.xml�ж����LinearLayout����.  
	            // ���Կ���ͨ��findViewById���������ҵ�list.xml�ж���������Ӷ���,����:
	        	//TextView TextClicked = (TextView) view.findViewById(R.id.title); 
	        	//String ssss = list.get(position).get("title").toString();
	        	Toast.makeText(ClockDisplay.this,"���������"+(position+1), Toast.LENGTH_SHORT).show();
	        	ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    		list = ((GlobalVariable)getApplication()).getList();//ȡ������ 
	        	Map<String, Object> tempmap = new HashMap<String, Object>();
	        	tempmap = list.get(position);
	        	int ClockID = Integer.parseInt(tempmap.get("_id").toString());
	        	if(Integer.parseInt(tempmap.get("onUse").toString()) == R.drawable.on_using_right){
	        		//�ر����ӣ�list��ɾ��
	        		tempmap.put("onUse", R.drawable.on_using_wrong);
	        		ObjectPool.mAlarmHelper.closeAlarm(ClockID,"SmileAlarm", "time out!!");//ɾ�����ӵ�pendingintent
	        	}else{
	        		//��������
	        		tempmap.put("onUse", R.drawable.on_using_right);Log.w("TAG", "+++����");
	        		long time =  Long.valueOf(tempmap.get("time").toString());
	        		ObjectPool.mAlarmHelper.openAlarm(ClockID,"SmileAlarm", "time out!!",time);
	        	}
	        	
	        	list.set(position, tempmap);
	        	((GlobalVariable)getApplication()).setList(list); //������� 
	        	onCreate(null);
	        	//�ĳɵ�ǰ�Ѿ����õ�ʱ��
				
	        }  
	    });  
		//-------����
		registerForContextMenu(mListView);  
		
		//--------java���������°�ť
		//setAdapter(adapter);
		//Button button = new Button(this);
		//button.setText("����������");
		//FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
		//		FrameLayout.LayoutParams.MATCH_PARENT,
		//		FrameLayout.LayoutParams.WRAP_CONTENT);
		//lp.gravity = Gravity.BOTTOM;
		//addContentView(button, lp);// ������ص�
		//---
		//---
		//((GlobalVariable)getApplication()).setList(list);
		//button.setOnClickListener(new OnClickListener() {
		SysSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mCalendar.setTimeInMillis(System.currentTimeMillis());
				int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
				int mMinute = mCalendar.get(Calendar.MINUTE);
				new TimePickerDialog(ClockDisplay.this,
						new TimePickerDialog.OnTimeSetListener() {
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								mCalendar.setTimeInMillis(System
										.currentTimeMillis());
								mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
								mCalendar.set(Calendar.MINUTE, minute);
								mCalendar.set(Calendar.SECOND, 0);
								mCalendar.set(Calendar.MILLISECOND, 0);
								int ClockID = ((GlobalVariable)getApplication()).getClockID();Log.w("TAG", "ClockID="+ClockID);
								boolean addClockSucceed = addList(hourOfDay, minute, ClockID, mCalendar.getTimeInMillis());
								if(addClockSucceed){
									//ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
									//list = ((GlobalVariable)getApplication()).getList();//ȡ������	
									ObjectPool.mAlarmHelper.openAlarm(ClockID,"SmileAlarm", "time out!!",mCalendar.getTimeInMillis());
									}//��һ������Ϊrequestcode����ʾ���ӵĽ���id��pendingintent��id��
								else{((GlobalVariable)getApplication()).setClockID();Log.w("TAG", "����ʧ��");}
							}
						}, mHour, mMinute, true).show();
			}
		});
	}
	//�����˵�����
	 @Override  
	 public void onCreateContextMenu(ContextMenu menu, View v,  
			 ContextMenuInfo menuInfo) {
			 	menu.setHeaderTitle("��ѡ�����");  
		        menu.add(0, 1, 0, "�޸�");  //��1��������ʾid
		        menu.add(0, 2, 1, "ɾ��");//��2��������ʾ����ѡ��
		        menu.add(0, 3, 2, "������Ϣ");  //��3��������ʾ˳��
	        }  
	 public boolean onContextItemSelected(MenuItem item) {  
	        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();  
	        int position = info.position;
	        // info.targetView�õ�list.xml�е�LinearLayout����.  
	        //ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			//list = ((GlobalVariable)getApplication()).getList();//ȡ������ 
            switch (item.getItemId()) {  
            case 1:  
                // �༭���� ��list��ʾ�͹㲥ͬʱ����
            	
            	Toast.makeText(ClockDisplay.this,"������޸�"+(position+1), Toast.LENGTH_SHORT).show();
            	mCalendar.setTimeInMillis(System.currentTimeMillis());
				int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
				int mMinute = mCalendar.get(Calendar.MINUTE);
				final int input_position = position;// ʹ�������ڲ��࣬��ʽ�����ⲿ�������ⲿ������Ҫfinal���Ρ�
				new TimePickerDialog(ClockDisplay.this,new TimePickerDialog.OnTimeSetListener() {
							public void onTimeSet(TimePicker view,int hourOfDay, int minute) {
								mCalendar.setTimeInMillis(System.currentTimeMillis());
								mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
								mCalendar.set(Calendar.MINUTE, minute);
								mCalendar.set(Calendar.SECOND, 0);
								mCalendar.set(Calendar.MILLISECOND, 0);
								//boolean addClockSucceed = addList(hourOfDay, minute);
								//if(addClockSucceed){
								//ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
								//list = ((GlobalVariable)getApplication()).getList();//ȡ������	
								//
								int ClockID = ((GlobalVariable)getApplication()).getClockID();Log.w("TAG", "ClockID="+ClockID);
								boolean addClockSucceed = addList(hourOfDay, minute, ClockID, mCalendar.getTimeInMillis());
								if(addClockSucceed){
									RemoveList(input_position);
									ObjectPool.mAlarmHelper.openAlarm(ClockID,"SmileAlarm", "time out!!",mCalendar.getTimeInMillis());
									Log.w("TAG", "�޸������ӵ�idΪ" + ClockID);
									}//��һ������Ϊrequestcode����ʾ���ӵĽ���id��pendingintent��id��
								else{((GlobalVariable)getApplication()).setClockID();Log.w("TAG", "����ʧ��");}
								//	}//��һ������Ϊrequestcode����ʾ���ӵĽ���id��pendingintent��id��
							}
						}, mHour, mMinute, true).show();  
                break;  
            case 2:  
                // ɾ������  
        		Toast.makeText(ClockDisplay.this,"�����ɾ��"+(position+1), Toast.LENGTH_SHORT).show();
        		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	    		list = ((GlobalVariable)getApplication()).getList();//ȡ������ 
	        	Map<String, Object> tempmap = new HashMap<String, Object>();
	        	tempmap = list.get(position);
	        	int ClockID = Integer.parseInt(tempmap.get("_id").toString());
	        	ObjectPool.mAlarmHelper.closeAlarm(ClockID,"SmileAlarm", "time out!!");//ɾ�����ӵ�pendingintent
        		RemoveList(position);
            default:  
                break;  
            }  
	        return false;  
	    }  
	 //-----------------------------------------------
	 //-----------------------------------------------
	 //-----------------------------------------------
	 //-----------------------------------------------
	 //-----------------------------------------------
	 public boolean addList(int hour, int minute, int id, long time) {
		boolean addClockSucceed = false;
		Map<String, Object> item = new HashMap<String, Object>();
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = ((GlobalVariable)getApplication()).getList();//ȡ������ 
		String tmps;
		if(minute>=0 && minute<=9){
			tmps = String.valueOf(hour) + ":0" + String.valueOf(minute);
		}else{
			tmps = String.valueOf(hour) + ":" + String.valueOf(minute);
		}
		//��Ҫ����list����Ϣͬһ������
		item.put("image", R.drawable.clock_icon2);
		item.put("_id", id);
		item.put("onUse", R.drawable.on_using_right);
		item.put("time", time);  //long
		//item.put("title", "����"+(list.size()+1)); //�������������
		item.put("info", tmps);	
		int FlagToAdd =0;
		int cnt = 0;
		int order = -1;
		boolean done = false;
		for (Map<String, Object> i : list){
			String strHour, strMin;
			for (String k : i.keySet())  {//�õ�map���е�key
				int mHour =-1, mMin = -1;
				 if(k=="info"){
					 String strTime = (String)i.get(k);
					 if(strTime ==getString(R.string.clock_setted)) {continue;}//list����
					 for(int j = 0;j<strTime.length();j++){				   	  
							if(strTime.charAt(j) == ':'){
								strHour = strTime.substring(0,j);
								strMin = strTime.substring(j+1);					
								mHour=Integer.parseInt(strHour);
								mMin=Integer.parseInt(strMin);
								break;
							}
					 }
				 }
				//������Ƚϣ���item�洢��ȡ����ֵ
				//����hour minute
				if(hour < mHour){
					//add to list
					if(list != null){
						FlagToAdd = 1;//hС��ֱ������
						order = cnt;
						done = true;
						break;
					}else{
						Log.w("TAG", "list is empty2"); // ������Ϣ	
					}
				} else if(hour == mHour){
					if(minute < mMin){
						//add to list 
						if(list != null){
							FlagToAdd = 2;//h��ͬmС��ֱ������
							order = cnt;
							done = true;
							break;
						}else{
							//FlagToAdd = 3;//ֱ�����������
							Log.w("TAG", "list is empty3"); // ������Ϣ
							
						}
					}else if(minute == mMin){
						FlagToAdd = 3;//ʱ����ͬ����ʧ��
						done = true;
						break;
					}else{continue;}									
				}else{continue;} 				
			}//����ѭ��������
			if(done){
				done = false;
				break;
			}
			else{
				cnt++;
			}
		}
		//ͬһ�������޸�list
		switch(FlagToAdd){
			case 0:{//û���޸Ĺ�,ֱ�����������
				addClockSucceed = true;
				item.put("title", "����"+(list.size()+1));
				list.add(item);
				((GlobalVariable)getApplication()).setList(list); //������� 
				Toast.makeText(ClockDisplay.this, "��������ʱ��Ϊ" + tmps, Toast.LENGTH_SHORT).show();
				break;}
			case 1:{//hС
				addClockSucceed = true;
				Map<String, Object> TempMap = new HashMap<String, Object>();
				TempMap = list.get(list.size()-1);
				item.put("title", "����"+(order+1));
				list.add(TempMap);
				for(int i=list.size()-2;i>order;i--){
			    	list.set(i, list.get(i-1));
			    }
				list.set(order, item);
				break;
			}
			case 2:{//hһ��mС
				addClockSucceed = true;
				Map<String, Object> TempMap = new HashMap<String, Object>();
				TempMap = list.get(list.size()-1);
				item.put("title", "����"+(order+1));
				list.add(TempMap);
				for(int i=list.size()-2;i>order;i--){
			    	list.set(i, list.get(i-1));
			    }
				list.set(order, item);
				break;
			}
			case 3:{//ʱ����ͬ����ʧ��
				addClockSucceed = false;
				Toast.makeText(ClockDisplay.this, "��ʱ���Ѿ�����һ������\n\t\t\t����ʧ��", Toast.LENGTH_SHORT).show();
				break;
			}
			default:{
				addClockSucceed = false;
				Log.w("TAG", "FlagToAdd--Error");break;
			}
		}
		IDreset();
		((GlobalVariable)getApplication()).setList(list); //������� 
		onCreate(null);
		return addClockSucceed;
	}	
	 
	 public void RemoveList(int position) {
		 ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 list = ((GlobalVariable)getApplication()).getList();//ȡ������
		 list.remove(position);
		 IDreset();
		 ((GlobalVariable)getApplication()).setList(list); //������� 
		 onCreate(null);
	 }

	public void IDreset() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = ((GlobalVariable)getApplication()).getList();//ȡ������
		Map<String, Object> TempMap = new HashMap<String, Object>();
		for(int i=0; i<list.size();i++){
			TempMap = list.get(i);
			TempMap.put("title", "����"+(i+1));
			list.set(i, TempMap);
		}
		((GlobalVariable)getApplication()).setList(list); //������� 
		 onCreate(null);
	}
}