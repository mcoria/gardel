package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChessConstants.encodeMove;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class CastlingTest extends AbstractPerftTest {
    public static final String POSITION = "r3k2r/8/8/4n3/3N4/8/8/R3K2R w KQkq - 0 1";
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
        assertEquals(1, result.getChildNode(Square.d4, Square.b3));
        assertEquals(1, result.getChildNode(Square.d4, Square.b5));
        assertEquals(1, result.getChildNode(Square.d4, Square.c2));
        assertEquals(1, result.getChildNode(Square.d4, Square.c6));
        assertEquals(1, result.getChildNode(Square.d4, Square.e2));
        assertEquals(1, result.getChildNode(Square.d4, Square.e6));
        assertEquals(1, result.getChildNode(Square.d4, Square.f3));
        assertEquals(1, result.getChildNode(Square.d4, Square.f5));
        assertEquals(1, result.getChildNode(Square.e1, Square.c1));
        assertEquals(1, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1, result.getChildNode(Square.e1, Square.d2));
        assertEquals(1, result.getChildNode(Square.e1, Square.e2));
        assertEquals(1, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1, result.getChildNode(Square.e1, Square.f2));
        assertEquals(1, result.getChildNode(Square.e1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.f1));
        assertEquals(1, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.h2));
        assertEquals(1, result.getChildNode(Square.h1, Square.h3));
        assertEquals(1, result.getChildNode(Square.h1, Square.h4));
        assertEquals(1, result.getChildNode(Square.h1, Square.h5));
        assertEquals(1, result.getChildNode(Square.h1, Square.h6));
        assertEquals(1, result.getChildNode(Square.h1, Square.h7));
        assertEquals(1, result.getChildNode(Square.h1, Square.h8));
        assertEquals(34, result.getMovesCount());
        assertEquals(34, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);
        assertEquals(33, result.getChildNode(Square.a1, Square.a2));
        assertEquals(32, result.getChildNode(Square.a1, Square.a3));
        assertEquals(31, result.getChildNode(Square.a1, Square.a4));
        assertEquals(30, result.getChildNode(Square.a1, Square.a5));
        assertEquals(29, result.getChildNode(Square.a1, Square.a6));
        assertEquals(25, result.getChildNode(Square.a1, Square.a7));
        assertEquals(3, result.getChildNode(Square.a1, Square.a8));
        assertEquals(34, result.getChildNode(Square.a1, Square.b1));
        assertEquals(33, result.getChildNode(Square.a1, Square.c1));
        assertEquals(34, result.getChildNode(Square.a1, Square.d1));
        assertEquals(34, result.getChildNode(Square.d4, Square.b3));
        assertEquals(34, result.getChildNode(Square.d4, Square.b5));
        assertEquals(34, result.getChildNode(Square.d4, Square.c2));
        assertEquals(31, result.getChildNode(Square.d4, Square.c6));
        assertEquals(34, result.getChildNode(Square.d4, Square.e2));
        assertEquals(30, result.getChildNode(Square.d4, Square.e6));
        assertEquals(34, result.getChildNode(Square.d4, Square.f3));
        assertEquals(33, result.getChildNode(Square.d4, Square.f5));
        assertEquals(34, result.getChildNode(Square.e1, Square.c1));
        assertEquals(34, result.getChildNode(Square.e1, Square.d1));
        assertEquals(34, result.getChildNode(Square.e1, Square.d2));
        assertEquals(34, result.getChildNode(Square.e1, Square.e2));
        assertEquals(34, result.getChildNode(Square.e1, Square.f1));
        assertEquals(34, result.getChildNode(Square.e1, Square.f2));
        assertEquals(31, result.getChildNode(Square.e1, Square.g1));
        assertEquals(31, result.getChildNode(Square.h1, Square.f1));
        assertEquals(33, result.getChildNode(Square.h1, Square.g1));
        assertEquals(33, result.getChildNode(Square.h1, Square.h2));
        assertEquals(32, result.getChildNode(Square.h1, Square.h3));
        assertEquals(31, result.getChildNode(Square.h1, Square.h4));
        assertEquals(30, result.getChildNode(Square.h1, Square.h5));
        assertEquals(29, result.getChildNode(Square.h1, Square.h6));
        assertEquals(25, result.getChildNode(Square.h1, Square.h7));
        assertEquals(3, result.getChildNode(Square.h1, Square.h8));
        assertEquals(34, result.getMovesCount());
        assertEquals(1030, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2_a1a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a8, Square.a2));
        assertEquals(1, result.getChildNode(Square.a8, Square.a3));
        assertEquals(1, result.getChildNode(Square.a8, Square.a4));
        assertEquals(1, result.getChildNode(Square.a8, Square.a5));
        assertEquals(1, result.getChildNode(Square.a8, Square.a6));
        assertEquals(1, result.getChildNode(Square.a8, Square.a7));
        assertEquals(1, result.getChildNode(Square.a8, Square.b8));
        assertEquals(1, result.getChildNode(Square.a8, Square.c8));
        assertEquals(1, result.getChildNode(Square.a8, Square.d8));
        assertEquals(1, result.getChildNode(Square.e5, Square.c4));
        assertEquals(1, result.getChildNode(Square.e5, Square.c6));
        assertEquals(1, result.getChildNode(Square.e5, Square.d3));
        assertEquals(1, result.getChildNode(Square.e5, Square.d7));
        assertEquals(1, result.getChildNode(Square.e5, Square.f3));
        assertEquals(1, result.getChildNode(Square.e5, Square.f7));
        assertEquals(1, result.getChildNode(Square.e5, Square.g4));
        assertEquals(1, result.getChildNode(Square.e5, Square.g6));
        assertEquals(1, result.getChildNode(Square.e8, Square.c8));
        assertEquals(1, result.getChildNode(Square.e8, Square.d7));
        assertEquals(1, result.getChildNode(Square.e8, Square.d8));
        assertEquals(1, result.getChildNode(Square.e8, Square.e7));
        assertEquals(1, result.getChildNode(Square.e8, Square.f7));
        assertEquals(1, result.getChildNode(Square.e8, Square.f8));
        assertEquals(1, result.getChildNode(Square.e8, Square.g8));
        assertEquals(1, result.getChildNode(Square.h8, Square.f8));
        assertEquals(1, result.getChildNode(Square.h8, Square.g8));
        assertEquals(1, result.getChildNode(Square.h8, Square.h1));
        assertEquals(1, result.getChildNode(Square.h8, Square.h2));
        assertEquals(1, result.getChildNode(Square.h8, Square.h3));
        assertEquals(1, result.getChildNode(Square.h8, Square.h4));
        assertEquals(1, result.getChildNode(Square.h8, Square.h5));
        assertEquals(1, result.getChildNode(Square.h8, Square.h6));
        assertEquals(1, result.getChildNode(Square.h8, Square.h7));
        assertEquals(33, result.getMovesCount());
        assertEquals(33, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2_e1g1() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e1.bitPosition(), Square.g1.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a8, Square.a1));
        assertEquals(1, result.getChildNode(Square.a8, Square.a2));
        assertEquals(1, result.getChildNode(Square.a8, Square.a3));
        assertEquals(1, result.getChildNode(Square.a8, Square.a4));
        assertEquals(1, result.getChildNode(Square.a8, Square.a5));
        assertEquals(1, result.getChildNode(Square.a8, Square.a6));
        assertEquals(1, result.getChildNode(Square.a8, Square.a7));
        assertEquals(1, result.getChildNode(Square.a8, Square.b8));
        assertEquals(1, result.getChildNode(Square.a8, Square.c8));
        assertEquals(1, result.getChildNode(Square.a8, Square.d8));
        assertEquals(1, result.getChildNode(Square.e5, Square.c4));
        assertEquals(1, result.getChildNode(Square.e5, Square.c6));
        assertEquals(1, result.getChildNode(Square.e5, Square.d3));
        assertEquals(1, result.getChildNode(Square.e5, Square.d7));
        assertEquals(1, result.getChildNode(Square.e5, Square.f3));
        assertEquals(1, result.getChildNode(Square.e5, Square.f7));
        assertEquals(1, result.getChildNode(Square.e5, Square.g4));
        assertEquals(1, result.getChildNode(Square.e5, Square.g6));
        assertEquals(1, result.getChildNode(Square.e8, Square.c8));
        assertEquals(1, result.getChildNode(Square.e8, Square.d7));
        assertEquals(1, result.getChildNode(Square.e8, Square.d8));
        assertEquals(1, result.getChildNode(Square.e8, Square.e7));
        assertEquals(1, result.getChildNode(Square.h8, Square.f8));
        assertEquals(1, result.getChildNode(Square.h8, Square.g8));
        assertEquals(1, result.getChildNode(Square.h8, Square.h1));
        assertEquals(1, result.getChildNode(Square.h8, Square.h2));
        assertEquals(1, result.getChildNode(Square.h8, Square.h3));
        assertEquals(1, result.getChildNode(Square.h8, Square.h4));
        assertEquals(1, result.getChildNode(Square.h8, Square.h5));
        assertEquals(1, result.getChildNode(Square.h8, Square.h6));
        assertEquals(1, result.getChildNode(Square.h8, Square.h7));
        assertEquals(31, result.getMovesCount());
        assertEquals(31, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);
        assertEquals(1057, result.getChildNode(Square.a1, Square.a2));
        assertEquals(1030, result.getChildNode(Square.a1, Square.a3));
        assertEquals(860, result.getChildNode(Square.a1, Square.a4));
        assertEquals(901, result.getChildNode(Square.a1, Square.a5));
        assertEquals(920, result.getChildNode(Square.a1, Square.a6));
        assertEquals(773, result.getChildNode(Square.a1, Square.a7));
        assertEquals(111, result.getChildNode(Square.a1, Square.a8));
        assertEquals(992, result.getChildNode(Square.a1, Square.b1));
        assertEquals(953, result.getChildNode(Square.a1, Square.c1));
        assertEquals(810, result.getChildNode(Square.a1, Square.d1));
        assertEquals(882, result.getChildNode(Square.d4, Square.b3));
        assertEquals(910, result.getChildNode(Square.d4, Square.b5));
        assertEquals(855, result.getChildNode(Square.d4, Square.c2));
        assertEquals(864, result.getChildNode(Square.d4, Square.c6));
        assertEquals(882, result.getChildNode(Square.d4, Square.e2));
        assertEquals(840, result.getChildNode(Square.d4, Square.e6));
        assertEquals(947, result.getChildNode(Square.d4, Square.f3));
        assertEquals(942, result.getChildNode(Square.d4, Square.f5));
        assertEquals(806, result.getChildNode(Square.e1, Square.c1));
        assertEquals(959, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1179, result.getChildNode(Square.e1, Square.d2));
        assertEquals(1201, result.getChildNode(Square.e1, Square.e2));
        assertEquals(923, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1128, result.getChildNode(Square.e1, Square.f2));
        assertEquals(863, result.getChildNode(Square.e1, Square.g1));
        assertEquals(868, result.getChildNode(Square.h1, Square.f1));
        assertEquals(957, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1089, result.getChildNode(Square.h1, Square.h2));
        assertEquals(1061, result.getChildNode(Square.h1, Square.h3));
        assertEquals(915, result.getChildNode(Square.h1, Square.h4));
        assertEquals(910, result.getChildNode(Square.h1, Square.h5));
        assertEquals(946, result.getChildNode(Square.h1, Square.h6));
        assertEquals(795, result.getChildNode(Square.h1, Square.h7));
        assertEquals(114, result.getChildNode(Square.h1, Square.h8));
        assertEquals(34, result.getMovesCount());
        assertEquals(30243, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_a1a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(20, result.getChildNode(Square.a8, Square.a2));
        assertEquals(32, result.getChildNode(Square.a8, Square.a3));
        assertEquals(33, result.getChildNode(Square.a8, Square.a4));
        assertEquals(34, result.getChildNode(Square.a8, Square.a5));
        assertEquals(35, result.getChildNode(Square.a8, Square.a6));
        assertEquals(36, result.getChildNode(Square.a8, Square.a7));
        assertEquals(37, result.getChildNode(Square.a8, Square.b8));
        assertEquals(37, result.getChildNode(Square.a8, Square.c8));
        assertEquals(37, result.getChildNode(Square.a8, Square.d8));
        assertEquals(36, result.getChildNode(Square.e5, Square.c4));
        assertEquals(37, result.getChildNode(Square.e5, Square.c6));
        assertEquals(4, result.getChildNode(Square.e5, Square.d3));
        assertEquals(37, result.getChildNode(Square.e5, Square.d7));
        assertEquals(5, result.getChildNode(Square.e5, Square.f3));
        assertEquals(37, result.getChildNode(Square.e5, Square.f7));
        assertEquals(36, result.getChildNode(Square.e5, Square.g4));
        assertEquals(37, result.getChildNode(Square.e5, Square.g6));
        assertEquals(37, result.getChildNode(Square.e8, Square.c8));
        assertEquals(37, result.getChildNode(Square.e8, Square.d7));
        assertEquals(37, result.getChildNode(Square.e8, Square.d8));
        assertEquals(37, result.getChildNode(Square.e8, Square.e7));
        assertEquals(37, result.getChildNode(Square.e8, Square.f7));
        assertEquals(37, result.getChildNode(Square.e8, Square.f8));
        assertEquals(34, result.getChildNode(Square.e8, Square.g8));
        assertEquals(34, result.getChildNode(Square.h8, Square.f8));
        assertEquals(36, result.getChildNode(Square.h8, Square.g8));
        assertEquals(3, result.getChildNode(Square.h8, Square.h1));
        assertEquals(28, result.getChildNode(Square.h8, Square.h2));
        assertEquals(32, result.getChildNode(Square.h8, Square.h3));
        assertEquals(33, result.getChildNode(Square.h8, Square.h4));
        assertEquals(34, result.getChildNode(Square.h8, Square.h5));
        assertEquals(35, result.getChildNode(Square.h8, Square.h6));
        assertEquals(36, result.getChildNode(Square.h8, Square.h7));
        assertEquals(33, result.getMovesCount());
        assertEquals(1057, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_a1a2_a8a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.d4, Square.b3));
        assertEquals(1, result.getChildNode(Square.d4, Square.b5));
        assertEquals(1, result.getChildNode(Square.d4, Square.c2));
        assertEquals(1, result.getChildNode(Square.d4, Square.c6));
        assertEquals(1, result.getChildNode(Square.d4, Square.e2));
        assertEquals(1, result.getChildNode(Square.d4, Square.e6));
        assertEquals(1, result.getChildNode(Square.d4, Square.f3));
        assertEquals(1, result.getChildNode(Square.d4, Square.f5));
        assertEquals(1, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1, result.getChildNode(Square.e1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.f1));
        assertEquals(1, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.h2));
        assertEquals(1, result.getChildNode(Square.h1, Square.h3));
        assertEquals(1, result.getChildNode(Square.h1, Square.h4));
        assertEquals(1, result.getChildNode(Square.h1, Square.h5));
        assertEquals(1, result.getChildNode(Square.h1, Square.h6));
        assertEquals(1, result.getChildNode(Square.h1, Square.h7));
        assertEquals(1, result.getChildNode(Square.h1, Square.h8));
        assertEquals(20, result.getMovesCount());
        assertEquals(20, result.getTotalNodes());
    }
}
