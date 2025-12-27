package net.chesstango.gardel.pgn;

import net.chesstango.gardel.epd.EPD;
import net.chesstango.gardel.fen.FEN;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class PGNTest {

    @Test
    public void testToEpd() throws IOException {
        PGN pgn = new PGN();

        pgn.setMoveList(List.of("a4"));

        Stream<EPD> epdStream = pgn.toEPD();

        List<EPD> epdList = epdStream.toList();

        assertEquals(1, epdList.size());

        EPD epdFirst = epdList.getFirst();

        assertEquals("a4", epdFirst.getSuppliedMoveStr());
    }

}
