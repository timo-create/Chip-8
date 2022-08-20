package Modell;

import java.util.ArrayList;
import java.util.List;

import Controller.Board;

public class King extends Piece {

	int KingOffsets[] = { 1, -1, 8, -8, 9, -9, 7, -7 };

	public King(int pieceType, int pieceTeam) {
		super(pieceType, pieceTeam);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Move> calculateMoves(int pieceTeam, int piecePosition) {
		List<Move> legalKingMoves = new ArrayList<Move>();

		for (int offset : KingOffsets) {

			int candidatePosition = piecePosition;
			if (piecePosition + offset <= 64 && piecePosition + offset >= 0) {

				if (isFirstColumnExclusion(candidatePosition, offset)
						|| isEighthColumnExclusion(candidatePosition, offset)) {
					break;
				}

				legalKingMoves.add(new Move(pieceType, pieceTeam, candidatePosition, candidatePosition + offset));

				candidatePosition += offset;
			}
		}

		return legalKingMoves;
	}

	public static boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.FIRST_COLUMN[currentPosition]
				&& (candidateOffset == -1 || candidateOffset == 7 || candidateOffset == -9);

	}

	public static boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.EIGHTH_COLUMN[currentPosition]
				&& (candidateOffset == 1 || candidateOffset == -7 || candidateOffset == 9);

	}

}
