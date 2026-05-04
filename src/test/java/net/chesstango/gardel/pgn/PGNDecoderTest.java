package net.chesstango.gardel.pgn;

import net.chesstango.gardel.epd.EPD;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mauricio Coria
 */
public class PGNDecoderTest {

    private PGNDecoder decoder;

    @BeforeEach
    public void setup() {
        decoder = new PGNDecoder();
    }


    @Test
    public void decodePGNHeaders01() {
        String lines =
                "[Event \"Computer chess game\"]\n" +
                        "[Site \"KANO-LENOVO\"]\n" +
                        "[Date \"2023.03.02\"]\n" +
                        "[Round \"10\"]\n" +
                        "[White \"Tango\"]\n" +
                        "[Black \"Chacarera\"]\n" +
                        "[FEN \"rn1qkbnr/pp2ppp1/2p4p/3pPb2/3P2PP/8/PPP2P2/RNBQKBNR b KQkq g3 0 5\"]\n" +
                        "[Result \"1/2-1/2\"]\n" +
                        "*\n";
        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("Computer chess game", pgn.getEvent());
        assertEquals("KANO-LENOVO", pgn.getSite());
        assertEquals("2023.03.02", pgn.getDate());
        assertEquals("10", pgn.getRound());
        assertEquals("Tango", pgn.getWhite());
        assertEquals("Chacarera", pgn.getBlack());
        assertEquals("rn1qkbnr/pp2ppp1/2p4p/3pPb2/3P2PP/8/PPP2P2/RNBQKBNR b KQkq g3 0 5", pgn.getFen().toString());
        assertEquals(PGN.Result.DRAW, pgn.getResult());
    }

    @Test
    public void decodePGN01() throws IOException {
        String lines = "[Event \"Balsa - Top 10\"]\n" +
                "[Site \"KANO-LENOVO\"]\n" +
                "[Date \"2023.03.02\"]\n" +
                "[Round \"10\"]\n" +
                "[White \"Tango\"]\n" +
                "[Black \"Chacarera\"]\n" +
                "[Result \"1/2-1/2\"]\n" +
                "[ECO \"B12\"]\n" +
                "[PlyCount \"11\"]\n" +
                "[EventDate \"2017.09.05\"]\n" +
                "[EventType \"simul\"]\n" +
                "[Source \"Sedat Canbaz\"]\n" +
                "\n" +
                "1. e4 c6 2. d4 d5 3. e5 Bf5 4. c3 e6 5. Nf3 Nd7 6. Be2 *\n";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("Balsa - Top 10", pgn.getEvent());
        assertEquals("KANO-LENOVO", pgn.getSite());
        assertEquals("2023.03.02", pgn.getDate());
        assertEquals("10", pgn.getRound());
        assertEquals("Tango", pgn.getWhite());
        assertEquals("Chacarera", pgn.getBlack());
        assertEquals(PGN.Result.DRAW, pgn.getResult());

        List<String> moves = pgn.getSanMoves();
        assertEquals("e4", moves.get(0));
        assertEquals("c6", moves.get(1));
        assertEquals("Be2", moves.get(10));
    }


    @Test
    public void decodePGN02() throws IOException {
        String lines = "[Event \"Rated Rapid game\"]\n" +
                "[Site \"https://lichess.org/cjatYH5c\"]\n" +
                "[Date \"2024.02.20\"]\n" +
                "[White \"ChessChildren\"]\n" +
                "[Black \"chesstango_bot\"]\n" +
                "[Result \"1-0\"]\n" +
                "[UTCDate \"2024.02.20\"]\n" +
                "[UTCTime \"21:23:34\"]\n" +
                "[WhiteElo \"1765\"]\n" +
                "[BlackElo \"1863\"]\n" +
                "[WhiteRatingDiff \"+7\"]\n" +
                "[BlackRatingDiff \"-7\"]\n" +
                "[WhiteTitle \"BOT\"]\n" +
                "[BlackTitle \"BOT\"]\n" +
                "[Variant \"Standard\"]\n" +
                "[TimeControl \"600+0\"]\n" +
                "[ECO \"E25\"]\n" +
                "[Opening \"Nimzo-Indian Defense: Sämisch Variation, Keres Variation\"]\n" +
                "[Termination \"Time forfeit\"]\n" +
                "[Annotator \"lichess.org\"]\n" +
                "\n" +
                "1. d4 Nf6 2. c4 e6 3. Nc3 Bb4 4. a3 Bxc3+ 5. bxc3 c5 6. f3 d5 7. cxd5 Nxd5 8. dxc5 { E25 Nimzo-Indian Defense: Sämisch Variation, Keres Variation } Qa5 9. Bd2 Qxc5 10. e4 Nf6 11. Qb3 O-O 12. Qb4 Re8 13. Qxc5 Na6 14. Bxa6 bxa6 15. e5 Nd7 16. Qc6 Rb8 17. Be3 Rf8 18. Qd6 { White wins on time. } 1-0";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("Rated Rapid game", pgn.getEvent());
        assertEquals("https://lichess.org/cjatYH5c", pgn.getSite());
        assertEquals("2024.02.20", pgn.getDate());
        assertEquals("ChessChildren", pgn.getWhite());
        assertEquals("chesstango_bot", pgn.getBlack());
        assertEquals(PGN.Result.WHITE_WINS, pgn.getResult());
        assertEquals(PGN.Termination.TIME_FORFEIT, pgn.getTermination());

        List<String> moves = pgn.getSanMoves();
        assertEquals("Qb4", moves.get(22));
    }

    @Test
    public void decodePGN03() throws IOException {
        String lines = "[Event \"b3644c68-3c6a-40ab-870a-3b965dd38c6c\"]\n" +
                "[Site \"LAPTOP-PTVVKHNB\"]\n" +
                "[Date \"2024.06.12\"]\n" +
                "[Round \"?\"]\n" +
                "[White \"Tango v0.0.28-SNAPSHOT\"]\n" +
                "[Black \"Spike 1.4\"]\n" +
                "[FEN \"r1bqkb1r/pp1p1ppp/2n1pn2/8/2PN4/2N5/PP2PPPP/R1BQKB1R w KQkq - 0 6\"]\n" +
                "[Result \"0-1\"]\n" +
                "\n" +
                "1. e4 e5 2. Nf5 b6 3. Bd3 g6 4. Ne3 Nb4 5. O-O Bb7\n" +
                "6. Ncd5 Nfxd5 7. cxd5 Rc8 8. Re1 Nxd3 9. Qxd3 Bb4 10. Bd2 Bc5\n" +
                "11. Bc3 Qg5 12. Rad1 O-O 13. d6 Qf4 14. g3 Qf3 15. Bxe5 Bxe4\n" +
                "16. Qb3 Qh1# 0-1\n";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("b3644c68-3c6a-40ab-870a-3b965dd38c6c", pgn.getEvent());
        assertEquals("LAPTOP-PTVVKHNB", pgn.getSite());
        assertEquals("2024.06.12", pgn.getDate());
        assertEquals("Tango v0.0.28-SNAPSHOT", pgn.getWhite());
        assertEquals("Spike 1.4", pgn.getBlack());
        assertEquals(PGN.Result.BLACK_WINS, pgn.getResult());

        List<String> moves = pgn.getSanMoves();
        assertEquals("Qh1#", moves.get(31));
    }

    @Test
    public void decodePGN04() throws IOException {
        String lines = "[Event \"b3644c68-3c6a-40ab-870a-3b965dd38c6c\"]\n" +
                "[Site \"LAPTOP-PTVVKHNB\"]\n" +
                "[Date \"2024.06.12\"]\n" +
                "[Round \"?\"]\n" +
                "[White \"Tango v0.0.28-SNAPSHOT\"]\n" +
                "[Black \"Spike 1.4\"]\n" +
                "[FEN \"r1bqkb1r/pp1p1ppp/2n1pn2/8/2PN4/2N5/PP2PPPP/R1BQKB1R w KQkq - 0 6\"]\n" +
                "[Result \"0-1\"]\n" +
                "[Termination \"normal\"]\n" +
                "\n" +
                "1. e4 e5 2. Nf5 b6 3. Bd3 g6 4. Ne3 Nb4 5. O-O Bb7\n" +
                "6. Ncd5 Nfxd5 7. cxd5 Rc8 8. Re1 Nxd3 9. Qxd3 Bb4 10. Bd2 Bc5\n" +
                "11. Bc3 Qg5 12. Rad1 O-O 13. d6 Qf4 14. g3 Qf3 15. Bxe5 Bxe4\n" +
                "16. Qb3 Qh1# 0-1\n";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("b3644c68-3c6a-40ab-870a-3b965dd38c6c", pgn.getEvent());
        assertEquals("LAPTOP-PTVVKHNB", pgn.getSite());
        assertEquals("2024.06.12", pgn.getDate());
        assertEquals("Tango v0.0.28-SNAPSHOT", pgn.getWhite());
        assertEquals("Spike 1.4", pgn.getBlack());
        assertEquals(PGN.Result.BLACK_WINS, pgn.getResult());
        assertEquals(PGN.Termination.NORMAL, pgn.getTermination());

        List<String> moves = pgn.getSanMoves();
        assertEquals("Qh1#", moves.get(31));
    }

    @Test
    public void decodePGN05() throws IOException {
        String lines = "[Event \"F/S Return Match\"]\n" +
                "[Site \"Belgrade, Serbia JUG\"]\n" +
                "[Date \"1992.11.04\"]\n" +
                "[Round \"29\"]\n" +
                "[White \"Fischer, Robert J.\"]\n" +
                "[Black \"Spassky, Boris V.\"]\n" +
                "[Result \"1/2-1/2\"]\n" +
                "1. e4 {[%clk 1:05:23]} e5 {[%clk 1:05:21]} \n" +
                "2. Nf3 {[%clk 1:04:50]} Nc6 {[%clk 1:04:48]} \n" +
                "3. Bb5 {[%clk 1:04:15]} a6 {[%clk 1:04:12]} \n" +
                "4. Ba4 {[%clk 1:03:40]} Nf6 {[%clk 1:03:38]} \n" +
                "5. O-O {[%clk 1:03:00]} Be7 {[%clk 1:02:57]} \n" +
                "6. Re1 {[%clk 1:02:20]} b5 {[%clk 1:02:15]} \n" +
                "7. Bb3 {[%clk 1:01:45]} O-O {[%clk 1:01:42]} \n" +
                "8. c3 {[%clk 1:01:10]} d5 {[%clk 1:01:08]} \n" +
                "9. exd5 {[%clk 1:00:30]} Nxd5 {[%clk 1:00:25]} \n" +
                "10. Nxe5 {[%clk 1:00:00]} Nxe5 {[%clk 0:59:55]} \n" +
                "1/2-1/2";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("F/S Return Match", pgn.getEvent());
        assertEquals("Belgrade, Serbia JUG", pgn.getSite());
        assertEquals("1992.11.04", pgn.getDate());
        assertEquals("Fischer, Robert J.", pgn.getWhite());
        assertEquals("Spassky, Boris V.", pgn.getBlack());
        assertEquals(PGN.Result.DRAW, pgn.getResult());

        List<String> moves = pgn.getSanMoves();
        assertEquals("Nxe5", moves.get(19));
    }

    @Test
    public void decodePGN06() throws IOException {
        String lines = "[Event \"Leela Knight Odds vs GM Joel Benjamin\"]\n" +
                "[Site \"?\"]\n" +
                "[Date \"2025.01.27\"]\n" +
                "[Round \"8\"]\n" +
                "[White \"Leela Knight Odds\"]\n" +
                "[Black \"Benjamin, Joel\"]\n" +
                "[Result \"1/2-1/2\"]\n" +
                "[BlackElo \"2473\"]\n" +
                "[WhiteTitle \"BOT\"]\n" +
                "[BlackTitle \"GM\"]\n" +
                "[TimeControl \"3600+30\"]\n" +
                "[SetUp \"1\"]\n" +
                "[Source \"LichessBroadcast\"]\n" +
                "[ImportDate \"2025-02-04\"]\n" +
                "[BlackFideId \"2000091\"]\n" +
                "[FEN \"rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/R1BQKBNR w KQkq - 0 1\"]\n" +
                "\n" +
                "1. e3 {[%clk 1:00:00]} 1. ... e5 {[%clk 1:00:00]} 2. b3 {[%clk 1:00:30]} \n" +
                "2. ... d5 {[%clk 0:57:28]} 3. Bb2 {[%clk 1:00:59]} 3. ... Bd6 {[%clk\n" +
                "0:57:53]} 4. c4 {[%clk 1:01:29]} 4. ... c6 {[%clk 0:56:29]} 5. Bd3 {[%clk \n" +
                "1:01:58]} 5. ... Nf6 {[%clk 0:56:23]} 6. Ne2 {[%clk 1:02:28]} 6. ... O-O {\n" +
                "[%clk 0:55:03]} 7. Rc1 {[%clk 1:02:53]} 7. ... Qe7 {[%clk 0:52:58]} 8. Bc2\n" +
                "{[%clk 1:03:20]} 8. ... Ba3 {[%clk 0:51:36]} 9. Bxa3 {[%clk\n" +
                "1:03:49]} 9. ... Qxa3 {[%clk 0:52:03]} 10. O-O {[%clk 1:04:17]} 10. ... \n" +
                "Nbd7 {[%clk 0:43:34]} 11. Bb1 {[%clk 1:04:42]} 11. ... Re8 {[%clk\n" +
                "0:43:21]} 12. h3 {[%clk 1:05:10]} 12. ... Qd6 {[%clk 0:43:11]} 13. d4 {\n" +
                "[%clk 1:05:36]} 13. ... exd4 {[%clk 0:39:06]} 14. Nxd4 {[%clk 1:06:04]} \n" +
                "14. ... dxc4 {[%clk 0:37:43]} 15. Rxc4 {[%clk 1:06:32]} 15. ... Ne5 {[%clk\n" +
                "0:34:32]} 16. Rc1 {[%clk 1:07:00]} 16. ... Bd7 {[%clk 0:34:15]} 17. Qc2 {\n" +
                "[%clk 1:07:28]} 17. ... b6 {[%clk 0:30:43]} 18. Rfd1 {[%clk 1:07:55]} \n" +
                "18. ... Qe7 {[%clk 0:31:08]} 19. Ne2 {[%clk 1:08:25]} 19. ... Rad8 {[%clk\n" +
                "0:28:54]} 20. Ng3 {[%clk 1:08:54]} 20. ... Ng6 {[%clk 0:25:56]} 21. Re1 {\n" +
                "[%clk 1:09:21]} 21. ... c5 {[%clk 0:25:37]} 22. Qe2 {[%clk 1:09:49]} \n" +
                "22. ... Bc8 {[%clk 0:25:00]} 23. a3 {[%clk 1:10:17]} 23. ... Nh4 {[%clk\n" +
                "0:22:49]} 24. Bd3 {[%clk 1:10:43]} 24. ... Bb7 {[%clk 0:22:14]} 25. e4 {\n" +
                "[%clk 1:11:12]} 25. ... Qe5 {[%clk 0:21:51]} 26. b4 {[%clk 1:11:40]} \n" +
                "26. ... cxb4 {[%clk 0:18:16]} 27. Bb5 {[%clk 1:12:08]} 27. ... Re6 {[%clk\n" +
                "0:13:50]} 28. Bc4 {[%clk 1:12:36]} 28. ... Red6 {[%clk 0:11:41]} 29. axb4 \n" +
                "{[%clk 1:13:04]} 29. ... Rd2 {[%clk 0:10:29]} 30. Qe3 {[%clk\n" +
                "1:13:32]} 30. ... Qd4 {[%clk 0:09:03]} 31. Qxd4 {[%clk 1:14:00]} 31. ... \n" +
                "R2xd4 {[%clk 0:08:58]} 32. f3 {[%clk 1:14:29]} 32. ... Kf8 {[%clk\n" +
                "0:08:22]} 33. b5 {[%clk 1:14:57]} 33. ... Bc8 {[%clk 0:06:41]} 34. Be2 {\n" +
                "[%clk 1:15:24]} 34. ... Be6 {[%clk 0:06:28]} 35. Ra1 {[%clk 1:15:53]} \n" +
                "35. ... R8d7 {[%clk 0:06:21]} 36. Ra3 {[%clk 1:16:22]} 36. ... Rc7 {[%clk\n" +
                "0:05:09]} 37. Rea1 {[%clk 1:16:50]} 37. ... Rdd7 {[%clk 0:05:36]} 38. f4 {\n" +
                "[%clk 1:17:19]} 38. ... Bc4 {[%clk 0:04:56]} 39. e5 {[%clk 1:17:47]} \n" +
                "39. ... Bxe2 {[%clk 0:02:57]} 40. Nxe2 {[%clk 1:18:15]} 40. ... Nd5 {[%clk\n" +
                "0:03:25]} 41. Ra4 {[%clk 1:18:42]} 41. ... Nf5 {[%clk 0:03:22]} 42. g4 {\n" +
                "[%clk 1:19:10]} 42. ... Nfe3 {[%clk 0:02:57]} 43. R1a3 {[%clk 1:19:39]} \n" +
                "43. ... Nc4 {[%clk 0:03:14]} 44. Rb3 {[%clk 1:20:07]} 44. ... Na5 {[%clk\n" +
                "0:03:30]} 45. Rd3 {[%clk 1:20:36]} 45. ... Ke8 {[%clk 0:03:30]} 46. Rad4 {\n" +
                "[%clk 1:21:04]} 46. ... Ne7 {[%clk 0:03:42]} 47. f5 {[%clk 1:21:32]} \n" +
                "47. ... Rxd4 {[%clk 0:04:01]} 48. Nxd4 {[%clk 1:22:01]} 48. ... Rd7 {[%clk\n" +
                "0:04:15]} 49. f6 {[%clk 1:22:30]} 49. ... gxf6 {[%clk 0:04:21]} 50. exf6 {\n" +
                "[%clk 1:22:59]} 50. ... Ng6 {[%clk 0:04:36]} 51. Re3+ {[%clk 1:23:27]} \n" +
                "51. ... Kd8 {[%clk 0:05:01]} 52. Nf5 {[%clk 1:23:56]} 52. ... Nc4 {[%clk\n" +
                "0:05:21]} 53. Re2 {[%clk 1:24:25]} 53. ... Rd1+ {[%clk 0:04:38]} 54. Kf2 {\n" +
                "[%clk 1:24:53]} 54. ... Nd6 {[%clk 0:05:03]} 55. Nh6 {[%clk 1:25:21]} \n" +
                "55. ... Rd4 {[%clk 0:02:15]} 56. Ra2 {[%clk 1:25:47]} 56. ... Rf4+ {[%clk\n" +
                "0:02:32]} 57. Kg2 {[%clk 1:26:15]} 57. ... Rxf6 {[%clk 0:02:44]} 58. g5 {\n" +
                "[%clk 1:26:44]} 58. ... Re6 {[%clk 0:02:29]} 59. Rxa7 {[%clk 1:27:12]} \n" +
                "59. ... Re5 {[%clk 0:02:07]} 60. Nxf7+ {[%clk 1:24:24]} 60. ... Nxf7 {\n" +
                "[%clk 0:02:32]} 61. Rxf7 {[%clk 1:24:53]} 61. ... Rxg5+ {[%clk 0:03:01]} \n" +
                "62. Kf2 {[%clk 1:25:23]} 62. ... Rh5 {[%clk 0:01:14]} 63. Rb7 {[%clk\n" +
                "1:25:51]} 63. ... Rxh3 {[%clk 0:01:23]} 64. Rxb6 {[%clk 1:26:20]} 64. ... \n" +
                "Kc7 {[%clk 0:01:51]} 65. Rc6+ {[%clk 1:26:48]} 65. ... Kb7 {[%clk\n" +
                "0:02:19]} 66. Rf6 {[%clk 1:27:18]} 66. ... Ne5 {[%clk 0:02:34]} 67. Re6 {\n" +
                "[%clk 1:27:46]} 67. ... Nd3+ {[%clk 0:02:53]} 68. Kg1 {[%clk 1:28:15]} \n" +
                "68. ... Rg3+ {[%clk 0:02:35]} 69. Kh2 {[%clk 1:28:43]} 69. ... Rg7 {[%clk\n" +
                "0:03:03]} 70. Rf6 {[%clk 1:29:13]} 70. ... Ne5 {[%clk 0:03:22]} 71. Kh3 {\n" +
                "[%clk 1:29:42]} 71. ... Rg6 {[%clk 0:03:16]} 72. Rf5 {[%clk 1:30:11]} \n" +
                "72. ... Re6 {[%clk 0:03:31]} 73. Kh4 {[%clk 1:30:40]} 73. ... Kb6 {[%clk\n" +
                "0:03:45]} 74. Kg5 {[%clk 1:31:09]} 74. ... Rg6+ {[%clk 0:04:03]} 75. Kf4 {\n" +
                "[%clk 1:31:36]} 75. ... Nd7 {[%clk 0:04:26]} 76. Rh5 {[%clk 1:32:05]} \n" +
                "76. ... h6 {[%clk 0:04:19]} 77. Kf5 {[%clk 1:32:34]} 77. ... Rd6 {[%clk\n" +
                "0:04:24]} 78. Kf4 {[%clk 1:33:03]} 78. ... Nc5 {[%clk 0:04:44]} 79. Ke5 {\n" +
                "[%clk 1:33:32]} 79. ... Rg6 {[%clk 0:04:30]} 80. Kf5 {[%clk 1:34:01]} \n" +
                "80. ... Re6 {[%clk 0:04:42]} 81. Rh4 {[%clk 1:34:29]} 81. ... Kxb5 {[%clk\n" +
                "0:05:05]} 82. Rg4 {[%clk 1:34:59]} 82. ... Re8 {[%clk 0:04:42]} 83. Kg6 {\n" +
                "[%clk 1:35:27]} 83. ... Re6+ {[%clk 0:03:15]} 84. Kg7 {[%clk 1:35:56]} \n" +
                "84. ... h5 {[%clk 0:01:58]} 85. Rh4 {[%clk 1:36:25]} 85. ... Re5 {[%clk\n" +
                "0:02:25]} 86. Kh6 {[%clk 1:36:54]} 86. ... Kc6 {[%clk 0:02:51]} 87. Rxh5 {\n" +
                "[%clk 1:37:24]} 87. ... Rxh5+ {[%clk 0:03:19]} 88. Kxh5 {[%clk 1:37:24]} \n" +
                "1/2-1/2";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("Leela Knight Odds vs GM Joel Benjamin", pgn.getEvent());
        assertEquals("?", pgn.getSite());
        assertEquals("2025.01.27", pgn.getDate());
        assertEquals("Leela Knight Odds", pgn.getWhite());
        assertEquals("Benjamin, Joel", pgn.getBlack());
        assertEquals(PGN.Result.DRAW, pgn.getResult());

        List<String> moves = pgn.getSanMoves();
        assertEquals(88 * 2 - 1, moves.size());
    }


    @Test
    public void decodePGN07() throws IOException {
        String lines = "[Event \"Prague Open B\"]\n" +
                "[Site \"?\"]\n" +
                "[Date \"2025.01.10\"]\n" +
                "[Round \"1.1\"]\n" +
                "[White \"Illandzis, Spyridon\"]\n" +
                "[Black \"Kotvalt, Antonin\"]\n" +
                "[Result \"1-0\"]\n" +
                "[WhiteElo \"1950\"]\n" +
                "[BlackElo \"1618\"]\n" +
                "[ECO \"A01\"]\n" +
                "[EventDate \"2025.01.10\"]\n" +
                "[PlyCount \"29\"]\n" +
                "[Source \"GreekBase\"]\n" +
                "[ImportDate \"2025-06-03\"]\n" +
                "[BlackFideId \"23753722\"]\n" +
                "\n" +
                "1. b3 d5 2. Bb2 c5 3. e3 e6 4. f4 Nf6 5. Nf3 Be7 6. Bb5+ Nc6 ( 6. ... Bd7 \n" +
                ") ( 6. ... Nbd7 ) 7. O-O O-O 8. Bxc6 bxc6 9. Ne5 Qb6 10. Rf3 Rd8?! {(6' \n" +
                "\uE018 18')} 11. Rg3 Bf8? {[#]} ( 11. ... c4!? ) 12. Nc4! dxc4 13. Bxf6 Rd7 {\n" +
                "[#]} 14. Rxg7+! ( 14. Bxg7? {he was hoping for} 14. ... Bxg7 15. Qg4 f5 ) \n" +
                "14. ... Kh8 ( 14. ... Bxg7 15. Qg4 Kf8 16. Qxg7+ Ke8 17. Qg8# ) 15. Rg8+! \n" +
                "1-0";

        PGN pgn = decoder.decodePGNs(CharStreams.fromString(lines)).findFirst().orElse(null);

        assertEquals("Prague Open B", pgn.getEvent());
        assertEquals("?", pgn.getSite());
        assertEquals("2025.01.10", pgn.getDate());
        assertEquals("Illandzis, Spyridon", pgn.getWhite());
        assertEquals("Kotvalt, Antonin", pgn.getBlack());
        assertEquals(PGN.Result.WHITE_WINS, pgn.getResult());

        List<String> moves = pgn.getSanMoves();
        assertEquals(15 * 2 - 1, moves.size());
    }

    @Test
    public void readGames() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("main/pgn/Balsa_Top10.pgn");

        List<PGN> pgnList = decoder.decodePGNs(inputStream).toList();

        assertEquals(10, pgnList.size());

        // 1st Game
        assertEquals("Balsa - Top 10", pgnList.get(0).getEvent());
        assertEquals("Be2", pgnList.get(0).getSanMoves().get(10));

        // 10th Game
        assertEquals("13", pgnList.get(9).getRound());
        assertEquals("Bd2", pgnList.get(9).getSanMoves().get(10));
    }


    @Test
    @Disabled
    public void Kasparov() throws IOException {
        Path resource = Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\PGN\\full\\players\\Kasparov\\Kasparov.pgn");

        List<PGN> pgnList = decoder.decodePGNs(resource).toList();

        assertEquals(2128, pgnList.size());

        List<EPD> kasparovEPDs = new LinkedList<>();
        pgnList.stream()
                .map(PGN::toEPD)
                .forEach(fenStream -> kasparovEPDs.addAll(fenStream.toList()));

        assertEquals(162894, kasparovEPDs.size());
    }

    @Test
    @Disabled
    public void Balsa_Top10() throws IOException {
        Path pgnDirectory = Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\PGN\\openings\\Balsa_v2724");

        Stream<PGN> bolsa10 = decoder.decodePGNs(pgnDirectory.resolve("Balsa_Top10.pgn"));
        assertEquals(10L, bolsa10.count());


        Stream<PGN> bolsa2724 = decoder.decodePGNs(pgnDirectory.resolve("Balsa_v2724.pgn"));
        assertEquals(2724L, bolsa2724.count());
    }

    @Test
    @Disabled
    public void LumbrasGigaBase_OTB_2025() throws IOException {
        Path lumbrasGigaBase_OTB_2025 = Path.of("C:\\java\\projects\\chess\\chess-utils\\testing\\PGN\\full\\LumbrasGigaBase\\OverTheBoard\\LumbrasGigaBase_OTB_2025.pgn");

        //System.out.println("LumbrasGigaBase_OTB_2025: " + lumbrasGigaBase_OTB_2025.toAbsolutePath());

        Stream<PGN> lumbrasGigaBase_OTB_2025_PGN = decoder.decodePGNs(lumbrasGigaBase_OTB_2025);

        assertEquals(105601L, lumbrasGigaBase_OTB_2025_PGN.count());
    }
}

