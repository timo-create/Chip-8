package Modell;

public class Move {
	
	
	int startPosition;
	int destinationPosition;
	int kindOfMove;
	
    public Move( int startPosition, int destinationPosition, int kindOfMove) {
		
		
		this.startPosition = startPosition;
		this.destinationPosition = destinationPosition;
		this.kindOfMove = kindOfMove;
		
	}

	public int getKindOfMove() {
		return kindOfMove;
	}

	public void setKindOfMove(int kindOfMove) {
		this.kindOfMove = kindOfMove;
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
