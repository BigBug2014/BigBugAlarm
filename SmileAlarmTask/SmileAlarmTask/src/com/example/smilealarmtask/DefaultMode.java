package com.example.smilealarmtask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by changyangyang on 14/12/14.
 */
public class DefaultMode extends Activity {

    private TextView mTestView;
    private EditText mEditText;
    private Button mButton;
    private char[] mChar=new char[48];//��ʾʫ��
    private char[] mCharAnswer=new char[7];//Ӧ����ʫ��
    private int s=0;
    private int id=0;
    private int i=0;
    public Boolean FlagUnlock;
 //  String str="abcde,12345,abcde,12345,abcde,12345,abcde,12345.";
 //   String(char a[]);
 //   char a[]={'b','o','y'};
 //   String s=new String(a);


//    private char poem[]={
//        '�','��','��','��','��',',',
//        '��','³','��','δ','��','��',
//        '��','��','��','��','��',',',
//        '��','��','��','��','��','��',
//        '��','��','��','��','��',',',
//        '��','��','��','��','��','��',
//        '��','��','��','��','��',',',
//        'һ','��','��','ɽ','С','��'};

    private char[] poem={
            '1','2','3','4','5','0',
            '1','2','3','4','5','0',
            '1','2','3','4','5','0',
            '1','2','3','4','5','0',
            '1','2','3','4','5','0',
            '1','2','3','4','5','0',
            '1','2','3','4','5','0',
            '1','2','3','4','5','0'};

    private char[] poem1={
            'a','b','c','d','e','0',
            'a','b','c','d','e','0',
            'a','b','c','d','e','0',
            'a','b','c','d','e','0',
            'a','b','c','d','e','0',
            'a','b','c','d','e','0',
            'a','b','c','d','e','0',
            'a','b','c','d','e','0'};

//���������
//random.nextInt(max)��ʾ����[0,max]֮����������Ȼ���(max-min+1)ȡģ��
//������[10,20]�����Ϊ������������0-20���������Ȼ���(20-10+1)ȡģ�õ�[0-10]֮����������Ȼ�����min=10��������ɵ���10-20�������
    //String[] args
    //private class RandomTest {
        public int random(int max,int min) {
      //      int max=8;
      //      int min=1;
            Random random = new Random();

            int s = random.nextInt(max)%(max-min+1) + min;
            System.out.println(s);
            return s;
        }
   // }

   // RandomTest random1;

  //  private void SelectChar{

  //  }


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defaultmode);

    //��ʾ���ʫ��
    mTestView = (TextView)findViewById(R.id.textView_default);
    s=random(8,1);
    id=random(2,1);
    if (id==1) {
        for (i = 0; i <= 47; i++) {
            if ((6 * (s - 1) <= i) && (i <= (6 * s - 1))) {
                mCharAnswer[i - 6 * (s - 1)] = poem[i];
                mChar[i] = '_';
            } else {
                mChar[i] = poem[i];
            }
        }
    }
    else{
        for (i = 0; i <= 47; i++) {
            if ((6 * (s - 1) <= i) && (i <= (6 * s - 1))) {
                mCharAnswer[i - 6 * (s - 1)] = poem1[i];
                mChar[i] = '_';
            } else {
                mChar[i] = poem1[i];
            }
        }
    }

//    System.out.println(mChar);
    mTestView.setText(mChar,0,48);


    mButton = (Button)findViewById(R.id.button_default);
    mButton.setOnClickListener(new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            //��ȡ�û�������Ϣ
            String str1;
            mEditText = (EditText)findViewById(R.id.editText_default);
            str1=mEditText.getText().toString();
            String answer=String.valueOf(mCharAnswer);
            //�ж��û������Ƿ���ȷ��������ȷʱ����������
            if(str1.equals(answer)){
                Intent intent = new Intent();
                intent.setClass(DefaultMode.this, UnlockTask.class);
                //����FlagUnlock
                Bundle bundle = new Bundle();
                bundle.putBoolean("FlagUnlock",true);
                intent.putExtras(bundle);
                /* ����һ���µ�Activity */
                startActivity(intent);
                /* �ر�ԭ����Activity */
                DefaultMode.this.finish();
//               Toast.makeText(DefaultMode.this,"Answer Right!",Toast.LENGTH_LONG).show();
            }
            else {
//                Toast.makeText(DefaultMode.this,"Answer Wrong!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(DefaultMode.this, UnlockTask.class);
                //����FlagUnlock
                Bundle bundle = new Bundle();
                bundle.putBoolean("FlagUnlock",false);
                intent.putExtras(bundle);
                startActivity(intent);
                DefaultMode.this.finish();
            }
        }
    });

}



}

