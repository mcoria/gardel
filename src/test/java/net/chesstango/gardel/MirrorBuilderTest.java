package net.chesstango.gardel;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENBuilder;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class MirrorBuilderTest {

    @Test
    public void mirrorChessPosition01() {
        FEN fen = FEN.of(FENParser.INITIAL_FEN);

        MirrorPositionBuilder<FEN> mirrorChessPositionBuilder = new MirrorPositionBuilder<>(new FENBuilder());

        fen.export(mirrorChessPositionBuilder);

        FEN fenMirror = mirrorChessPositionBuilder.getPositionRepresentation();

        assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1", fenMirror.toString());
    }


    @Test
    public void mirrorChessPosition02() {
        FEN fen = FEN.of("4rr1k/pppb2bp/2q1n1p1/4p3/8/1BPPBN2/PP2QPP1/2KR3R w - - 8 20");

        MirrorPositionBuilder<FEN> mirrorChessPositionBuilder = new MirrorPositionBuilder<>(new FENBuilder());

        fen.export(mirrorChessPositionBuilder);

        FEN fenMirror = mirrorChessPositionBuilder.getPositionRepresentation();

        assertEquals("2kr3r/pp2qpp1/1bppbn2/8/4P3/2Q1N1P1/PPPB2BP/4RR1K b - - 8 20", fenMirror.toString());
    }

    @Test
    public void mirrorChessPosition03() {
        FEN fen = FEN.of("r1bqkb1r/pp3ppp/2nppn2/1N6/2P1P3/2N5/PP3PPP/R1BQKB1R b KQkq - 2 7");

        MirrorPositionBuilder<FEN> mirrorChessPositionBuilder = new MirrorPositionBuilder<>(new FENBuilder());

        fen.export(mirrorChessPositionBuilder);

        FEN fenMirror = mirrorChessPositionBuilder.getPositionRepresentation();

        assertEquals("r1bqkb1r/pp3ppp/2n5/2p1p3/1n6/2NPPN2/PP3PPP/R1BQKB1R w KQkq - 2 7", fenMirror.toString());
    }

    @Test
    public void mirrorChessPosition04() {
        FEN fen = FEN.of("rn1qkbnr/pp2ppp1/2p4p/3pPb2/3P2PP/8/PPP2P2/RNBQKBNR b KQkq g3 0 5");

        MirrorPositionBuilder<FEN> mirrorChessPositionBuilder = new MirrorPositionBuilder<>(new FENBuilder());

        fen.export(mirrorChessPositionBuilder);

        FEN fenMirror = mirrorChessPositionBuilder.getPositionRepresentation();

        assertEquals("rnbqkbnr/ppp2p2/8/3p2pp/3PpB2/2P4P/PP2PPP1/RN1QKBNR w KQkq g6 0 5", fenMirror.toString());
    }
}
