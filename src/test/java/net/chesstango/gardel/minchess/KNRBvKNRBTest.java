package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class KNRBvKNRBTest extends AbstractPerftTest {
    public static final String POSITION = "7b/6nr/b3k3/8/4K3/1RN5/6B1/B7 w - - 0 1";
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }

    @Test
    public void test_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);

        assertEquals(1, result.getChildNode(Square.a1, Square.b2));
        assertEquals(1, result.getChildNode(Square.b3, Square.a3));
        assertEquals(1, result.getChildNode(Square.b3, Square.b1));
        assertEquals(1, result.getChildNode(Square.b3, Square.b2));
        assertEquals(1, result.getChildNode(Square.b3, Square.b4));
        assertEquals(1, result.getChildNode(Square.b3, Square.b5));
        assertEquals(1, result.getChildNode(Square.b3, Square.b6));
        assertEquals(1, result.getChildNode(Square.b3, Square.b7));
        assertEquals(1, result.getChildNode(Square.b3, Square.b8));
        assertEquals(1, result.getChildNode(Square.c3, Square.a2));
        assertEquals(1, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1, result.getChildNode(Square.c3, Square.b1));
        assertEquals(1, result.getChildNode(Square.c3, Square.b5));
        assertEquals(1, result.getChildNode(Square.c3, Square.d1));
        assertEquals(1, result.getChildNode(Square.c3, Square.d5));
        assertEquals(1, result.getChildNode(Square.c3, Square.e2));
        assertEquals(1, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1, result.getChildNode(Square.e4, Square.e3));
        assertEquals(1, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1, result.getChildNode(Square.e4, Square.f4));
        assertEquals(1, result.getChildNode(Square.g2, Square.f1));
        assertEquals(1, result.getChildNode(Square.g2, Square.f3));
        assertEquals(1, result.getChildNode(Square.g2, Square.h1));
        assertEquals(1, result.getChildNode(Square.g2, Square.h3));
        assertEquals(24, result.getMovesCount());
        assertEquals(24, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);

        assertEquals(21, result.getChildNode(Square.a1, Square.b2));
        assertEquals(21, result.getChildNode(Square.b3, Square.a3));
        assertEquals(21, result.getChildNode(Square.b3, Square.b1));
        assertEquals(21, result.getChildNode(Square.b3, Square.b2));
        assertEquals(21, result.getChildNode(Square.b3, Square.b4));
        assertEquals(17, result.getChildNode(Square.b3, Square.b5));
        assertEquals(3, result.getChildNode(Square.b3, Square.b6));
        assertEquals(17, result.getChildNode(Square.b3, Square.b7));
        assertEquals(21, result.getChildNode(Square.b3, Square.b8));
        assertEquals(20, result.getChildNode(Square.c3, Square.a2));
        assertEquals(20, result.getChildNode(Square.c3, Square.a4));
        assertEquals(20, result.getChildNode(Square.c3, Square.b1));
        assertEquals(15, result.getChildNode(Square.c3, Square.b5));
        assertEquals(20, result.getChildNode(Square.c3, Square.d1));
        assertEquals(19, result.getChildNode(Square.c3, Square.d5));
        assertEquals(19, result.getChildNode(Square.c3, Square.e2));
        assertEquals(22, result.getChildNode(Square.e4, Square.d4));
        assertEquals(23, result.getChildNode(Square.e4, Square.e3));
        assertEquals(23, result.getChildNode(Square.e4, Square.f3));
        assertEquals(21, result.getChildNode(Square.e4, Square.f4));
        assertEquals(21, result.getChildNode(Square.g2, Square.f1));
        assertEquals(21, result.getChildNode(Square.g2, Square.f3));
        assertEquals(21, result.getChildNode(Square.g2, Square.h1));
        assertEquals(6, result.getChildNode(Square.g2, Square.h3));
        assertEquals(24, result.getMovesCount());
        assertEquals(454, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);
        assertEquals(442, result.getChildNode(Square.a1, Square.b2));
        assertEquals(400, result.getChildNode(Square.b3, Square.a3));
        assertEquals(512, result.getChildNode(Square.b3, Square.b1));
        assertEquals(495, result.getChildNode(Square.b3, Square.b2));
        assertEquals(477, result.getChildNode(Square.b3, Square.b4));
        assertEquals(443, result.getChildNode(Square.b3, Square.b5));
        assertEquals(96, result.getChildNode(Square.b3, Square.b6));
        assertEquals(410, result.getChildNode(Square.b3, Square.b7));
        assertEquals(541, result.getChildNode(Square.b3, Square.b8));
        assertEquals(539, result.getChildNode(Square.c3, Square.a2));
        assertEquals(556, result.getChildNode(Square.c3, Square.a4));
        assertEquals(522, result.getChildNode(Square.c3, Square.b1));
        assertEquals(405, result.getChildNode(Square.c3, Square.b5));
        assertEquals(556, result.getChildNode(Square.c3, Square.d1));
        assertEquals(608, result.getChildNode(Square.c3, Square.d5));
        assertEquals(551, result.getChildNode(Square.c3, Square.e2));
        assertEquals(537, result.getChildNode(Square.e4, Square.d4));
        assertEquals(670, result.getChildNode(Square.e4, Square.e3));
        assertEquals(526, result.getChildNode(Square.e4, Square.f3));
        assertEquals(603, result.getChildNode(Square.e4, Square.f4));
        assertEquals(483, result.getChildNode(Square.g2, Square.f1));
        assertEquals(461, result.getChildNode(Square.g2, Square.f3));
        assertEquals(404, result.getChildNode(Square.g2, Square.h1));
        assertEquals(153, result.getChildNode(Square.g2, Square.h3));
        assertEquals(24, result.getMovesCount());
        assertEquals(11390, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);
        assertEquals(9815, result.getChildNode(Square.a1, Square.b2));
        assertEquals(8981, result.getChildNode(Square.b3, Square.a3));
        assertEquals(11549, result.getChildNode(Square.b3, Square.b1));
        assertEquals(11274, result.getChildNode(Square.b3, Square.b2));
        assertEquals(10684, result.getChildNode(Square.b3, Square.b4));
        assertEquals(9270, result.getChildNode(Square.b3, Square.b5));
        assertEquals(1923, result.getChildNode(Square.b3, Square.b6));
        assertEquals(8240, result.getChildNode(Square.b3, Square.b7));
        assertEquals(11799, result.getChildNode(Square.b3, Square.b8));
        assertEquals(12211, result.getChildNode(Square.c3, Square.a2));
        assertEquals(12282, result.getChildNode(Square.c3, Square.a4));
        assertEquals(11771, result.getChildNode(Square.c3, Square.b1));
        assertEquals(8185, result.getChildNode(Square.c3, Square.b5));
        assertEquals(12573, result.getChildNode(Square.c3, Square.d1));
        assertEquals(12583, result.getChildNode(Square.c3, Square.d5));
        assertEquals(11898, result.getChildNode(Square.c3, Square.e2));
        assertEquals(11504, result.getChildNode(Square.e4, Square.d4));
        assertEquals(14921, result.getChildNode(Square.e4, Square.e3));
        assertEquals(12140, result.getChildNode(Square.e4, Square.f3));
        assertEquals(13307, result.getChildNode(Square.e4, Square.f4));
        assertEquals(10562, result.getChildNode(Square.g2, Square.f1));
        assertEquals(10246, result.getChildNode(Square.g2, Square.f3));
        assertEquals(9360, result.getChildNode(Square.g2, Square.h1));
        assertEquals(3062, result.getChildNode(Square.g2, Square.h3));
        assertEquals(24, result.getMovesCount());
        assertEquals(250140, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);
        PerftResult result = perft.start(game, 5);
        assertEquals(246155, result.getChildNode(Square.a1, Square.b2));
        assertEquals(209849, result.getChildNode(Square.b3, Square.a3));
        assertEquals(285936, result.getChildNode(Square.b3, Square.b1));
        assertEquals(265769, result.getChildNode(Square.b3, Square.b2));
        assertEquals(262696, result.getChildNode(Square.b3, Square.b4));
        assertEquals(236810, result.getChildNode(Square.b3, Square.b5));
        assertEquals(53524, result.getChildNode(Square.b3, Square.b6));
        assertEquals(201490, result.getChildNode(Square.b3, Square.b7));
        assertEquals(305109, result.getChildNode(Square.b3, Square.b8));
        assertEquals(318072, result.getChildNode(Square.c3, Square.a2));
        assertEquals(326447, result.getChildNode(Square.c3, Square.a4));
        assertEquals(302185, result.getChildNode(Square.c3, Square.b1));
        assertEquals(218143, result.getChildNode(Square.c3, Square.b5));
        assertEquals(332699, result.getChildNode(Square.c3, Square.d1));
        assertEquals(366566, result.getChildNode(Square.c3, Square.d5));
        assertEquals(329563, result.getChildNode(Square.c3, Square.e2));
        assertEquals(295779, result.getChildNode(Square.e4, Square.d4));
        assertEquals(436640, result.getChildNode(Square.e4, Square.e3));
        assertEquals(313858, result.getChildNode(Square.e4, Square.f3));
        assertEquals(387851, result.getChildNode(Square.e4, Square.f4));
        assertEquals(269793, result.getChildNode(Square.g2, Square.f1));
        assertEquals(255688, result.getChildNode(Square.g2, Square.f3));
        assertEquals(218567, result.getChildNode(Square.g2, Square.h1));
        assertEquals(75944, result.getChildNode(Square.g2, Square.h3));
        assertEquals(24, result.getMovesCount());
        assertEquals(6515133, result.getTotalNodes());
    }
}
