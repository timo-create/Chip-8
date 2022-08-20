package Modell;

import java.util.ArrayList;
import java.util.List;

import Controller.Board;

public class Bishop extends Piece {
	
	int bishopOffsets[] = {9,-9,7,-7};

	public Bishop(int pieceType, int pieceTeam) {
		super(pieceType, pieceTeam);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Move> calculateMoves(int pieceTeam, int piecePosition) {
		List<Move> legalBishopMoves = new ArrayList<Move>();
		
		for(int offset : bishopOffsets) {
			
			int candidatePosition = piecePosition;
			
			while(candidatePosition+offset <= 64 && candidatePosition+offset >=0 && Board.isOccupied(candidatePosition+offset) == false){
				
				if(isFirstColumnExclusion(candidatePosition, offset) || isEighthColumnExclusion(candidatePosition, offset)) {
					break;
				}
				
				legalBishopMoves.add(new Move(pieceType, pieceTeam, candidatePosition, candidatePosition+offset));
				
				candidatePosition+=offset;
			}
		}
		
		return legalBishopMoves;
	}
	
	
	public static boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.FIRST_COLUMN[currentPosition]
				&& (candidateOffset == 7 || candidateOffset == -9 );

	}
	
	public static boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.EIGHTH_COLUMN[currentPosition]
				&& (candidateOffset == 9 || candidateOffset == -7 );

	}

}
