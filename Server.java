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

// 소켓통신용 서버 코드
public class Server extends Thread {
	//크기가 가변적으로 변하는 선형리스트, 객체들이 저장용량을 토과한다면 부족한 크기만큼 늘어남
	static ArrayList<Socket> list = new ArrayList<Socket>(); // 유저 확인용, 유저정보 저장(?)
	static Socket socket = null; //서버 소켓 생성
	
	public Server(Socket socket) {
		this.socket = socket; // 유저 socket을 할당
		list.add(socket); // 유저를 list에 추가
	}
    	// Thread 에서 start() 메소드 사용 시 자동으로 해당 메소드 시작 (Thread별로 개별적 수행)
		//Thread에 들어있는 메소드
    	public void run() {
		try {
	        	// 연결 확인용
				//socket.getInetAddress() 소켓이 연결된 사용자의 IP주소 받음
				System.out.println("서버 : " + socket.getInetAddress() + " IP의 클라이언트와 연결되었습니다");
				
				// InputStream - 클라이언트에서 보낸 메세지 읽기
				// InputStream - 입력받은 데이터는 int형으로 저장, 10진수의 UTF-16값으로 저장, 1byte만 읽음 -> 한글을 읽을 수 없음
				// InputStreamReader로 InputStream을 감싸면서 char형으로 변환
				//socket.getInputStream()로 받아들여 input객체에 저장
				InputStream input = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				// OutputStream - 서버에서 클라이언트로 메세지 보내기
				OutputStream out = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(out, true);
				
				// 클라이언트에게 연결되었다는 메세지 보내기
				writer.println("Server connect! Enter your ID!");
				
				String readValue; // Client에서 보낸 값 저장
				String name = null; // 클라이언트 이름 설정용
				Socket theSocket = null; // 본인 소켓 담기
				boolean identify = false;
				
	            		// 클라이언트가 메세지 입력시마다 수행
				while((readValue = reader.readLine()) != null ) 
				{
					if(!identify) { // 연결 후 한번만 노출
						name = readValue; // 이름 할당
						identify = true;
						writer.println(name + " enter room.");
						continue;
					}
					
					if (readValue.equals("quit")) {
	                    System.out.println("it push quit");
	                    for (int i = 0; i < list.size(); i++) {
	                        if (theSocket == list.get(i)) {
	                            out = list.get(i).getOutputStream();
	                            writer = new PrintWriter(out, true);
	                            writer.println("you are fired");
	                            list.remove(theSocket);
	                        }
	                    }
	                    continue;
	                }
					
	                // list 안에 클라이언트 정보가 담겨있음
					for(int i = 0; i<list.size(); i++) 
					{ 
						out = list.get(i).getOutputStream();
						writer = new PrintWriter(out, true);
						writer.println(name + " : " + readValue);
						writer.println(list.get(i)); 
					}
				}
			} 
			catch (Exception e) 
			{
			    e.printStackTrace(); // 예외처리
			} finally {
				try {
					socket.close();
				}catch(IOException e){
					System.out.println("비정상적인 종료");
				}
			}
    	}	
	
	public static void main(String[] args) {
    		try {
                      int socketPort = 1234; // 소켓 포트 설정용
                      ServerSocket serverSocket = new ServerSocket(socketPort); // 서버 소켓 만들기, bind()
                      // 서버 오픈 확인용
                      System.out.println("socket : " + socketPort + "으로 서버가 열렸습니다");
			
                      // 소켓 서버가 종료될 때까지 무한루프, listen()부분
                      while(true) {
                    	// 서버에 클라이언트 접속 요청을 받아들임, 넘어오는 정보(사용자 IP)?
                          Socket socketUser = serverSocket.accept(); 
                          // Thread 안에 클라이언트 정보를 담아줌
                          Thread thd = new Server(socketUser); //서버에 접속한 유저의 정보를 전달, 서버소켓 생성
                          thd.start(); // Thread 시작, start메소드는 Thread클래스가 가짐
                      }                 
            
		} catch (IOException e) {
			e.printStackTrace(); // 예외처리
		}

	}

}
