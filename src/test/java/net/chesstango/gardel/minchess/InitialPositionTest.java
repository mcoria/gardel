package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class InitialPositionTest extends AbstractPerftTest {
    public static final String POSITION = FENParser.INITIAL_FEN;
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }

    @Test
    public void test_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.a2, Square.a3));
        assertEquals(1, result.getChildNode(Square.a2, Square.a4));
        assertEquals(1, result.getChildNode(Square.b2, Square.b3));
        assertEquals(1, result.getChildNode(Square.b2, Square.b4));
        assertEquals(1, result.getChildNode(Square.c2, Square.c3));
        assertEquals(1, result.getChildNode(Square.c2, Square.c4));
        assertEquals(1, result.getChildNode(Square.d2, Square.d3));
        assertEquals(1, result.getChildNode(Square.d2, Square.d4));
        assertEquals(1, result.getChildNode(Square.e2, Square.e3));
        assertEquals(1, result.getChildNode(Square.e2, Square.e4));
        assertEquals(1, result.getChildNode(Square.f2, Square.f3));
        assertEquals(1, result.getChildNode(Square.f2, Square.f4));
        assertEquals(1, result.getChildNode(Square.g2, Square.g3));
        assertEquals(1, result.getChildNode(Square.g2, Square.g4));
        assertEquals(1, result.getChildNode(Square.h2, Square.h3));
        assertEquals(1, result.getChildNode(Square.h2, Square.h4));
        assertEquals(1, result.getChildNode(Square.b1, Square.a3));
        assertEquals(1, result.getChildNode(Square.b1, Square.c3));
        assertEquals(1, result.getChildNode(Square.g1, Square.f3));
        assertEquals(1, result.getChildNode(Square.g1, Square.h3));

        assertEquals(20, result.getMovesCount());
        assertEquals(20, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);
        assertEquals(20, result.getChildNode(Square.a2, Square.a3));
        assertEquals(20, result.getChildNode(Square.a2, Square.a4));
        assertEquals(20, result.getChildNode(Square.b2, Square.b3));
        assertEquals(20, result.getChildNode(Square.b2, Square.b4));
        assertEquals(20, result.getChildNode(Square.c2, Square.c3));
        assertEquals(20, result.getChildNode(Square.c2, Square.c4));
        assertEquals(20, result.getChildNode(Square.d2, Square.d3));
        assertEquals(20, result.getChildNode(Square.d2, Square.d4));
        assertEquals(20, result.getChildNode(Square.e2, Square.e3));
        assertEquals(20, result.getChildNode(Square.e2, Square.e4));
        assertEquals(20, result.getChildNode(Square.f2, Square.f3));
        assertEquals(20, result.getChildNode(Square.f2, Square.f4));
        assertEquals(20, result.getChildNode(Square.g2, Square.g3));
        assertEquals(20, result.getChildNode(Square.g2, Square.g4));
        assertEquals(20, result.getChildNode(Square.h2, Square.h3));
        assertEquals(20, result.getChildNode(Square.h2, Square.h4));
        assertEquals(20, result.getChildNode(Square.b1, Square.a3));
        assertEquals(20, result.getChildNode(Square.b1, Square.c3));
        assertEquals(20, result.getChildNode(Square.g1, Square.f3));
        assertEquals(20, result.getChildNode(Square.g1, Square.h3));

        assertEquals(20, result.getMovesCount());
        assertEquals(400, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);
        assertEquals(380, result.getChildNode(Square.a2, Square.a3));
        assertEquals(420, result.getChildNode(Square.a2, Square.a4));
        assertEquals(420, result.getChildNode(Square.b2, Square.b3));
        assertEquals(421, result.getChildNode(Square.b2, Square.b4));
        assertEquals(420, result.getChildNode(Square.c2, Square.c3));
        assertEquals(441, result.getChildNode(Square.c2, Square.c4));
        assertEquals(539, result.getChildNode(Square.d2, Square.d3));
        assertEquals(560, result.getChildNode(Square.d2, Square.d4));
        assertEquals(599, result.getChildNode(Square.e2, Square.e3));
        assertEquals(600, result.getChildNode(Square.e2, Square.e4));
        assertEquals(380, result.getChildNode(Square.f2, Square.f3));
        assertEquals(401, result.getChildNode(Square.f2, Square.f4));
        assertEquals(420, result.getChildNode(Square.g2, Square.g3));
        assertEquals(421, result.getChildNode(Square.g2, Square.g4));
        assertEquals(380, result.getChildNode(Square.h2, Square.h3));
        assertEquals(420, result.getChildNode(Square.h2, Square.h4));
        assertEquals(400, result.getChildNode(Square.b1, Square.a3));
        assertEquals(440, result.getChildNode(Square.b1, Square.c3));
        assertEquals(440, result.getChildNode(Square.g1, Square.f3));
        assertEquals(400, result.getChildNode(Square.g1, Square.h3));

        assertEquals(20, result.getMovesCount());
        assertEquals(8902, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);
        assertEquals(8457, result.getChildNode(Square.a2, Square.a3));
        assertEquals(9329, result.getChildNode(Square.a2, Square.a4));
        assertEquals(9345, result.getChildNode(Square.b2, Square.b3));
        assertEquals(9332, result.getChildNode(Square.b2, Square.b4));
        assertEquals(9272, result.getChildNode(Square.c2, Square.c3));
        assertEquals(9744, result.getChildNode(Square.c2, Square.c4));
        assertEquals(11959, result.getChildNode(Square.d2, Square.d3));
        assertEquals(12435, result.getChildNode(Square.d2, Square.d4));
        assertEquals(13134, result.getChildNode(Square.e2, Square.e3));
        assertEquals(13160, result.getChildNode(Square.e2, Square.e4));
        assertEquals(8457, result.getChildNode(Square.f2, Square.f3));
        assertEquals(8929, result.getChildNode(Square.f2, Square.f4));
        assertEquals(9345, result.getChildNode(Square.g2, Square.g3));
        assertEquals(9328, result.getChildNode(Square.g2, Square.g4));
        assertEquals(8457, result.getChildNode(Square.h2, Square.h3));
        assertEquals(9329, result.getChildNode(Square.h2, Square.h4));
        assertEquals(8885, result.getChildNode(Square.b1, Square.a3));
        assertEquals(9755, result.getChildNode(Square.b1, Square.c3));
        assertEquals(9748, result.getChildNode(Square.g1, Square.f3));
        assertEquals(8881, result.getChildNode(Square.g1, Square.h3));

        assertEquals(20, result.getMovesCount());
        assertEquals(197281, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 5);
        assertEquals(181046, result.getChildNode(Square.a2, Square.a3));
        assertEquals(217832, result.getChildNode(Square.a2, Square.a4));
        assertEquals(215255, result.getChildNode(Square.b2, Square.b3));
        assertEquals(216145, result.getChildNode(Square.b2, Square.b4));
        assertEquals(222861, result.getChildNode(Square.c2, Square.c3));
        assertEquals(240082, result.getChildNode(Square.c2, Square.c4));
        assertEquals(328511, result.getChildNode(Square.d2, Square.d3));
        assertEquals(361790, result.getChildNode(Square.d2, Square.d4));
        assertEquals(402988, result.getChildNode(Square.e2, Square.e3));
        assertEquals(405385, result.getChildNode(Square.e2, Square.e4));
        assertEquals(178889, result.getChildNode(Square.f2, Square.f3));
        assertEquals(198473, result.getChildNode(Square.f2, Square.f4));
        assertEquals(217210, result.getChildNode(Square.g2, Square.g3));
        assertEquals(214048, result.getChildNode(Square.g2, Square.g4));
        assertEquals(181044, result.getChildNode(Square.h2, Square.h3));
        assertEquals(218829, result.getChildNode(Square.h2, Square.h4));
        assertEquals(198572, result.getChildNode(Square.b1, Square.a3));
        assertEquals(234656, result.getChildNode(Square.b1, Square.c3));
        assertEquals(233491, result.getChildNode(Square.g1, Square.f3));
        assertEquals(198502, result.getChildNode(Square.g1, Square.h3));

        assertEquals(20, result.getMovesCount());
        assertEquals(4865609, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L6() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 6);
        assertEquals(4463267, result.getChildNode(Square.a2, Square.a3));
        assertEquals(5363555, result.getChildNode(Square.a2, Square.a4));
        assertEquals(5310358, result.getChildNode(Square.b2, Square.b3));
        assertEquals(5293555, result.getChildNode(Square.b2, Square.b4));
        assertEquals(5417640, result.getChildNode(Square.c2, Square.c3));
        assertEquals(5866666, result.getChildNode(Square.c2, Square.c4));
        assertEquals(8073082, result.getChildNode(Square.d2, Square.d3));
        assertEquals(8879566, result.getChildNode(Square.d2, Square.d4));
        assertEquals(9726018, result.getChildNode(Square.e2, Square.e3));
        assertEquals(9771632, result.getChildNode(Square.e2, Square.e4));
        assertEquals(4404141, result.getChildNode(Square.f2, Square.f3));
        assertEquals(4890429, result.getChildNode(Square.f2, Square.f4));
        assertEquals(5346260, result.getChildNode(Square.g2, Square.g3));
        assertEquals(5239875, result.getChildNode(Square.g2, Square.g4));
        assertEquals(4463070, result.getChildNode(Square.h2, Square.h3));
        assertEquals(5385554, result.getChildNode(Square.h2, Square.h4));
        assertEquals(5708064, result.getChildNode(Square.b1, Square.c3));
        assertEquals(4856835, result.getChildNode(Square.b1, Square.a3));
        assertEquals(4877234, result.getChildNode(Square.g1, Square.h3));
        assertEquals(5723523, result.getChildNode(Square.g1, Square.f3));

        assertEquals(20, result.getMovesCount());
        assertEquals(119060324, result.getTotalNodes());
    }

    @Test
    @Disabled
    public void test_POS1_PERFT_L7() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 7);
        assertEquals(106743106, result.getChildNode(Square.a2, Square.a3));
        assertEquals(133233975, result.getChildNode(Square.b2, Square.b3));
        assertEquals(144074944, result.getChildNode(Square.c2, Square.c3));
        assertEquals(227598692, result.getChildNode(Square.d2, Square.d3));
        assertEquals(306138410, result.getChildNode(Square.e2, Square.e3));
        assertEquals(102021008, result.getChildNode(Square.f2, Square.f3));
        assertEquals(135987651, result.getChildNode(Square.g2, Square.g3));
        assertEquals(106678423, result.getChildNode(Square.h2, Square.h3));
        assertEquals(137077337, result.getChildNode(Square.a2, Square.a4));
        assertEquals(134087476, result.getChildNode(Square.b2, Square.b4));
        assertEquals(157756443, result.getChildNode(Square.c2, Square.c4));
        assertEquals(269605599, result.getChildNode(Square.d2, Square.d4));
        assertEquals(309478263, result.getChildNode(Square.e2, Square.e4));
        assertEquals(119614841, result.getChildNode(Square.f2, Square.f4));
        assertEquals(130293018, result.getChildNode(Square.g2, Square.g4));
        assertEquals(138495290, result.getChildNode(Square.h2, Square.h4));
        assertEquals(120142144, result.getChildNode(Square.b1, Square.a3));
        assertEquals(148527161, result.getChildNode(Square.b1, Square.c3));
        assertEquals(147678554, result.getChildNode(Square.g1, Square.f3));
        assertEquals(120669525, result.getChildNode(Square.g1, Square.h3));

        assertEquals(20, result.getMovesCount());
        assertEquals(3195901860L, result.getTotalNodes());
    }
}
