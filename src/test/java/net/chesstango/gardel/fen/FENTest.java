package net.chesstango.gardel.fen;

import net.chesstango.gardel.ascii.ASCIIBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author Mauricio Coria
 */
public class FENTest {

    @Test
    public void testExportToASCII() {
        ASCIIBuilder asciiBuilder = new ASCIIBuilder();

        FEN.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
                .export(asciiBuilder);

        String position = asciiBuilder.getPositionRepresentation();

        System.out.println(position);
    }
}
