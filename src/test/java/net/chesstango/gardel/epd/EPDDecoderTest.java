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

}
