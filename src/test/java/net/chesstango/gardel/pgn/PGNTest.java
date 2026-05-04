package net.chesstango.gardel.pgn;

import net.chesstango.gardel.epd.EPD;
import net.chesstango.gardel.fen.FEN;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Mauricio Coria
 */
public class PGNTest {

    @Test
    public void test_toEPD(){
        PGN pgn = new PGN();

        pgn.setSanMoves(List.of("a4"));

        Stream<EPD> epdStream = pgn.toEPD();

        List<EPD> epdList = epdStream.toList();

        assertEquals(1, epdList.size());

        EPD epdFirst = epdList.getFirst();

        assertEquals("a4", epdFirst.getSuppliedMoveStr());
        assertEquals("0", epdFirst.getHalfMoveClock());
        assertEquals("1", epdFirst.getFullMoveClock());
    }

    @Test
    public void test_fromFEN() {
        PGN pgn = PGN.from(FEN.START_POSITION);

        assertNull(pgn.getFen());

        assertEquals(PGN.Result.ONGOING, pgn.getResult());

        assertNotNull(pgn.getSanMoves());
    }

    @Test
    public void test_toCoordinateMoves() {
        PGN pgn = PGN.from("""
                [Event "188c6255-5216-463a-bfc6-300b98a14ff9"]
                [Site "LAPTOP-PTVVKHNB"]
                [Date "2026.05.03"]
                [Round "?"]
                [White "Tango"]
                [Black "Oponent"]
                [Result "0-1"]
                [Termination "normal"]
                [SearchRange "10:66"]
                
                1. Nf3 Nf6 2. d4 d5 3. e3 Bg4 4. Be2 Bxf3 5. Bxf3 e6
                6. Nc3 Nc6 7. O-O Rc8 8. h3 Bd6 9. e4 dxe4 10. Bxe4 Nxe4
                11. Nxe4 Nxd4 12. Bg5 Be7 13. Bxe7 Kxe7 14. c3 Nf5 15. Qe2 c5
                16. Rad1 Qc7 17. Qh5 h6 18. Ng3 Nxg3 19. fxg3 Rhf8 20. Qg4 g6
                21. Qh4+ g5 22. Qg4 c4 23. Rf2 f5 24. Qf3 f4 25. g4 Rcd8
                26. Rd4 b5 27. Qe4 Kf6 28. Re2 Rde8 29. Red2 Qc5 30. Qh7 Rh8
                31. Qe4 h5 32. Rd1 hxg4 33. hxg4 Rh4 34. Qf3 Rc8 35. Kf1 Rh1+
                36. Ke2 Rch8 37. Qe4 R8h2 38. Rxh1 Rxh1 39. Rd8 Qg1 40. Qd4+ Qxd4
                41. Rxd4 Rb1 42. Rd2 a5 43. Kf3 a4 44. Ke2 Ra1 45. a3 Rg1
                46. Kf2 Rb1 47. Kf3 Ke7 48. Ke4 Rh1 49. Kf3 Rc1 50. Ke2 Rb1
                51. Kf2 Kf7 52. Re2 Kf6 53. Rd2 Kg6 54. Re2 Kf7 55. Rd2 e5
                56. Re2 Ke6 57. Kf3 Rf1+ 58. Ke4 Rd1 59. Kf3 Rd3+ 60. Kf2 Kd5
                61. Re1 e4 62. Ke2 e3 63. Kf3 Rd2 64. Re2 Rxe2 65. Kxe2 Ke4
                66. Kd1 f3 67. gxf3+ Kxf3 68. Ke1 e2 69. Kd2 Kf2 70. b4 cxb3
                71. Kc1 e1=Q+ 72. Kb2 Qd2+ 73. Kb1 Qc2+ 74. Ka1 Qc1# 0-1
                """);

        assertEquals("[g1f3, g8f6, d2d4, d7d5, e2e3, c8g4, f1e2, g4f3, e2f3, e7e6, b1c3, b8c6, e1g1, a8c8, h2h3, f8d6, e3e4, d5e4, f3e4, f6e4, c3e4, c6d4, c1g5, d6e7, g5e7, e8e7, c2c3, d4f5, d1e2, c7c5, a1d1, d8c7, e2h5, h7h6, e4g3, f5g3, f2g3, h8f8, h5g4, g7g6, g4h4, g6g5, h4g4, c5c4, f1f2, f7f5, g4f3, f5f4, g3g4, c8d8, d1d4, b7b5, f3e4, e7f6, f2e2, d8e8, e2d2, c7c5, e4h7, f8h8, h7e4, h6h5, d2d1, h5g4, h3g4, h8h4, e4f3, e8c8, g1f1, h4h1, f1e2, c8h8, f3e4, h8h2, d1h1, h2h1, d4d8, c5g1, e4d4, g1d4, d8d4, h1b1, d4d2, a7a5, e2f3, a5a4, f3e2, b1a1, a2a3, a1g1, e2f2, g1b1, f2f3, f6e7, f3e4, b1h1, e4f3, h1c1, f3e2, c1b1, e2f2, e7f7, d2e2, f7f6, e2d2, f6g6, d2e2, g6f7, e2d2, e6e5, d2e2, f7e6, f2f3, b1f1, f3e4, f1d1, e4f3, d1d3, f3f2, e6d5, e2e1, e5e4, f2e2, e4e3, e2f3, d3d2, e1e2, d2e2, f3e2, d5e4, e2d1, f4f3, g2f3, e4f3, d1e1, e3e2, e1d2, f3f2, b2b4, c4b3, d2c1, e2e1q, c1b2, e1d2, b2b1, d2c2, b1a1, c2c1]", pgn.toCoordinateMoves().toString());
    }

}
