package com.kcci.pleintent0202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "mike");
                setResult(RESULT_OK, intent);
                finish();
            }

        });
        Intent gIntent = getIntent(); //여기 Intent~Log.d까지를 활용하여 Main 과 교류할 수 있다.
        Intent gIntent1 = getIntent();
        String gStr = gIntent.getStringExtra("arg");
        String gStr1 = gIntent1.getStringExtra("arg1");
        Log.d("MenuActivity",gStr);
        Log.d("MenuActivity",gStr1);
    }
}