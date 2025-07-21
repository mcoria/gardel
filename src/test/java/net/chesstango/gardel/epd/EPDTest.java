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
    public void testIsMoveSuccess() {
        EPD epd = epdDecoder.readEdpLine("1Q4rr/1p1bkp2/pP6/2p1pP2/3nP1Bp/2P1q2P/7K/3R1R2 w - - bm f5-f6+; ce +M1; pv f5-f6+; id \"9482\";");
        assertTrue(epd.isMoveSuccess("f5f6"));
    }
}
