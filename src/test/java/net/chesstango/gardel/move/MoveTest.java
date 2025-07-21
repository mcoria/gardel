package net.chesstango.gardel.move;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class MoveTest {
    private Move move;

    @Test
    public void testReadMove_c7c8() {
        Move move = Move.of("c7c8");

        assertEquals(Move.of(Move.Square.c7, Move.Square.c8), move);
    }

    @Test
    public void testReadMove_c7c8_promotion() {
        Move move = Move.of("c7c8q");

        assertEquals(Move.of(Move.Square.c7, Move.Square.c8, Move.PromotionPiece.QUEEN), move);
    }
}
