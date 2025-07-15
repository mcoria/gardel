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
}
