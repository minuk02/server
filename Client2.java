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
        	//BufferedReader ���۸� �̿��� �Է�
        	//BufferedReader�� InputStreamReader�� ��ü�� �Է°����� ���
        	//InputStreamReader�� InPutStream���� �Է¹��� ����Ʈ ���� ���ڿ��� ���� ���� �����ͷ� ó���ϵ��� ��ȯ
        	//getInputStream�� InPutStream�� �������ΰ�(?) -> getInputStream�� �����͸� �����Ҷ� �Է½�Ʈ���� �����ϴ� �޼ҵ�
        	//getInputStream�������� ���� �����͸� �޾ƿ�
        	InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            //���ѷ����� �Է��� ���� ������ �����
            while(true) {
            	//�Է��� �����͸� �� �ٷ� �о� String���� ��ȯ
            	//���� ���� -> String (?)
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
		socket = new Socket("192.168.219.108", 1234); //�����û����
		
		//���� ������ ����
        System.out.println("connect server!"); //���� ����
        System.out.println(socket);
        
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        while(true) {
            writer.println(scanner.nextLine()); //�Է��� �޼��� �߼�
        }
        
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}
