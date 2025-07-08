package net.chesstango.gardel.minchess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.chesstango.gardel.minchess.MinChessConstants.encodeMove;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class KiwipeteTest extends AbstractPerftTest {
    public static final String POSITION = "r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - 0 1";
    private PerftBrute perft;

    @BeforeEach
    public void setup() {
        perft = new PerftBrute();
    }

    @Test
    public void test_POS1_PERFT_L1() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 1);
        assertEquals(1, result.getChildNode(Square.e1, Square.g1));
        assertEquals(1, result.getChildNode(Square.e1, Square.c1));
        assertEquals(1, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1, result.getChildNode(Square.e1, Square.d1));

        assertEquals(1, result.getChildNode(Square.d5, Square.d6));
        assertEquals(1, result.getChildNode(Square.d5, Square.e6));

        assertEquals(1, result.getChildNode(Square.a2, Square.a3));
        assertEquals(1, result.getChildNode(Square.a2, Square.a4));

        assertEquals(1, result.getChildNode(Square.b2, Square.b3));

        assertEquals(1, result.getChildNode(Square.g2, Square.g3));
        assertEquals(1, result.getChildNode(Square.g2, Square.g4));
        assertEquals(1, result.getChildNode(Square.g2, Square.h3));

        assertEquals(1, result.getChildNode(Square.e5, Square.d3));
        assertEquals(1, result.getChildNode(Square.e5, Square.f7));
        assertEquals(1, result.getChildNode(Square.e5, Square.c4));
        assertEquals(1, result.getChildNode(Square.e5, Square.g6));
        assertEquals(1, result.getChildNode(Square.e5, Square.g4));
        assertEquals(1, result.getChildNode(Square.e5, Square.c6));
        assertEquals(1, result.getChildNode(Square.e5, Square.d7));

        assertEquals(1, result.getChildNode(Square.c3, Square.b1));
        assertEquals(1, result.getChildNode(Square.c3, Square.a4));
        assertEquals(1, result.getChildNode(Square.c3, Square.d1));
        assertEquals(1, result.getChildNode(Square.c3, Square.b5));

        assertEquals(1, result.getChildNode(Square.f3, Square.g3));
        assertEquals(1, result.getChildNode(Square.f3, Square.h3));
        assertEquals(1, result.getChildNode(Square.f3, Square.e3));
        assertEquals(1, result.getChildNode(Square.f3, Square.d3));
        assertEquals(1, result.getChildNode(Square.f3, Square.g4));
        assertEquals(1, result.getChildNode(Square.f3, Square.h5));
        assertEquals(1, result.getChildNode(Square.f3, Square.f4));
        assertEquals(1, result.getChildNode(Square.f3, Square.f5));
        assertEquals(1, result.getChildNode(Square.f3, Square.f6));

        assertEquals(1, result.getChildNode(Square.d2, Square.c1));
        assertEquals(1, result.getChildNode(Square.d2, Square.e3));
        assertEquals(1, result.getChildNode(Square.d2, Square.f4));
        assertEquals(1, result.getChildNode(Square.d2, Square.g5));
        assertEquals(1, result.getChildNode(Square.d2, Square.h6));

        assertEquals(1, result.getChildNode(Square.e2, Square.d1));
        assertEquals(1, result.getChildNode(Square.e2, Square.f1));
        assertEquals(1, result.getChildNode(Square.e2, Square.d3));
        assertEquals(1, result.getChildNode(Square.e2, Square.c4));
        assertEquals(1, result.getChildNode(Square.e2, Square.b5));
        assertEquals(1, result.getChildNode(Square.e2, Square.a6));

        assertEquals(1, result.getChildNode(Square.a1, Square.b1));
        assertEquals(1, result.getChildNode(Square.a1, Square.c1));
        assertEquals(1, result.getChildNode(Square.a1, Square.d1));

        assertEquals(1, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1, result.getChildNode(Square.h1, Square.f1));

        assertEquals(48, result.getMovesCount());
        assertEquals(48, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L2() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 2);
        assertEquals(43, result.getChildNode(Square.e1, Square.g1));
        assertEquals(43, result.getChildNode(Square.e1, Square.c1));
        assertEquals(43, result.getChildNode(Square.e1, Square.f1));
        assertEquals(43, result.getChildNode(Square.e1, Square.d1));

        assertEquals(41, result.getChildNode(Square.d5, Square.d6));
        assertEquals(46, result.getChildNode(Square.d5, Square.e6));

        assertEquals(44, result.getChildNode(Square.a2, Square.a3));
        assertEquals(44, result.getChildNode(Square.a2, Square.a4));

        assertEquals(42, result.getChildNode(Square.b2, Square.b3));

        assertEquals(42, result.getChildNode(Square.g2, Square.g3));
        assertEquals(42, result.getChildNode(Square.g2, Square.g4));
        assertEquals(43, result.getChildNode(Square.g2, Square.h3));

        assertEquals(43, result.getChildNode(Square.e5, Square.d3));
        assertEquals(44, result.getChildNode(Square.e5, Square.f7));
        assertEquals(42, result.getChildNode(Square.e5, Square.c4));
        assertEquals(42, result.getChildNode(Square.e5, Square.g6));
        assertEquals(44, result.getChildNode(Square.e5, Square.g4));
        assertEquals(41, result.getChildNode(Square.e5, Square.c6));
        assertEquals(45, result.getChildNode(Square.e5, Square.d7));

        assertEquals(42, result.getChildNode(Square.c3, Square.b1));
        assertEquals(42, result.getChildNode(Square.c3, Square.a4));
        assertEquals(42, result.getChildNode(Square.c3, Square.d1));
        assertEquals(39, result.getChildNode(Square.c3, Square.b5));

        assertEquals(43, result.getChildNode(Square.f3, Square.g3));
        assertEquals(43, result.getChildNode(Square.f3, Square.h3));
        assertEquals(43, result.getChildNode(Square.f3, Square.e3));
        assertEquals(42, result.getChildNode(Square.f3, Square.d3));
        assertEquals(43, result.getChildNode(Square.f3, Square.g4));
        assertEquals(43, result.getChildNode(Square.f3, Square.h5));
        assertEquals(43, result.getChildNode(Square.f3, Square.f4));
        assertEquals(45, result.getChildNode(Square.f3, Square.f5));
        assertEquals(39, result.getChildNode(Square.f3, Square.f6));

        assertEquals(43, result.getChildNode(Square.d2, Square.c1));
        assertEquals(43, result.getChildNode(Square.d2, Square.e3));
        assertEquals(43, result.getChildNode(Square.d2, Square.f4));
        assertEquals(42, result.getChildNode(Square.d2, Square.g5));
        assertEquals(41, result.getChildNode(Square.d2, Square.h6));

        assertEquals(44, result.getChildNode(Square.e2, Square.d1));
        assertEquals(44, result.getChildNode(Square.e2, Square.f1));
        assertEquals(42, result.getChildNode(Square.e2, Square.d3));
        assertEquals(41, result.getChildNode(Square.e2, Square.c4));
        assertEquals(39, result.getChildNode(Square.e2, Square.b5));
        assertEquals(36, result.getChildNode(Square.e2, Square.a6));

        assertEquals(43, result.getChildNode(Square.a1, Square.b1));
        assertEquals(43, result.getChildNode(Square.a1, Square.c1));
        assertEquals(43, result.getChildNode(Square.a1, Square.d1));

        assertEquals(43, result.getChildNode(Square.h1, Square.g1));
        assertEquals(43, result.getChildNode(Square.h1, Square.f1));

        assertEquals(48, result.getMovesCount());
        assertEquals(2039, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L3() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 3);
        assertEquals(2059, result.getChildNode(Square.e1, Square.g1));
        assertEquals(1887, result.getChildNode(Square.e1, Square.c1));
        assertEquals(1855, result.getChildNode(Square.e1, Square.f1));
        assertEquals(1894, result.getChildNode(Square.e1, Square.d1));
        assertEquals(1991, result.getChildNode(Square.d5, Square.d6));
        assertEquals(2241, result.getChildNode(Square.d5, Square.e6));
        assertEquals(2186, result.getChildNode(Square.a2, Square.a3));
        assertEquals(2149, result.getChildNode(Square.a2, Square.a4));
        assertEquals(1964, result.getChildNode(Square.b2, Square.b3));
        assertEquals(1882, result.getChildNode(Square.g2, Square.g3));
        assertEquals(1843, result.getChildNode(Square.g2, Square.g4));
        assertEquals(1970, result.getChildNode(Square.g2, Square.h3));
        assertEquals(1803, result.getChildNode(Square.e5, Square.d3));
        assertEquals(2080, result.getChildNode(Square.e5, Square.f7));
        assertEquals(1880, result.getChildNode(Square.e5, Square.c4));
        assertEquals(1997, result.getChildNode(Square.e5, Square.g6));
        assertEquals(1878, result.getChildNode(Square.e5, Square.g4));
        assertEquals(2027, result.getChildNode(Square.e5, Square.c6));
        assertEquals(2124, result.getChildNode(Square.e5, Square.d7));
        assertEquals(2038, result.getChildNode(Square.c3, Square.b1));
        assertEquals(2203, result.getChildNode(Square.c3, Square.a4));
        assertEquals(2040, result.getChildNode(Square.c3, Square.d1));
        assertEquals(2138, result.getChildNode(Square.c3, Square.b5));
        assertEquals(2214, result.getChildNode(Square.f3, Square.g3));
        assertEquals(2360, result.getChildNode(Square.f3, Square.h3));
        assertEquals(2174, result.getChildNode(Square.f3, Square.e3));
        assertEquals(2005, result.getChildNode(Square.f3, Square.d3));
        assertEquals(2169, result.getChildNode(Square.f3, Square.g4));
        assertEquals(2267, result.getChildNode(Square.f3, Square.h5));
        assertEquals(2132, result.getChildNode(Square.f3, Square.f4));
        assertEquals(2396, result.getChildNode(Square.f3, Square.f5));
        assertEquals(2111, result.getChildNode(Square.f3, Square.f6));
        assertEquals(1963, result.getChildNode(Square.d2, Square.c1));
        assertEquals(2136, result.getChildNode(Square.d2, Square.e3));
        assertEquals(2000, result.getChildNode(Square.d2, Square.f4));
        assertEquals(2134, result.getChildNode(Square.d2, Square.g5));
        assertEquals(2019, result.getChildNode(Square.d2, Square.h6));
        assertEquals(1733, result.getChildNode(Square.e2, Square.d1));
        assertEquals(2060, result.getChildNode(Square.e2, Square.f1));
        assertEquals(2050, result.getChildNode(Square.e2, Square.d3));
        assertEquals(2082, result.getChildNode(Square.e2, Square.c4));
        assertEquals(2057, result.getChildNode(Square.e2, Square.b5));
        assertEquals(1907, result.getChildNode(Square.e2, Square.a6));
        assertEquals(1969, result.getChildNode(Square.a1, Square.b1));
        assertEquals(1968, result.getChildNode(Square.a1, Square.c1));
        assertEquals(1885, result.getChildNode(Square.a1, Square.d1));
        assertEquals(2013, result.getChildNode(Square.h1, Square.g1));
        assertEquals(1929, result.getChildNode(Square.h1, Square.f1));

        assertEquals(48, result.getMovesCount());
        assertEquals(97862, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4() {
        MinChess game = createGame(POSITION);

        PerftResult result = perft.start(game, 4);
        assertEquals(86975, result.getChildNode(Square.e1, Square.g1));
        assertEquals(79803, result.getChildNode(Square.e1, Square.c1));
        assertEquals(77887, result.getChildNode(Square.e1, Square.f1));
        assertEquals(79989, result.getChildNode(Square.e1, Square.d1));
        assertEquals(79551, result.getChildNode(Square.d5, Square.d6));
        assertEquals(97464, result.getChildNode(Square.d5, Square.e6));
        assertEquals(94405, result.getChildNode(Square.a2, Square.a3));
        assertEquals(90978, result.getChildNode(Square.a2, Square.a4));
        assertEquals(81066, result.getChildNode(Square.b2, Square.b3));
        assertEquals(77468, result.getChildNode(Square.g2, Square.g3));
        assertEquals(75677, result.getChildNode(Square.g2, Square.g4));
        assertEquals(82759, result.getChildNode(Square.g2, Square.h3));
        assertEquals(77431, result.getChildNode(Square.e5, Square.d3));
        assertEquals(88799, result.getChildNode(Square.e5, Square.f7));
        assertEquals(77752, result.getChildNode(Square.e5, Square.c4));
        assertEquals(83866, result.getChildNode(Square.e5, Square.g6));
        assertEquals(79912, result.getChildNode(Square.e5, Square.g4));
        assertEquals(83885, result.getChildNode(Square.e5, Square.c6));
        assertEquals(93913, result.getChildNode(Square.e5, Square.d7));
        assertEquals(84773, result.getChildNode(Square.c3, Square.b1));
        assertEquals(91447, result.getChildNode(Square.c3, Square.a4));
        assertEquals(84782, result.getChildNode(Square.c3, Square.d1));
        assertEquals(81498, result.getChildNode(Square.c3, Square.b5));
        assertEquals(94461, result.getChildNode(Square.f3, Square.g3));
        assertEquals(98524, result.getChildNode(Square.f3, Square.h3));
        assertEquals(92505, result.getChildNode(Square.f3, Square.e3));
        assertEquals(83727, result.getChildNode(Square.f3, Square.d3));
        assertEquals(92037, result.getChildNode(Square.f3, Square.g4));
        assertEquals(95034, result.getChildNode(Square.f3, Square.h5));
        assertEquals(90488, result.getChildNode(Square.f3, Square.f4));
        assertEquals(104992, result.getChildNode(Square.f3, Square.f5));
        assertEquals(77838, result.getChildNode(Square.f3, Square.f6));
        assertEquals(83037, result.getChildNode(Square.d2, Square.c1));
        assertEquals(90274, result.getChildNode(Square.d2, Square.e3));
        assertEquals(84869, result.getChildNode(Square.d2, Square.f4));
        assertEquals(87951, result.getChildNode(Square.d2, Square.g5));
        assertEquals(82323, result.getChildNode(Square.d2, Square.h6));
        assertEquals(74963, result.getChildNode(Square.e2, Square.d1));
        assertEquals(88728, result.getChildNode(Square.e2, Square.f1));
        assertEquals(85119, result.getChildNode(Square.e2, Square.d3));
        assertEquals(84835, result.getChildNode(Square.e2, Square.c4));
        assertEquals(79739, result.getChildNode(Square.e2, Square.b5));
        assertEquals(69334, result.getChildNode(Square.e2, Square.a6));
        assertEquals(83348, result.getChildNode(Square.a1, Square.b1));
        assertEquals(83263, result.getChildNode(Square.a1, Square.c1));
        assertEquals(79695, result.getChildNode(Square.a1, Square.d1));
        assertEquals(84876, result.getChildNode(Square.h1, Square.g1));
        assertEquals(81563, result.getChildNode(Square.h1, Square.f1));

        assertEquals(48, result.getMovesCount());
        assertEquals(4085603, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4_e5f7() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e5.bitPosition(), Square.f7.bitPosition()));

        PerftResult result = perft.start(game, 3);
        assertEquals(2084, result.getChildNode(Square.a6, Square.b5));
        assertEquals(2050, result.getChildNode(Square.a6, Square.b7));
        assertEquals(2046, result.getChildNode(Square.a6, Square.c4));
        assertEquals(1809, result.getChildNode(Square.a6, Square.c8));
        assertEquals(2037, result.getChildNode(Square.a6, Square.d3));
        assertEquals(1854, result.getChildNode(Square.a6, Square.e2));
        assertEquals(2126, result.getChildNode(Square.a8, Square.b8));
        assertEquals(1986, result.getChildNode(Square.a8, Square.c8));
        assertEquals(2025, result.getChildNode(Square.a8, Square.d8));
        assertEquals(2171, result.getChildNode(Square.b4, Square.b3));
        assertEquals(2120, result.getChildNode(Square.b4, Square.c3));
        assertEquals(1987, result.getChildNode(Square.b6, Square.a4));
        assertEquals(2002, result.getChildNode(Square.b6, Square.c4));
        assertEquals(1799, result.getChildNode(Square.b6, Square.c8));
        assertEquals(1941, result.getChildNode(Square.b6, Square.d5));
        assertEquals(1983, result.getChildNode(Square.c7, Square.c5));
        assertEquals(2078, result.getChildNode(Square.c7, Square.c6));
        assertEquals(2005, result.getChildNode(Square.d7, Square.d6));
        assertEquals(2086, result.getChildNode(Square.e6, Square.d5));
        assertEquals(1908, result.getChildNode(Square.e6, Square.e5));
        assertEquals(2368, result.getChildNode(Square.e7, Square.c5));
        assertEquals(2167, result.getChildNode(Square.e7, Square.d6));
        assertEquals(1929, result.getChildNode(Square.e7, Square.d8));
        assertEquals(1817, result.getChildNode(Square.e7, Square.f7));
        assertEquals(1895, result.getChildNode(Square.e7, Square.f8));
        assertEquals(1836, result.getChildNode(Square.e8, Square.f7));
        assertEquals(2023, result.getChildNode(Square.e8, Square.f8));
        assertEquals(1988, result.getChildNode(Square.e8, Square.g8));
        assertEquals(2129, result.getChildNode(Square.f6, Square.d5));
        assertEquals(2455, result.getChildNode(Square.f6, Square.e4));
        assertEquals(2214, result.getChildNode(Square.f6, Square.g4));
        assertEquals(1998, result.getChildNode(Square.f6, Square.g8));
        assertEquals(2088, result.getChildNode(Square.f6, Square.h5));
        assertEquals(1998, result.getChildNode(Square.f6, Square.h7));
        assertEquals(1991, result.getChildNode(Square.g6, Square.g5));
        assertEquals(1858, result.getChildNode(Square.g7, Square.f8));
        assertEquals(2074, result.getChildNode(Square.g7, Square.h6));
        assertEquals(2331, result.getChildNode(Square.h3, Square.g2));
        assertEquals(1763, result.getChildNode(Square.h8, Square.f8));
        assertEquals(1812, result.getChildNode(Square.h8, Square.g8));
        assertEquals(2084, result.getChildNode(Square.h8, Square.h4));
        assertEquals(2081, result.getChildNode(Square.h8, Square.h5));
        assertEquals(1900, result.getChildNode(Square.h8, Square.h6));
        assertEquals(1903, result.getChildNode(Square.h8, Square.h7));
        assertEquals(44, result.getMovesCount());
        assertEquals(88799, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4_e5f7_e8f7() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e5.bitPosition(), Square.f7.bitPosition()));
        game.doMove(encodeMove(Square.e8.bitPosition(), Square.f7.bitPosition()));

        PerftResult result = perft.start(game, 2);
        assertEquals(45, result.getChildNode(Square.a1, Square.b1));
        assertEquals(45, result.getChildNode(Square.a1, Square.c1));
        assertEquals(45, result.getChildNode(Square.a1, Square.d1));
        assertEquals(46, result.getChildNode(Square.a2, Square.a3));
        assertEquals(46, result.getChildNode(Square.a2, Square.a4));
        assertEquals(44, result.getChildNode(Square.b2, Square.b3));
        assertEquals(44, result.getChildNode(Square.c3, Square.a4));
        assertEquals(44, result.getChildNode(Square.c3, Square.b1));
        assertEquals(41, result.getChildNode(Square.c3, Square.b5));
        assertEquals(44, result.getChildNode(Square.c3, Square.d1));
        assertEquals(45, result.getChildNode(Square.d2, Square.c1));
        assertEquals(45, result.getChildNode(Square.d2, Square.e3));
        assertEquals(52, result.getChildNode(Square.d2, Square.f4));
        assertEquals(44, result.getChildNode(Square.d2, Square.g5));
        assertEquals(43, result.getChildNode(Square.d2, Square.h6));
        assertEquals(43, result.getChildNode(Square.d5, Square.d6));
        assertEquals(6, result.getChildNode(Square.d5, Square.e6));
        assertEquals(45, result.getChildNode(Square.e1, Square.c1));
        assertEquals(45, result.getChildNode(Square.e1, Square.d1));
        assertEquals(45, result.getChildNode(Square.e1, Square.f1));
        assertEquals(45, result.getChildNode(Square.e1, Square.g1));
        assertEquals(39, result.getChildNode(Square.e2, Square.a6));
        assertEquals(42, result.getChildNode(Square.e2, Square.b5));
        assertEquals(43, result.getChildNode(Square.e2, Square.c4));
        assertEquals(46, result.getChildNode(Square.e2, Square.d1));
        assertEquals(44, result.getChildNode(Square.e2, Square.d3));
        assertEquals(46, result.getChildNode(Square.e2, Square.f1));
        assertEquals(44, result.getChildNode(Square.e4, Square.e5));
        assertEquals(51, result.getChildNode(Square.f3, Square.d3));
        assertEquals(52, result.getChildNode(Square.f3, Square.e3));
        assertEquals(45, result.getChildNode(Square.f3, Square.f4));
        assertEquals(47, result.getChildNode(Square.f3, Square.f5));
        assertEquals(5, result.getChildNode(Square.f3, Square.f6));
        assertEquals(52, result.getChildNode(Square.f3, Square.g3));
        assertEquals(52, result.getChildNode(Square.f3, Square.g4));
        assertEquals(52, result.getChildNode(Square.f3, Square.h3));
        assertEquals(51, result.getChildNode(Square.f3, Square.h5));
        assertEquals(44, result.getChildNode(Square.g2, Square.g3));
        assertEquals(44, result.getChildNode(Square.g2, Square.g4));
        assertEquals(45, result.getChildNode(Square.g2, Square.h3));
        assertEquals(45, result.getChildNode(Square.h1, Square.f1));
        assertEquals(45, result.getChildNode(Square.h1, Square.g1));
        assertEquals(42, result.getMovesCount());
        assertEquals(1836, result.getTotalNodes());
    }

    @Test
    public void test_POS1_PERFT_L4_e5f7_e8f7_a1b1() {
        MinChess game = createGame(POSITION);
        game.doMove(encodeMove(Square.e5.bitPosition(), Square.f7.bitPosition()));
        game.doMove(encodeMove(Square.e8.bitPosition(), Square.f7.bitPosition()));
        game.doMove(encodeMove(Square.a1.bitPosition(), Square.b1.bitPosition()));

        PerftResult result = perft.start(game, 1);

        // Alfil a6
        assertEquals(1, result.getChildNode(Square.a6, Square.b5));
        assertEquals(1, result.getChildNode(Square.a6, Square.b7));
        assertEquals(1, result.getChildNode(Square.a6, Square.c4));
        assertEquals(1, result.getChildNode(Square.a6, Square.c8));
        assertEquals(1, result.getChildNode(Square.a6, Square.d3));
        assertEquals(1, result.getChildNode(Square.a6, Square.e2));

        // Torre a8
        assertEquals(1, result.getChildNode(Square.a8, Square.b8));
        assertEquals(1, result.getChildNode(Square.a8, Square.c8));
        assertEquals(1, result.getChildNode(Square.a8, Square.d8));
        assertEquals(1, result.getChildNode(Square.a8, Square.e8));
        assertEquals(1, result.getChildNode(Square.a8, Square.f8));
        assertEquals(1, result.getChildNode(Square.a8, Square.g8));

        // Torre h8
        assertEquals(1, result.getChildNode(Square.h8, Square.b8));
        assertEquals(1, result.getChildNode(Square.h8, Square.c8));
        assertEquals(1, result.getChildNode(Square.h8, Square.d8));
        assertEquals(1, result.getChildNode(Square.h8, Square.e8));
        assertEquals(1, result.getChildNode(Square.h8, Square.f8));
        assertEquals(1, result.getChildNode(Square.h8, Square.g8));
        assertEquals(1, result.getChildNode(Square.h8, Square.h4));
        assertEquals(1, result.getChildNode(Square.h8, Square.h5));
        assertEquals(1, result.getChildNode(Square.h8, Square.h6));
        assertEquals(1, result.getChildNode(Square.h8, Square.h7));

        // Peon b4
        assertEquals(1, result.getChildNode(Square.b4, Square.b3));
        assertEquals(1, result.getChildNode(Square.b4, Square.c3));

        // Peon c7
        assertEquals(1, result.getChildNode(Square.c7, Square.c5));
        assertEquals(1, result.getChildNode(Square.c7, Square.c6));

        // Peon d7
        assertEquals(1, result.getChildNode(Square.d7, Square.d6));

        // Peon e6
        assertEquals(1, result.getChildNode(Square.e6, Square.d5));
        assertEquals(1, result.getChildNode(Square.e6, Square.e5));

        // Reina e7
        assertEquals(1, result.getChildNode(Square.e7, Square.c5));
        assertEquals(1, result.getChildNode(Square.e7, Square.d6));
        assertEquals(1, result.getChildNode(Square.e7, Square.d8));
        assertEquals(1, result.getChildNode(Square.e7, Square.e8));
        assertEquals(1, result.getChildNode(Square.e7, Square.f8));

        // Rey f7
        assertEquals(1, result.getChildNode(Square.f7, Square.e8));
        assertEquals(1, result.getChildNode(Square.f7, Square.f8));
        assertEquals(1, result.getChildNode(Square.f7, Square.g8));

        // Peon g6
        assertEquals(1, result.getChildNode(Square.g6, Square.g5));

        // Alfil g7
        assertEquals(1, result.getChildNode(Square.g7, Square.f8));
        assertEquals(1, result.getChildNode(Square.g7, Square.h6));

        // Peaon h3
        assertEquals(1, result.getChildNode(Square.h3, Square.g2));

        // Caballo b6
        assertEquals(1, result.getChildNode(Square.b6, Square.c4));
        assertEquals(1, result.getChildNode(Square.b6, Square.c8));
        assertEquals(1, result.getChildNode(Square.b6, Square.d5));
        assertEquals(1, result.getChildNode(Square.b6, Square.a4));

        assertEquals(45, result.getMovesCount());
        assertEquals(45, result.getTotalNodes());
    }
}
