package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
