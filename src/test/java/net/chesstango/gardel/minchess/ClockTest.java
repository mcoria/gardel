package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FEN;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChessConstants.encodeMove;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mauricio Coria
 */
public class ClockTest {

    @Test
    public void test_START_POSITION() {
        MinChess game = MinChess.from(FEN.START_POSITION);

        assertTrue(game.getTurn());
        assertEquals(0, game.getHalfMoveClock());
        assertEquals(1, game.getFullMoveClock());

        assertEquals(FEN.START_POSITION, game.toFEN());
    }

    @Test
    public void test_START_POSITION_a2a4() {
        MinChess game = MinChess.from(FEN.START_POSITION);

        game.doMove(encodeMove(Square.e2.bitPosition(), Square.e4.bitPosition()));

        assertFalse(game.getTurn());
        assertEquals(0, game.getHalfMoveClock());
        assertEquals(1, game.getFullMoveClock());

        assertEquals(FEN.from("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"), game.toFEN());
    }
}
