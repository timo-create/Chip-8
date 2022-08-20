package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Modell.Piece;
import View.View;

public class GameController implements MouseListener {

	Board board;
	View view;

	public GameController(View view) {
		board = new Board();
		this.view = view ;
		board.runStartPosition();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int rank = 7 - Math.round(e.getX() / 100);
		int file = 7 - Math.round(e.getY() / 100);
		int position = file * 8 + rank;
		Piece targetPiece = Board.squares.get(position);
		view.highLightPieceMoves(targetPiece.calculateMoves(targetPiece.pieceTeam, position));

		System.out.println(position);

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

}
