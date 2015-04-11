package utils;
import javax.swing.SwingUtilities;
import utils.*;



public class ClientDriver {

//	public static void main(String[] args) {
//		
//		
//		ClientComm comm = new ClientComm("10.31.213.163");
//		
//		comm.send("50 100");
//		
//	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				new InputBox();
			}
		});	
	}

}
