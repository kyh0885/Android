package com.kcci.event0202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        textView = findViewById(R.id.textView);
        View view = findViewById(R.id.view); //첫번째 view만 되어있기 때문이다.
        view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if(action==MotionEvent.ACTION_DOWN){
                    println("손가락 눌림 : "+curX +", "+curY);
                } else if (action==MotionEvent.ACTION_MOVE) {
                    println("손가락 움직임 : "+curX +", "+curY);
                } else if (action==MotionEvent.ACTION_UP) {
                    println("손가락 뗌 : "+curX + ", "+curY);
                }
                return true;
            }
        });
    }

    private void println(String data) {
        textView.append(data+"\n");
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}