package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class SANEncoderTest {

    private SANEncoder encoder = new SANEncoder();


    @Test
    public void testPawnMovePush() {
        FEN fen = FEN.of(FENParser.INITIAL_FEN);

        Move move = Move.of(Move.Square.e2, Move.Square.e3);
        String actual = encoder.encodeAlgebraicNotation(move, fen);

        assertEquals("e3", actual);
    }

    @Test
    public void testPawnMoveCapture() {
        FEN fen = FEN.of("rnbqkbnr/ppp1p1pp/8/3p1p2/2P1P3/8/PP1P1PPP/RNBQKBNR w KQkq f6 0 3");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.e4, Move.Square.f5);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("exf5", actual);

        move = Move.of(Move.Square.c4, Move.Square.d5);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("cxd5", actual);

        move = Move.of(Move.Square.e4, Move.Square.d5);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("exd5", actual);
    }


    @Test
    public void testPawnMoveCaptureEnPassant() {
        FEN fen = FEN.of("rnbqkbnr/1ppp1pp1/7p/p2PpP2/8/8/PPP1P1PP/RNBQKBNR w KQkq e6 0 5");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.d5, Move.Square.e6);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("dxe6", actual);

        move = Move.of(Move.Square.f5, Move.Square.e6);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("fxe6", actual);
    }


    @Test
    public void testPawnMovePromotion() {
        FEN fen = FEN.of("3b3k/2P5/8/8/4P3/8/PP1P1PPP/R3K2R w KQ - 0 1");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.c7, Move.Square.c8, Move.PromotionPiece.ROOK);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("c8=R", actual);

        move = Move.of(Move.Square.c7, Move.Square.d8, Move.PromotionPiece.QUEEN);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("cxd8=Q", actual);
    }

        /*
    @Test
    public void testCastling(){
        FEN fen = FEN.of("3b3k/2P5/8/8/4P3/8/PP1P1PPP/R3K2R w KQ - 0 1 ");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.e1, Move.Square.c1);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("O-O-O", actual);

        move = Move.of(Move.Square.e1, Move.Square.g1);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("O-O", actual);
    }

    @Test
    public void testKnightMove01(){
        FEN fen = FEN.of("r1k4r/ppp4p/2nb1pq1/3p1np1/4p1Q1/4P3/PPPPNPPP/RNB1K2R w KQ - 0 1");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.b1, Move.Square.c3);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("Nbc3", actual);
    }

    @Test
    public void testKnightMove02(){
        FEN fen = FEN.of("rk2q3/ppp5/6n1/2b5/4pp2/P1N5/1PPPKPRP/R1B5 b - - 19 47");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.g6, Move.Square.h4);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("Nh4", actual);
    }


    @Test
    public void testKnightMove03(){
        FEN fen = FEN.of("rk2q3/ppp5/5p2/2b2np1/4p3/P1N1Pn2/1PPPKPRP/R1B5 b - - 3 34");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.f3, Move.Square.h4);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("N3h4", actual);

        move = Move.of(Move.Square.f5, Move.Square.h4);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("N5h4", actual);
    }

    @Test
    public void testBishopMove01(){
        FEN fen = FEN.of("r3r1k1/pp1n1ppp/2p5/4Pb2/2B2P2/B1P5/P5PP/R2R2K1 w - - 0 1");

        Move move = null;
        String actual = null;

        move = Move.of(Move.Square.c4, Move.Square.e6);
        actual = encoder.encodeAlgebraicNotation(move, fen);
        assertEquals("Be6", actual);

    }

     */

}
