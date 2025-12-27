package net.chesstango.gardel.pgn;

import net.chesstango.gardel.fen.FEN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class PGNStringEncoderTest {

    private PGNStringEncoder encoder;

    @BeforeEach
    public void setup() {
        encoder = new PGNStringEncoder();
    }

    @Test
    public void testEncode01() {
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
                """, encoder.encode(pgn));
    }

    @Test
    public void testEncode02() {
        PGN pgn = new PGN();
        pgn.setEvent("TheEvent");
        pgn.setSite("TheSite");
        pgn.setDate("2020.01.01");
        pgn.setWhite("TheWhite");
        pgn.setBlack("TheBlack");
        pgn.setFen(FEN.of("1nbqk2r/2p2ppp/r3pn2/pp6/PbpP4/5NP1/1PQBPPBP/RN3RK1 b k a3 0 9"));
        pgn.setResult(PGN.Result.ONGOING);
        pgn.setTermination(PGN.Termination.NORMAL);
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
                [Termination "normal"]
                
                9... Ng4 10. d5 *
                """, encoder.encode(pgn));
    }

}
