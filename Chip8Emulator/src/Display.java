import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Draw panel = new Draw();
	KeyBoard kb = new KeyBoard();

	public Display() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.addKeyListener(kb);
		Container c = this.getContentPane();
		c.setPreferredSize(new Dimension(512, 256));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
	}

}
