package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Mauricio Coria
 */
public class SANDecoderTest {

    private SANDecoder decoder = new SANDecoder();


    @Test
    public void testBishop() {
        FEN fen = FEN.of("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expected = Move.of(Move.Square.c1, Move.Square.d2, Move.Piece.BISHOP_WHITE);
        Move actual = decoder.decode("Bd2", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void testPawnPush01() {
        FEN fen = FEN.of("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expected = Move.of(Move.Square.a2, Move.Square.a3, Move.Piece.PAWN_WHITE);
        Move actual = decoder.decode("a3", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void testPawnMove1() {
        FEN fen = FEN.of(FENParser.INITIAL_FEN);

        Move expected = Move.of(Move.Square.e2, Move.Square.e3, Move.Piece.PAWN_WHITE);
        Move actual = decoder.decode("e3", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void testPawnMoveCapture() {
        FEN fen = FEN.of("rnbqkbnr/ppp1p1pp/8/3p1p2/2P1P3/8/PP1P1PPP/RNBQKBNR w KQkq f6 0 3");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.e4, Move.Square.f5, Move.Piece.PAWN_WHITE, Move.Piece.PAWN_BLACK);
        actual = decoder.decode("exf5", fen);
        assertEquals(expected, actual);

        expected = Move.of(Move.Square.c4, Move.Square.d5, Move.Piece.PAWN_WHITE, Move.Piece.PAWN_BLACK);
        actual = decoder.decode("cxd5", fen);
        assertEquals(expected, actual);

        expected = Move.of(Move.Square.e4, Move.Square.d5, Move.Piece.PAWN_WHITE, Move.Piece.PAWN_BLACK);
        actual = decoder.decode("exd5", fen);
        assertEquals(expected, actual);
    }


    @Test
    public void testCastling() {
        FEN fen = FEN.of("3b3k/2P5/8/8/4P3/8/PP1P1PPP/R3K2R w KQ - 0 1 ");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.e1, Move.Square.c1, Move.Piece.KING_WHITE);
        actual = decoder.decode("O-O-O", fen);
        assertEquals(expected, actual);

        expected = Move.of(Move.Square.e1, Move.Square.g1, Move.Piece.KING_WHITE);
        actual = decoder.decode("O-O", fen);
        assertEquals(expected, actual);
    }

    @Test
    public void testKnightMove01() {
        FEN fen = FEN.of("r1k4r/ppp4p/2nb1pq1/3p1np1/4p1Q1/4P3/PPPPNPPP/RNB1K2R w KQ - 0 1");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.b1, Move.Square.a3, Move.Piece.KNIGHT_WHITE);
        actual = decoder.decode("Na3", fen);
        assertEquals(expected, actual);

        expected = Move.of(Move.Square.b1, Move.Square.c3, Move.Piece.KNIGHT_WHITE);
        actual = decoder.decode("Nbc3", fen);
        assertEquals(expected, actual);


        expected = Move.of(Move.Square.e2, Move.Square.c3, Move.Piece.KNIGHT_WHITE);
        actual = decoder.decode("Nec3", fen);
        assertEquals(expected, actual);
    }

    @Test
    public void testKnightMove02() {
        FEN fen = FEN.of("rk2q3/ppp5/6n1/2b5/4pp2/P1N5/1PPPKPRP/R1B5 b - - 19 47");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.g6, Move.Square.h4, Move.Piece.KNIGHT_BLACK);
        actual = decoder.decode("Nh4", fen);
        assertEquals(expected, actual);
    }

    @Test
    public void testKnightMove03() {
        FEN fen = FEN.of("rk2q3/ppp5/5p2/2b2np1/4p3/P1N1Pn2/1PPPKPRP/R1B5 b - - 3 34");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.f3, Move.Square.h4, Move.Piece.KNIGHT_BLACK);
        actual = decoder.decode("N3h4", fen);
        assertEquals(expected, actual);

        expected = Move.of(Move.Square.f5, Move.Square.h4, Move.Piece.KNIGHT_BLACK);
        actual = decoder.decode("N5h4", fen);
        assertEquals(expected, actual);
    }


    @Test
    public void testKnightMove04() {
        FEN fen = FEN.of("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expected = Move.of(Move.Square.g1, Move.Square.e2, Move.Piece.KNIGHT_WHITE);
        Move actual = decoder.decode("Nge2", fen);

        assertEquals(expected, actual);
    }

    @Test
    public void testKnightMove05() {
        FEN fen = FEN.of("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expected = Move.of(Move.Square.g1, Move.Square.f3, Move.Piece.KNIGHT_WHITE);
        Move actual = decoder.decode("Nf3", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void testPawnMovePromotion01() {
        FEN fen = FEN.of("3b3k/2P5/8/8/4P3/8/PP1P1PPP/R3K2R w KQ - 0 1");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.c7, Move.Square.c8, Move.Piece.PAWN_WHITE, Move.Piece.EMPTY, Move.PromotionPiece.ROOK);
        actual = decoder.decode("c8=R", fen);
        assertEquals(expected, actual);

        expected = Move.of(Move.Square.c7, Move.Square.d8, Move.Piece.PAWN_WHITE, Move.Piece.BISHOP_BLACK, Move.PromotionPiece.QUEEN);
        actual = decoder.decode("cxd8=Q", fen);
        assertEquals(expected, actual);
    }

    @Test
    public void testPawnMovePromotion02() {
        FEN fen = FEN.of("8/PR1nk2p/4p1p1/8/3p3P/5K2/8/8 w - - 9 54");

        Move expected = Move.of(Move.Square.a7, Move.Square.a8, Move.Piece.QUEEN_WHITE);
        assertNotNull(expected);

        Move actual = decoder.decode("a8Q", fen);
        assertNotNull(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testPawnMovePromotion03() {
        FEN fen = FEN.of("2k1r3/1p1b4/p1p5/P1P5/1P1P1p2/3B1K2/6pP/3R4 b - - 0 37");

        Move expected = Move.of(Move.Square.g2, Move.Square.g1, Move.Piece.QUEEN_BLACK);
        assertNotNull(expected);

        Move actual = decoder.decode("g1", fen);
        assertNotNull(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testCheck01() {
        FEN fen = FEN.of("6k1/5p2/p6p/1pQ3P1/2n1B3/2P1PpP1/q4r2/4R1K1 b - - 0 37");

        Move expected = Move.of(Move.Square.f2, Move.Square.f1, null);
        assertNotNull(expected);

        Move actual = decoder.decode("Rf1+", fen);
        assertNotNull(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testCheck02() {
        FEN fen = FEN.of("r1bq1rk1/ppp2ppp/3p1n2/3Np3/1bPnP3/5NP1/PP1P1PBP/R1BQ1RK1 b - - 3 8");

        Move expected = Move.of(Move.Square.d4, Move.Square.f3, null);
        assertNotNull(expected);

        Move actual = decoder.decode("Nxf3+", fen);
        assertNotNull(actual);

        assertEquals(expected, actual);
    }

}
