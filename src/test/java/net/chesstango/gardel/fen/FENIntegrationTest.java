package net.chesstango.gardel.fen;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class FENIntegrationTest {

    @Test
    public void testTurnoWhite() {
        FENParser decoder = new FENParser();

        FENObjectBuilder encoder = new FENObjectBuilder();

        FENExporter exporter = new FENExporter(encoder);

        FEN fen = decoder.parseFEN(FEN.START_POSITION_STRING);

        exporter.export(fen);

        FEN fenResult = encoder.getPositionRepresentation();

        assertEquals(fen, fenResult);

    }

}
