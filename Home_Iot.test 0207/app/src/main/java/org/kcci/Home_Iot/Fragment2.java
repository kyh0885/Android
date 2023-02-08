package org.kcci.Home_Iot;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class Fragment2 extends Fragment {
    MainActivity mainActivity;
    Button button;
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageButton imageButtonLight;
    ImageButton imageButtonBlind;
    ImageButton imageButtonAir;
    Switch switch1;
    Switch switch2;
    Switch switch3;

    boolean imageButtonLightCheck;
    boolean imageButtonBlindCheck;
    boolean imageButtonAirCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        button = view.findViewById(R.id.button);
        switch1 = view.findViewById(R.id.switch1);
        switch2 = view.findViewById(R.id.switch2);
        switch3 = view.findViewById(R.id.switch3);
        imageButtonLight = view.findViewById(R.id.imageButtonLight1);
        imageButtonBlind = view.findViewById(R.id.imageButtonBlind1);
        imageButtonAir = view.findViewById(R.id.imageButtonAir1);
        mainActivity = (MainActivity) getActivity();

        imageButtonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClientThread.socket != null)
                    if (imageButtonLightCheck)
                        mainActivity.clientThread.sendData("[KYH_ARD]LED OFF\n");
                    else
                        mainActivity.clientThread.sendData("[KYH_ARD]LED ON\n");
            }
        });
        imageButtonBlind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClientThread.socket != null)
                    if (imageButtonBlindCheck)
                        mainActivity.clientThread.sendData("[KYH_ARD]BLIND OFF\n");
                    else
                        mainActivity.clientThread.sendData("[KYH_ARD]BLIND ON\n");
            }
        });
        imageButtonAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClientThread.socket != null)
                    if (imageButtonAirCheck)
                        mainActivity.clientThread.sendData("[KYH_ARD]AIR OFF\n");
                    else
                        mainActivity.clientThread.sendData("[KYH_ARD]AIR ON\n");
            }
        });
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()){
                    switch1.setChecked(false);
                    mainActivity.clientThread.sendData("LED ON");
                }else{
                    switch1.setChecked(false);
                    mainActivity.clientThread.sendData("LED OFF");
                }
            }
        });
        switch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(switch2.isChecked()){
                    switch2.setChecked(false);
                    mainActivity.clientThread.sendData("BLIND ON");
                }else{
                    switch2.setChecked(false);
                    mainActivity.clientThread.sendData("BLIND OFF");
                }
            }
        });
        switch3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(switch3.isChecked()){
                    switch3.setChecked(false);
                    mainActivity.clientThread.sendData("AIR ON");
                }else{
                    switch3.setChecked(false);
                    mainActivity.clientThread.sendData("AIR OFF");
                }
            }
        });
        return view;
    }

    void recvDataProcess(String strRecvData) { // 수신 데이터에 의한
        String[] splitLists = strRecvData.toString().split("\\[|]|@|\\r");
        for(int i=0; i<splitLists.length; i++)
            Log.d("recvDataProcess","i : "+i+", value : "+splitLists[i]);
        if(splitLists[2].equals("LED ON")){
            imageButtonLight.setImageResource(R.drawable.led_on);
            imageButtonLightCheck =true;
            switch1.setChecked(true);
        }else if (splitLists[2].equals("LED OFF")){
            imageButtonLight.setImageResource(R.drawable.led_off);
            imageButtonLightCheck =false;
            switch1.setChecked(false);
        }
        else if(splitLists[2].equals("BLIND ON")){
            imageButtonBlind.setImageResource(R.drawable.blinds_on);
            imageButtonBlindCheck =true;
            switch2.setChecked(true);
        }else if (splitLists[2].equals("BLIND OFF")){
            imageButtonBlind.setImageResource(R.drawable.blinds_off);
            imageButtonBlindCheck =false;
            switch2.setChecked(false);
        }
        else if(splitLists[2].equals("AIR ON")){
            imageButtonAir.setImageResource(R.drawable.air_on);
            imageButtonAirCheck =true;
            switch3.setChecked(true);
        }else if (splitLists[2].equals("AIR OFF")){
            imageButtonAir.setImageResource(R.drawable.air_off);
            imageButtonAirCheck =false;
            switch3.setChecked(false);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(button.isClickable()){
                    textView.append(splitLists[3] + "%"); //조도
                    textView2.append(splitLists[4] + "°C"); //온도
                    textView3.append(splitLists[5]+ "%"); //습도
                }
            }
        });

    }
}
