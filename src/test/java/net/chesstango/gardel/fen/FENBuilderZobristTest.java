package net.chesstango.gardel.fen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class FENBuilderZobristTest {
    private FENStringBuilder fenStringBuilder;


    @BeforeEach
    public void setUp() throws Exception {
        fenStringBuilder = new FENStringBuilder();
        fenStringBuilder.setIgnoreClocks(true);
        fenStringBuilder.setIgnoreEnPassantSquareIfNotCapturePresente(true);
    }

    /**
     * Observar que b3 esta presente por que es posible movimiento de En Passant Capture
     */
    @Test
    public void test_encode_zobrist_with_EnPassantCapture() {
        FEN fen = FEN.of("5rk1/1ppb3p/2pb4/6q1/pP1r4/2PQR2P/P2B2P1/6KN b - b3 0 1");

        fen.export(fenStringBuilder);

        String fenZobrist = fenStringBuilder.getPositionRepresentation();

        assertEquals("5rk1/1ppb3p/2pb4/6q1/pP1r4/2PQR2P/P2B2P1/6KN b - b3", fenZobrist);
    }


    /**
     * Observar que b3 NO esta presente por que NO existe movimiento En Passant Capture
     */
    @Test
    public void test_encode_zobrist_without_EnPassantCapture() {
        FEN fen = FEN.of("5rk1/1ppb3p/p1pb4/6q1/1P1r4/2PQR2P/P2B2P1/6KN b - b3 0 1");

        fen.export(fenStringBuilder);

        String fenZobrist = fenStringBuilder.getPositionRepresentation();

        assertEquals("5rk1/1ppb3p/p1pb4/6q1/1P1r4/2PQR2P/P2B2P1/6KN b - -", fenZobrist);
    }


}
