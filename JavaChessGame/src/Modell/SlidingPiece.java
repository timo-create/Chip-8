package Modell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controller.Board;

public class SlidingPiece extends Piece {

	int offsets[] = { 1, -1, 8, -8, 9, -9, 7, -7 };

	public SlidingPiece(int pieceType, int pieceTeam) {
		super(pieceType, pieceTeam);

	}

	@Override
	public List<Move> calculateMoves(int piecePosition) {
		List<Move> legalMoves = new ArrayList<Move>();

		int pieceOffsets[] = offsets;

		if (pieceType == PieceTypes.Rook) {

			pieceOffsets = Arrays.copyOfRange(offsets, 0, offsets.length / 2);
		}

		if (pieceType == PieceTypes.Bishop) {

			pieceOffsets = Arrays.copyOfRange(offsets, offsets.length / 2, offsets.length);
		}

		for (int offset : pieceOffsets) {

			int candidatePosition = piecePosition;

			while (candidatePosition + offset < 63 && candidatePosition + offset >= 0) {

				if (isFirstColumnExclusion(candidatePosition, offset)
						|| isEighthColumnExclusion(candidatePosition, offset)) {
					break;
				}

				if (Board.isOccupiedBy(piecePosition, candidatePosition + offset) == "enemy") {

					legalMoves.add(new Move(piecePosition, candidatePosition + offset,2));
					break;

				} else if (Board.isOccupiedBy(piecePosition, candidatePosition + offset) == "null") {
					legalMoves.add(new Move(piecePosition, candidatePosition + offset,1));

					candidatePosition += offset;
				} else if (Board.isOccupiedBy(piecePosition, candidatePosition + offset) == "member") {
					break;
				}

			}
		}
        
		return legalMoves;
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
