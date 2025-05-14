package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class KNvKNTest extends AbstractPerftTest {
    public static final String POSITION = "8/6n1/4k3/8/4K3/2N5/8/8 w - - 0 1";
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }


    @Test
    public void testKing_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.e4, Square.d3));
        assertEquals(1, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1, result.getChildNode(Square.c3, Square.b1));
        assertEquals(1, result.getChildNode(Square.c3, Square.e2));
        assertEquals(1, result.getChildNode(Square.e4, Square.e3));
        assertEquals(1, result.getChildNode(Square.c3, Square.b5));
        assertEquals(1, result.getChildNode(Square.c3, Square.d5));
        assertEquals(1, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1, result.getChildNode(Square.c3, Square.a2));
        assertEquals(1, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1, result.getChildNode(Square.c3, Square.d1));
        assertEquals(1, result.getChildNode(Square.e4, Square.f4));

        assertEquals(12, result.getMovesCount());
        assertEquals(12, result.getTotalNodes());
    }


    @Test
    public void testKing_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);

        assertEquals(8, result.getChildNode(Square.c3, Square.d1));
        assertEquals(6, result.getChildNode(Square.c3, Square.d5));
        assertEquals(9, result.getChildNode(Square.e4, Square.d4));
        assertEquals(10, result.getChildNode(Square.e4, Square.f3));
        assertEquals(7, result.getChildNode(Square.c3, Square.b5));
        assertEquals(8, result.getChildNode(Square.e4, Square.f4));
        assertEquals(8, result.getChildNode(Square.c3, Square.b1));
        assertEquals(8, result.getChildNode(Square.c3, Square.a4));
        assertEquals(10, result.getChildNode(Square.e4, Square.e3));
        assertEquals(8, result.getChildNode(Square.c3, Square.a2));
        assertEquals(10, result.getChildNode(Square.e4, Square.d3));
        assertEquals(8, result.getChildNode(Square.c3, Square.e2));
        assertEquals(12, result.getMovesCount());
        assertEquals(100, result.getTotalNodes());
    }

    @Test
    public void testKing_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);

        assertEquals(92, result.getChildNode(Square.c3, Square.e2));
        assertEquals(76, result.getChildNode(Square.c3, Square.a4));
        assertEquals(103, result.getChildNode(Square.e4, Square.f4));
        assertEquals(68, result.getChildNode(Square.c3, Square.a2));
        assertEquals(145, result.getChildNode(Square.e4, Square.e3));
        assertEquals(68, result.getChildNode(Square.c3, Square.b1));
        assertEquals(81, result.getChildNode(Square.c3, Square.b5));
        assertEquals(145, result.getChildNode(Square.e4, Square.d3));
        assertEquals(151, result.getChildNode(Square.e4, Square.f3));
        assertEquals(114, result.getChildNode(Square.e4, Square.d4));
        assertEquals(76, result.getChildNode(Square.c3, Square.d1));
        assertEquals(77, result.getChildNode(Square.c3, Square.d5));
        assertEquals(12, result.getMovesCount());
        assertEquals(1196, result.getTotalNodes());
    }

    @Test
    public void testKing_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);

        assertEquals(960, result.getChildNode(Square.c3, Square.e2));
        assertEquals(1150, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1092, result.getChildNode(Square.e4, Square.f4));
        assertEquals(1634, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1585, result.getChildNode(Square.e4, Square.d3));
        assertEquals(725, result.getChildNode(Square.c3, Square.d5));
        assertEquals(749, result.getChildNode(Square.c3, Square.b1));
        assertEquals(790, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1548, result.getChildNode(Square.e4, Square.e3));
        assertEquals(747, result.getChildNode(Square.c3, Square.a2));
        assertEquals(834, result.getChildNode(Square.c3, Square.d1));
        assertEquals(802, result.getChildNode(Square.c3, Square.b5));
        assertEquals(12, result.getMovesCount());
        assertEquals(12616, result.getTotalNodes());
    }

    @Test
    public void testKing_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 5);

        assertEquals(10953, result.getChildNode(Square.c3, Square.e2));
        assertEquals(8610, result.getChildNode(Square.c3, Square.a4));
        assertEquals(13112, result.getChildNode(Square.e4, Square.f4));
        assertEquals(7573, result.getChildNode(Square.c3, Square.a2));
        assertEquals(19885, result.getChildNode(Square.e4, Square.e3));
        assertEquals(21091, result.getChildNode(Square.e4, Square.f3));
        assertEquals(7518, result.getChildNode(Square.c3, Square.b1));
        assertEquals(9419, result.getChildNode(Square.c3, Square.b5));
        assertEquals(20501, result.getChildNode(Square.e4, Square.d3));
        assertEquals(8963, result.getChildNode(Square.c3, Square.d1));
        assertEquals(8942, result.getChildNode(Square.c3, Square.d5));
        assertEquals(14114, result.getChildNode(Square.e4, Square.d4));
        assertEquals(12, result.getMovesCount());
        assertEquals(150681, result.getTotalNodes());
    }

    @Test
    public void testKing_POS1_PERFT_L5_debug1() {
        MinChess game = createGame(POSITION);
        //game.doMove();

        PerftResult result = perft.start(game, 5);

        assertEquals(10953, result.getChildNode(Square.c3, Square.e2));
        assertEquals(8610, result.getChildNode(Square.c3, Square.a4));
        assertEquals(13112, result.getChildNode(Square.e4, Square.f4));
        assertEquals(7573, result.getChildNode(Square.c3, Square.a2));
        assertEquals(19885, result.getChildNode(Square.e4, Square.e3));
        assertEquals(21091, result.getChildNode(Square.e4, Square.f3));
        assertEquals(7518, result.getChildNode(Square.c3, Square.b1));
        assertEquals(9419, result.getChildNode(Square.c3, Square.b5));
        assertEquals(20501, result.getChildNode(Square.e4, Square.d3));
        assertEquals(8963, result.getChildNode(Square.c3, Square.d1));
        assertEquals(8942, result.getChildNode(Square.c3, Square.d5));
        assertEquals(14114, result.getChildNode(Square.e4, Square.d4));
        assertEquals(12, result.getMovesCount());
        assertEquals(150681, result.getTotalNodes());
    }

}
