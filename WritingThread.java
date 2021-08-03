package Multichatt;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WritingThread extends Thread{ // ������ �޼��� ������ Thread
    Socket socket = null;
    Scanner scanner = new Scanner(System.in); //ä�ÿ� scanner

    public WritingThread(Socket socket) { //������
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
            while(true) {
                writer.println(scanner.nextLine()); //�Է��� �޼��� �߼�
                System.out.println("-"+writer+"-");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}