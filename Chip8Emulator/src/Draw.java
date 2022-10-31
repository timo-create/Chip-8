import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Draw extends JPanel {

	public int[] video = new int[64 * 32];
	public int blocksize = 8;

	public Draw() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		for (int i = 0; i < 64*32; i++) {
			
			

			int x = (i % 64) *blocksize;
			int y = (i / 64) *blocksize;
			
		
			//System.out.println("x: "+x+" y: "+y+" i: "+video[i]);
			

			if (video[i] == 1) {

				
				g.setColor(Color.white);
				g.fillRect(x, y, blocksize,blocksize);
				

			}
			else {
				
				g.setColor(Color.black);
				
				g.fillRect(x, y, blocksize, blocksize);
				
			}
		

		}
	}

}
