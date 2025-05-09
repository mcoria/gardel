package net.chesstango.gardel.polyglot;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class PolyglotKeyBuilderTest {
    private PolyglotKeyBuilder polyglotKeyBuilder;

    @BeforeEach
    public void setUp() {
        this.polyglotKeyBuilder = new PolyglotKeyBuilder();
    }

    @Test
    public void generateKeyINITIAL_FEN() {
        Long polyglotKey = FEN
                .of(FENParser.INITIAL_FEN)
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X463B96181691FC9CL, polyglotKey.longValue());
    }

    /**
     * position after e2e4
     * FEN=rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1
     * key=823c9b50fd114196
     */

    @Test
    public void generateKey01() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X823C9B50FD114196L, polyglotKey.longValue());
    }


    /**
     * position after e2e4 d75
     * FEN=rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2
     * key=0756b94461c50fb0
     */

    @Test
    public void generateKey02() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq d6 0 2")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X0756B94461C50FB0L, polyglotKey.longValue());
    }

    /**
     * position after e2e4 d7d5 e4e5
     * FEN=rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2
     * key=662fafb965db29d4
     */

    @Test
    public void generateKey03() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/ppp1pppp/8/3pP3/8/8/PPPP1PPP/RNBQKBNR b KQkq - 0 2")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X662FAFB965DB29D4L, polyglotKey.longValue());
    }

    /**
     * position after e2e4 d7d5 e4e5 f7f5
     * FEN=rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3
     * key=22a48b5a8e47ff78
     */
    @Test
    public void generateKey04() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPP1PPP/RNBQKBNR w KQkq f6 0 3")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X22A48B5A8E47FF78L, polyglotKey.longValue());
    }

    /**
     * position after e2e4 d7d5 e4e5 f7f5 e1e2
     * FEN=rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR b kq - 0 3
     * key=652a607ca3f242c1
     */

    @Test
    public void generateKey05() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/ppp1p1pp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR b kq - 0 3")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X652A607CA3F242C1L, polyglotKey.longValue());
    }

    /**
     * position after e2e4 d7d5 e4e5 f7f5 e1e2 e8f7
     * FEN=rnbq1bnr/ppp1pkpp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR w - - 0 4
     * key=00fdd303c946bdd9
     */
    @Test
    public void generateKey06() {
        Long polyglotKey = FEN
                .of("rnbq1bnr/ppp1pkpp/8/3pPp2/8/8/PPPPKPPP/RNBQ1BNR w - - 0 4")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X00FDD303C946BDD9L, polyglotKey.longValue());
    }


    /**
     * position after a2a4 b7b5 h2h4 b5b4 c2c4
     * FEN=rnbqkbnr/p1pppppp/8/8/PpP4P/8/1P1PPPP1/RNBQKBNR b KQkq c3 0 3
     * key=3c8123ea7b067637
     */
    @Test
    public void generateKey07() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/p1pppppp/8/8/PpP4P/8/1P1PPPP1/RNBQKBNR b KQkq c3 0 3")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X3C8123EA7B067637L, polyglotKey.longValue());
    }


    /**
     * position after a2a4 b7b5 h2h4 b5b4 c2c4 b4c3 a1a3
     * FEN=rnbqkbnr/p1pppppp/8/8/P6P/R1p5/1P1PPPP1/1NBQKBNR b Kkq - 0 4
     * key=5c3f9b829b279560
     */
    @Test
    public void generateKey08() {
        Long polyglotKey = FEN
                .of("rnbqkbnr/p1pppppp/8/8/P6P/R1p5/1P1PPPP1/1NBQKBNR b Kkq - 0 4")
                .export(polyglotKeyBuilder);

        //System.out.printf("%016X", polyglotKey);

        assertEquals(0X5C3F9B829B279560L, polyglotKey.longValue());
    }
}
