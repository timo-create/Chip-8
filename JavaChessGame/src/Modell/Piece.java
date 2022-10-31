package Modell;
import java.util.ArrayList;
import java.util.List;


public abstract class Piece {
	
	public final int pieceType; 
	public final int pieceTeam;
	public List<Move> targetMoves = new ArrayList<Move>();
	
	
	public Piece(final int pieceType, final int pieceTeam){
		this.pieceType = pieceType;
		this.pieceTeam = pieceTeam;
		
		
	}
	
	

	
	public abstract List<Move> calculateMoves(int piecePosition);



	
}
