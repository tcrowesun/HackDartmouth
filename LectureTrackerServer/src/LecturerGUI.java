import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import javax.swing.*;

/** @see http://stackoverflow.com/a/3002830/230513 */
public class LecturerGUI {
	private static final int width = 800, height = 800;		// canvas size

	private TrackerServer trackerServer;
	private ServerSocket socket;
	private String IPAddress;
	private String portNumber;

	private void display() throws IOException, InterruptedException {
		InetAddress IP = InetAddress.getLocalHost();
		IPAddress = IP.getHostAddress();
		JTextField ipLabel = new JTextField("Your IP Address is: " + IPAddress);
		JTextField field2 = new JTextField("e.g. 4242");
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel(ipLabel.getText()));
		panel.add(new JLabel("Enter Port Number:"));
		panel.add(field2);
		int result = JOptionPane.showConfirmDialog(null, panel, "Start",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			portNumber = field2.getText();
			System.out.println(ipLabel.getText()
					+ " " + portNumber);
			this.socket = new ServerSocket(Integer.parseInt(field2.getText()));
			trackerServer = new TrackerServer(socket);
			(new Thread(trackerServer)).start();
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						(new LecturerGUI()).display2(socket, IPAddress, portNumber);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			System.out.println("Cancelled");
		}
	}

	private void display2(ServerSocket socket, String IPAddress, String portNumber) throws IOException {
		//Custom button text
		Object[] options = {"Finish"};
		int choice = JOptionPane.showOptionDialog(null,
				"Collecting Data...\nIP Address: " + IPAddress + "\nPort Number: " + portNumber + "\n\nClick below to end data collection",
				"Control Panel",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE,
				null,
				options,
				options[0]);
		if(choice == 0 || choice == JOptionPane.CLOSED_OPTION){
			socket.close();
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
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
