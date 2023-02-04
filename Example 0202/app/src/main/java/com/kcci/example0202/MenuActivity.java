package com.kcci.example0202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    String strId = null;
    String strPw = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button button = findViewById(R.id.button);
        EditText editTextId = findViewById(R.id.editTextId);
        EditText editTextPw = findViewById(R.id.editTextPw);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(text);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(RESULT_OK, intent);
                strId = editTextId.getText().toString();
                strPw = editTextPw.getText().toString();
                intent.putExtra("text",strId);
                intent.putExtra("text2",strPw);
                startActivity(intent);

                finish();
            }
        });
    }
}