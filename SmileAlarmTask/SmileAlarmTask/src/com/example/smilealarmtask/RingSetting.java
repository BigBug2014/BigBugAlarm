package com.example.smilealarmtask;

import java.util.ArrayList;  
import java.util.List;  
  
import android.net.Uri;
import android.os.Bundle;  
import android.app.Activity;  
import android.app.AlertDialog;  
import android.content.DialogInterface;
import android.view.Menu;  
import android.view.View;  
import android.widget.CheckBox;  
import android.widget.CompoundButton;  
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.media.*;
import android.content.*;
import android.preference.*;

public class RingSetting extends PreferenceActivity {  
      
	//Intent intent=new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);   
    //intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);   
    //intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "…Ë÷√ƒ÷¡Â¡Â…˘");   
    //startActivityForResult(intent, AlarmButton);
	
	private static final int ALARM_RINGTONE_PICKED = 3;
	public static final String ALARM_RINGTONE    = "pref_alarm_ringtone";  
    public static final String ALARM_RINGTONE_TITLE_NAME    = "pref_alarm_ringtone_title_name";
    private String alarmStr;
    private Preference mAlarmSoundsPref; 
    
    @SuppressWarnings("deprecation")
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        addPreferencesFromResource(R.layout.ringsetting);
        mAlarmSoundsPref = findPreference("pref_alarm_ringtone");  
        
        //set default preferences
        SharedPreferences innersharedPreferences = PreferenceManager.getDefaultSharedPreferences(RingSetting.this);  
        String alarmRingtoneTitleName = innersharedPreferences.getString(ALARM_RINGTONE_TITLE_NAME, null);  
        if(alarmRingtoneTitleName!=null){  
            mAlarmSoundsPref.setSummary(alarmRingtoneTitleName);  
        }else{  
            mAlarmSoundsPref.setSummary(getString(R.string.pref_summary_alarm_ringtone));  
        }
    }
    
    @SuppressWarnings("deprecation")
	@Override  
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {  
        if(preference == mAlarmSoundsPref){  
            doPickAlarmRingtone();  
        }    
        return super.onPreferenceTreeClick(preferenceScreen, preference);  
    }
    
    private void doPickAlarmRingtone(){  
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);    
        alarmStr = sharedPreferences.getString(ALARM_RINGTONE, null);   
          
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);  
        // Allow user to pick 'Default'  
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);  
        // Show only ringtones  
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);  
        //set the default Notification value  
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));  
        // Don't show 'Silent'  
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, true);  
          
        Uri alarmUri;  
        if (alarmStr != null) {  
            alarmUri = Uri.parse(alarmStr);  
            // Put checkmark next to the current ringtone for this contact  
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, alarmUri);  
        } else {  
            // Otherwise pick default ringtone Uri so that something is selected.  
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);  
            // Put checkmark next to the current ringtone for this contact  
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, alarmUri);  
        }  
          
        startActivityForResult(intent, ALARM_RINGTONE_PICKED);  
    }
    
    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);    
        SharedPreferences.Editor editor = sharedPreferences.edit();    
        if (resultCode != RESULT_OK) {  
            return;  
        }  
        Uri pickedUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);  
            if(null == pickedUri){  
                  editor.putString(ALARM_RINGTONE_TITLE_NAME, getString(R.string.select_ringtone_slient));  
                  editor.putString(ALARM_RINGTONE, null);   
                  editor.commit();   
            }else{  
                  Ringtone ringtone =  RingtoneManager.getRingtone(RingSetting.this, pickedUri);  
                  String strRingtone = ringtone.getTitle(RingSetting.this);  
                  editor.putString(ALARM_RINGTONE_TITLE_NAME, strRingtone);  
                  editor.putString(ALARM_RINGTONE, pickedUri.toString());    
                  editor.commit();    
            }       
    } 
  
}  
