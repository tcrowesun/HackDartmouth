import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import javax.swing.*;
import utils.ClientComm;

public class ClientGrid extends JFrame implements ActionListener, MouseListener, MouseMotionListener{	
	private static final long serialVersionUID = 1L;
	
	private static final int width = 400, height = 400;
	
	
	// GUI components
	private JComponent canvas;
	private Point point = null;
	
	//adding
	private JPanel topPanel;
	private JTextField ipField;
	private JLabel ipLabel;
	
	private ClientComm comm;
	//adding
	
	public ClientGrid() {
		super("Grid");
		
		//adding
		setupIpLabel();
		//adding
		
		// Helpers to create the canvas and GUI
		setupCanvas();
		
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		
		//adding
		cp.add(topPanel, BorderLayout.NORTH);
		//adding
		
		// Top right of window
		setLocationRelativeTo(null);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - width;
        int y = 0;
        setLocation(x, y);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	private void setupIpLabel(){
			topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout());
			ipLabel = new JLabel("Enter IP Address");
			ipLabel.setForeground(Color.darkGray);
			ipField = new JTextField(30);
			ipField.addActionListener(this);
			topPanel.add(ipLabel);
			topPanel.add(ipField);
			
	}
	
	private void setupCanvas() {
		canvas = new JComponent() {
			private static final long serialVersionUID = 1L;
		};
		canvas.setPreferredSize(new Dimension(width, height));
		
		canvas.addMouseListener(this);		
		canvas.addMouseMotionListener(this);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ClientGrid();
			}
		});	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ip = ipField.getText();
		comm = new ClientComm(ip);
		if (comm.isConnected()){
			topPanel.remove(ipField);
			ipLabel.setText("Success");
			repaint();
		} else {
			ipLabel.setText("<font color='red'>Connection Failed. Try Again<\font>");
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		point = e.getPoint();
		
		System.out.println(point.getX() + " " + point.getY());
		
	}
}
