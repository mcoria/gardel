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

    @Test
    public void test_POS1_PERFT_L3_a1a2_e5d3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.e5.bitPosition(), Square.d3.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1, result.getChildNode(Square.e1, Square.d2));
        assertEquals(1, result.getChildNode(Square.e1, Square.e2));
        assertEquals(1, result.getChildNode(Square.e1, Square.f1));
        assertEquals(4, result.getMovesCount());
        assertEquals(4, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_a1a2_e8c8() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.e8.bitPosition(), Square.c8.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a2, Square.a1));
        assertEquals(1, result.getChildNode(Square.a2, Square.a3));
        assertEquals(1, result.getChildNode(Square.a2, Square.a4));
        assertEquals(1, result.getChildNode(Square.a2, Square.a5));
        assertEquals(1, result.getChildNode(Square.a2, Square.a6));
        assertEquals(1, result.getChildNode(Square.a2, Square.a7));
        assertEquals(1, result.getChildNode(Square.a2, Square.a8));
        assertEquals(1, result.getChildNode(Square.a2, Square.b2));
        assertEquals(1, result.getChildNode(Square.a2, Square.c2));
        assertEquals(1, result.getChildNode(Square.a2, Square.d2));
        assertEquals(1, result.getChildNode(Square.a2, Square.e2));
        assertEquals(1, result.getChildNode(Square.a2, Square.f2));
        assertEquals(1, result.getChildNode(Square.a2, Square.g2));
        assertEquals(1, result.getChildNode(Square.a2, Square.h2));
        assertEquals(1, result.getChildNode(Square.d4, Square.b3));
        assertEquals(1, result.getChildNode(Square.d4, Square.b5));
        assertEquals(1, result.getChildNode(Square.d4, Square.c2));
        assertEquals(1, result.getChildNode(Square.d4, Square.c6));
        assertEquals(1, result.getChildNode(Square.d4, Square.e2));
        assertEquals(1, result.getChildNode(Square.d4, Square.e6));
        assertEquals(1, result.getChildNode(Square.d4, Square.f3));
        assertEquals(1, result.getChildNode(Square.d4, Square.f5));
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
        assertEquals(37, result.getMovesCount());
        assertEquals(37, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_e1d1() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e1.bitPosition(), Square.d1.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(3, result.getChildNode(Square.a8, Square.a1));
        assertEquals(23, result.getChildNode(Square.a8, Square.a2));
        assertEquals(27, result.getChildNode(Square.a8, Square.a3));
        assertEquals(28, result.getChildNode(Square.a8, Square.a4));
        assertEquals(29, result.getChildNode(Square.a8, Square.a5));
        assertEquals(30, result.getChildNode(Square.a8, Square.a6));
        assertEquals(31, result.getChildNode(Square.a8, Square.a7));
        assertEquals(32, result.getChildNode(Square.a8, Square.b8));
        assertEquals(30, result.getChildNode(Square.a8, Square.c8));
        assertEquals(24, result.getChildNode(Square.a8, Square.d8));
        assertEquals(31, result.getChildNode(Square.e5, Square.c4));
        assertEquals(32, result.getChildNode(Square.e5, Square.c6));
        assertEquals(30, result.getChildNode(Square.e5, Square.d3));
        assertEquals(32, result.getChildNode(Square.e5, Square.d7));
        assertEquals(30, result.getChildNode(Square.e5, Square.f3));
        assertEquals(32, result.getChildNode(Square.e5, Square.f7));
        assertEquals(32, result.getChildNode(Square.e5, Square.g4));
        assertEquals(32, result.getChildNode(Square.e5, Square.g6));
        assertEquals(24, result.getChildNode(Square.e8, Square.c8));
        assertEquals(32, result.getChildNode(Square.e8, Square.d7));
        assertEquals(32, result.getChildNode(Square.e8, Square.d8));
        assertEquals(32, result.getChildNode(Square.e8, Square.e7));
        assertEquals(32, result.getChildNode(Square.e8, Square.f7));
        assertEquals(32, result.getChildNode(Square.e8, Square.f8));
        assertEquals(32, result.getChildNode(Square.e8, Square.g8));
        assertEquals(32, result.getChildNode(Square.h8, Square.f8));
        assertEquals(32, result.getChildNode(Square.h8, Square.g8));
        assertEquals(3, result.getChildNode(Square.h8, Square.h1));
        assertEquals(23, result.getChildNode(Square.h8, Square.h2));
        assertEquals(27, result.getChildNode(Square.h8, Square.h3));
        assertEquals(28, result.getChildNode(Square.h8, Square.h4));
        assertEquals(29, result.getChildNode(Square.h8, Square.h5));
        assertEquals(30, result.getChildNode(Square.h8, Square.h6));
        assertEquals(31, result.getChildNode(Square.h8, Square.h7));
        assertEquals(34, result.getMovesCount());
        assertEquals(959, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_e1d1_a8a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e1.bitPosition(), Square.d1.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a1, Square.a2));
        assertEquals(1, result.getChildNode(Square.a1, Square.b1));
        assertEquals(1, result.getChildNode(Square.a1, Square.c1));
        assertEquals(1, result.getChildNode(Square.d1, Square.c1));
        assertEquals(1, result.getChildNode(Square.d1, Square.e1));
        assertEquals(1, result.getChildNode(Square.d4, Square.b3));
        assertEquals(1, result.getChildNode(Square.d4, Square.b5));
        assertEquals(1, result.getChildNode(Square.d4, Square.c2));
        assertEquals(1, result.getChildNode(Square.d4, Square.c6));
        assertEquals(1, result.getChildNode(Square.d4, Square.e2));
        assertEquals(1, result.getChildNode(Square.d4, Square.e6));
        assertEquals(1, result.getChildNode(Square.d4, Square.f3));
        assertEquals(1, result.getChildNode(Square.d4, Square.f5));
        assertEquals(1, result.getChildNode(Square.h1, Square.e1));
        assertEquals(1, result.getChildNode(Square.h1, Square.f1));
        assertEquals(1, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.h2));
        assertEquals(1, result.getChildNode(Square.h1, Square.h3));
        assertEquals(1, result.getChildNode(Square.h1, Square.h4));
        assertEquals(1, result.getChildNode(Square.h1, Square.h5));
        assertEquals(1, result.getChildNode(Square.h1, Square.h6));
        assertEquals(1, result.getChildNode(Square.h1, Square.h7));
        assertEquals(1, result.getChildNode(Square.h1, Square.h8));
        assertEquals(23, result.getMovesCount());
        assertEquals(23, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);
        assertEquals(32028, result.getChildNode(Square.a1, Square.a2));
        assertEquals(30619, result.getChildNode(Square.a1, Square.a3));
        assertEquals(24972, result.getChildNode(Square.a1, Square.a4));
        assertEquals(25243, result.getChildNode(Square.a1, Square.a5));
        assertEquals(25406, result.getChildNode(Square.a1, Square.a6));
        assertEquals(19262, result.getChildNode(Square.a1, Square.a7));
        assertEquals(2381, result.getChildNode(Square.a1, Square.a8));
        assertEquals(30912, result.getChildNode(Square.a1, Square.b1));
        assertEquals(29535, result.getChildNode(Square.a1, Square.c1));
        assertEquals(24881, result.getChildNode(Square.a1, Square.d1));
        assertEquals(27183, result.getChildNode(Square.d4, Square.b3));
        assertEquals(26571, result.getChildNode(Square.d4, Square.b5));
        assertEquals(26424, result.getChildNode(Square.d4, Square.c2));
        assertEquals(24959, result.getChildNode(Square.d4, Square.c6));
        assertEquals(27416, result.getChildNode(Square.d4, Square.e2));
        assertEquals(23495, result.getChildNode(Square.d4, Square.e6));
        assertEquals(29327, result.getChildNode(Square.d4, Square.f3));
        assertEquals(26995, result.getChildNode(Square.d4, Square.f5));
        assertEquals(24169, result.getChildNode(Square.e1, Square.c1));
        assertEquals(29229, result.getChildNode(Square.e1, Square.d1));
        assertEquals(36510, result.getChildNode(Square.e1, Square.d2));
        assertEquals(37511, result.getChildNode(Square.e1, Square.e2));
        assertEquals(28367, result.getChildNode(Square.e1, Square.f1));
        assertEquals(35369, result.getChildNode(Square.e1, Square.f2));
        assertEquals(25235, result.getChildNode(Square.e1, Square.g1));
        assertEquals(25889, result.getChildNode(Square.h1, Square.f1));
        assertEquals(29693, result.getChildNode(Square.h1, Square.g1));
        assertEquals(33307, result.getChildNode(Square.h1, Square.h2));
        assertEquals(31769, result.getChildNode(Square.h1, Square.h3));
        assertEquals(26475, result.getChildNode(Square.h1, Square.h4));
        assertEquals(25429, result.getChildNode(Square.h1, Square.h5));
        assertEquals(26120, result.getChildNode(Square.h1, Square.h6));
        assertEquals(19624, result.getChildNode(Square.h1, Square.h7));
        assertEquals(2500, result.getChildNode(Square.h1, Square.h8));
        assertEquals(34, result.getMovesCount());
        assertEquals(894805, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4_a1a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 3);
        assertEquals(661, result.getChildNode(Square.a8, Square.a2));
        assertEquals(1056, result.getChildNode(Square.a8, Square.a3));
        assertEquals(1007, result.getChildNode(Square.a8, Square.a4));
        assertEquals(1003, result.getChildNode(Square.a8, Square.a5));
        assertEquals(1158, result.getChildNode(Square.a8, Square.a6));
        assertEquals(1195, result.getChildNode(Square.a8, Square.a7));
        assertEquals(1124, result.getChildNode(Square.a8, Square.b8));
        assertEquals(1124, result.getChildNode(Square.a8, Square.c8));
        assertEquals(1016, result.getChildNode(Square.a8, Square.d8));
        assertEquals(1046, result.getChildNode(Square.e5, Square.c4));
        assertEquals(1073, result.getChildNode(Square.e5, Square.c6));
        assertEquals(132, result.getChildNode(Square.e5, Square.d3));
        assertEquals(978, result.getChildNode(Square.e5, Square.d7));
        assertEquals(157, result.getChildNode(Square.e5, Square.f3));
        assertEquals(953, result.getChildNode(Square.e5, Square.f7));
        assertEquals(981, result.getChildNode(Square.e5, Square.g4));
        assertEquals(980, result.getChildNode(Square.e5, Square.g6));
        assertEquals(1001, result.getChildNode(Square.e8, Square.c8));
        assertEquals(1298, result.getChildNode(Square.e8, Square.d7));
        assertEquals(999, result.getChildNode(Square.e8, Square.d8));
        assertEquals(1284, result.getChildNode(Square.e8, Square.e7));
        assertEquals(1224, result.getChildNode(Square.e8, Square.f7));
        assertEquals(946, result.getChildNode(Square.e8, Square.f8));
        assertEquals(936, result.getChildNode(Square.e8, Square.g8));
        assertEquals(982, result.getChildNode(Square.h8, Square.f8));
        assertEquals(1082, result.getChildNode(Square.h8, Square.g8));
        assertEquals(111, result.getChildNode(Square.h8, Square.h1));
        assertEquals(915, result.getChildNode(Square.h8, Square.h2));
        assertEquals(1096, result.getChildNode(Square.h8, Square.h3));
        assertEquals(1068, result.getChildNode(Square.h8, Square.h4));
        assertEquals(1011, result.getChildNode(Square.h8, Square.h5));
        assertEquals(1198, result.getChildNode(Square.h8, Square.h6));
        assertEquals(1233, result.getChildNode(Square.h8, Square.h7));
        assertEquals(33, result.getMovesCount());
        assertEquals(32028, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4_a1a2_a8a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(37, result.getChildNode(Square.d4, Square.b3));
        assertEquals(37, result.getChildNode(Square.d4, Square.b5));
        assertEquals(32, result.getChildNode(Square.d4, Square.c2));
        assertEquals(35, result.getChildNode(Square.d4, Square.c6));
        assertEquals(34, result.getChildNode(Square.d4, Square.e2));
        assertEquals(34, result.getChildNode(Square.d4, Square.e6));
        assertEquals(37, result.getChildNode(Square.d4, Square.f3));
        assertEquals(36, result.getChildNode(Square.d4, Square.f5));
        assertEquals(37, result.getChildNode(Square.e1, Square.d1));
        assertEquals(37, result.getChildNode(Square.e1, Square.f1));
        assertEquals(34, result.getChildNode(Square.e1, Square.g1));
        assertEquals(34, result.getChildNode(Square.h1, Square.f1));
        assertEquals(36, result.getChildNode(Square.h1, Square.g1));
        assertEquals(36, result.getChildNode(Square.h1, Square.h2));
        assertEquals(35, result.getChildNode(Square.h1, Square.h3));
        assertEquals(34, result.getChildNode(Square.h1, Square.h4));
        assertEquals(33, result.getChildNode(Square.h1, Square.h5));
        assertEquals(32, result.getChildNode(Square.h1, Square.h6));
        assertEquals(28, result.getChildNode(Square.h1, Square.h7));
        assertEquals(3, result.getChildNode(Square.h1, Square.h8));
        assertEquals(20, result.getMovesCount());
        assertEquals(661, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4_a1a2_a8a2_d4b3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.d4.bitPosition(), Square.b3.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a2, Square.a1));
        assertEquals(1, result.getChildNode(Square.a2, Square.a3));
        assertEquals(1, result.getChildNode(Square.a2, Square.a4));
        assertEquals(1, result.getChildNode(Square.a2, Square.a5));
        assertEquals(1, result.getChildNode(Square.a2, Square.a6));
        assertEquals(1, result.getChildNode(Square.a2, Square.a7));
        assertEquals(1, result.getChildNode(Square.a2, Square.a8));
        assertEquals(1, result.getChildNode(Square.a2, Square.b2));
        assertEquals(1, result.getChildNode(Square.a2, Square.c2));
        assertEquals(1, result.getChildNode(Square.a2, Square.d2));
        assertEquals(1, result.getChildNode(Square.a2, Square.e2));
        assertEquals(1, result.getChildNode(Square.a2, Square.f2));
        assertEquals(1, result.getChildNode(Square.a2, Square.g2));
        assertEquals(1, result.getChildNode(Square.a2, Square.h2));
        assertEquals(1, result.getChildNode(Square.e5, Square.c4));
        assertEquals(1, result.getChildNode(Square.e5, Square.c6));
        assertEquals(1, result.getChildNode(Square.e5, Square.d3));
        assertEquals(1, result.getChildNode(Square.e5, Square.d7));
        assertEquals(1, result.getChildNode(Square.e5, Square.f3));
        assertEquals(1, result.getChildNode(Square.e5, Square.f7));
        assertEquals(1, result.getChildNode(Square.e5, Square.g4));
        assertEquals(1, result.getChildNode(Square.e5, Square.g6));
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
        assertEquals(37, result.getMovesCount());
        assertEquals(37, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 5);
        assertEquals(969348, result.getChildNode(Square.a1, Square.a2));
        assertEquals(941675, result.getChildNode(Square.a1, Square.a3));
        assertEquals(734845, result.getChildNode(Square.a1, Square.a4));
        assertEquals(759735, result.getChildNode(Square.a1, Square.a5));
        assertEquals(781848, result.getChildNode(Square.a1, Square.a6));
        assertEquals(582284, result.getChildNode(Square.a1, Square.a7));
        assertEquals(74098, result.getChildNode(Square.a1, Square.a8));
        assertEquals(894363, result.getChildNode(Square.a1, Square.b1));
        assertEquals(841539, result.getChildNode(Square.a1, Square.c1));
        assertEquals(647043, result.getChildNode(Square.a1, Square.d1));
        assertEquals(723328, result.getChildNode(Square.d4, Square.b3));
        assertEquals(726285, result.getChildNode(Square.d4, Square.b5));
        assertEquals(695991, result.getChildNode(Square.d4, Square.c2));
        assertEquals(691559, result.getChildNode(Square.d4, Square.c6));
        assertEquals(724792, result.getChildNode(Square.d4, Square.e2));
        assertEquals(673871, result.getChildNode(Square.d4, Square.e6));
        assertEquals(805082, result.getChildNode(Square.d4, Square.f3));
        assertEquals(770648, result.getChildNode(Square.d4, Square.f5));
        assertEquals(618577, result.getChildNode(Square.e1, Square.c1));
        assertEquals(833338, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1148637, result.getChildNode(Square.e1, Square.d2));
        assertEquals(1196267, result.getChildNode(Square.e1, Square.e2));
        assertEquals(791768, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1090356, result.getChildNode(Square.e1, Square.f2));
        assertEquals(683133, result.getChildNode(Square.e1, Square.g1));
        assertEquals(719351, result.getChildNode(Square.h1, Square.f1));
        assertEquals(856227, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1021119, result.getChildNode(Square.h1, Square.h2));
        assertEquals(994365, result.getChildNode(Square.h1, Square.h3));
        assertEquals(791442, result.getChildNode(Square.h1, Square.h4));
        assertEquals(771800, result.getChildNode(Square.h1, Square.h5));
        assertEquals(813763, result.getChildNode(Square.h1, Square.h6));
        assertEquals(600840, result.getChildNode(Square.h1, Square.h7));
        assertEquals(78713, result.getChildNode(Square.h1, Square.h8));
        assertEquals(34, result.getMovesCount());
        assertEquals(26048030, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_a1a2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));

        PerftResult result = perft.start(game, 4);
        assertEquals(11676, result.getChildNode(Square.a8, Square.a2));
        assertEquals(30015, result.getChildNode(Square.a8, Square.a3));
        assertEquals(29078, result.getChildNode(Square.a8, Square.a4));
        assertEquals(29688, result.getChildNode(Square.a8, Square.a5));
        assertEquals(35205, result.getChildNode(Square.a8, Square.a6));
        assertEquals(36908, result.getChildNode(Square.a8, Square.a7));
        assertEquals(34716, result.getChildNode(Square.a8, Square.b8));
        assertEquals(34597, result.getChildNode(Square.a8, Square.c8));
        assertEquals(30872, result.getChildNode(Square.a8, Square.d8));
        assertEquals(33286, result.getChildNode(Square.e5, Square.c4));
        assertEquals(35138, result.getChildNode(Square.e5, Square.c6));
        assertEquals(4112, result.getChildNode(Square.e5, Square.d3));
        assertEquals(32212, result.getChildNode(Square.e5, Square.d7));
        assertEquals(4966, result.getChildNode(Square.e5, Square.f3));
        assertEquals(31363, result.getChildNode(Square.e5, Square.f7));
        assertEquals(30985, result.getChildNode(Square.e5, Square.g4));
        assertEquals(31968, result.getChildNode(Square.e5, Square.g6));
        assertEquals(30463, result.getChildNode(Square.e8, Square.c8));
        assertEquals(41096, result.getChildNode(Square.e8, Square.d7));
        assertEquals(30989, result.getChildNode(Square.e8, Square.d8));
        assertEquals(40896, result.getChildNode(Square.e8, Square.e7));
        assertEquals(38993, result.getChildNode(Square.e8, Square.f7));
        assertEquals(29359, result.getChildNode(Square.e8, Square.f8));
        assertEquals(27930, result.getChildNode(Square.e8, Square.g8));
        assertEquals(29191, result.getChildNode(Square.h8, Square.f8));
        assertEquals(33513, result.getChildNode(Square.h8, Square.g8));
        assertEquals(2141, result.getChildNode(Square.h8, Square.h1));
        assertEquals(23066, result.getChildNode(Square.h8, Square.h2));
        assertEquals(30563, result.getChildNode(Square.h8, Square.h3));
        assertEquals(29998, result.getChildNode(Square.h8, Square.h4));
        assertEquals(29480, result.getChildNode(Square.h8, Square.h5));
        assertEquals(36505, result.getChildNode(Square.h8, Square.h6));
        assertEquals(38380, result.getChildNode(Square.h8, Square.h7));
        assertEquals(33, result.getMovesCount());
        assertEquals(969348, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_a1a2_a8a3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a3.bitPosition()));

        PerftResult result = perft.start(game, 3);
        assertEquals(913, result.getChildNode(Square.a2, Square.a1));
        assertEquals(721, result.getChildNode(Square.a2, Square.a3));
        assertEquals(1157, result.getChildNode(Square.a2, Square.b2));
        assertEquals(1119, result.getChildNode(Square.a2, Square.c2));
        assertEquals(971, result.getChildNode(Square.a2, Square.d2));
        assertEquals(791, result.getChildNode(Square.a2, Square.e2));
        assertEquals(1024, result.getChildNode(Square.a2, Square.f2));
        assertEquals(1119, result.getChildNode(Square.a2, Square.g2));
        assertEquals(893, result.getChildNode(Square.a2, Square.h2));
        assertEquals(790, result.getChildNode(Square.d4, Square.b3));
        assertEquals(965, result.getChildNode(Square.d4, Square.b5));
        assertEquals(750, result.getChildNode(Square.d4, Square.c2));
        assertEquals(957, result.getChildNode(Square.d4, Square.c6));
        assertEquals(830, result.getChildNode(Square.d4, Square.e2));
        assertEquals(936, result.getChildNode(Square.d4, Square.e6));
        assertEquals(931, result.getChildNode(Square.d4, Square.f3));
        assertEquals(1004, result.getChildNode(Square.d4, Square.f5));
        assertEquals(1091, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1001, result.getChildNode(Square.e1, Square.d2));
        assertEquals(1038, result.getChildNode(Square.e1, Square.e2));
        assertEquals(976, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1010, result.getChildNode(Square.e1, Square.f2));
        assertEquals(937, result.getChildNode(Square.e1, Square.g1));
        assertEquals(924, result.getChildNode(Square.h1, Square.f1));
        assertEquals(1011, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1094, result.getChildNode(Square.h1, Square.h2));
        assertEquals(1102, result.getChildNode(Square.h1, Square.h3));
        assertEquals(978, result.getChildNode(Square.h1, Square.h4));
        assertEquals(975, result.getChildNode(Square.h1, Square.h5));
        assertEquals(1021, result.getChildNode(Square.h1, Square.h6));
        assertEquals(878, result.getChildNode(Square.h1, Square.h7));
        assertEquals(108, result.getChildNode(Square.h1, Square.h8));
        assertEquals(32, result.getMovesCount());
        assertEquals(30015, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_a1a2_a8a3_a2f2() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a3.bitPosition()));
        game.doMove(encodeMove(Square.a2.bitPosition(), Square.f2.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(2, result.getChildNode(Square.a3, Square.a1));
        assertEquals(34, result.getChildNode(Square.a3, Square.a2));
        assertEquals(36, result.getChildNode(Square.a3, Square.a4));
        assertEquals(36, result.getChildNode(Square.a3, Square.a5));
        assertEquals(36, result.getChildNode(Square.a3, Square.a6));
        assertEquals(36, result.getChildNode(Square.a3, Square.a7));
        assertEquals(36, result.getChildNode(Square.a3, Square.a8));
        assertEquals(36, result.getChildNode(Square.a3, Square.b3));
        assertEquals(36, result.getChildNode(Square.a3, Square.c3));
        assertEquals(34, result.getChildNode(Square.a3, Square.d3));
        assertEquals(5, result.getChildNode(Square.a3, Square.e3));
        assertEquals(31, result.getChildNode(Square.a3, Square.f3));
        assertEquals(35, result.getChildNode(Square.a3, Square.g3));
        assertEquals(31, result.getChildNode(Square.a3, Square.h3));
        assertEquals(35, result.getChildNode(Square.e5, Square.c4));
        assertEquals(36, result.getChildNode(Square.e5, Square.c6));
        assertEquals(4, result.getChildNode(Square.e5, Square.d3));
        assertEquals(36, result.getChildNode(Square.e5, Square.d7));
        assertEquals(5, result.getChildNode(Square.e5, Square.f3));
        assertEquals(35, result.getChildNode(Square.e5, Square.f7));
        assertEquals(36, result.getChildNode(Square.e5, Square.g4));
        assertEquals(36, result.getChildNode(Square.e5, Square.g6));
        assertEquals(36, result.getChildNode(Square.e8, Square.d7));
        assertEquals(36, result.getChildNode(Square.e8, Square.d8));
        assertEquals(36, result.getChildNode(Square.e8, Square.e7));
        assertEquals(36, result.getChildNode(Square.h8, Square.f8));
        assertEquals(35, result.getChildNode(Square.h8, Square.g8));
        assertEquals(3, result.getChildNode(Square.h8, Square.h1));
        assertEquals(30, result.getChildNode(Square.h8, Square.h2));
        assertEquals(31, result.getChildNode(Square.h8, Square.h3));
        assertEquals(32, result.getChildNode(Square.h8, Square.h4));
        assertEquals(33, result.getChildNode(Square.h8, Square.h5));
        assertEquals(34, result.getChildNode(Square.h8, Square.h6));
        assertEquals(35, result.getChildNode(Square.h8, Square.h7));
        assertEquals(34, result.getMovesCount());
        assertEquals(1024, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_a1a2_a8a3_a2f2_a3e3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.a2.bitPosition()));
        game.doMove(encodeMove(Square.a8.bitPosition(), Square.a3.bitPosition()));
        game.doMove(encodeMove(Square.a2.bitPosition(), Square.f2.bitPosition()));
        game.doMove(encodeMove(Square.a3.bitPosition(), Square.e3.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.d4, Square.e2));
        assertEquals(1, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1, result.getChildNode(Square.e1, Square.d2));
        assertEquals(1, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1, result.getChildNode(Square.f2, Square.e2));
        assertEquals(5, result.getMovesCount());
        assertEquals(5, result.getTotalNodes());
    }
}
