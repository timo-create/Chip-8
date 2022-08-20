package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Board;
import Modell.Move;
import Modell.Piece;

public class View extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<BufferedImage> piecesImages;
	private Map<Integer, JLabel> tiles;

	public View() {
		piecesImages = getPiecesImages(loadImages("res/pieces.png"));
		tiles = new HashMap<>(64, 1.0F);
	}

	private BufferedImage loadImages(String path) {

		BufferedImage bufimg = null;
		try {
			bufimg = ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			System.err.println("An error occurred while attempting to load '" + path + "'");

			return null;
		}

		return bufimg;
	}

	private List<BufferedImage> getPiecesImages(BufferedImage spritesheet) {

		List<BufferedImage> images = new ArrayList<BufferedImage>();

		for (int row = 1; row <= 2; row++) {
			for (int col = 1; col <= 6; col++) {

				images.add(spritesheet.getSubimage((col * 100) - 100, (row * 100) - 100, 100, 100));

			}
		}

		return images;

	}

	private void drawBoard(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				if ((x + y) % 2 != 0) {
					g2d.setColor(new Color(224, 182, 252));
				} else {
					g2d.setColor(new Color(136, 23, 212));
				}

				g2d.fillRect(x * 100, y * 100, 100, 100);
			}
		}
	}

	public void drawStartingPosition() {

		for (int i = 0; i < 64; i++) {

			Piece p = Board.squares.get(i);
			int[] coordinate = calculateCoordinateFromPosition(i);
			PieceLabel label;

			if (p != null) {
				label = new PieceLabel(getCorrectImage(p));

			} else {
				label = new PieceLabel(null);
			}

			label.setBounds(coordinate[0] * 100, coordinate[1] * 100, 100, 100);
			this.add(label);
			tiles.put(i, label);

		}

	}

	private BufferedImage getCorrectImage(Piece p) {

		int team = p.pieceTeam;
		int type = p.pieceType;

		if (team == 8) {

			switch (type) {

			case 1:
				return piecesImages.get(0);
			case 2:
				return piecesImages.get(5);
			case 3:
				return piecesImages.get(3);
			case 4:
				return piecesImages.get(2);
			case 5:
				return piecesImages.get(4);
			case 6:
				return piecesImages.get(1);

			}

		} else {

			switch (type) {

			case 1:
				return piecesImages.get(6);
			case 2:
				return piecesImages.get(11);
			case 3:
				return piecesImages.get(9);
			case 4:
				return piecesImages.get(8);
			case 5:
				return piecesImages.get(10);
			case 6:
				return piecesImages.get(7);

			}
		}

		return null;

	}

	public void highLightPieceMoves(List<Move> targetPositions) {

		for (Move target : targetPositions) {

			int[] coordinateFromTarget = calculateCoordinateFromPosition(target.getDestinationPosition());
			JLabel label = tiles.get(target.getDestinationPosition());
		
			

		}

	}

	private int[] calculateCoordinateFromPosition(int position) {

		int x;
		int y;

		x = 7 - (position % 8);
		y = 7 - ((position - (position % 8)) / 8);

		return new int[] { x, y };

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawStartingPosition();

	}

}

class PieceLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage pieceImage;

	public PieceLabel(BufferedImage pieceImage) {

		this.pieceImage = pieceImage;

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (pieceImage != null) {

			g.drawImage(pieceImage, 0, 0, 100, 100, this);
		}

	}

}

class TileLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
		g.fillRect(0, 0, 100, 100);

	}

}
