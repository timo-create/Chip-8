package Controller;

import java.util.HashMap;
import java.util.Map;
import Modell.King;
import Modell.Knight;
import Modell.Move;
import Modell.Pawn;
import Modell.Piece;
import Modell.PieceTypes;
import Modell.SlidingPiece;

public class Board {

	public static final boolean[] FIRST_COLUMN = initColumn(0);
	public static final boolean[] SECOND_COLUMN = initColumn(1);
	public static final boolean[] SEVENTH_COLUMN = initColumn(6);
	public static final boolean[] EIGHTH_COLUMN = initColumn(7);

	public static final boolean[] FORTH_ROW = initRow(3);
	public static final boolean[] FIFTH_ROW = initRow(4);
	public static final boolean[] SEVENTH_ROW = initRow(6);
	public static final boolean[] SECONDE_ROW = initRow(1);

	public static Map<Integer, Piece> squares;

	public Board() {

		squares = new HashMap<>(32, 1.0F);

	}

	public void runStartPosition() {

		setPiece(0, new SlidingPiece(PieceTypes.Rook, PieceTypes.White));
		setPiece(1, new Knight(PieceTypes.Knight, PieceTypes.White));
		setPiece(2, new SlidingPiece(PieceTypes.Bishop, PieceTypes.White));
		setPiece(3, new King(PieceTypes.King, PieceTypes.White));
		setPiece(4, new SlidingPiece(PieceTypes.Queen, PieceTypes.White));
		setPiece(5, new SlidingPiece(PieceTypes.Bishop, PieceTypes.White));
		setPiece(6, new Knight(PieceTypes.Knight, PieceTypes.White));
		setPiece(7, new SlidingPiece(PieceTypes.Rook, PieceTypes.White));
		setPiece(8, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(9, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(10, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(11, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(12, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(13, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(14, new Pawn(PieceTypes.Pawn, PieceTypes.White));
		setPiece(15, new Pawn(PieceTypes.Pawn, PieceTypes.White));

		setPiece(48, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(49, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(50, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(51, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(52, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(53, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(54, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(55, new Pawn(PieceTypes.Pawn, PieceTypes.Black));
		setPiece(56, new SlidingPiece(PieceTypes.Rook, PieceTypes.Black));
		setPiece(57, new Knight(PieceTypes.Knight, PieceTypes.Black));
		setPiece(58, new SlidingPiece(PieceTypes.Bishop, PieceTypes.Black));
		setPiece(59, new King(PieceTypes.King, PieceTypes.Black));
		setPiece(60, new SlidingPiece(PieceTypes.Queen, PieceTypes.Black));
		setPiece(61, new SlidingPiece(PieceTypes.Bishop, PieceTypes.Black));
		setPiece(62, new Knight(PieceTypes.Knight, PieceTypes.Black));
		setPiece(63, new SlidingPiece(PieceTypes.Rook, PieceTypes.Black));

	}

	public Piece getPiece(int piecePosition) {
		return squares.get(piecePosition);
	}

	public void setPiece(int piecePosition, Piece piece) {
		squares.put(piecePosition, piece);
	}

	public void makeMove(Move move) {

		Piece p = squares.get(move.getStartPosition());
		squares.remove(move.getStartPosition());
		setPiece(move.getDestinationPosition(), p);
	}

	public int getMyKing(int pieceTeam) {

		for (Map.Entry<Integer, Piece> entry : squares.entrySet()) {

			if (entry.getValue().pieceType == PieceTypes.King && entry.getValue().pieceTeam == pieceTeam) {

				return entry.getKey();
			}

		}

		return 0;

	}

	private static boolean[] initColumn(int columnNumber) {

		boolean[] column = new boolean[64];

		do {

			column[columnNumber] = true;
			columnNumber += 8;

		} while (columnNumber < 64);

		return column;

	}

	private static boolean[] initRow(int rowNumber) {

		boolean[] row = new boolean[64];
		int offset = 0;

		do {

			row[rowNumber * 8 + offset] = true;
			offset += 1;

		} while (offset < 8);

		return row;

	}

	public static String isOccupiedBy(int startSquare, int targetSquare) {

		if (squares.get(targetSquare) == null) {
			return "null";
		}

		if (squares.get(targetSquare).pieceTeam == squares.get(startSquare).pieceTeam) {
			return "member";
		}

		return "enemy";

	}

	public static boolean isOccupied(int targetSquare) {

		if (squares.get(targetSquare) == null) {
			return false;
		}

		return true;

	}

}
