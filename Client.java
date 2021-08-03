package Multichatt;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = null; //소켓생성
            
            //소켓 서버에 접속
            socket = new Socket("192.168.219.108", 4321); //연결요청보냄
            System.out.println("connect server!"); //접속 성공
            System.out.println(socket);
            
            ListeningThread t1 = new ListeningThread(socket); // 서버에서 보낸 메세지 읽는 Thread
            WritingThread t2 = new WritingThread(socket);

            t1.start(); //ListeningThread 실행
            t2.start();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}