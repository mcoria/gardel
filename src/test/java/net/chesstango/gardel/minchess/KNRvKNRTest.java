package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class KNRvKNRTest extends AbstractPerftTest {
    public static final String POSITION = "8/6nr/4k3/8/4K3/1RN5/8/8 w - - 0 1";
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }

    @Test
    public void test_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.b3, Square.b4));
        assertEquals(1, result.getChildNode(Square.b3, Square.b5));
        assertEquals(1, result.getChildNode(Square.b3, Square.b2));
        assertEquals(1, result.getChildNode(Square.b3, Square.b8));
        assertEquals(1, result.getChildNode(Square.b3, Square.b1));
        assertEquals(1, result.getChildNode(Square.b3, Square.b6));
        assertEquals(1, result.getChildNode(Square.b3, Square.b7));
        assertEquals(1, result.getChildNode(Square.b3, Square.a3));

        assertEquals(1, result.getChildNode(Square.e4, Square.f4));
        assertEquals(1, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1, result.getChildNode(Square.e4, Square.d3));
        assertEquals(1, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1, result.getChildNode(Square.e4, Square.e3));

        assertEquals(1, result.getChildNode(Square.c3, Square.b5));
        assertEquals(1, result.getChildNode(Square.c3, Square.b1));
        assertEquals(1, result.getChildNode(Square.c3, Square.d1));
        assertEquals(1, result.getChildNode(Square.c3, Square.d5));
        assertEquals(1, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1, result.getChildNode(Square.c3, Square.a2));
        assertEquals(1, result.getChildNode(Square.c3, Square.e2));
        assertEquals(20, result.getMovesCount());
        assertEquals(20, result.getTotalNodes());
    }
}
