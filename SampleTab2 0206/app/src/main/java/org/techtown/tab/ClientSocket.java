package org.techtown.tab;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ClientSocket extends Thread {
    static String serverIp = "10.10.141.7";
    static int serverPort=5000;
    static  String clientId = "KYH_JAVA";
    static String clientPw = "Passwd";
    static Socket socket = null;

    ClientSocket(){
    }
    ClientSocket(String strIp, int intPort){ //옆의 MainActivity 와 소켓 통신하기위한 선언
        serverIp = strIp;
        serverPort = intPort;
    }
    @Override
    public void run() { //Thread 실행
        try {
            socket = new Socket();
            displayText("[연결 요청]");
            Log.d("run()", "ip : "+serverIp +"," + "id :" + serverPort);
            socket.connect(new InetSocketAddress(serverIp, serverPort));
            displayText("[연결 성공]");

            byte[] bytes = new byte[100];
            String message = null;
            InputStream is = socket.getInputStream(); //InputStream을 is로 선언

            while (true) { //수신부 구현 (while문으로 계속 실행이 되도록)
                int readByteCount = is.read(bytes);
                if(readByteCount<=0)
                    break;
                message = new String(bytes, 0, readByteCount, "UTF-8");
                displayText("[데이터 받기 성공]: " + message);
                sendMainActivity(message);
            }
            is.close();
        } catch (Exception e) {
            displayText("서버가 중지되었습니다");
        }

        if (!socket.isClosed()) { // 만약 소켓이 닫혔다면
            try {
                socket.close();
            } catch (IOException e1) {
            }
        }
        displayText("클라이언트가 종료되었습니다");

    }
    void  stopClient(){
        if(socket !=null && !socket.isClosed()){ //NULL 값이 아니고 소켓이 닫혀있다면
            displayText("클라이언트 중지");
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 송신부 구현
    synchronized void sendData(String data) { // Thread하나 더 구현하여서 수신단과 충돌이 나지 않게함
        Thread sendThread = new Thread() {
            @Override
            public void run() {
                try {
                    byte[] bytes = data.getBytes("UTF-8");
                    OutputStream os = socket.getOutputStream();
                    os.write(bytes);
                    os.flush();
                    displayText("데이터 보내기 성공");
                } catch (Exception e) {
                    displayText("서버를 확인하세요");
                }
            }
        };
        sendThread.start();
    }
    synchronized void sendMainActivity(String text){
        Message message = MainActivity.mainHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("msg",text);
        message.setData(bundle);
        MainActivity.mainHandler.sendMessage(message);
    }
    synchronized void displayText(String text){
        Log.d("displayText", text);
    }
}