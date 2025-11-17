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


    @Test
    public void testToString() {
        PGN pgn = new PGN();
        pgn.setEvent("TheEvent");
        pgn.setSite("TheSite");
        pgn.setDate("2020.01.01");
        pgn.setWhite("TheWhite");
        pgn.setBlack("TheBlack");
        pgn.setFen(FEN.of("1nbqk2r/2p2ppp/r3pn2/pp6/PbpP4/5NP1/1PQBPPBP/RN3RK1 b k a3 0 9"));
        pgn.setResult(PGN.Result.ONGOING);
        pgn.setMoveList(List.of("Ng4", "d5"));

        assertEquals("""
                [Event "TheEvent"]
                [Site "TheSite"]
                [Date "2020.01.01"]
                [Round "?"]
                [White "TheWhite"]
                [Black "TheBlack"]
                [FEN "1nbqk2r/2p2ppp/r3pn2/pp6/PbpP4/5NP1/1PQBPPBP/RN3RK1 b k a3 0 9"]
                [Result "*"]
                
                9... Ng4 10. d5 *
                """, pgn.toString());
    }
}
