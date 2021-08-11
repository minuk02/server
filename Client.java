  
package Multichatt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client extends Thread{

	static Socket socket = null;
	
	public Client(Socket socket) {
		this.socket = socket;
	}
	
	public void run() { //Listening 부분 서버->클라이언트
		try {
        	InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

            while(true) {
                System.out.println(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public static void main(String[] args) {
        try {
            Socket socket = null; //소켓생성
            
            //소켓 서버에 접속
            socket = new Socket("192.168.219.108", 1234); //연결요청보냄
            System.out.println("connect server!"); //접속 성공
            
            //ListeningThread t1 = new ListeningThread(socket);과 유사
            Client client = new Client(socket); //Listening 부분을 객체로 생성 
            //t1.start(); 과 유사
            client.start();//Listening 부분을 실행
            
            Scanner scanner = new Scanner(System.in); //채팅용 scanner   
            try {

                   PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                   while(true) {
                	   String scanString = scanner.nextLine();
                       writer.println(scanString); //입력한 메세지 발송
                       if (scanString.equals("quit")) {
                           System.out.println("you push quit!!!");
                           break;
                       }
                   }

                } catch (Exception e) {
                    e.printStackTrace();
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}