package View;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import Controller.GameController;



public class ViewFrame extends JFrame {
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	GameController gc;
	View panel;

	public ViewFrame() {
		panel = new View();
		gc = new GameController(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		Container c = this.getContentPane();
		c.setPreferredSize(new Dimension(800, 800));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		

	}

}
