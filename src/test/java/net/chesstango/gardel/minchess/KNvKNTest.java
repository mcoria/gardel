package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChessConstants.encodeMove;
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
    public void test_POS1_PERFT_L1() {
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
    public void test_POS1_PERFT_L2() {
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
    public void test_POS1_PERFT_L3() {
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
    public void test_POS1_PERFT_L4() {
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
    public void test_POS1_PERFT_L5() {
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
    public void test_POS1_PERFT_L5_debug1() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.e2.bitPosition()));

        PerftResult result = perft.start(game, 4);

        assertEquals(1002, result.getChildNode(Square.g7, Square.h5));
        assertEquals(1166, result.getChildNode(Square.g7, Square.e8));
        assertEquals(1071, result.getChildNode(Square.g7, Square.f5));
        assertEquals(1762, result.getChildNode(Square.e6, Square.d7));
        assertEquals(1780, result.getChildNode(Square.e6, Square.e7));
        assertEquals(1253, result.getChildNode(Square.e6, Square.f6));
        assertEquals(1646, result.getChildNode(Square.e6, Square.f7));
        assertEquals(1273, result.getChildNode(Square.e6, Square.d6));
        assertEquals(8, result.getMovesCount());
        assertEquals(10953, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_debug2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.e2.bitPosition()));
        game.doMove(encodeMove(Square.g7.bitPosition(), Square.h5.bitPosition()));

        PerftResult result = perft.start(game, 3);
        assertEquals(75, result.getChildNode(Square.e2, Square.f4));
        assertEquals(71, result.getChildNode(Square.e2, Square.g1));
        assertEquals(99, result.getChildNode(Square.e2, Square.c3));
        assertEquals(140, result.getChildNode(Square.e4, Square.e3));
        assertEquals(78, result.getChildNode(Square.e2, Square.c1));
        assertEquals(143, result.getChildNode(Square.e4, Square.d3));
        assertEquals(132, result.getChildNode(Square.e4, Square.f3));
        assertEquals(85, result.getChildNode(Square.e2, Square.g3));
        assertEquals(113, result.getChildNode(Square.e4, Square.d4));
        assertEquals(66, result.getChildNode(Square.e2, Square.d4));
        assertEquals(10, result.getMovesCount());
        assertEquals(1002, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_debug3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.e2.bitPosition()));
        game.doMove(encodeMove(Square.g7.bitPosition(), Square.h5.bitPosition()));
        game.doMove(encodeMove(Square.e2.bitPosition(), Square.f4.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(15, result.getChildNode(Square.e6, Square.d7));
        assertEquals(15, result.getChildNode(Square.e6, Square.e7));
        assertEquals(4, result.getChildNode(Square.h5, Square.f4));
        assertEquals(13, result.getChildNode(Square.e6, Square.f6));
        assertEquals(13, result.getChildNode(Square.e6, Square.d6));
        assertEquals(15, result.getChildNode(Square.e6, Square.f7));
        assertEquals(6, result.getMovesCount());
        assertEquals(75, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_debug4() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.e2.bitPosition()));
        game.doMove(encodeMove(Square.g7.bitPosition(), Square.h5.bitPosition()));
        game.doMove(encodeMove(Square.e2.bitPosition(), Square.f4.bitPosition()));
        game.doMove(encodeMove(Square.h5.bitPosition(), Square.f4.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1, result.getChildNode(Square.e4, Square.f4));
        assertEquals(1, result.getChildNode(Square.e4, Square.e3));
        assertEquals(4, result.getMovesCount());
        assertEquals(4, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 6);

        assertEquals(97930, result.getChildNode(Square.c3, Square.d1));
        assertEquals(82972, result.getChildNode(Square.c3, Square.a2));
        assertEquals(230062, result.getChildNode(Square.e4, Square.f3));
        assertEquals(83065, result.getChildNode(Square.c3, Square.b1));
        assertEquals(224306, result.getChildNode(Square.e4, Square.d3));
        assertEquals(148380, result.getChildNode(Square.e4, Square.d4));
        assertEquals(117162, result.getChildNode(Square.c3, Square.e2));
        assertEquals(96481, result.getChildNode(Square.c3, Square.b5));
        assertEquals(139173, result.getChildNode(Square.e4, Square.f4));
        assertEquals(90878, result.getChildNode(Square.c3, Square.a4));
        assertEquals(88823, result.getChildNode(Square.c3, Square.d5));
        assertEquals(214414, result.getChildNode(Square.e4, Square.e3));
        assertEquals(12, result.getMovesCount());
        assertEquals(1613646, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L7() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 7);

        assertEquals(1095564, result.getChildNode(Square.c3, Square.d1));
        assertEquals(2762987, result.getChildNode(Square.e4, Square.d3));
        assertEquals(1031680, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1082885, result.getChildNode(Square.c3, Square.d5));
        assertEquals(2644890, result.getChildNode(Square.e4, Square.e3));
        assertEquals(891861, result.getChildNode(Square.c3, Square.a2));
        assertEquals(1784650, result.getChildNode(Square.e4, Square.d4));
        assertEquals(889490, result.getChildNode(Square.c3, Square.b1));
        assertEquals(2805224, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1634937, result.getChildNode(Square.e4, Square.f4));
        assertEquals(1370235, result.getChildNode(Square.c3, Square.e2));
        assertEquals(1140470, result.getChildNode(Square.c3, Square.b5));
        assertEquals(12, result.getMovesCount());
        assertEquals(19134873, result.getTotalNodes());
    }

}
