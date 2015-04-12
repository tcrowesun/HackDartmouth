package utils;
import java.io.*;
import java.net.Socket;



public class ClientComm extends Thread {

	private PrintWriter out;
	private BufferedReader in;
	private Boolean connected = false;
	private final static int PORT_NUMBER = 4242;
	private Socket sock;

	public ClientComm(String serverIP){		
		sock = null;
		try {	
			sock = new Socket(serverIP, PORT_NUMBER);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			connected = true;
		} catch (IOException e) {
			if (sock != null) {
				try {
					sock.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
	
	public boolean send(String msg) {
		out.println(msg);
		
		
		try {
			String response=in.readLine();
			return(response.equals("OK"));

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			if (sock != null) {
				try {
					sock.close();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			return false;
		}

		
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	

}
