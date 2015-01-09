package com.example.smilealarmtask;

/**
 * Created by changyangyang on 14/12/5.
 */
import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class UnlockTask extends Activity {

    boolean FlagUnlock;
    //private Context c;

    //public UnlockTask(Context c) {
    //    this.c = c;

    //}




    public void onCreate(Bundle savedInstanceState) {
        UnlockOutcome();
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        //����FlagUnlock
        Bundle bunde = this.getIntent().getExtras();
        FlagUnlock=bunde.getBoolean("FlagUnlock");
    }

//    public Boolean getFlagUnlock(){
//        return FlagUnlock;
//    }

    //�����󵯳��Ի����������ɹ���ѡ������˵���ȡ�������������ɹ���ѡ�����������Ĭ�Ͻ���
    public void UnlockOutcome(){
        FlagUnlock=true;
        AlertDialog.Builder builder = new AlertDialog.Builder(UnlockTask.this);
        if (FlagUnlock==true) {
            builder.setTitle("outcome")
                    .setIcon(R.drawable.clock)  //ClockDemo\res\drawable-hdpi�µ��ļ�
                    .setMessage("Unlock Succeed!")
                    .setNegativeButton("cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                    .setPositiveButton("go to main activity", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            //TODU auto-generated method stub
                            arg0.dismiss();
                            //��ת����ҳ��
                            Intent it = new Intent(UnlockTask.this,SmileAlarmTask.class);
                            startActivity(it);
                        }
                    });
            builder.create().show();
        }
        else {
            builder.setTitle("outcome")
                    .setIcon(R.drawable.clock)  //ClockDemo\res\drawable-hdpi�µ��ļ�
                    .setMessage("Unlock Failed!")
                    .setNegativeButton("Default mode",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    //TODU auto-generated method stub
                                    arg0.dismiss();
                                    //��ת��Ĭ�Ͻ�������
                                    Intent it = new Intent(UnlockTask.this,DefaultMode.class);
                                    startActivity(it);
                                }
                            })
                    .setPositiveButton("Unlock again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            //TODU auto-generated method stub
                            arg0.dismiss();
                            //��ת����ѡ��������
                            Intent it = new Intent(UnlockTask.this,DefaultMode.class);
                            startActivity(it);
                        }
                    });
            builder.create().show();
        }
    }
}



