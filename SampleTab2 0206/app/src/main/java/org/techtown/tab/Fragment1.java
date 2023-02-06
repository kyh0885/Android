package org.techtown.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment1 extends Fragment {
    MainActivity mainActivity;
    ClientSocket clientSocket;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        EditText editText = view.findViewById(R.id.editText);
        Button buttonSend = view.findViewById(R.id.button2);
        buttonSend.setEnabled(true);
        mainActivity = (MainActivity)getActivity();

        textView = view.findViewById(R.id.textView);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSend = editText.getText().toString();
                mainActivity.clientSocket.sendData(strSend);
                editText.setText("");
            }
        });
        return view;
    }
    void recvDataProcess(String data){
        data += "\n";
        textView.append(data);
    }
}
