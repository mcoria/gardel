package net.chesstango.gardel.minchess;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Mauricio Coria
 *
 */
public class PerftResult {
	
	@Setter
    @Getter
    private long totalNodes;
	
	private final Map<Short, Long> moves = new HashMap<>();

	public void add(Short move, long nodeCount) {
		moves.put(move, nodeCount);
	}

    public long getMovesCount() {
		return moves.size();
	}

	public Map<Short, Long> getChilds(){
		return moves;
	}

	public long getChildNode(Square from, Square to) {
		for(Entry<Short, Long> entry: moves.entrySet()){
			final short move = entry.getKey();
			final short moveEncoded = binaryEncoding(from, to);
			if(move == moveEncoded){
				return entry.getValue();
			}
		}
		throw new RuntimeException("Move not found");
	}
	
	public boolean moveExists(Square from, Square to) {
		for(Entry<Short, Long> entry: moves.entrySet()){
			final short move = entry.getKey();
			final short moveEncoded = binaryEncoding(from, to);
			if(move == moveEncoded){
				return true;
			}
		}
		return false;
	}


	static short binaryEncoding(Square fromSquare, Square toSquare) {
		return (short) (fromSquare.getBinaryEncodedFrom() | toSquare.getBinaryEncodedTo());
	}
}
