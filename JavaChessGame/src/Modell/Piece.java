package Modell;
import java.util.List;


public abstract class Piece {
	
	public final int pieceType; 
	public final int pieceTeam;
	
	
	public Piece(final int pieceType, final int pieceTeam){
		this.pieceType = pieceType;
		this.pieceTeam = pieceTeam;
		
	}
	
	

	
	public abstract List<Move> calculateMoves(int pieceTeam, int piecePosition);



	
}