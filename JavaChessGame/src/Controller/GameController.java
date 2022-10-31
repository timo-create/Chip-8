package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modell.Move;
import Modell.Piece;
import View.View;

public class GameController implements MouseListener {

	Board board;
	View view;
	List<Move> highLightPositions = new ArrayList<Move>();
	public static List<Move> gameMoves= new ArrayList<Move>();
	int moveCounter;
	int colorToPlay;
	boolean highLightMode;

	public GameController(View view) {

		colorToPlay = 8;
		highLightMode = false;
		board = new Board();
		this.view = view;
		view.addMouseListener(this);
		board.runStartPosition();

	}
	
	
	public static Move getLatestMove() {
		if(gameMoves.size() > 0) return gameMoves.get(gameMoves.size()-1);
		
		return null; 
	}

	private List<Move> pseudoLegalMoves() {

		List<Move> allMoves = new ArrayList<>();
		for (Map.Entry<Integer, Piece> entry : Board.squares.entrySet()) {

			allMoves.addAll(entry.getValue().calculateMoves(entry.getKey()));
			

		}

		return allMoves;

	}

	private List<Move> legalMoves(int pieceTeam) {

		List<Move> allLegalMoves = new ArrayList<>();
		Map<Integer, Piece> oldMap = new HashMap<>(Board.squares);

		for (Move move : pseudoLegalMoves()) {

			board.makeMove(move);
			List<Move> newPseudoLegalMoves = pseudoLegalMoves();

			if (newPseudoLegalMoves.stream()
					.anyMatch(response -> response.getDestinationPosition() == board.getMyKing(pieceTeam))) {

			} else {
				allLegalMoves.add(move);
			}

			Board.squares = new HashMap<Integer, Piece>(oldMap);

		}

		return allLegalMoves;

	}

	private List<Move> getPieceMoves(int position, int pieceTeam) {

		List<Move> allPieceMoves = legalMoves(pieceTeam);
		List<Move> myPieceMoves = new ArrayList<>();

		for (Move m : allPieceMoves) {

			if (m.getStartPosition() == position) {
				myPieceMoves.add(m);
			}
		}

		return myPieceMoves;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int rank = 7 - Math.round(e.getX() / 100);
		int file = 7 - Math.round(e.getY() / 100);
		int position = file * 8 + rank;

		if (Board.squares.get(position) != null && Board.squares.get(position).pieceTeam == colorToPlay) {

			highLightPositions = getPieceMoves(position, colorToPlay);
			view.highLightPositions = highLightPositions;
			view.repaint();
			highLightMode = true;

		} else if (Board.squares.get(position) != null && Board.squares.get(position).pieceTeam != colorToPlay
				|| Board.squares.get(position) == null) {

			for (int i = 0; i < highLightPositions.size(); i++) {

				if (position == highLightPositions.get(i).getDestinationPosition()) {

					board.makeMove(highLightPositions.get(i));
					gameMoves.add(highLightPositions.get(i));
					view.repaint();

					highLightPositions.clear();
					colorToPlay = colorToPlay == 8 ? 16 : 8;
					break;

				}

			}
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

}
