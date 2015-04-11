package utils;
import java.io.*;
import java.net.Socket;


public class ClientComm extends Thread {

	private PrintWriter out;
	private BufferedReader in;

	public ClientComm(String serverIP){
		try {
			Socket sock = new Socket(serverIP, 4242);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("Connected");
		} catch (IOException e) {
			System.err.println("Failed to connect to server socket");
		}
	}
	
	public void send(String msg) {
		out.println(msg);
	}
	
	

}