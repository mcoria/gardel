package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class MinChessTest extends AbstractPerftTest {
    private MinChessBuilder minChessBuilder;
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        minChessBuilder = new MinChessBuilder();
        perft = new PerftBrute();
    }

    @Test
    public void testKing_POS1() {
        FEN fen = FEN.of("8/8/4k3/8/4K3/8/8/8 w KQkq - 0 1");
        fen.export(minChessBuilder);
        MinChess minChess = minChessBuilder.getPositionRepresentation();
        short[] moves = new short[64];
        int size = minChess.generateMoves(moves);

        assertEquals(5, size);
    }


    @Test
    public void testKing_POS1_PERFT() {
        MinChess game = createGame("8/8/4k3/8/4K3/8/8/8 w KQkq - 0 1");

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1, result.getChildNode(Square.e4, Square.d3));
        assertEquals(1, result.getChildNode(Square.e4, Square.e3));
        assertEquals(1, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1, result.getChildNode(Square.e4, Square.f4));
        assertEquals(5, result.getMovesCount());
        assertEquals(5, result.getTotalNodes());
    }


    @Test
    public void testKing_POS1_PERFT_L2() {
        MinChess game = createGame("8/8/4k3/8/4K3/8/8/8 w KQkq - 0 1");

        PerftResult result = perft.start(game, 2);

        assertEquals(8, result.getChildNode(Square.e4, Square.d3));
        assertEquals(8, result.getChildNode(Square.e4, Square.e3));
        assertEquals(6, result.getChildNode(Square.e4, Square.d4));
        assertEquals(6, result.getChildNode(Square.e4, Square.f4));
        assertEquals(8, result.getChildNode(Square.e4, Square.f3));
        assertEquals(5, result.getMovesCount());
        assertEquals(36, result.getTotalNodes());
    }

    @Test
    public void testKing_POS1_PERFT_L3() {
        MinChess game = createGame("8/8/4k3/8/4K3/8/8/8 w KQkq - 0 1");

        PerftResult result = perft.start(game, 3);

        assertEquals(58, result.getChildNode(Square.e4, Square.f3));
        assertEquals(42, result.getChildNode(Square.e4, Square.f4));
        assertEquals(57, result.getChildNode(Square.e4, Square.e3));
        assertEquals(58, result.getChildNode(Square.e4, Square.d3));
        assertEquals(42, result.getChildNode(Square.e4, Square.d4));
        assertEquals(5, result.getMovesCount());
        assertEquals(257, result.getTotalNodes());
    }

    @Test
    public void testKing_POS1_PERFT_L6() {
        MinChess game = createGame("8/8/4k3/8/4K3/8/8/8 w KQkq - 0 1");

        PerftResult result = perft.start(game, 6);

        assertEquals(23876, result.getChildNode(Square.e4, Square.f3));
        assertEquals(23000, result.getChildNode(Square.e4, Square.e3));
        assertEquals(15566, result.getChildNode(Square.e4, Square.d4));
        assertEquals(15566, result.getChildNode(Square.e4, Square.f4));
        assertEquals(23876, result.getChildNode(Square.e4, Square.d3));
        assertEquals(5, result.getMovesCount());
        assertEquals(101884, result.getTotalNodes());
    }
}
