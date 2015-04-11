import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import javax.swing.*;
import utils.ClientComm;

public class ClientGrid extends JFrame implements ActionListener, MouseListener, MouseMotionListener{	
	private static final long serialVersionUID = 1L;
	
	private static final int width = 400, height = 400;
	private static final int rad = 10;
	
	// GUI components
	private JComponent canvas;
	private Point point = null;
	private JPanel topPanel;
	private JTextField ipField;
	private JLabel ipLabel;
	
	private ClientComm comm;
	
	public ClientGrid() {
		super("Grid");
		
		setupIpLabel();
		
		// Helpers to create the canvas and GUI
		setupCanvas();
		
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		cp.add(topPanel, BorderLayout.NORTH);
		
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
			ipField = new JTextField(10);
			ipField.addActionListener(this);
			topPanel.add(ipLabel);
			topPanel.add(ipField);
			
	}
	
	private void setupCanvas() {
		canvas = new JComponent() {
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int width_mid = width / 2;
				int height_mid = height / 2;
				g.drawLine(0, height_mid, width, height_mid);
				g.drawLine(width_mid, 0, width_mid, height);

				if (point != null) {
					// Lines to axes
					int circX = (int) point.getX() - rad;
					int circY = (int) point.getY() - rad;

					// Color vertical line
					if (circY + rad / 2 < height / 2) {
						g.setColor(Color.GREEN);
					} else if (circY + rad / 2 > height / 2) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.BLACK);
					}
					g.drawLine(circX + rad / 2, 0, circX + rad / 2, height);

					// Color horizontal line
					if (circX + rad / 2 < width / 2) {
						g.setColor(Color.RED);
					} else if (circX + rad / 2 > width / 2) {
						g.setColor(Color.GREEN);
					} else {
						g.setColor(Color.BLACK);
					}
					g.drawLine(0, circY + rad / 2, width, circY + rad / 2);

					// Black dot at mouse point
					g.setColor(Color.BLACK);
					g.fillOval(circX, circY, rad, rad);
				}
			}
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
		point = e.getPoint();
		
		if(comm != null && comm.isConnected()){
			comm.send(Integer.toString((int)point.getX()) + " " + Integer.toString((int)point.getY()));
		}
		
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
			ipLabel.setForeground(Color.green);
			repaint();
		} else {
			ipLabel.setText("Connection Failed. Try Again");
			ipLabel.setForeground(Color.red);
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
		canvas.repaint();
	}
}
