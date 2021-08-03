package Multichatt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ListeningThread extends Thread{ // �������� ���� �޼����� �д� Thread
    Socket socket = null;

    public ListeningThread(Socket socket) { // ������
        this.socket = socket; // �޾ƿ� Socket Parameter �� �ش� Ŭ���� Socket �� �ֱ�
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
                System.out.println("-"+reader+"-");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}