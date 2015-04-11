package utils;
import java.io.*;
import java.net.Socket;



public class ClientComm extends Thread {

	private PrintWriter out;
	private BufferedReader in;
	private Boolean connected = false;
	private final static int PORT_NUMBER = 4242;

	public ClientComm(String serverIP){
		try {
			Socket sock = new Socket(serverIP, PORT_NUMBER);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			connected = true;
		} catch (IOException e) {
			System.err.println("Failed to connect to server socket");
		}
	}
	
	public boolean send(String msg) {
		out.println(msg);
		
		
		try {
			String response=in.readLine();
			System.out.println(response);
			return(response!="OK");
				
				
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
			
	
		
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	

}
