package utils;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import javax.swing.*;



public class InputBox extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static final int width = 200, height = 75;
	
	private Container cp;
	private JTextField ipField;
	private JLabel ipLabel;
	private ClientComm comm;
	
	public InputBox(){
	
		super("IP Information");
		
		ipLabel = new JLabel("Enter IP Address");
		ipLabel.setForeground(Color.white);
		ipField = new JTextField(10);
		ipField.addActionListener(this);

		
		cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(ipLabel);
		cp.add(ipField);
		cp.setBackground(Color.black);
		cp.setPreferredSize(new Dimension(width, height));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
		
		
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		String ip = ipField.getText();
		comm = new ClientComm(ip);
		if (comm.isConnected()){
			new ClientGrid(comm);
			dispose();
			
		} else {
			ipLabel.setText("Connection Failed. Try Again");
			ipLabel.setForeground(Color.red);
			repaint();
		}
	}
	
}