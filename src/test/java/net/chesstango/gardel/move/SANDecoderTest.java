package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class SANDecoderTest {

    private SANDecoder decoder = new SANDecoder();


    @Test
    public void test_bishop() {
        FEN fen = FEN.of("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expected = Move.of(Move.Square.c1, Move.Square.d2, Move.Piece.BISHOP_WHITE);
        Move actual = decoder.decode("Bd2", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void test_pawn_push01() {
        FEN fen = FEN.of("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expected = Move.of(Move.Square.a2, Move.Square.a3, Move.Piece.PAWN_WHITE);
        Move actual = decoder.decode("a3", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void test_pawnMove1() {
        FEN fen = FEN.of(FENParser.INITIAL_FEN);

        Move expected = Move.of(Move.Square.e2, Move.Square.e3, Move.Piece.PAWN_WHITE);
        Move actual = decoder.decode("e3", fen);

        assertEquals(expected, actual);
    }


    @Test
    public void test_pawnMove_capture() {
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
    public void test_castling() {
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
    public void test_knight_move01() {
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
    public void test_knight_move02() {
        FEN fen = FEN.of("rk2q3/ppp5/6n1/2b5/4pp2/P1N5/1PPPKPRP/R1B5 b - - 19 47");

        Move expected = null;
        Move actual = null;

        expected = Move.of(Move.Square.g6, Move.Square.h4, Move.Piece.KNIGHT_BLACK);
        actual = decoder.decode("Nh4", fen);
        assertEquals(expected, actual);
    }

    /*
    @Test
    public void test_knight_move03() {
        Game game = Game.fromFEN("rk2q3/ppp5/5p2/2b2np1/4p3/P1N1Pn2/1PPPKPRP/R1B5 b - - 3 34");

        Move expectedMove = null;
        Move decodedMove = null;

        expectedMove = game.getMove(Square.f3, Square.h4);
        decodedMove = decoder.decode("N3h4", game.getPossibleMoves());
        assertEquals(expectedMove, decodedMove);

        expectedMove = game.getMove(Square.f5, Square.h4);
        decodedMove = decoder.decode("N5h4", game.getPossibleMoves());
        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_knight_move04() {
        Game game = Game.fromFEN("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expectedMove = game.getMove(Square.g1, Square.e2);
        Move decodedMove = decoder.decode("Nge2", game.getPossibleMoves());

        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_knight_move05() {
        Game game = Game.fromFEN("rnbqk2r/pp1p1ppp/4pn2/2p5/1bPP4/2N1P3/PP3PPP/R1BQKBNR w KQkq c6 0 5");

        Move expectedMove = game.getMove(Square.g1, Square.f3);
        Move decodedMove = decoder.decode("Nf3", game.getPossibleMoves());

        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_pawnMove_promotion01() {
        Game game = Game.fromFEN("3b3k/2P5/8/8/4P3/8/PP1P1PPP/R3K2R w KQ - 0 1");

        Move expectedMove = null;
        Move decodedMove = null;

        expectedMove = game.getMove(Square.c7, Square.c8, Piece.ROOK_WHITE);
        decodedMove = decoder.decode("c8=R", game.getPossibleMoves());
        assertEquals(expectedMove, decodedMove);

        expectedMove = game.getMove(Square.c7, Square.d8, Piece.QUEEN_WHITE);
        decodedMove = decoder.decode("cxd8=Q", game.getPossibleMoves());
        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_pawnMove_promotion02() {
        Game game = Game.fromFEN("8/PR1nk2p/4p1p1/8/3p3P/5K2/8/8 w - - 9 54");

        Move expectedMove = game.getMove(Square.a7, Square.a8, Piece.QUEEN_WHITE);
        assertNotNull(expectedMove);

        Move decodedMove = decoder.decode("a8Q", game.getPossibleMoves());
        assertNotNull(decodedMove);

        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_pawnMove_promotion03() {
        Game game = Game.fromFEN("2k1r3/1p1b4/p1p5/P1P5/1P1P1p2/3B1K2/6pP/3R4 b - - 0 37");

        Move expectedMove = game.getMove(Square.g2, Square.g1, Piece.QUEEN_BLACK);
        assertNotNull(expectedMove);

        Move decodedMove = decoder.decode("g1", game.getPossibleMoves());
        assertNotNull(decodedMove);

        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_check01() {
        Game game = Game.fromFEN("6k1/5p2/p6p/1pQ3P1/2n1B3/2P1PpP1/q4r2/4R1K1 b - - 0 37");

        Move expectedMove = game.getMove(Square.f2, Square.f1);
        assertNotNull(expectedMove);

        Move decodedMove =decoder.decode("Rf1+", game.getPossibleMoves());
        assertNotNull(decodedMove);

        assertEquals(expectedMove, decodedMove);
    }

    @Test
    public void test_check02() {
        Game game = Game.fromFEN("r1bq1rk1/ppp2ppp/3p1n2/3Np3/1bPnP3/5NP1/PP1P1PBP/R1BQ1RK1 b - - 3 8");

        Move expectedMove = game.getMove(Square.d4, Square.f3);
        assertNotNull(expectedMove);

        Move decodedMove = decoder.decode("Nxf3+", game.getPossibleMoves());
        assertNotNull(decodedMove);

        assertEquals(expectedMove, decodedMove);
    }

     */
}
