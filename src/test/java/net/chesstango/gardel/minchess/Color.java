package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 *
 */
public enum Color {
	WHITE,
	BLACK;
	
	public Color oppositeColor(){
		if(this == WHITE) 
			return Color.BLACK;
		else
			return Color.WHITE;
	}	
}
