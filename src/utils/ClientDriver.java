package utils;
import javax.swing.SwingUtilities;



public class ClientDriver {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				new InputBox();
			}
		});	
	}

}
