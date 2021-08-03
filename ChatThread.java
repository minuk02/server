package Multichatt;
//클라->서버
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
//서버->클라
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatThread extends Thread{
	Socket socket = null;
    Scanner scanner = new Scanner(System.in); //채팅용 scanner
    
    public ChatThread(Socket socket) { //생성자
        //받아온 socket Parameter 를 해당 클래스 socket 에 넣기
        this.socket = socket;
    }
    public void run() {
        try {
            //OutputStream - 클라이언트에서 Server 로 메세지 발송
            //PrintWriter 출력만 가능한 객체
            //PrintWriter 에 위 OutputStream 을 담아 사용
        	//PrintWriter(OutPutStream, boolean auto flush)형태를 사용함(?)
        	//socket.getOutputStream()로 클라에서 보낸 데이터를 받아옴(?)
        	//StandardCharsets.UTF_8 인코딩 방식을 지정????
	            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	        	//BufferedReader 버퍼를 이용한 입력
	        	//BufferedReader는 InputStreamReader의 객체를 입력값으로 사용
	        	//InputStreamReader는 InPutStream에서 입력받은 바이트 단위 문자열을 문자 단위 데이터로 처리하도록 변환
	        	//getInputStream은 InPutStream랑 같은것인가(?) -> getInputStream은 데이터를 수신할때 입력스트림을 리턴하는 메소드
	        	//getInputStream서버에서 보낸 데이터를 받아옴
	            InputStream input = socket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
	            while(true) 
	            {
	                writer.println(scanner.nextLine()); //입력한 메세지 발송
	                System.out.println(reader.readLine());
	            }
        	}catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    
}

