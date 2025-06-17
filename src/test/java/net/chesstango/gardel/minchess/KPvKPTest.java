package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
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
    public void test_POS1_PERFT_L2_a7_a8_KNIGHT() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChessConstants.PromotionPiece.KNIGHT));

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
    public void test_POS1_PERFT_L2_a7_a8_QUEEN() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.a7.bitPosition(), Square.a8.bitPosition(), MinChessConstants.PromotionPiece.QUEEN));

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
    public void test_POS1_PERFT_L2_b2_b4() {
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
}
