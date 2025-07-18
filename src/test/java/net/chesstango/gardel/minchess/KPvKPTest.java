package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChessConstants.encodeMove;
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

    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);

        assertEquals(17, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(14, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(17, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(14, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(19, result.getChildNode(Square.b2, Square.b3));
        assertEquals(19, result.getChildNode(Square.b2, Square.b4));
        assertEquals(17, result.getChildNode(Square.e2, Square.d1));
        assertEquals(17, result.getChildNode(Square.e2, Square.d2));
        assertEquals(17, result.getChildNode(Square.e2, Square.e1));
        assertEquals(17, result.getChildNode(Square.e2, Square.e3));
        assertEquals(17, result.getChildNode(Square.e2, Square.f1));
        assertEquals(17, result.getChildNode(Square.e2, Square.f2));
        assertEquals(17, result.getChildNode(Square.e2, Square.f3));
        assertEquals(15, result.getChildNode(Square.e4, Square.d5));
        assertEquals(14, result.getChildNode(Square.e4, Square.e5));
        assertEquals(9, result.getChildNode(Square.f5, Square.f6));
        assertEquals(18, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(275, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2_a7a8KNIGHT() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.KNIGHT));

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.a4, Square.a3));
        assertEquals(1, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1, result.getChildNode(Square.d5, Square.d4));
        assertEquals(1, result.getChildNode(Square.d5, Square.e4));
        assertEquals(1, result.getChildNode(Square.e7, Square.d6));
        assertEquals(1, result.getChildNode(Square.e7, Square.d7));
        assertEquals(1, result.getChildNode(Square.e7, Square.d8));
        assertEquals(1, result.getChildNode(Square.e7, Square.e8));
        assertEquals(1, result.getChildNode(Square.e7, Square.f6));
        assertEquals(1, result.getChildNode(Square.e7, Square.f7));
        assertEquals(1, result.getChildNode(Square.e7, Square.f8));
        assertEquals(1, result.getChildNode(Square.g7, Square.g5));
        assertEquals(1, result.getChildNode(Square.g7, Square.g6));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(17, result.getMovesCount());
        assertEquals(17, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2_a7a8_QUEEN() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.QUEEN));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a4, Square.a3));
        assertEquals(1, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1, result.getChildNode(Square.d5, Square.d4));
        assertEquals(1, result.getChildNode(Square.d5, Square.e4));
        assertEquals(1, result.getChildNode(Square.e7, Square.d6));
        assertEquals(1, result.getChildNode(Square.e7, Square.d7));
        assertEquals(1, result.getChildNode(Square.e7, Square.f6));
        assertEquals(1, result.getChildNode(Square.e7, Square.f7));
        assertEquals(1, result.getChildNode(Square.g7, Square.g5));
        assertEquals(1, result.getChildNode(Square.g7, Square.g6));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(14, result.getMovesCount());
        assertEquals(14, result.getTotalNodes());

    }

    @Test
    public void test_POS1_PERFT_L2_b2b4() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b4.bitPosition()));

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.a4, Square.a3));
        assertEquals(1, result.getChildNode(Square.a4, Square.b3));
        assertEquals(1, result.getChildNode(Square.c4, Square.b3));
        assertEquals(1, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1, result.getChildNode(Square.d5, Square.d4));
        assertEquals(1, result.getChildNode(Square.d5, Square.e4));
        assertEquals(1, result.getChildNode(Square.e7, Square.d6));
        assertEquals(1, result.getChildNode(Square.e7, Square.d7));
        assertEquals(1, result.getChildNode(Square.e7, Square.d8));
        assertEquals(1, result.getChildNode(Square.e7, Square.e8));
        assertEquals(1, result.getChildNode(Square.e7, Square.f6));
        assertEquals(1, result.getChildNode(Square.e7, Square.f7));
        assertEquals(1, result.getChildNode(Square.e7, Square.f8));
        assertEquals(1, result.getChildNode(Square.g7, Square.g5));
        assertEquals(1, result.getChildNode(Square.g7, Square.g6));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(19, result.getMovesCount());
        assertEquals(19, result.getTotalNodes());
    }


    @Test
    public void test_POS1_PERFT_L2_b2b3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b3.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a4, Square.a3));
        assertEquals(1, result.getChildNode(Square.a4, Square.b3));
        assertEquals(1, result.getChildNode(Square.c4, Square.b3));
        assertEquals(1, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1, result.getChildNode(Square.d5, Square.d4));
        assertEquals(1, result.getChildNode(Square.d5, Square.e4));
        assertEquals(1, result.getChildNode(Square.e7, Square.d6));
        assertEquals(1, result.getChildNode(Square.e7, Square.d7));
        assertEquals(1, result.getChildNode(Square.e7, Square.d8));
        assertEquals(1, result.getChildNode(Square.e7, Square.e8));
        assertEquals(1, result.getChildNode(Square.e7, Square.f6));
        assertEquals(1, result.getChildNode(Square.e7, Square.f7));
        assertEquals(1, result.getChildNode(Square.e7, Square.f8));
        assertEquals(1, result.getChildNode(Square.g7, Square.g5));
        assertEquals(1, result.getChildNode(Square.g7, Square.g6));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(19, result.getMovesCount());
        assertEquals(19, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2_e4e5() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e4.bitPosition(), Square.e5.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a4, Square.a3));
        assertEquals(1, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1, result.getChildNode(Square.d5, Square.d4));
        assertEquals(1, result.getChildNode(Square.e7, Square.d7));
        assertEquals(1, result.getChildNode(Square.e7, Square.d8));
        assertEquals(1, result.getChildNode(Square.e7, Square.e8));
        assertEquals(1, result.getChildNode(Square.e7, Square.f7));
        assertEquals(1, result.getChildNode(Square.e7, Square.f8));
        assertEquals(1, result.getChildNode(Square.g7, Square.g5));
        assertEquals(1, result.getChildNode(Square.g7, Square.g6));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(14, result.getMovesCount());
        assertEquals(14, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);
        assertEquals(264, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(371, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(246, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(328, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(324, result.getChildNode(Square.b2, Square.b3));
        assertEquals(292, result.getChildNode(Square.b2, Square.b4));
        assertEquals(232, result.getChildNode(Square.e2, Square.d1));
        assertEquals(274, result.getChildNode(Square.e2, Square.d2));
        assertEquals(231, result.getChildNode(Square.e2, Square.e1));
        assertEquals(261, result.getChildNode(Square.e2, Square.e3));
        assertEquals(217, result.getChildNode(Square.e2, Square.f1));
        assertEquals(272, result.getChildNode(Square.e2, Square.f2));
        assertEquals(258, result.getChildNode(Square.e2, Square.f3));
        assertEquals(235, result.getChildNode(Square.e4, Square.d5));
        assertEquals(220, result.getChildNode(Square.e4, Square.e5));
        assertEquals(157, result.getChildNode(Square.f5, Square.f6));
        assertEquals(309, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(4491, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_a7a8BISHOP() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.BISHOP));

        PerftResult result = perft.start(game, 2);
        assertEquals(17, result.getChildNode(Square.a4, Square.a3));
        assertEquals(17, result.getChildNode(Square.c4, Square.c3));
        assertEquals(14, result.getChildNode(Square.d5, Square.d4));
        assertEquals(14, result.getChildNode(Square.d5, Square.e4));
        assertEquals(16, result.getChildNode(Square.e7, Square.d6));
        assertEquals(16, result.getChildNode(Square.e7, Square.d7));
        assertEquals(16, result.getChildNode(Square.e7, Square.d8));
        assertEquals(16, result.getChildNode(Square.e7, Square.e8));
        assertEquals(15, result.getChildNode(Square.e7, Square.f6));
        assertEquals(16, result.getChildNode(Square.e7, Square.f7));
        assertEquals(16, result.getChildNode(Square.e7, Square.f8));
        assertEquals(18, result.getChildNode(Square.g7, Square.g5));
        assertEquals(18, result.getChildNode(Square.g7, Square.g6));
        assertEquals(15, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(13, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(15, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(12, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(17, result.getMovesCount());
        assertEquals(264, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_a7a8BISHOP_g7g5() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.BISHOP));
        game.doMove(encodeMove(Square.g7.bitPosition(), Square.g5.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a8, Square.b7));
        assertEquals(1, result.getChildNode(Square.a8, Square.c6));
        assertEquals(1, result.getChildNode(Square.a8, Square.d5));
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
        assertEquals(1, result.getChildNode(Square.f5, Square.g6));
        assertEquals(1, result.getChildNode(Square.h5, Square.g6));
        assertEquals(1, result.getChildNode(Square.h5, Square.h6));
        assertEquals(18, result.getMovesCount());
        assertEquals(18, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_b2b4() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b4.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(16, result.getChildNode(Square.a4, Square.a3));
        assertEquals(15, result.getChildNode(Square.a4, Square.b3));
        assertEquals(16, result.getChildNode(Square.c4, Square.b3));
        assertEquals(16, result.getChildNode(Square.c4, Square.c3));
        assertEquals(14, result.getChildNode(Square.d5, Square.d4));
        assertEquals(13, result.getChildNode(Square.d5, Square.e4));
        assertEquals(16, result.getChildNode(Square.e7, Square.d6));
        assertEquals(16, result.getChildNode(Square.e7, Square.d7));
        assertEquals(16, result.getChildNode(Square.e7, Square.d8));
        assertEquals(16, result.getChildNode(Square.e7, Square.e8));
        assertEquals(15, result.getChildNode(Square.e7, Square.f6));
        assertEquals(16, result.getChildNode(Square.e7, Square.f7));
        assertEquals(16, result.getChildNode(Square.e7, Square.f8));
        assertEquals(18, result.getChildNode(Square.g7, Square.g5));
        assertEquals(18, result.getChildNode(Square.g7, Square.g6));
        assertEquals(15, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(15, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(12, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(13, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(19, result.getMovesCount());
        assertEquals(292, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3_b2b4_a4b3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b4.bitPosition()));
        game.doMove(encodeMove(Square.a4.bitPosition(), Square.b3.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(1, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
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
        assertEquals(15, result.getMovesCount());
        assertEquals(15, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);
        PerftResult result = perft.start(game, 4);
        assertEquals(3948, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(4210, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(4431, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(4511, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(5492, result.getChildNode(Square.b2, Square.b3));
        assertEquals(4528, result.getChildNode(Square.b2, Square.b4));
        assertEquals(3540, result.getChildNode(Square.e2, Square.d1));
        assertEquals(4362, result.getChildNode(Square.e2, Square.d2));
        assertEquals(3526, result.getChildNode(Square.e2, Square.e1));
        assertEquals(4181, result.getChildNode(Square.e2, Square.e3));
        assertEquals(3293, result.getChildNode(Square.e2, Square.f1));
        assertEquals(4366, result.getChildNode(Square.e2, Square.f2));
        assertEquals(4097, result.getChildNode(Square.e2, Square.f3));
        assertEquals(3306, result.getChildNode(Square.e4, Square.d5));
        assertEquals(3076, result.getChildNode(Square.e4, Square.e5));
        assertEquals(2324, result.getChildNode(Square.f5, Square.f6));
        assertEquals(5100, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(68291, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);
        PerftResult result = perft.start(game, 5);
        assertEquals(53296, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(61350, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(93399, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(108971, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(85913, result.getChildNode(Square.b2, Square.b3));
        assertEquals(65411, result.getChildNode(Square.b2, Square.b4));
        assertEquals(50424, result.getChildNode(Square.e2, Square.d1));
        assertEquals(66117, result.getChildNode(Square.e2, Square.d2));
        assertEquals(50717, result.getChildNode(Square.e2, Square.e1));
        assertEquals(64544, result.getChildNode(Square.e2, Square.e3));
        assertEquals(45997, result.getChildNode(Square.e2, Square.f1));
        assertEquals(67508, result.getChildNode(Square.e2, Square.f2));
        assertEquals(63238, result.getChildNode(Square.e2, Square.f3));
        assertEquals(49353, result.getChildNode(Square.e4, Square.d5));
        assertEquals(45641, result.getChildNode(Square.e4, Square.e5));
        assertEquals(37985, result.getChildNode(Square.f5, Square.f6));
        assertEquals(82285, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(1092149, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_e2e3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e2.bitPosition(), Square.e3.bitPosition()));

        PerftResult result = perft.start(game, 4);
        assertEquals(4756, result.getChildNode(Square.a4, Square.a3));
        assertEquals(4739, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1336, result.getChildNode(Square.d5, Square.d4));
        assertEquals(3069, result.getChildNode(Square.d5, Square.e4));
        assertEquals(3887, result.getChildNode(Square.e7, Square.d6));
        assertEquals(4260, result.getChildNode(Square.e7, Square.d7));
        assertEquals(3367, result.getChildNode(Square.e7, Square.d8));
        assertEquals(3380, result.getChildNode(Square.e7, Square.e8));
        assertEquals(3089, result.getChildNode(Square.e7, Square.f6));
        assertEquals(3762, result.getChildNode(Square.e7, Square.f7));
        assertEquals(3106, result.getChildNode(Square.e7, Square.f8));
        assertEquals(3833, result.getChildNode(Square.g7, Square.g5));
        assertEquals(5110, result.getChildNode(Square.g7, Square.g6));
        assertEquals(4654, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(3279, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(5425, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(3492, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(17, result.getMovesCount());
        assertEquals(64544, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_e2e3_a4a3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e2.bitPosition(), Square.e3.bitPosition()));
        game.doMove(encodeMove(Square.a4.bitPosition(), Square.a3.bitPosition()));

        PerftResult result = perft.start(game, 3);
        assertEquals(396, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(273, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(255, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(353, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(228, result.getChildNode(Square.b2, Square.a3));
        assertEquals(273, result.getChildNode(Square.b2, Square.b3));
        assertEquals(258, result.getChildNode(Square.b2, Square.b4));
        assertEquals(302, result.getChildNode(Square.e3, Square.d2));
        assertEquals(269, result.getChildNode(Square.e3, Square.d4));
        assertEquals(310, result.getChildNode(Square.e3, Square.e2));
        assertEquals(301, result.getChildNode(Square.e3, Square.f2));
        assertEquals(285, result.getChildNode(Square.e3, Square.f3));
        assertEquals(285, result.getChildNode(Square.e3, Square.f4));
        assertEquals(265, result.getChildNode(Square.e4, Square.d5));
        assertEquals(228, result.getChildNode(Square.e4, Square.e5));
        assertEquals(157, result.getChildNode(Square.f5, Square.f6));
        assertEquals(318, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(4756, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_e2e3_a4a3_e3f4() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e2.bitPosition(), Square.e3.bitPosition()));
        game.doMove(encodeMove(Square.a4.bitPosition(), Square.a3.bitPosition()));
        game.doMove(encodeMove(Square.e3.bitPosition(), Square.f4.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(16, result.getChildNode(Square.a3, Square.a2));
        assertEquals(14, result.getChildNode(Square.a3, Square.b2));
        assertEquals(18, result.getChildNode(Square.c4, Square.c3));
        assertEquals(15, result.getChildNode(Square.d5, Square.d4));
        assertEquals(15, result.getChildNode(Square.d5, Square.e4));
        assertEquals(16, result.getChildNode(Square.e7, Square.d6));
        assertEquals(17, result.getChildNode(Square.e7, Square.d7));
        assertEquals(17, result.getChildNode(Square.e7, Square.d8));
        assertEquals(17, result.getChildNode(Square.e7, Square.e8));
        assertEquals(14, result.getChildNode(Square.e7, Square.f6));
        assertEquals(17, result.getChildNode(Square.e7, Square.f7));
        assertEquals(17, result.getChildNode(Square.e7, Square.f8));
        assertEquals(8, result.getChildNode(Square.g7, Square.g5));
        assertEquals(19, result.getChildNode(Square.g7, Square.g6));
        assertEquals(16, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(16, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(17, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(16, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(18, result.getMovesCount());
        assertEquals(285, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5_e2e3_a4a3_e3f4_g7g5() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e2.bitPosition(), Square.e3.bitPosition()));
        game.doMove(encodeMove(Square.a4.bitPosition(), Square.a3.bitPosition()));
        game.doMove(encodeMove(Square.e3.bitPosition(), Square.f4.bitPosition()));
        game.doMove(encodeMove(Square.g7.bitPosition(), Square.g5.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.f4, Square.e3));
        assertEquals(1, result.getChildNode(Square.f4, Square.e5));
        assertEquals(1, result.getChildNode(Square.f4, Square.f3));
        assertEquals(1, result.getChildNode(Square.f4, Square.g3));
        assertEquals(1, result.getChildNode(Square.f4, Square.g4));
        assertEquals(1, result.getChildNode(Square.f4, Square.g5));
        assertEquals(1, result.getChildNode(Square.f5, Square.g6));
        assertEquals(1, result.getChildNode(Square.h5, Square.g6));
        assertEquals(8, result.getMovesCount());
        assertEquals(8, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6() {
        MinChess game = createGame(POSITION);
        PerftResult result = perft.start(game, 6);
        assertEquals(1353631, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(849653, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(1328133, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(973943, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(1375250, result.getChildNode(Square.b2, Square.b3));
        assertEquals(978140, result.getChildNode(Square.b2, Square.b4));
        assertEquals(735902, result.getChildNode(Square.e2, Square.d1));
        assertEquals(1034657, result.getChildNode(Square.e2, Square.d2));
        assertEquals(743789, result.getChildNode(Square.e2, Square.e1));
        assertEquals(1031201, result.getChildNode(Square.e2, Square.e3));
        assertEquals(667296, result.getChildNode(Square.e2, Square.f1));
        assertEquals(1069243, result.getChildNode(Square.e2, Square.f2));
        assertEquals(993835, result.getChildNode(Square.e2, Square.f3));
        assertEquals(705091, result.getChildNode(Square.e4, Square.d5));
        assertEquals(646461, result.getChildNode(Square.e4, Square.e5));
        assertEquals(560871, result.getChildNode(Square.f5, Square.f6));
        assertEquals(1308682, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(16355778, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_a7a8ROOK() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.ROOK));

        PerftResult result = perft.start(game, 5);
        assertEquals(120628, result.getChildNode(Square.a4, Square.a3));
        assertEquals(110026, result.getChildNode(Square.c4, Square.c3));
        assertEquals(69013, result.getChildNode(Square.d5, Square.d4));
        assertEquals(65840, result.getChildNode(Square.d5, Square.e4));
        assertEquals(110863, result.getChildNode(Square.e7, Square.d6));
        assertEquals(101110, result.getChildNode(Square.e7, Square.d7));
        assertEquals(83150, result.getChildNode(Square.e7, Square.f6));
        assertEquals(78051, result.getChildNode(Square.e7, Square.f7));
        assertEquals(85698, result.getChildNode(Square.g7, Square.g5));
        assertEquals(114164, result.getChildNode(Square.g7, Square.g6));
        assertEquals(62928, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(141170, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(137073, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(73917, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(14, result.getMovesCount());
        assertEquals(1353631, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_a7a8ROOK_e7f6() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.ROOK));
        game.doMove(encodeMove(Square.e7.bitPosition(), Square.f6.bitPosition()));

        PerftResult result = perft.start(game, 4);
        assertEquals(3637, result.getChildNode(Square.a8, Square.a4));
        assertEquals(3687, result.getChildNode(Square.a8, Square.a5));
        assertEquals(1258, result.getChildNode(Square.a8, Square.a6));
        assertEquals(3249, result.getChildNode(Square.a8, Square.a7));
        assertEquals(4588, result.getChildNode(Square.a8, Square.b8));
        assertEquals(4354, result.getChildNode(Square.a8, Square.c8));
        assertEquals(4128, result.getChildNode(Square.a8, Square.d8));
        assertEquals(3286, result.getChildNode(Square.a8, Square.e8));
        assertEquals(874, result.getChildNode(Square.a8, Square.f8));
        assertEquals(3794, result.getChildNode(Square.a8, Square.g8));
        assertEquals(3951, result.getChildNode(Square.a8, Square.h8));
        assertEquals(5429, result.getChildNode(Square.b2, Square.b3));
        assertEquals(4596, result.getChildNode(Square.b2, Square.b4));
        assertEquals(3424, result.getChildNode(Square.e2, Square.d1));
        assertEquals(4195, result.getChildNode(Square.e2, Square.d2));
        assertEquals(3413, result.getChildNode(Square.e2, Square.e1));
        assertEquals(3954, result.getChildNode(Square.e2, Square.e3));
        assertEquals(3249, result.getChildNode(Square.e2, Square.f1));
        assertEquals(4206, result.getChildNode(Square.e2, Square.f2));
        assertEquals(3658, result.getChildNode(Square.e2, Square.f3));
        assertEquals(3837, result.getChildNode(Square.e4, Square.d5));
        assertEquals(1419, result.getChildNode(Square.e4, Square.e5));
        assertEquals(4964, result.getChildNode(Square.h5, Square.h6));
        assertEquals(23, result.getMovesCount());
        assertEquals(83150, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_a7a8ROOK_e7f6_b2b3() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.ROOK));
        game.doMove(encodeMove(Square.e7.bitPosition(), Square.f6.bitPosition()));
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b3.bitPosition()));

        PerftResult result = perft.start(game, 3);
        assertEquals(320, result.getChildNode(Square.a4, Square.a3));
        assertEquals(303, result.getChildNode(Square.a4, Square.b3));
        assertEquals(273, result.getChildNode(Square.c4, Square.b3));
        assertEquals(303, result.getChildNode(Square.c4, Square.c3));
        assertEquals(283, result.getChildNode(Square.d5, Square.d4));
        assertEquals(296, result.getChildNode(Square.d5, Square.e4));
        assertEquals(378, result.getChildNode(Square.f6, Square.e5));
        assertEquals(360, result.getChildNode(Square.f6, Square.e7));
        assertEquals(326, result.getChildNode(Square.f6, Square.f7));
        assertEquals(408, result.getChildNode(Square.f6, Square.g5));
        assertEquals(339, result.getChildNode(Square.g7, Square.g5));
        assertEquals(404, result.getChildNode(Square.g7, Square.g6));
        assertEquals(443, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(279, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(414, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(300, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(16, result.getMovesCount());
        assertEquals(5429, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_a7a8ROOK_e7f6_b2b3_f6g5() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.ROOK));
        game.doMove(encodeMove(Square.e7.bitPosition(), Square.f6.bitPosition()));
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b3.bitPosition()));
        game.doMove(encodeMove(Square.f6.bitPosition(), Square.g5.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(15, result.getChildNode(Square.a8, Square.a4));
        assertEquals(17, result.getChildNode(Square.a8, Square.a5));
        assertEquals(15, result.getChildNode(Square.a8, Square.a6));
        assertEquals(17, result.getChildNode(Square.a8, Square.a7));
        assertEquals(17, result.getChildNode(Square.a8, Square.b8));
        assertEquals(17, result.getChildNode(Square.a8, Square.c8));
        assertEquals(17, result.getChildNode(Square.a8, Square.d8));
        assertEquals(17, result.getChildNode(Square.a8, Square.e8));
        assertEquals(16, result.getChildNode(Square.a8, Square.f8));
        assertEquals(17, result.getChildNode(Square.a8, Square.g8));
        assertEquals(15, result.getChildNode(Square.a8, Square.h8));
        assertEquals(14, result.getChildNode(Square.b3, Square.a4));
        assertEquals(15, result.getChildNode(Square.b3, Square.b4));
        assertEquals(15, result.getChildNode(Square.b3, Square.c4));
        assertEquals(17, result.getChildNode(Square.e2, Square.d1));
        assertEquals(17, result.getChildNode(Square.e2, Square.d2));
        assertEquals(17, result.getChildNode(Square.e2, Square.e1));
        assertEquals(16, result.getChildNode(Square.e2, Square.e3));
        assertEquals(17, result.getChildNode(Square.e2, Square.f1));
        assertEquals(17, result.getChildNode(Square.e2, Square.f2));
        assertEquals(15, result.getChildNode(Square.e2, Square.f3));
        assertEquals(16, result.getChildNode(Square.e4, Square.d5));
        assertEquals(16, result.getChildNode(Square.e4, Square.e5));
        assertEquals(18, result.getChildNode(Square.f5, Square.f6));
        assertEquals(18, result.getChildNode(Square.h5, Square.h6));
        assertEquals(25, result.getMovesCount());
        assertEquals(408, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6_a7a8ROOK_e7f6_b2b3_f6g5_b3a4() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChess.ROOK));
        game.doMove(encodeMove(Square.e7.bitPosition(), Square.f6.bitPosition()));
        game.doMove(encodeMove(Square.b2.bitPosition(), Square.b3.bitPosition()));
        game.doMove(encodeMove(Square.f6.bitPosition(), Square.g5.bitPosition()));
        game.doMove(encodeMove(Square.b3.bitPosition(), Square.a4.bitPosition()));

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.c4, Square.c3));
        assertEquals(1, result.getChildNode(Square.d5, Square.d4));
        assertEquals(1, result.getChildNode(Square.d5, Square.e4));
        assertEquals(1, result.getChildNode(Square.g5, Square.f4));
        assertEquals(1, result.getChildNode(Square.g5, Square.f6));
        assertEquals(1, result.getChildNode(Square.g5, Square.g4));
        assertEquals(1, result.getChildNode(Square.g5, Square.h4));
        assertEquals(1, result.getChildNode(Square.g5, Square.h5));
        assertEquals(1, result.getChildNode(Square.g5, Square.h6));
        assertEquals(1, result.getChildNode(Square.g7, Square.g6));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.ROOK_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.BISHOP_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.KNIGHT_BLACK));
        assertEquals(1, result.getChildNode(Square.h2, Square.h1, Piece.QUEEN_BLACK));
        assertEquals(14, result.getMovesCount());
        assertEquals(14, result.getTotalNodes());
    }

    @Test
    @Disabled
    public void test_POS1_PERFT_L7() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 7);
        assertEquals(26067994, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(13851764, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(31259020, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(11147427, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(20922344, result.getChildNode(Square.b2, Square.b3));
        assertEquals(14055990, result.getChildNode(Square.b2, Square.b4));
        assertEquals(10796909, result.getChildNode(Square.e2, Square.d1));
        assertEquals(15468361, result.getChildNode(Square.e2, Square.d2));
        assertEquals(11075704, result.getChildNode(Square.e2, Square.e1));
        assertEquals(15833641, result.getChildNode(Square.e2, Square.e3));
        assertEquals(9864824, result.getChildNode(Square.e2, Square.f1));
        assertEquals(16445096, result.getChildNode(Square.e2, Square.f2));
        assertEquals(15356122, result.getChildNode(Square.e2, Square.f3));
        assertEquals(10462493, result.getChildNode(Square.e4, Square.d5));
        assertEquals(9505517, result.getChildNode(Square.e4, Square.e5));
        assertEquals(8957888, result.getChildNode(Square.f5, Square.f6));
        assertEquals(20915923, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(261987017, result.getTotalNodes());
    }

    @Test
    @Disabled
    public void test_POS1_PERFT_L8() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 8);
        assertEquals(180835438, result.getChildNode(Square.a7, Square.a8, Piece.KNIGHT_WHITE));
        assertEquals(223920815, result.getChildNode(Square.a7, Square.a8, Piece.BISHOP_WHITE));
        assertEquals(401057318, result.getChildNode(Square.a7, Square.a8, Piece.QUEEN_WHITE));
        assertEquals(397791194, result.getChildNode(Square.a7, Square.a8, Piece.ROOK_WHITE));
        assertEquals(329056510, result.getChildNode(Square.b2, Square.b3));
        assertEquals(210324242, result.getChildNode(Square.b2, Square.b4));
        assertEquals(155566967, result.getChildNode(Square.e2, Square.d1));
        assertEquals(243062794, result.getChildNode(Square.e2, Square.d2));
        assertEquals(161051941, result.getChildNode(Square.e2, Square.e1));
        assertEquals(256602070, result.getChildNode(Square.e2, Square.e3));
        assertEquals(141710258, result.getChildNode(Square.e2, Square.f1));
        assertEquals(261623014, result.getChildNode(Square.e2, Square.f2));
        assertEquals(243019740, result.getChildNode(Square.e2, Square.f3));
        assertEquals(154953476, result.getChildNode(Square.e4, Square.d5));
        assertEquals(138675715, result.getChildNode(Square.e4, Square.e5));
        assertEquals(133296005, result.getChildNode(Square.f5, Square.f6));
        assertEquals(330821471, result.getChildNode(Square.h5, Square.h6));
        assertEquals(17, result.getMovesCount());
        assertEquals(3963368968L, result.getTotalNodes());
    }
}
