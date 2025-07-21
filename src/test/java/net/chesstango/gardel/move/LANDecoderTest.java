package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class LANDecoderTest {

    private LANDecoder decoder = new LANDecoder();

    @Test
    public void testDecodeLAN01_Promotion() {
        FEN fen = FEN.of("8/kpP2r1p/p6r/n3Q1p1/P6q/1PN3p1/5P2/2RR2K1 w - - 0 1");

        Move expected = Move.of(Move.Square.c7, Move.Square.c8, Move.PromotionPiece.KNIGHT);

        Move actual = decoder.decode("c7-c8N+", fen);

        assertEquals(expected, actual);
    }

    @Test
    public void testDecodeMovePawn() {
        FEN fen = FEN.of("1Q4rr/1p1bkp2/pP6/2p1pP2/3nP1Bp/2P1q2P/7K/3R1R2 w - - 0 1");
        Move move = decoder.decode("f5-f6+", fen);
        assertEquals(Move.of(Move.Square.f5, Move.Square.f6), move);
    }

    @Test
    public void testDecodeMoveRook() {
        FEN fen = FEN.of("6k1/p1pp2pp/2p5/8/3P3q/3bP3/PP3rPP/R1K1Q2R b - - 0 1");
        Move move = decoder.decode("Rf2-c2+", fen);
        assertEquals(Move.of(Move.Square.f2, Move.Square.c2), move);
    }
}
