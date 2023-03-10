package com.example.countdown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

   private TextView mTextViewTimeCountDown;
   private Button mButtonStartPause;
   private Button mButtonReset;


   private static final long START_TIME_IN_MILLIS=6000;
   private CountDownTimer countDownTimer;
   private boolean mTimeRunning=false;
   private long mTimeLeftInMillis=START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewTimeCountDown=findViewById(R.id.count_down);
        mButtonStartPause=findViewById(R.id.button_start_pause);
        mButtonReset=findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mTimeRunning){
                    startTimer();
                }else{
                   pauseTimer();
                }


            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTimeLeftInMillis=START_TIME_IN_MILLIS;
                updateCountDown();
                mButtonReset.setVisibility(View.INVISIBLE);
                mButtonStartPause.setVisibility(View.VISIBLE);

            }
        });



    }


    private void startTimer(){

        countDownTimer=new CountDownTimer(mTimeLeftInMillis,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis=millisUntilFinished;
                updateCountDown();

            }

            @Override
            public void onFinish() {

                mButtonStartPause.setText("Start");
                mTimeRunning=false;
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);


            }
        }.start();

        mButtonStartPause.setText("Pause");
        mTimeRunning=true;
        mButtonReset.setVisibility(View.INVISIBLE);


    }

    private void updateCountDown(){

        int minutes=(int)(mTimeLeftInMillis/1000)/60;
        int second=(int)(mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
        mTextViewTimeCountDown.setText(timeLeftFormatted);

    }

    private void pauseTimer(){
        countDownTimer.cancel();
        mTimeRunning=false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
        mButtonStartPause.setVisibility(View.INVISIBLE);
    }



}