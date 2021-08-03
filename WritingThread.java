package Multichatt;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WritingThread extends Thread{ // 서버로 메세지 보내는 Thread
    Socket socket = null;
    Scanner scanner = new Scanner(System.in); //채팅용 scanner

    public WritingThread(Socket socket) { //생성자
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
            while(true) {
                writer.println(scanner.nextLine()); //입력한 메세지 발송
                System.out.println("-"+writer+"-");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}