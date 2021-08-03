package Multichatt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ListeningThread extends Thread{ // 서버에서 보낸 메세지를 읽는 Thread
    Socket socket = null;

    public ListeningThread(Socket socket) { // 생성자
        this.socket = socket; // 받아온 Socket Parameter 를 해당 클래스 Socket 에 넣기
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
                System.out.println("-"+reader+"-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}