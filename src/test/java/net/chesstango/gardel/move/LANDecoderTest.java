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

        MoveCoordinates expected = MoveCoordinates.from(MoveCoordinates.Square.c7, MoveCoordinates.Square.c8, MoveCoordinates.PromotionPiece.knight);

        MoveCoordinates actual = decoder.decode("c7-c8N+", fen);

        assertEquals(expected, actual);
    }
}
