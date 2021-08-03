package Multichatt;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = null; //���ϻ���
            
            //���� ������ ����
            socket = new Socket("192.168.219.108", 4321); //�����û����
            System.out.println("connect server!"); //���� ����
            System.out.println(socket);
            
            ListeningThread t1 = new ListeningThread(socket); // �������� ���� �޼��� �д� Thread
            WritingThread t2 = new WritingThread(socket);

            t1.start(); //ListeningThread ����
            t2.start();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}