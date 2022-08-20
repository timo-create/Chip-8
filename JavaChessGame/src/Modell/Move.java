package Modell;

public class Move {
	
	int pieceType;
	int pieceTeam;
	int startPosition;
	int destinationPosition;
	
    public Move(int pieceType, int pieceTeam, int startPosition, int destinationPosition) {
		
		this.pieceType = pieceType; 
		this.pieceTeam = pieceTeam;
		this.startPosition = startPosition;
		this.destinationPosition = destinationPosition;
		
	}
	
	public int getPieceType() {
		return pieceType;
	}

	public void setPieceType(int pieceType) {
		this.pieceType = pieceType;
	}

	public int getPieceTeam() {
		return pieceTeam;
	}

	public void setPieceTeam(int pieceTeam) {
		this.pieceTeam = pieceTeam;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getDestinationPosition() {
		return destinationPosition;
	}

	public void setDestinationPosition(int destinationPosition) {
		this.destinationPosition = destinationPosition;
	}

	

}
