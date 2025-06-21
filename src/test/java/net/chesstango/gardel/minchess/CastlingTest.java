package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class CastlingTest extends AbstractPerftTest {
    public static final String POSITION = "r3k2r/8/1PP2PP1/2N2N2/2n2n2/1pp2pp1/8/R3K2R w KQkq - 0 1";
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }

    @Test
    public void test_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a1, Square.a2));
        assertEquals(1, result.getChildNode(Square.a1, Square.a3));
        assertEquals(1, result.getChildNode(Square.a1, Square.a4));
        assertEquals(1, result.getChildNode(Square.a1, Square.a5));
        assertEquals(1, result.getChildNode(Square.a1, Square.a6));
        assertEquals(1, result.getChildNode(Square.a1, Square.a7));
        assertEquals(1, result.getChildNode(Square.a1, Square.a8));
        assertEquals(1, result.getChildNode(Square.a1, Square.b1));
        assertEquals(1, result.getChildNode(Square.a1, Square.c1));
        assertEquals(1, result.getChildNode(Square.a1, Square.d1));
        assertEquals(1, result.getChildNode(Square.b6, Square.b7));
        assertEquals(1, result.getChildNode(Square.c5, Square.a4));
        assertEquals(1, result.getChildNode(Square.c5, Square.a6));
        assertEquals(1, result.getChildNode(Square.c5, Square.b3));
        assertEquals(1, result.getChildNode(Square.c5, Square.b7));
        assertEquals(1, result.getChildNode(Square.c5, Square.d3));
        assertEquals(1, result.getChildNode(Square.c5, Square.d7));
        assertEquals(1, result.getChildNode(Square.c5, Square.e4));
        assertEquals(1, result.getChildNode(Square.c5, Square.e6));
        assertEquals(1, result.getChildNode(Square.c6, Square.c7));
        assertEquals(1, result.getChildNode(Square.e1, Square.c1));
        assertEquals(1, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1, result.getChildNode(Square.e1, Square.g1));
        assertEquals(1, result.getChildNode(Square.f5, Square.d4));
        assertEquals(1, result.getChildNode(Square.f5, Square.d6));
        assertEquals(1, result.getChildNode(Square.f5, Square.e3));
        assertEquals(1, result.getChildNode(Square.f5, Square.e7));
        assertEquals(1, result.getChildNode(Square.f5, Square.g3));
        assertEquals(1, result.getChildNode(Square.f5, Square.g7));
        assertEquals(1, result.getChildNode(Square.f5, Square.h4));
        assertEquals(1, result.getChildNode(Square.f5, Square.h6));
        assertEquals(1, result.getChildNode(Square.f6, Square.f7));
        assertEquals(1, result.getChildNode(Square.g6, Square.g7));
        assertEquals(1, result.getChildNode(Square.h1, Square.f1));
        assertEquals(1, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.h2));
        assertEquals(1, result.getChildNode(Square.h1, Square.h3));
        assertEquals(1, result.getChildNode(Square.h1, Square.h4));
        assertEquals(1, result.getChildNode(Square.h1, Square.h5));
        assertEquals(1, result.getChildNode(Square.h1, Square.h6));
        assertEquals(1, result.getChildNode(Square.h1, Square.h7));
        assertEquals(1, result.getChildNode(Square.h1, Square.h8));
        assertEquals(43, result.getMovesCount());
        assertEquals(43, result.getTotalNodes());
    }

}
