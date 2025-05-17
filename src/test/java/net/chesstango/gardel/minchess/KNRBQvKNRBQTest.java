package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class KNRBQvKNRBQTest extends AbstractPerftTest {
    public static final String POSITION = "7b/2q3nr/b3k3/8/4K3/1RN5/2Q3B1/B7 w - - 0 1";
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
        assertEquals(1, result.getChildNode(Square.c2, Square.a2));
        assertEquals(1, result.getChildNode(Square.c2, Square.b1));
        assertEquals(1, result.getChildNode(Square.c2, Square.b2));
        assertEquals(1, result.getChildNode(Square.c2, Square.c1));
        assertEquals(1, result.getChildNode(Square.c2, Square.d1));
        assertEquals(1, result.getChildNode(Square.c2, Square.d2));
        assertEquals(1, result.getChildNode(Square.c2, Square.d3));
        assertEquals(1, result.getChildNode(Square.c2, Square.e2));
        assertEquals(1, result.getChildNode(Square.c2, Square.f2));
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
        assertEquals(1, result.getChildNode(Square.g2, Square.f1));
        assertEquals(1, result.getChildNode(Square.g2, Square.f3));
        assertEquals(1, result.getChildNode(Square.g2, Square.h1));
        assertEquals(1, result.getChildNode(Square.g2, Square.h3));
        assertEquals(32, result.getMovesCount());
        assertEquals(32, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);
        assertEquals(40, result.getChildNode(Square.a1, Square.b2));
        assertEquals(40, result.getChildNode(Square.b3, Square.a3));
        assertEquals(40, result.getChildNode(Square.b3, Square.b1));
        assertEquals(40, result.getChildNode(Square.b3, Square.b2));
        assertEquals(40, result.getChildNode(Square.b3, Square.b4));
        assertEquals(36, result.getChildNode(Square.b3, Square.b5));
        assertEquals(6, result.getChildNode(Square.b3, Square.b6));
        assertEquals(38, result.getChildNode(Square.b3, Square.b7));
        assertEquals(40, result.getChildNode(Square.b3, Square.b8));
        assertEquals(40, result.getChildNode(Square.c2, Square.a2));
        assertEquals(40, result.getChildNode(Square.c2, Square.b1));
        assertEquals(40, result.getChildNode(Square.c2, Square.b2));
        assertEquals(40, result.getChildNode(Square.c2, Square.c1));
        assertEquals(38, result.getChildNode(Square.c2, Square.d1));
        assertEquals(38, result.getChildNode(Square.c2, Square.d2));
        assertEquals(36, result.getChildNode(Square.c2, Square.d3));
        assertEquals(39, result.getChildNode(Square.c2, Square.e2));
        assertEquals(38, result.getChildNode(Square.c2, Square.f2));
        assertEquals(40, result.getChildNode(Square.c3, Square.a2));
        assertEquals(40, result.getChildNode(Square.c3, Square.a4));
        assertEquals(40, result.getChildNode(Square.c3, Square.b1));
        assertEquals(35, result.getChildNode(Square.c3, Square.b5));
        assertEquals(40, result.getChildNode(Square.c3, Square.d1));
        assertEquals(39, result.getChildNode(Square.c3, Square.d5));
        assertEquals(39, result.getChildNode(Square.c3, Square.e2));
        assertEquals(40, result.getChildNode(Square.e4, Square.d4));
        assertEquals(41, result.getChildNode(Square.e4, Square.e3));
        assertEquals(41, result.getChildNode(Square.e4, Square.f3));
        assertEquals(40, result.getChildNode(Square.g2, Square.f1));
        assertEquals(40, result.getChildNode(Square.g2, Square.f3));
        assertEquals(40, result.getChildNode(Square.g2, Square.h1));
        assertEquals(6, result.getChildNode(Square.g2, Square.h3));
        assertEquals(32, result.getMovesCount());
        assertEquals(1190, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);
        assertEquals(968, result.getChildNode(Square.a1, Square.b2));
        assertEquals(1012, result.getChildNode(Square.b3, Square.a3));
        assertEquals(1190, result.getChildNode(Square.b3, Square.b1));
        assertEquals(998, result.getChildNode(Square.b3, Square.b2));
        assertEquals(1159, result.getChildNode(Square.b3, Square.b4));
        assertEquals(1149, result.getChildNode(Square.b3, Square.b5));
        assertEquals(188, result.getChildNode(Square.b3, Square.b6));
        assertEquals(1097, result.getChildNode(Square.b3, Square.b7));
        assertEquals(1252, result.getChildNode(Square.b3, Square.b8));
        assertEquals(1040, result.getChildNode(Square.c2, Square.a2));
        assertEquals(999, result.getChildNode(Square.c2, Square.b1));
        assertEquals(902, result.getChildNode(Square.c2, Square.b2));
        assertEquals(1192, result.getChildNode(Square.c2, Square.c1));
        assertEquals(1204, result.getChildNode(Square.c2, Square.d1));
        assertEquals(1239, result.getChildNode(Square.c2, Square.d2));
        assertEquals(1153, result.getChildNode(Square.c2, Square.d3));
        assertEquals(1171, result.getChildNode(Square.c2, Square.e2));
        assertEquals(1317, result.getChildNode(Square.c2, Square.f2));
        assertEquals(1349, result.getChildNode(Square.c3, Square.a2));
        assertEquals(1411, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1318, result.getChildNode(Square.c3, Square.b1));
        assertEquals(1198, result.getChildNode(Square.c3, Square.b5));
        assertEquals(1380, result.getChildNode(Square.c3, Square.d1));
        assertEquals(1598, result.getChildNode(Square.c3, Square.d5));
        assertEquals(1365, result.getChildNode(Square.c3, Square.e2));
        assertEquals(1097, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1427, result.getChildNode(Square.e4, Square.e3));
        assertEquals(1230, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1178, result.getChildNode(Square.g2, Square.f1));
        assertEquals(1129, result.getChildNode(Square.g2, Square.f3));
        assertEquals(1028, result.getChildNode(Square.g2, Square.h1));
        assertEquals(212, result.getChildNode(Square.g2, Square.h3));
        assertEquals(32, result.getMovesCount());
        assertEquals(36150, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);
        assertEquals(35392, result.getChildNode(Square.a1, Square.b2));
        assertEquals(35772, result.getChildNode(Square.b3, Square.a3));
        assertEquals(42501, result.getChildNode(Square.b3, Square.b1));
        assertEquals(36232, result.getChildNode(Square.b3, Square.b2));
        assertEquals(40954, result.getChildNode(Square.b3, Square.b4));
        assertEquals(37638, result.getChildNode(Square.b3, Square.b5));
        assertEquals(5896, result.getChildNode(Square.b3, Square.b6));
        assertEquals(35637, result.getChildNode(Square.b3, Square.b7));
        assertEquals(43954, result.getChildNode(Square.b3, Square.b8));
        assertEquals(31346, result.getChildNode(Square.c2, Square.a2));
        assertEquals(37148, result.getChildNode(Square.c2, Square.b1));
        assertEquals(33318, result.getChildNode(Square.c2, Square.b2));
        assertEquals(43418, result.getChildNode(Square.c2, Square.c1));
        assertEquals(39983, result.getChildNode(Square.c2, Square.d1));
        assertEquals(41091, result.getChildNode(Square.c2, Square.d2));
        assertEquals(35446, result.getChildNode(Square.c2, Square.d3));
        assertEquals(38258, result.getChildNode(Square.c2, Square.e2));
        assertEquals(44145, result.getChildNode(Square.c2, Square.f2));
        assertEquals(47688, result.getChildNode(Square.c3, Square.a2));
        assertEquals(49017, result.getChildNode(Square.c3, Square.a4));
        assertEquals(46440, result.getChildNode(Square.c3, Square.b1));
        assertEquals(37448, result.getChildNode(Square.c3, Square.b5));
        assertEquals(49015, result.getChildNode(Square.c3, Square.d1));
        assertEquals(53777, result.getChildNode(Square.c3, Square.d5));
        assertEquals(45834, result.getChildNode(Square.c3, Square.e2));
        assertEquals(36502, result.getChildNode(Square.e4, Square.d4));
        assertEquals(49353, result.getChildNode(Square.e4, Square.e3));
        assertEquals(42477, result.getChildNode(Square.e4, Square.f3));
        assertEquals(42731, result.getChildNode(Square.g2, Square.f1));
        assertEquals(41744, result.getChildNode(Square.g2, Square.f3));
        assertEquals(39092, result.getChildNode(Square.g2, Square.h1));
        assertEquals(7542, result.getChildNode(Square.g2, Square.h3));
        assertEquals(32, result.getMovesCount());
        assertEquals(1246789, result.getTotalNodes());

    }
}
