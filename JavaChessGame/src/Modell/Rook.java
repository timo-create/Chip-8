package Modell;
import java.util.ArrayList;
import java.util.List;


import Controller.Board;

public class Rook extends Piece {
	
	int RookOffsets[] = {1,-1,8,-8};

	public Rook(int pieceType, int pieceTeam) {
		super(pieceType, pieceTeam);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Move> calculateMoves(int pieceTeam, int piecePosition) {
		
	List<Move> legalRookMoves = new ArrayList<Move>();
		
		for(int offset : RookOffsets) {
			
			int candidatePosition = piecePosition;
			
			while(candidatePosition+offset <= 64 && candidatePosition+offset >=0 && Board.isOccupied(candidatePosition+offset) == false){
				
				if(isFirstColumnExclusion(candidatePosition, offset) || isEighthColumnExclusion(candidatePosition, offset)) {
					break;
				}
				
				legalRookMoves.add(new Move(pieceType, pieceTeam, candidatePosition, candidatePosition+offset));
				
				candidatePosition+=offset;
			}
		}
		
		return legalRookMoves;
		
	}

	public static boolean isFirstColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.FIRST_COLUMN[currentPosition]
				&& (candidateOffset == -1 );

	}
	
	public static boolean isEighthColumnExclusion(int currentPosition, int candidateOffset) {

		return Board.EIGHTH_COLUMN[currentPosition]
				&& (candidateOffset == 1 );

	}
}
