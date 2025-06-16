package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class KPvKPTest extends AbstractPerftTest {
    public static final String POSITION = "8/P3k1p1/8/3p1P1P/p1p1P3/8/1P2K2p/8 w - - 0 1";
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }

    @Test
    public void test_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(1, result.getChildNode(Square.b2, Square.b3));
        assertEquals(1, result.getChildNode(Square.b2, Square.b4));
        assertEquals(1, result.getChildNode(Square.e2, Square.d1));
        assertEquals(1, result.getChildNode(Square.e2, Square.d2));
        assertEquals(1, result.getChildNode(Square.e2, Square.e1));
        assertEquals(1, result.getChildNode(Square.e2, Square.e3));
        assertEquals(1, result.getChildNode(Square.e2, Square.f1));
        assertEquals(1, result.getChildNode(Square.e2, Square.f2));
        assertEquals(1, result.getChildNode(Square.e2, Square.f3));
        assertEquals(1, result.getChildNode(Square.e4, Square.d5));
        assertEquals(1, result.getChildNode(Square.e4, Square.e5));
        assertEquals(1, result.getChildNode(Square.f5, Square.f6));
        assertEquals(1, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(17, result.getTotalNodes());
    }
}
