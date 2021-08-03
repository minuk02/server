package Multichatt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// ������ſ� ���� �ڵ�
public class Server extends Thread {
	//ũ�Ⱑ ���������� ���ϴ� ��������Ʈ, ��ü���� ����뷮�� ����Ѵٸ� ������ ũ�⸸ŭ �þ
	static ArrayList<Socket> list = new ArrayList<Socket>(); // ���� Ȯ�ο�, �������� ����(?)
	static Socket socket = null; //���� ���� ����
	
	public Server(Socket socket) {
		this.socket = socket; // ���� socket�� �Ҵ�
		list.add(socket); // ������ list�� �߰�
	}
    	// Thread ���� start() �޼ҵ� ��� �� �ڵ����� �ش� �޼ҵ� ���� (Thread���� ������ ����)
		//Thread�� ����ִ� �޼ҵ�
    	public void run() {
		try {
	        	// ���� Ȯ�ο�
				//socket.getInetAddress() ������ ����� ������� IP�ּ� ����
				System.out.println("���� : " + socket.getInetAddress() + " IP�� Ŭ���̾�Ʈ�� ����Ǿ����ϴ�");
				
				// InputStream - Ŭ���̾�Ʈ���� ���� �޼��� �б�
				// InputStream - �Է¹��� �����ʹ� int������ ����, 10������ UTF-16������ ����, 1byte�� ���� -> �ѱ��� ���� �� ����
				// InputStreamReader�� InputStream�� ���θ鼭 char������ ��ȯ
				//socket.getInputStream()�� �޾Ƶ鿩 input��ü�� ����
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				// OutputStream - �������� Ŭ���̾�Ʈ�� �޼��� ������
				OutputStream out = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(out, true);
				
				// Ŭ���̾�Ʈ���� ����Ǿ��ٴ� �޼��� ������
				writer.println("Server connect! Enter your ID!");
				
				String readValue; // Client���� ���� �� ����
				String name = null; // Ŭ���̾�Ʈ �̸� ������
				boolean identify = false;
				
	            		// Ŭ���̾�Ʈ�� �޼��� �Է½ø��� ����
				while((readValue = reader.readLine()) != null ) 
				{
					if(!identify) { // ���� �� �ѹ��� ����
						name = readValue; // �̸� �Ҵ�
						identify = true;
						writer.println(name + "���� �����ϼ̽��ϴ�.");
						continue;
					}
					
	                // list �ȿ� Ŭ���̾�Ʈ ������ �������
					for(int i = 0; i<list.size(); i++) 
					{ 
						out = list.get(i).getOutputStream();
						writer = new PrintWriter(out, true);
						//new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
	                    			// Ŭ���̾�Ʈ���� �޼��� �߼�
						writer.println(name + " : " + readValue); 
					}
				}
			} 
			catch (Exception e) 
			{
			    e.printStackTrace(); // ����ó��
			}    		
    	}	
	
	public static void main(String[] args) {
    		try {
                      int socketPort = 4321; // ���� ��Ʈ ������
                      ServerSocket serverSocket = new ServerSocket(socketPort); // ���� ���� �����, bind()
                      // ���� ���� Ȯ�ο�
                      System.out.println("socket : " + socketPort + "���� ������ ���Ƚ��ϴ�");
			
                      // ���� ������ ����� ������ ���ѷ���, listen()�κ�
                      while(true) {
                    	// ������ Ŭ���̾�Ʈ ���� ��û�� �޾Ƶ���, �Ѿ���� ����(����� IP)?
                          Socket socketUser = serverSocket.accept(); 
                          // Thread �ȿ� Ŭ���̾�Ʈ ������ �����
                          Thread thd = new Server(socketUser); //������ ������ ������ ������ ����, �������� ����
                          thd.start(); // Thread ����, start�޼ҵ�� ThreadŬ������ ����
                      }                 
            
		} catch (IOException e) {
			e.printStackTrace(); // ����ó��
		}

	}

}
