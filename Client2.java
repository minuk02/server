package Multichatt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Client2 extends Thread{

	Socket socket = null;
	
	public Client2(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
        try {
        	//BufferedReader 버퍼를 이용한 입력
        	//BufferedReader는 InputStreamReader의 객체를 입력값으로 사용
        	//InputStreamReader는 InPutStream에서 입력받은 바이트 단위 문자열을 문자 단위 데이터로 처리하도록 변환
        	//getInputStream은 InPutStream랑 같은것인가(?) -> getInputStream은 데이터를 수신할때 입력스트림을 리턴하는 메소드
        	//getInputStream서버에서 보낸 데이터를 받아옴
        	InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            //무한루프는 입력이 있을 때마다 실행됨
            while(true) {
            	//입력한 데이터를 한 줄로 읽어 String으로 변환
            	//문자 단위 -> String (?)
                System.out.println(reader.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
    public static void main(String[] args) {
    	try {
    		Scanner scanner = new Scanner(System.in);
    		
    	Socket socket =null;
		socket = new Socket("192.168.219.108", 1234); //연결요청보냄
		
		//소켓 서버에 접속
        System.out.println("connect server!"); //접속 성공
        System.out.println(socket);
        
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        while(true) {
            writer.println(scanner.nextLine()); //입력한 메세지 발송
        }
        
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}
