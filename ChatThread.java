package Multichatt;
//Ŭ��->����
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
//����->Ŭ��
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatThread extends Thread{
	Socket socket = null;
    Scanner scanner = new Scanner(System.in); //ä�ÿ� scanner
    
    public ChatThread(Socket socket) { //������
        //�޾ƿ� socket Parameter �� �ش� Ŭ���� socket �� �ֱ�
        this.socket = socket;
    }
    public void run() {
        try {
            //OutputStream - Ŭ���̾�Ʈ���� Server �� �޼��� �߼�
            //PrintWriter ��¸� ������ ��ü
            //PrintWriter �� �� OutputStream �� ��� ���
        	//PrintWriter(OutPutStream, boolean auto flush)���¸� �����(?)
        	//socket.getOutputStream()�� Ŭ�󿡼� ���� �����͸� �޾ƿ�(?)
        	//StandardCharsets.UTF_8 ���ڵ� ����� ����????
	            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
	        	//BufferedReader ���۸� �̿��� �Է�
	        	//BufferedReader�� InputStreamReader�� ��ü�� �Է°����� ���
	        	//InputStreamReader�� InPutStream���� �Է¹��� ����Ʈ ���� ���ڿ��� ���� ���� �����ͷ� ó���ϵ��� ��ȯ
	        	//getInputStream�� InPutStream�� �������ΰ�(?) -> getInputStream�� �����͸� �����Ҷ� �Է½�Ʈ���� �����ϴ� �޼ҵ�
	        	//getInputStream�������� ���� �����͸� �޾ƿ�
	            InputStream input = socket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
	            while(true) 
	            {
	                writer.println(scanner.nextLine()); //�Է��� �޼��� �߼�
	                System.out.println(reader.readLine());
	            }
        	}catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    
}

