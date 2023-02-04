package com.kcci.scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    String strId =null;
    String strPw =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linearlayout);

        Button login = findViewById(R.id.login);
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);
        login.setOnClickListener(new View.OnClickListener(){ //클릭했을때 이벤트를 처리하기 위함(시그널 메소드)

            @Override
            public void onClick(View view) { //실제 클릭이 되었을때 작동하는 메소드
                strId = editTextId.getText().toString(); //입력된 문자열을 읽기 위함, 문자열로 변환
                strPw = editTextPw.getText().toString();
                if(strId.length()!=0 &&strPw.length()!=0){
                    Log.d("onClick()","id: "+strId +"pw : "+strPw);
                    Toast.makeText(view.getContext(),"id: "+strId +" pw : "+strPw, Toast.LENGTH_LONG).show(); // 클릭버튼 눌렀을때 메세지창 보여주기
                    editTextId.setText(""); // 다쓰고 로그인하면 창에있는것을 지워주라
                    editTextPw.setText("");
                }else{
                    Toast.makeText(view.getContext(),"id와 pw가 입력되지 않았습니다", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}