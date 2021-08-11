  
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
	
	public void run() { //Listening �κ� ����->Ŭ���̾�Ʈ
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
            Socket socket = null; //���ϻ���
            
            //���� ������ ����
            socket = new Socket("192.168.219.108", 1234); //�����û����
            System.out.println("connect server!"); //���� ����
            
            //ListeningThread t1 = new ListeningThread(socket);�� ����
            Client client = new Client(socket); //Listening �κ��� ��ü�� ���� 
            //t1.start(); �� ����
            client.start();//Listening �κ��� ����
            
            Scanner scanner = new Scanner(System.in); //ä�ÿ� scanner   
            try {

                   PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
                   while(true) {
                	   String scanString = scanner.nextLine();
                       writer.println(scanString); //�Է��� �޼��� �߼�
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