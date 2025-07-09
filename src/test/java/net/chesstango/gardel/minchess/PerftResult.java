package net.chesstango.gardel.minchess;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Mauricio Coria
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

    public Map<Short, Long> getChildren() {
        return moves;
    }

    public long getChildNode(Square from, Square to) {
        return getChildNode(binaryEncoding(from, to));
    }

    public long getChildNode(Square from, Square to, Piece promotion) {
        return getChildNode(binaryEncoding(from, to, promotion));
    }

    private long getChildNode(short moveEncoded) {
        for (Entry<Short, Long> entry : moves.entrySet()) {
            final short move = entry.getKey();
            if (move == moveEncoded) {
                return entry.getValue();
            }
        }
        throw new RuntimeException("Move not found");
    }

    static short binaryEncoding(Square fromSquare, Square toSquare) {
        return (short) (fromSquare.getBinaryEncodedFrom() | toSquare.getBinaryEncodedTo());
    }

    static short binaryEncoding(Square from, Square to, Piece promotion) {
        short fromToEncoded = binaryEncoding(from, to);
        short pieceEncoded = switch (promotion) {
            case KNIGHT_BLACK, KNIGHT_WHITE -> 1;
            case BISHOP_BLACK, BISHOP_WHITE -> 2;
            case ROOK_BLACK, ROOK_WHITE -> 3;
            case QUEEN_BLACK, QUEEN_WHITE -> 4;
            default -> throw new RuntimeException("Invalid promotion");
        };
        return (short) (pieceEncoded << 12 | fromToEncoded);
    }
}
