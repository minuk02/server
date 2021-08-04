  
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
	
	public void run() {
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

            Client client = new Client(socket);
            client.start();
            
            Scanner scanner = new Scanner(System.in); //채팅용 scanner
            while(true) {
            	try {

                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                    while(true) {
                        writer.println(scanner.nextLine()); //입력한 메세지 발송
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}