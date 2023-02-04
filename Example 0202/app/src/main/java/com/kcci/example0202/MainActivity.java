package com.kcci.example0202;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String strIp = null;
    String strId = null;
    String strPw = null;

    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button2);
        EditText editIp = findViewById(R.id.editIp);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        String text2 = intent.getStringExtra("text2");

        TextView textView = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView3);
        textView.setText(text);
        textView2.setText(text2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                strIp = editIp.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
                intent.putExtra("text",strIp);
                startActivity(intent);
            }
        });

    }
}
