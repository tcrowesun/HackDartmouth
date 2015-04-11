import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.*;

/** @see http://stackoverflow.com/a/3002830/230513 */
public class LecturerGUI {
	private static final int width = 800, height = 800;		// canvas size
	
	private TrackerServer trackerServer;

	private void display() throws IOException {
		
		JTextField field1 = new JTextField("e.g. 10.31.213.163");
		JTextField field2 = new JTextField("e.g. 4242");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("Enter IP Address:"));
		panel.add(field1);
		panel.add(new JLabel("Enter Port Number:"));
		panel.add(field2);
		int result = JOptionPane.showConfirmDialog(null, panel, "Start",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			System.out.println( field1.getText()
					+ " " + field2.getText());
			trackerServer = new TrackerServer(new ServerSocket(Integer.parseInt(field2.getText())));
			trackerServer.getConnections();
		} else {
			System.out.println("Cancelled");
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					(new LecturerGUI()).display();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}