package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChessConstants.encodeMove;
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


    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);

        assertEquals(15, result.getChildNode(Square.e4, Square.f4));
        assertEquals(17, result.getChildNode(Square.e4, Square.e3));
        assertEquals(17, result.getChildNode(Square.e4, Square.f3));
        assertEquals(15, result.getChildNode(Square.c3, Square.a2));
        assertEquals(15, result.getChildNode(Square.b3, Square.b5));
        assertEquals(15, result.getChildNode(Square.b3, Square.b8));
        assertEquals(13, result.getChildNode(Square.c3, Square.d5));
        assertEquals(15, result.getChildNode(Square.c3, Square.a4));
        assertEquals(15, result.getChildNode(Square.b3, Square.b4));
        assertEquals(15, result.getChildNode(Square.b3, Square.b2));
        assertEquals(14, result.getChildNode(Square.c3, Square.b5));
        assertEquals(15, result.getChildNode(Square.b3, Square.a3));
        assertEquals(3, result.getChildNode(Square.b3, Square.b6));
        assertEquals(16, result.getChildNode(Square.e4, Square.d4));
        assertEquals(15, result.getChildNode(Square.c3, Square.e2));
        assertEquals(12, result.getChildNode(Square.b3, Square.b7));
        assertEquals(15, result.getChildNode(Square.c3, Square.d1));
        assertEquals(15, result.getChildNode(Square.b3, Square.b1));
        assertEquals(17, result.getChildNode(Square.e4, Square.d3));
        assertEquals(15, result.getChildNode(Square.c3, Square.b1));
        assertEquals(20, result.getMovesCount());
        assertEquals(289, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);

        assertEquals(368, result.getChildNode(Square.b3, Square.b2));
        assertEquals(361, result.getChildNode(Square.e4, Square.e3));
        assertEquals(352, result.getChildNode(Square.b3, Square.b5));
        assertEquals(324, result.getChildNode(Square.c3, Square.d5));
        assertEquals(284, result.getChildNode(Square.b3, Square.a3));
        assertEquals(277, result.getChildNode(Square.c3, Square.b5));
        assertEquals(365, result.getChildNode(Square.b3, Square.b8));
        assertEquals(298, result.getChildNode(Square.c3, Square.b1));
        assertEquals(291, result.getChildNode(Square.e4, Square.f4));
        assertEquals(312, result.getChildNode(Square.b3, Square.b4));
        assertEquals(305, result.getChildNode(Square.e4, Square.d4));
        assertEquals(326, result.getChildNode(Square.c3, Square.a4));
        assertEquals(312, result.getChildNode(Square.c3, Square.a2));
        assertEquals(375, result.getChildNode(Square.e4, Square.f3));
        assertEquals(84, result.getChildNode(Square.b3, Square.b6));
        assertEquals(326, result.getChildNode(Square.c3, Square.d1));
        assertEquals(354, result.getChildNode(Square.b3, Square.b1));
        assertEquals(363, result.getChildNode(Square.e4, Square.d3));
        assertEquals(276, result.getChildNode(Square.b3, Square.b7));
        assertEquals(355, result.getChildNode(Square.c3, Square.e2));
        assertEquals(20, result.getMovesCount());
        assertEquals(6308, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);

        assertEquals(6293, result.getChildNode(Square.b3, Square.b5));
        assertEquals(5987, result.getChildNode(Square.c3, Square.a4));
        assertEquals(5692, result.getChildNode(Square.e4, Square.d4));
        assertEquals(7024, result.getChildNode(Square.e4, Square.e3));
        assertEquals(5563, result.getChildNode(Square.c3, Square.d5));
        assertEquals(5915, result.getChildNode(Square.c3, Square.a2));
        assertEquals(6139, result.getChildNode(Square.c3, Square.d1));
        assertEquals(5886, result.getChildNode(Square.b3, Square.b4));
        assertEquals(4601, result.getChildNode(Square.b3, Square.b7));
        assertEquals(5617, result.getChildNode(Square.c3, Square.b1));
        assertEquals(7180, result.getChildNode(Square.e4, Square.d3));
        assertEquals(6913, result.getChildNode(Square.b3, Square.b2));
        assertEquals(5386, result.getChildNode(Square.b3, Square.a3));
        assertEquals(6529, result.getChildNode(Square.b3, Square.b8));
        assertEquals(1266, result.getChildNode(Square.b3, Square.b6));
        assertEquals(5059, result.getChildNode(Square.c3, Square.b5));
        assertEquals(7395, result.getChildNode(Square.e4, Square.f3));
        assertEquals(6379, result.getChildNode(Square.c3, Square.e2));
        assertEquals(5600, result.getChildNode(Square.e4, Square.f4));
        assertEquals(6649, result.getChildNode(Square.b3, Square.b1));
        assertEquals(20, result.getMovesCount());
        assertEquals(117073, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 5);

        assertEquals(121980, result.getChildNode(Square.e4, Square.d4));
        assertEquals(105765, result.getChildNode(Square.c3, Square.b1));
        assertEquals(112962, result.getChildNode(Square.c3, Square.a2));
        assertEquals(154278, result.getChildNode(Square.b3, Square.b2));
        assertEquals(123007, result.getChildNode(Square.c3, Square.d5));
        assertEquals(145454, result.getChildNode(Square.b3, Square.b8));
        assertEquals(162213, result.getChildNode(Square.e4, Square.f3));
        assertEquals(114075, result.getChildNode(Square.b3, Square.a3));
        assertEquals(98237, result.getChildNode(Square.b3, Square.b7));
        assertEquals(101605, result.getChildNode(Square.c3, Square.b5));
        assertEquals(141136, result.getChildNode(Square.b3, Square.b5));
        assertEquals(153444, result.getChildNode(Square.e4, Square.d3));
        assertEquals(122987, result.getChildNode(Square.b3, Square.b4));
        assertEquals(118211, result.getChildNode(Square.e4, Square.f4));
        assertEquals(121826, result.getChildNode(Square.c3, Square.d1));
        assertEquals(151251, result.getChildNode(Square.e4, Square.e3));
        assertEquals(148700, result.getChildNode(Square.b3, Square.b1));
        assertEquals(30683, result.getChildNode(Square.b3, Square.b6));
        assertEquals(136782, result.getChildNode(Square.c3, Square.e2));
        assertEquals(118940, result.getChildNode(Square.c3, Square.a4));
        assertEquals(20, result.getMovesCount());
        assertEquals(2483536, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 6);

        assertEquals(2363756, result.getChildNode(Square.b3, Square.b4));
        assertEquals(2565589, result.getChildNode(Square.c3, Square.e2));
        assertEquals(3009816, result.getChildNode(Square.e4, Square.e3));
        assertEquals(1910432, result.getChildNode(Square.c3, Square.b5));
        assertEquals(2351411, result.getChildNode(Square.c3, Square.d1));
        assertEquals(2045379, result.getChildNode(Square.c3, Square.b1));
        assertEquals(2212999, result.getChildNode(Square.b3, Square.a3));
        assertEquals(541878, result.getChildNode(Square.b3, Square.b6));
        assertEquals(2858626, result.getChildNode(Square.b3, Square.b1));
        assertEquals(2724720, result.getChildNode(Square.b3, Square.b8));
        assertEquals(2327908, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1747591, result.getChildNode(Square.b3, Square.b7));
        assertEquals(2614597, result.getChildNode(Square.b3, Square.b5));
        assertEquals(2254962, result.getChildNode(Square.c3, Square.a4));
        assertEquals(2304740, result.getChildNode(Square.e4, Square.f4));
        assertEquals(3082252, result.getChildNode(Square.e4, Square.d3));
        assertEquals(2197875, result.getChildNode(Square.c3, Square.a2));
        assertEquals(3237330, result.getChildNode(Square.e4, Square.f3));
        assertEquals(2958990, result.getChildNode(Square.b3, Square.b2));
        assertEquals(2214933, result.getChildNode(Square.c3, Square.d5));
        assertEquals(20, result.getMovesCount());
        assertEquals(47525784, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_debug1() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b3.bitPosition(), Square.b4.bitPosition()));

        PerftResult result = perft.start(game, 5);

        assertEquals(173143, result.getChildNode(Square.h7, Square.h5));
        assertEquals(210709, result.getChildNode(Square.g7, Square.f5));
        assertEquals(174018, result.getChildNode(Square.e6, Square.e7));
        assertEquals(142444, result.getChildNode(Square.e6, Square.f6));
        assertEquals(141988, result.getChildNode(Square.h7, Square.h6));
        assertEquals(125064, result.getChildNode(Square.h7, Square.h3));
        assertEquals(129786, result.getChildNode(Square.e6, Square.d6));
        assertEquals(172458, result.getChildNode(Square.e6, Square.d7));
        assertEquals(188415, result.getChildNode(Square.h7, Square.h1));
        assertEquals(194137, result.getChildNode(Square.g7, Square.e8));
        assertEquals(185222, result.getChildNode(Square.h7, Square.h2));
        assertEquals(33582, result.getChildNode(Square.h7, Square.h4));
        assertEquals(191514, result.getChildNode(Square.h7, Square.h8));
        assertEquals(170361, result.getChildNode(Square.e6, Square.f7));
        assertEquals(130915, result.getChildNode(Square.g7, Square.h5));
        assertEquals(15, result.getMovesCount());
        assertEquals(2363756, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_debug2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b3.bitPosition(), Square.b4.bitPosition()));
        game.doMove(encodeMove(Square.e6.bitPosition(), Square.f7.bitPosition()));

        PerftResult result = perft.start(game, 4);

        assertEquals(5990, result.getChildNode(Square.c3, Square.b1));
        assertEquals(8141, result.getChildNode(Square.b4, Square.b5));
        assertEquals(9404, result.getChildNode(Square.e4, Square.f3));
        assertEquals(9101, result.getChildNode(Square.e4, Square.d3));
        assertEquals(2038, result.getChildNode(Square.b4, Square.b7));
        assertEquals(7200, result.getChildNode(Square.b4, Square.d4));
        assertEquals(8373, result.getChildNode(Square.b4, Square.b1));
        assertEquals(6597, result.getChildNode(Square.b4, Square.c4));
        assertEquals(7151, result.getChildNode(Square.e4, Square.e5));
        assertEquals(6005, result.getChildNode(Square.c3, Square.a2));
        assertEquals(6083, result.getChildNode(Square.c3, Square.d5));
        assertEquals(8690, result.getChildNode(Square.b4, Square.b2));
        assertEquals(6316, result.getChildNode(Square.c3, Square.a4));
        assertEquals(7495, result.getChildNode(Square.e4, Square.f4));
        assertEquals(7242, result.getChildNode(Square.c3, Square.e2));
        assertEquals(6630, result.getChildNode(Square.c3, Square.d1));
        assertEquals(6855, result.getChildNode(Square.b4, Square.b6));
        assertEquals(7999, result.getChildNode(Square.e4, Square.d5));
        assertEquals(8939, result.getChildNode(Square.e4, Square.e3));
        assertEquals(7287, result.getChildNode(Square.b4, Square.a4));
        assertEquals(6917, result.getChildNode(Square.e4, Square.d4));
        assertEquals(6062, result.getChildNode(Square.c3, Square.b5));
        assertEquals(6900, result.getChildNode(Square.b4, Square.b8));
        assertEquals(6946, result.getChildNode(Square.b4, Square.b3));
        assertEquals(24, result.getMovesCount());
        assertEquals(170361, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_debug3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b3.bitPosition(), Square.b4.bitPosition()));
        game.doMove(encodeMove(Square.e6.bitPosition(), Square.f7.bitPosition()));
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.b1.bitPosition()));

        PerftResult result = perft.start(game, 3);

        assertEquals(395, result.getChildNode(Square.g7, Square.f5));
        assertEquals(432, result.getChildNode(Square.h7, Square.h1));
        assertEquals(249, result.getChildNode(Square.f7, Square.e6));
        assertEquals(264, result.getChildNode(Square.g7, Square.h5));
        assertEquals(383, result.getChildNode(Square.h7, Square.h5));
        assertEquals(334, result.getChildNode(Square.f7, Square.e7));
        assertEquals(358, result.getChildNode(Square.g7, Square.e8));
        assertEquals(446, result.getChildNode(Square.h7, Square.h8));
        assertEquals(443, result.getChildNode(Square.h7, Square.h2));
        assertEquals(391, result.getChildNode(Square.g7, Square.e6));
        assertEquals(446, result.getChildNode(Square.h7, Square.h6));
        assertEquals(268, result.getChildNode(Square.f7, Square.e8));
        assertEquals(279, result.getChildNode(Square.f7, Square.f6));
        assertEquals(117, result.getChildNode(Square.h7, Square.h4));
        assertEquals(292, result.getChildNode(Square.f7, Square.g6));
        assertEquals(271, result.getChildNode(Square.f7, Square.f8));
        assertEquals(369, result.getChildNode(Square.h7, Square.h3));
        assertEquals(253, result.getChildNode(Square.f7, Square.g8));
        assertEquals(18, result.getMovesCount());
        assertEquals(5990, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_debug4() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b3.bitPosition(), Square.b4.bitPosition()));
        game.doMove(encodeMove(Square.e6.bitPosition(), Square.f7.bitPosition()));
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.b1.bitPosition()));
        game.doMove(encodeMove(Square.f7.bitPosition(), Square.g8.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(14, result.getChildNode(Square.e4, Square.f4));
        assertEquals(14, result.getChildNode(Square.b1, Square.d2));
        assertEquals(13, result.getChildNode(Square.b4, Square.b7));
        assertEquals(14, result.getChildNode(Square.b4, Square.b2));
        assertEquals(14, result.getChildNode(Square.e4, Square.d3));
        assertEquals(14, result.getChildNode(Square.b4, Square.c4));
        assertEquals(14, result.getChildNode(Square.e4, Square.e3));
        assertEquals(14, result.getChildNode(Square.e4, Square.e5));
        assertEquals(2, result.getChildNode(Square.b4, Square.b8));
        assertEquals(14, result.getChildNode(Square.e4, Square.f3));
        assertEquals(14, result.getChildNode(Square.b4, Square.b3));
        assertEquals(14, result.getChildNode(Square.b4, Square.d4));
        assertEquals(14, result.getChildNode(Square.b1, Square.a3));
        assertEquals(14, result.getChildNode(Square.b1, Square.c3));
        assertEquals(14, result.getChildNode(Square.b4, Square.b6));
        assertEquals(14, result.getChildNode(Square.b4, Square.a4));
        assertEquals(14, result.getChildNode(Square.e4, Square.d5));
        assertEquals(14, result.getChildNode(Square.e4, Square.d4));
        assertEquals(14, result.getChildNode(Square.b4, Square.b5));
        assertEquals(19, result.getMovesCount());
        assertEquals(253, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_debug5() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b3.bitPosition(), Square.b4.bitPosition()));
        game.doMove(encodeMove(Square.e6.bitPosition(), Square.f7.bitPosition()));
        game.doMove(encodeMove(Square.c3.bitPosition(), Square.b1.bitPosition()));
        game.doMove(encodeMove(Square.f7.bitPosition(), Square.g8.bitPosition()));
        game.doMove(encodeMove(Square.e4.bitPosition(), Square.f4.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.g7, Square.e6));
        assertEquals(1, result.getChildNode(Square.g7, Square.e8));
        assertEquals(1, result.getChildNode(Square.g7, Square.h5));
        assertEquals(1, result.getChildNode(Square.h7, Square.h8));
        assertEquals(1, result.getChildNode(Square.h7, Square.h1));
        assertEquals(1, result.getChildNode(Square.h7, Square.h5));
        assertEquals(1, result.getChildNode(Square.h7, Square.h3));
        assertEquals(1, result.getChildNode(Square.h7, Square.h6));
        assertEquals(1, result.getChildNode(Square.h7, Square.h4));
        assertEquals(1, result.getChildNode(Square.g8, Square.f7));
        assertEquals(1, result.getChildNode(Square.h7, Square.h2));
        assertEquals(1, result.getChildNode(Square.g8, Square.f8));
        assertEquals(1, result.getChildNode(Square.g8, Square.h8));
        assertEquals(1, result.getChildNode(Square.g7, Square.f5));
        assertEquals(14, result.getMovesCount());
        assertEquals(14, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_debug5_01() {
        MinChess game = createGame("6k1/6nr/8/4K3/1R6/8/8/1N6 b - - 0 1");

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.g7, Square.e6));
        assertEquals(1, result.getChildNode(Square.g7, Square.e8));
        assertEquals(1, result.getChildNode(Square.g7, Square.h5));
        assertEquals(1, result.getChildNode(Square.h7, Square.h8));
        assertEquals(1, result.getChildNode(Square.h7, Square.h1));
        assertEquals(1, result.getChildNode(Square.h7, Square.h5));
        assertEquals(1, result.getChildNode(Square.h7, Square.h3));
        assertEquals(1, result.getChildNode(Square.h7, Square.h6));
        assertEquals(1, result.getChildNode(Square.h7, Square.h4));
        assertEquals(1, result.getChildNode(Square.g8, Square.f7));
        assertEquals(1, result.getChildNode(Square.h7, Square.h2));
        assertEquals(1, result.getChildNode(Square.g8, Square.f8));
        assertEquals(1, result.getChildNode(Square.g8, Square.h8));
        assertEquals(1, result.getChildNode(Square.g7, Square.f5));
        assertEquals(14, result.getMovesCount());
        assertEquals(14, result.getTotalNodes());
    }
}
