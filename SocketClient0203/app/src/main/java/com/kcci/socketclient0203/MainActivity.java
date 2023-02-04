package com.kcci.socketclient0203;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static Handler mainHandler;
    ClientSocket clientSocket;
    TextView textView;
    ScrollView scrollView;
    public SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
    // 날짜 띄우기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextIp = findViewById(R.id.editorTextIp); //10.10.141.7 로 고정시킴
        EditText editTextId = findViewById(R.id.editorTextId); //Port 번호 5000으로 고정시킴
        ToggleButton toggleButtonStart = findViewById(R.id.start);
        Button buttonSend =findViewById(R.id.buttonSend);
        EditText editTextSend = findViewById(R.id.editTextSend);
        buttonSend.setEnabled(false);

        textView = findViewById(R.id.textViewRecv);
        scrollView = findViewById(R.id.scrollViewRecv);


        toggleButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButtonStart.isChecked()){
                    String strIp = editTextIp.getText().toString();
                    String strId = editTextId.getText().toString();
                    clientSocket = new ClientSocket(strIp,strId);
                    clientSocket.start(); //Client 소켓통신 시작
                    buttonSend.setEnabled(true);

                }else{
                    clientSocket.stopClient();
                    buttonSend.setEnabled(false);
                }
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSend = editTextSend.getText().toString();
                clientSocket.sendData(strSend);
                editTextSend.setText("");
            }
        });
        mainHandler = new MainHandler();
    }
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            Date date = new Date();
            String data = bundle.getString("msg");
            String strDate = dataFormat.format(date);
            data += "\n";
            strDate += " "+ data;
            textView.append(strDate);
            scrollView.fullScroll(View.FOCUS_DOWN);
        }
    }

}