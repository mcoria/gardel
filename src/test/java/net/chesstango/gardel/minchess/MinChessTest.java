package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class MinChessTest {
    private MinChessBuilder minChessBuilder;

    @BeforeEach
    public void setup() {
        minChessBuilder = new MinChessBuilder();
    }

    @Test
    public void test() {
        FEN fen = FEN.of(FENParser.INITIAL_FEN);
        fen.export(minChessBuilder);
        MinChess minChess = minChessBuilder.getPositionRepresentation();
    }

    @Test
    public void testKing_POS1() {
        FEN fen = FEN.of("8/8/4k3/8/4K3/8/8/8 w KQkq - 0 1 ");
        fen.export(minChessBuilder);
        MinChess minChess = minChessBuilder.getPositionRepresentation();
        short[] moves = new short[64];
        int size = minChess.generateMoves(moves);

        assertEquals(5, size);
    }
}
