package Modell;

import java.util.ArrayList;
import java.util.List;
import Controller.Board;

public class Knight extends Piece {

	int knightOffsets[] = { 6, 10, 15, 17, -6, -10, -15, -17 };

	public Knight(final int pieceType, final int pieceTeam) {
		super(pieceType, pieceTeam);

	}

	@Override
	public List<Move> calculateMoves(int piecePosition) {

		List<Move> legalKnightMoves = new ArrayList<Move>();

		for (int offset : knightOffsets) {

			int candidatePosition = piecePosition + offset;

			if (piecePosition + offset <= 64 && piecePosition + offset >= 0) {

				if (isFirstColumnExclusion(piecePosition, offset) || isSeventhColumnExclusion(piecePosition, offset)
						|| isSecondColumnExclusion(piecePosition, offset)
						|| isEighthColumnExclusion(piecePosition, offset)) {

					continue;
				}
				if (Board.isOccupiedBy(piecePosition, candidatePosition) == "enemy") {

					legalKnightMoves.add(new Move(piecePosition, candidatePosition, 2));
					continue;

				} else if (Board.isOccupiedBy(piecePosition, candidatePosition) == "member") {

					continue;
				} else if (Board.isOccupiedBy(piecePosition, candidatePosition) == "null") {
					legalKnightMoves.add(new Move(piecePosition, candidatePosition, 1));
				}
			}
		}

		return legalKnightMoves;
	}

	public static boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.FIRST_COLUMN[currentPosition]
				&& (candidateOffset == -17 || candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);

	}

	public static boolean isSecondColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.SECOND_COLUMN[currentPosition] && (candidateOffset == 6 || candidateOffset == -10);

	}

	public static boolean isSeventhColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 10);

	}

	public static boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.EIGHTH_COLUMN[currentPosition]
				&& (candidateOffset == -15 || candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);

	}

}
