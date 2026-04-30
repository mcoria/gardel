package net.chesstango.gardel.epd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class EPDDecoderTest {

    private EPDDecoder epdDecoder;

    @BeforeEach
    public void setup() {
        epdDecoder = new EPDDecoder();
    }

    @Test
    @Disabled
    public void testReadMateAll() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\Java\\projects\\chess\\chess-utils\\testing\\40H-EPD-databases\\mate-all.epd"));

        assertEquals(23268, epdStream.count());
    }


    @Test
    @Disabled
    public void testReadWAC() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\wac.epd"));

        assertEquals(300, epdStream.count());
    }

    @Test
    @Disabled
    public void testReadWAC2018() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\wac-2018.epd"));

        assertEquals(201, epdStream.count());
    }

    @Test
    @Disabled
    public void testSilentButDeadly() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\sbd.epd"));

        assertEquals(134, epdStream.count());
    }

    @Test
    @Disabled
    public void testPET() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\pet.epd"));

        assertEquals(48, epdStream.count());
    }

    @Test
    @Disabled
    public void test_arasana2023() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\arasan2023.epd"));

        assertEquals(200, epdStream.count());
    }

    @Test
    @Disabled
    public void test_eet() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\eet.epd"));

        assertEquals(100, epdStream.count());
    }

    @Test
    @Disabled
    public void test_bt2630() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\bt2630.epd"));

        assertEquals(30, epdStream.count());
    }

    @Test
    @Disabled
    public void test_Kaufman() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\Kaufman.epd"));

        assertEquals(25, epdStream.count());
    }

    @Test
    @Disabled
    public void test_lapuce2() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\lapuce2.epd"));

        assertEquals(35, epdStream.count());
    }

    @Test
    @Disabled
    public void test_LCTII() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\LCTII.epd"));

        assertEquals(35, epdStream.count());
    }

    @Test
    @Disabled
    public void test_CCROneHourTest() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\CCROneHourTest.epd"));

        assertEquals(24, epdStream.count());
    }


    @Test
    @Disabled
    public void testReadEvalTunnerWhite() throws IOException {
        Stream<EPD> epdStream = epdDecoder.decodeEPDs(Path.of("C:\\Java\\projects\\chess\\chess-utils\\testing\\positions\\Texel\\eval-tunner-white.txt"));

        assertEquals(32571, epdStream.count());
    }

    @Test
    @Disabled
    public void allEPDsFiles() {
        List<String> epdFiles = List.of(
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\mate-all.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\Bratko-Kopec.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\wac-2018.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\sbd.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\Nolot.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\pet.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\arasan2023.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\eet.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\bt2630.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\Kaufman.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\lapuce2.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\LCTII.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\CCROneHourTest.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS1.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS2.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS3.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS4.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS5.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS6.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS7.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS8.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS9.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS10.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS11.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS12.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS13.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS14.epd",
                "C:\\java\\projects\\chess\\chess-utils\\testing\\EPD\\database\\STS15.epd"
        );

        epdFiles.forEach(fileName -> {
            try {
                epdDecoder.decodeEPDs(Path.of(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /*
    @Test
    public void testReadEDP09() {
        EPD epd = epdDecoder.readEdpLine("5rk1/1ppb3p/p1pb4/6q1/3P1p1r/2P1R2P/PP1BQ1P1/5RKN w - - bm Rg3; id \"WAC.003\";");

        assertEquals("5rk1/1ppb3p/p1pb4/6q1/3P1p1r/2P1R2P/PP1BQ1P1/5RKN w - -", epd.getFenWithoutClocks());
        assertEquals("Rg3", epd.getBestMovesStr());
        assertEquals("WAC.003", epd.getId());
        assertEquals(1, epd.getBestMovesStr().split(" ").length);
        assertEquals(Piece.ROOK_WHITE, epd.movesStringToMoves(epd.getBestMovesStr()).getFirst().getFrom().getPiece());
    }

    @Test
    public void testReadEDP10() {
        EPD epd = epdDecoder.readEdpLine("r3r1k1/pp1n1ppp/2p5/4Pb2/2B2P2/B1P5/P5PP/R2R2K1 w - - bm e6; id \"WAC.072\";");

        assertEquals("r3r1k1/pp1n1ppp/2p5/4Pb2/2B2P2/B1P5/P5PP/R2R2K1 w - -", epd.getFenWithoutClocks());
        assertEquals("e6", epd.getBestMovesStr());
        assertEquals("WAC.072", epd.getId());
        assertEquals(1, epd.getBestMovesStr().split(" ").length);

        Move firstMove = epd.movesStringToMoves(epd.getBestMovesStr()).getFirst();
        assertEquals(Piece.PAWN_WHITE, firstMove.getFrom().getPiece());
    }

    @Test
    public void testReadEDP11() {
        EPD epd = epdDecoder.readEdpLine("r2r2k1/p2n1p2/4q2p/3p2p1/1PpB4/P1NnPP2/2Q3PP/R2R2K1 b - - bm N7e5; c0 \"N7e5=10, a5=6, a6=6, Nb8=5\"; id \"STS(v12.0) Center Control.081\";");

        assertEquals("r2r2k1/p2n1p2/4q2p/3p2p1/1PpB4/P1NnPP2/2Q3PP/R2R2K1 b - -", epd.getFenWithoutClocks());
        assertEquals("N7e5", epd.getBestMovesStr());
        assertEquals("STS(v12.0) Center Control.081", epd.getId());
        assertEquals(1, epd.getBestMovesStr().split(" ").length);

        Move firstMove = epd.movesStringToMoves(epd.getBestMovesStr()).getFirst();
        assertEquals(Piece.KNIGHT_BLACK, firstMove.getFrom().getPiece());
        assertEquals(Square.d7, firstMove.getFrom().getSquare());
        assertEquals(Square.e5, firstMove.getTo().getSquare());
    }

    @Test
    public void testReadEDP12() {
        EPD epd = epdDecoder.readEdpLine("3r2k1/1p3ppp/2pq4/p1n5/P6P/1P6/1PB2QP1/1K2R3 w - - am Rd1; id \"position 03\";");

        assertEquals("3r2k1/1p3ppp/2pq4/p1n5/P6P/1P6/1PB2QP1/1K2R3 w - -", epd.getFenWithoutClocks());
        assertEquals("Rd1", epd.getAvoidMovesStr());
        assertEquals("position 03", epd.getId());
        assertEquals(1, epd.getAvoidMovesStr().split(" ").length);

        Move firstMove = epd.movesStringToMoves(epd.getAvoidMovesStr()).getFirst();
        assertEquals(Piece.ROOK_WHITE, firstMove.getFrom().getPiece());
        assertEquals(Square.e1, firstMove.getFrom().getSquare());
        assertEquals(Square.d1, firstMove.getTo().getSquare());
    }
     */

}
