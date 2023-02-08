package org.kcci.Home_Iot;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Fragment1Home extends Fragment {
    MainActivity mainActivity;
    ImageButton imageButtonLamp;
    ImageButton imageButtonPlug;
    boolean imageButtonLampCheck;
    boolean imageButtonPlugCheck;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1home, container, false);
        imageButtonLamp = view.findViewById(R.id.imageButtonLamp);
        imageButtonPlug = view.findViewById(R.id.imageButtonPlug);
        mainActivity = (MainActivity) getActivity();

        imageButtonLamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClientThread.socket != null)
                    if (imageButtonLampCheck) //초기값 0
                        mainActivity.clientThread.sendData("[KYH_ARD]LAMP OFF\n");
                    else
                        mainActivity.clientThread.sendData("[KYH_ARD]LAMP ON\n");
            }
        });
        imageButtonPlug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ClientThread.socket != null)
                    if (imageButtonPlugCheck) //초기값 0
                        mainActivity.clientThread.sendData("[KYH_ARD]PLUG OFF\n");
                    else
                        mainActivity.clientThread.sendData("[KYH_ARD]PLUG ON\n");
            }
        });

        return view;
    }
        void recvDataProcess(String strRecvData) { // 수신 데이터에 의한 램프와 플러그 제어
            String[] splitLists = strRecvData.toString().split("\\[|]|@|\\r");
            for(int i=0; i<splitLists.length; i++)
                Log.d("recvDataProcess","i : "+i+", value : "+splitLists[i]);
            if(splitLists[2].equals("LAMP ON")){
                imageButtonLamp.setImageResource(R.drawable.plug_on);
                imageButtonLampCheck =true;
            }else if (splitLists[2].equals("LAMP OFF")){
                imageButtonLamp.setImageResource(R.drawable.plug_on);
                imageButtonLampCheck =false;
            }
            else if(splitLists[2].equals("PLUG ON")){
                imageButtonLamp.setImageResource(R.drawable.plug_on);
                imageButtonPlugCheck =true;
            }else if (splitLists[2].equals("PLUG OFF")){
                imageButtonLamp.setImageResource(R.drawable.plug_off);
                imageButtonPlugCheck =false;
            }
        }
    }

