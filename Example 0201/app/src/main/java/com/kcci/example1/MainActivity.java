package com.kcci.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strId = null;
    String strPw = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.button);
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);

        TextView textView = (TextView)findViewById(R.id.textView);
        TextView textView2 = (TextView)findViewById(R.id.textView2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strId = editTextId.getText().toString();
                strPw = editTextPw.getText().toString();
                if (strId.length() != 0 && strPw.length() != 0) {
                    Log.d("onClick()", "id: " + strId + "pw : " + strPw);
                    Toast.makeText(view.getContext(), "id : " + strId + ", pw : " + strPw, Toast.LENGTH_LONG).show();
                    textView.setText(strId);
                    textView2.setText(strPw);
                }
            }
        });
    }
}