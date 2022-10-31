package Modell;

import java.util.ArrayList;
import java.util.List;

import Controller.Board;
import Controller.GameController;

public class Pawn extends Piece {

	private int pawnOffsetsWhite[] = { 9, 7, 8, 16 };
	private int pawnOffsetsBlack[] = { -7, -9, -8, -16 };
	private int pawnOffsets[];
	boolean isFirstMove;
	boolean enPassantPotential;
	

	public Pawn(int pieceType, int pieceTeam) {
		super(pieceType, pieceTeam);
		isFirstMove = true;
		pawnOffsets = pieceTeam == 8 ? pawnOffsetsWhite : pawnOffsetsBlack;
	}

	@Override
	public List<Move> calculateMoves(int piecePosition) {
		List<Move> legalPawnMoves = new ArrayList<Move>();

		if (pieceTeam == PieceTypes.White && piecePosition > 15 && isFirstMove) isFirstMove = false;

		else if(pieceTeam == PieceTypes.White && piecePosition <= 15 ) isFirstMove = true;
		
		

		if (pieceTeam == PieceTypes.Black && piecePosition < 48 && isFirstMove) isFirstMove = false;
		
		else if (pieceTeam == PieceTypes.Black && piecePosition >= 48) isFirstMove = true;
		
		// check if enpassant is possible
		
	 
		

		for (int offset : pawnOffsets) {

			int candidatePosition = piecePosition + offset;

			if (piecePosition + offset <= 64 && piecePosition + offset >= 0) {

				if (isFirstColumnExclusion(piecePosition, offset) || isEighthColumnExclusion(piecePosition, offset)) {
					continue;
				}
				if (Board.isOccupiedBy(piecePosition, candidatePosition) == "enemy" && offset != pawnOffsets[2]
						&& offset != pawnOffsets[3]) {
					legalPawnMoves.add(new Move(piecePosition, candidatePosition, 2));
				} else if (Board.isOccupiedBy(piecePosition, candidatePosition) == "null" && offset == pawnOffsets[2]) {
					legalPawnMoves.add(new Move(piecePosition, candidatePosition, 1));
					if(checkEnPassantRow(piecePosition)) {
					    	
						Move latestMove = GameController.getLatestMove();
						if(latestMove != null) System.out.println(latestMove.destinationPosition);
						
						if(latestMove != null &&  latestMove.destinationPosition == piecePosition+1) {
							System.out.println("wow worked");
						}
					}
					if(Board.isOccupiedBy(piecePosition,  piecePosition + pawnOffsets[3]) == "null" && isFirstMove){
						
						legalPawnMoves.add(new Move(piecePosition, piecePosition + pawnOffsets[3], 1));
					}
					
					
					
				}
			}

		}

		return legalPawnMoves;
	}
	
	
	public boolean checkEnPassantRow(int piecePosition) {
		

		return Board.FORTH_ROW[piecePosition];
	}

	public boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.FIRST_COLUMN[currentPosition] && (candidateOffset == pawnOffsets[1]);

	}

	public boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.EIGHTH_COLUMN[currentPosition] && (candidateOffset == pawnOffsets[0]);

	}

}
