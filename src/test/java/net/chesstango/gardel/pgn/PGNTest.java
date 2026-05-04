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
    public void testToEpd(){
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

        System.out.println(pgn);
    }

}
