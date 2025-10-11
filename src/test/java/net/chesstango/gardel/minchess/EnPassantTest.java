package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FEN;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class EnPassantTest {

    @Test
    public void test() {
        MinChess minChess = MinChess.from(FEN.of("r2q1rk1/pp2bppp/2npbn2/2p5/1PP1p3/P3P3/1BNPBPPP/RN1QK2R w KQ - 0 0"));

        minChess.doMove(binaryEncoding(Square.d2, Square.d4));

        assertEquals("r2q1rk1/pp2bppp/2npbn2/2p5/1PPPp3/P3P3/1BN1BPPP/RN1QK2R b KQ d3 0 0", minChess.toFEN().toString());
    }


    static short binaryEncoding(Square fromSquare, Square toSquare) {
        return (short) (fromSquare.getBinaryEncodedFrom() | toSquare.getBinaryEncodedTo());
    }
}
