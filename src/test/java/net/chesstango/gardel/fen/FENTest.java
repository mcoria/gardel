package net.chesstango.gardel.fen;

import net.chesstango.gardel.MirrorPositionBuilder;
import net.chesstango.gardel.ascii.ASCIIBuilder;
import org.junit.jupiter.api.Test;

/**
 * @author Mauricio Coria
 */
public class FENTest {

    @Test
    public void testExportToASCII() {
        FEN fen = FEN.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

        // ACSIIBuilder is a class that implements the PositionBuilder interface
        ASCIIBuilder asciiBuilder = new ASCIIBuilder();

        // Export FEN to the ASCII builder
        fen.export(asciiBuilder);

        // Get the ASCII representation of the position
        String position = asciiBuilder.getPositionRepresentation();

        System.out.println(position);
    }


    @Test
    public void testExportToASCII_Mirror() {
        FEN fen = FEN.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

        ASCIIBuilder asciiBuilder = new ASCIIBuilder();

        // MirrorPositionBuilder is a PositionBuilder that mirrors the position and wraps another PositionBuilder.
        MirrorPositionBuilder<String> mirrorPositionBuilder = new MirrorPositionBuilder<>(asciiBuilder);

        // Export FEN to the mirror position builder
        fen.export(mirrorPositionBuilder);

        // Get the ASCII representation of the position, but mirrored.
        String position = asciiBuilder.getPositionRepresentation();

        System.out.println(position);
    }
}
