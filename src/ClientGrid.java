import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import javax.swing.*;
import utils.ClientComm;
import java.lang.Thread;

public class ClientGrid extends JFrame implements MouseListener, MouseMotionListener{	
	private static final long serialVersionUID = 1L;
	
	private static final int width = 400, height = 400;
	private static final int rad = 10;
	
	// GUI components
	private Container cp;
	private JComponent canvas;
	private Point point = null;

	
	private ClientComm comm;
	
	public ClientGrid(ClientComm comm) {
		super("Grid");
		
		this.comm = comm;
		
		
		// Helpers to create the canvas and GUI
		setupCanvas();
		
		
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(canvas, BorderLayout.CENTER);
		
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		point = e.getPoint();
		canvas.repaint();
	}
}
