package org.techtown.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment3Telnet extends Fragment {

    ClientSocket clientSocket;
    TextView textView;
    ScrollView scrollView;
    public SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        EditText editTextIp = view.findViewById(R.id.editorTextIp); //10.10.141.7 로 고정시킴
        EditText editTextPort = view.findViewById(R.id.editorTextPort); //Port 번호 5000으로 고정시킴
        ToggleButton toggleButtonStart = view.findViewById(R.id.start);
        Button buttonSend = view.findViewById(R.id.buttonSend);
        EditText editTextSend = view.findViewById(R.id.editTextSend);
        buttonSend.setEnabled(false);

        textView = view.findViewById(R.id.textViewRecv);
        scrollView = view.findViewById(R.id.scrollViewRecv);


        toggleButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toggleButtonStart.isChecked()){
                    String strIp = editTextIp.getText().toString();
                    int intPort = Integer.parseInt(editTextPort.getText().toString());
                    clientSocket = new ClientSocket(strIp,intPort);
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
        return view;
    }
    void recvDataProcess(String data){

        Date date = new Date();
        String strDate = dataFormat.format(date);
        data += "\n";
        strDate += " "+ data;
        textView.append(strDate);
        scrollView.fullScroll(View.FOCUS_DOWN);
        }
}
