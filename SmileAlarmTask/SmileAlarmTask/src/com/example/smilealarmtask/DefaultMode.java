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
    private char[] mChar=new char[48];//显示诗句
    private char[] mCharAnswer=new char[7];//应输入诗句
    private int s=0;
    private int id=0;
    private int i=0;
    public Boolean FlagUnlock;
 //  String str="abcde,12345,abcde,12345,abcde,12345,abcde,12345.";
 //   String(char a[]);
 //   char a[]={'b','o','y'};
 //   String s=new String(a);


//    private char poem[]={
//        '岱','宗','夫','如','何',',',
//        '齐','鲁','青','未','了','。',
//        '造','化','钟','神','秀',',',
//        '阴','阳','割','昏','晓','。',
//        '荡','胸','生','层','云',',',
//        '决','眦','入','归','鸟','。',
//        '会','当','凌','绝','顶',',',
//        '一','览','众','山','小','。'};

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

//生成随机数
//random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
//以生成[10,20]随机数为例，首先生成0-20的随机数，然后对(20-10+1)取模得到[0-10]之间的随机数，然后加上min=10，最后生成的是10-20的随机数
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

    //显示填空诗句
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
            //读取用户输入信息
            String str1;
            mEditText = (EditText)findViewById(R.id.editText_default);
            str1=mEditText.getText().toString();
            String answer=String.valueOf(mCharAnswer);
            //判断用户输入是否正确，输入正确时跳回主界面
            if(str1.equals(answer)){
                Intent intent = new Intent();
                intent.setClass(DefaultMode.this, UnlockTask.class);
                //传参FlagUnlock
                Bundle bundle = new Bundle();
                bundle.putBoolean("FlagUnlock",true);
                intent.putExtras(bundle);
                /* 调用一个新的Activity */
                startActivity(intent);
                /* 关闭原本的Activity */
                DefaultMode.this.finish();
//               Toast.makeText(DefaultMode.this,"Answer Right!",Toast.LENGTH_LONG).show();
            }
            else {
//                Toast.makeText(DefaultMode.this,"Answer Wrong!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(DefaultMode.this, UnlockTask.class);
                //传参FlagUnlock
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

