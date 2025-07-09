package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @Test
    public void test_POS1_PERFT_L5() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 5);
        assertEquals(1038348, result.getChildNode(Square.a1, Square.b2));
        assertEquals(1045984, result.getChildNode(Square.b3, Square.a3));
        assertEquals(1296588, result.getChildNode(Square.b3, Square.b1));
        assertEquals(1020039, result.getChildNode(Square.b3, Square.b2));
        assertEquals(1252665, result.getChildNode(Square.b3, Square.b4));
        assertEquals(1198326, result.getChildNode(Square.b3, Square.b5));
        assertEquals(186204, result.getChildNode(Square.b3, Square.b6));
        assertEquals(1098067, result.getChildNode(Square.b3, Square.b7));
        assertEquals(1400502, result.getChildNode(Square.b3, Square.b8));
        assertEquals(907621, result.getChildNode(Square.c2, Square.a2));
        assertEquals(1047329, result.getChildNode(Square.c2, Square.b1));
        assertEquals(895660, result.getChildNode(Square.c2, Square.b2));
        assertEquals(1339610, result.getChildNode(Square.c2, Square.c1));
        assertEquals(1264288, result.getChildNode(Square.c2, Square.d1));
        assertEquals(1345386, result.getChildNode(Square.c2, Square.d2));
        assertEquals(1119478, result.getChildNode(Square.c2, Square.d3));
        assertEquals(1176866, result.getChildNode(Square.c2, Square.e2));
        assertEquals(1454091, result.getChildNode(Square.c2, Square.f2));
        assertEquals(1589136, result.getChildNode(Square.c3, Square.a2));
        assertEquals(1666710, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1525317, result.getChildNode(Square.c3, Square.b1));
        assertEquals(1274563, result.getChildNode(Square.c3, Square.b5));
        assertEquals(1638160, result.getChildNode(Square.c3, Square.d1));
        assertEquals(2070825, result.getChildNode(Square.c3, Square.d5));
        assertEquals(1563827, result.getChildNode(Square.c3, Square.e2));
        assertEquals(1093432, result.getChildNode(Square.e4, Square.d4));
        assertEquals(1765978, result.getChildNode(Square.e4, Square.e3));
        assertEquals(1396174, result.getChildNode(Square.e4, Square.f3));
        assertEquals(1335364, result.getChildNode(Square.g2, Square.f1));
        assertEquals(1271450, result.getChildNode(Square.g2, Square.f3));
        assertEquals(1141583, result.getChildNode(Square.g2, Square.h1));
        assertEquals(233734, result.getChildNode(Square.g2, Square.h3));
        assertEquals(32, result.getMovesCount());
        assertEquals(39653305, result.getTotalNodes());

    }

    @Test
    @Disabled
    public void test_POS1_PERFT_L6() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 6);
        assertEquals(36666051, result.getChildNode(Square.a1, Square.b2));
        assertEquals(36505685, result.getChildNode(Square.b3, Square.a3));
        assertEquals(45539047, result.getChildNode(Square.b3, Square.b1));
        assertEquals(35769596, result.getChildNode(Square.b3, Square.b2));
        assertEquals(43607084, result.getChildNode(Square.b3, Square.b4));
        assertEquals(40009704, result.getChildNode(Square.b3, Square.b5));
        assertEquals(6315178, result.getChildNode(Square.b3, Square.b6));
        assertEquals(35521665, result.getChildNode(Square.b3, Square.b7));
        assertEquals(48484024, result.getChildNode(Square.b3, Square.b8));
        assertEquals(29694396, result.getChildNode(Square.c2, Square.a2));
        assertEquals(37798546, result.getChildNode(Square.c2, Square.b1));
        assertEquals(31810893, result.getChildNode(Square.c2, Square.b2));
        assertEquals(47758518, result.getChildNode(Square.c2, Square.c1));
        assertEquals(43033202, result.getChildNode(Square.c2, Square.d1));
        assertEquals(45442176, result.getChildNode(Square.c2, Square.d2));
        assertEquals(36112497, result.getChildNode(Square.c2, Square.d3));
        assertEquals(39498874, result.getChildNode(Square.c2, Square.e2));
        assertEquals(49103373, result.getChildNode(Square.c2, Square.f2));
        assertEquals(55630531, result.getChildNode(Square.c3, Square.a2));
        assertEquals(56823801, result.getChildNode(Square.c3, Square.a4));
        assertEquals(52560441, result.getChildNode(Square.c3, Square.b1));
        assertEquals(40626002, result.getChildNode(Square.c3, Square.b5));
        assertEquals(56876536, result.getChildNode(Square.c3, Square.d1));
        assertEquals(69666932, result.getChildNode(Square.c3, Square.d5));
        assertEquals(52272762, result.getChildNode(Square.c3, Square.e2));
        assertEquals(36040208, result.getChildNode(Square.e4, Square.d4));
        assertEquals(60167040, result.getChildNode(Square.e4, Square.e3));
        assertEquals(47627374, result.getChildNode(Square.e4, Square.f3));
        assertEquals(46908196, result.getChildNode(Square.g2, Square.f1));
        assertEquals(45260359, result.getChildNode(Square.g2, Square.f3));
        assertEquals(41365439, result.getChildNode(Square.g2, Square.h1));
        assertEquals(8258117, result.getChildNode(Square.g2, Square.h3));
        assertEquals(32, result.getMovesCount());
        assertEquals(1358754247, result.getTotalNodes());

    }
}
