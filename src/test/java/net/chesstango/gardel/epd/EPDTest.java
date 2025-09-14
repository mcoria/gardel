package net.chesstango.gardel.epd;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Mauricio Coria
 */
public class EPDTest {

    private static EPDDecoder epdDecoder;

    @BeforeEach
    public void setup() {
        epdDecoder = new EPDDecoder();
    }

    @Test
    public void testIsMoveSuccess01() {
        EPD epd = epdDecoder.readEdpLine("1Q4rr/1p1bkp2/pP6/2p1pP2/3nP1Bp/2P1q2P/7K/3R1R2 w - - bm f5-f6+; ce +M1; pv f5-f6+; id \"9482\";");
        assertTrue(epd.isMoveSuccess("f5f6"));
    }

    @Test
    public void testIsMoveSuccess02() {
        EPD epd = epdDecoder.readEdpLine("r1b1Rbkr/pp4pp/2p3n1/3p2BB/8/2N2R2/PPP2PPP/2K5 w - - bm Re8xf8+; ce +M2; pv Re8xf8+ Ng6xf8 Bh5-f7+; id \"1043\";");
        assertTrue(epd.isMoveSuccess("e8f8"));
    }

    @Test
    public void testIsMoveSuccess03() {
        EPD epd = epdDecoder.readEdpLine("3r2k1/1p3ppp/2pq4/p1n5/P6P/1P6/1PB2QP1/1K2R3 w - - am Rd1; id \"position 03\";");
        assertTrue(epd.isMoveSuccess("e1f8"));
    }
}
