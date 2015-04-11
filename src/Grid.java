import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;

import javax.swing.*;

public class Grid extends JFrame {	
	private static final long serialVersionUID = 1L;
	
	private static final int width = 400, height = 400;
	
	// GUI components
	private JComponent canvas;
	private Point point = null;
	
	public Grid() {
		super("Grid");
		
		// Helpers to create the canvas and GUI
		setupCanvas();
		
		Container cp = getContentPane();
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
		};
		canvas.setPreferredSize(new Dimension(width, height));
		
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				point = event.getPoint();
				
				// TODO: Draw dot at mouse coordinates
				
				canvas.repaint();
			}
		});		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Grid();
			}
		});	
	}
}
