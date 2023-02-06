package org.techtown.tab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_CODE_LOGIN_ACTIVITY = 100;    //상수 정의
    static Handler mainHandler;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3Telnet fragment3;
    ClientSocket clientSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3Telnet();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1).commit();

                        return true;
                    case R.id.tab2:
                        Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();

                        return true;
                    case R.id.tab3:
                        Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment3).commit();

                        return true;
                }

                return false;
            }
        });
        mainHandler = new MainHandler();
    }
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();             // msg로 받은 데이터를 bundle 객체에 저장
            String data = bundle.getString("msg");     //bundle객체에서 저장된 "msg"text data에 저장
            fragment1.recvDataProcess(data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.login:
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.putExtra("serverIp", ClientSocket.serverIp);
                intent.putExtra("serverPort", ClientSocket.serverPort);
                intent.putExtra("clientId", ClientSocket.clientId);
                intent.putExtra("clientPw", ClientSocket.clientPw);
                startActivityForResult(intent, REQUEST_CODE_LOGIN_ACTIVITY);
                break;
            case R.id.setting:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_LOGIN_ACTIVITY){
            if(resultCode == RESULT_OK){
                ClientSocket.serverIp = data.getStringExtra("serverIp");
                ClientSocket.serverPort = data.getIntExtra("serverPort", ClientSocket.serverPort);
                ClientSocket.clientId = data.getStringExtra("clientId");
                ClientSocket.clientPw = data.getStringExtra("clientPw");
                clientSocket = new ClientSocket();
                clientSocket.start();
            }else if (resultCode == RESULT_CANCELED){
                clientSocket.stopClient();
                ClientSocket.socket = null;
            }
        }
    }
}
