package net.chesstango.gardel.pgn;

import java.util.List;

/**
 * @author Mauricio Coria
 */
public class PGNEncoder {

    public String encode(PGN pgn) {
        StringBuilder sb = new StringBuilder();

        sb.append("[Event \"").append(pgn.getEvent() == null ? "?" : pgn.getEvent()).append("\"]\n");
        sb.append("[Site \"").append(pgn.getSite() == null ? "?" : pgn.getSite()).append("\"]\n");
        sb.append("[Date \"").append(pgn.getDate() == null ? "?" : pgn.getDate()).append("\"]\n");
        sb.append("[Round \"").append(pgn.getRound() == null ? "?" : pgn.getRound()).append("\"]\n");
        sb.append("[White \"").append(pgn.getWhite() == null ? "X" : pgn.getWhite()).append("\"]\n");
        sb.append("[Black \"").append(pgn.getBlack() == null ? "X" : pgn.getBlack()).append("\"]\n");
        if (pgn.getFen() != null) {
            sb.append("[FEN \"").append(pgn.getFen()).append("\"]\n");
        }
        sb.append("[Result \"").append(pgn.getResult()).append("\"]\n");
        if (pgn.getTermination() != null) {
            sb.append("[Termination \"").append(pgn.getTermination()).append("\"]\n");
        }

        pgn.getOtherTags()
                .forEach((key, value) -> sb.append("[").append(key).append(" \"").append(value).append("\"]\n"));

        sb.append("\n");


        List<PGNMove> moves = pgn.getPgnMoves();

        if (!moves.isEmpty()) {
            int clock = pgn.getFen() != null ? Integer.parseInt(pgn.getFen().getFullMoveClock()) : 1;

            boolean whiteTurn = pgn.getFen() == null || "w".equals(pgn.getFen().getActiveColor());

            boolean firstMovePrinted = false;

            for (int i = 0; i < moves.size(); i++) {
                if (i > 0 && i % 10 == 0) {
                    sb.append("\n");
                }

                if (whiteTurn) {
                    sb.append(clock)
                            .append(". ")
                            .append(moves.get(i).toString())
                            .append(" ");
                    whiteTurn = false;
                } else {
                    if (!firstMovePrinted) {
                        sb.append(clock)
                                .append("... ");
                    }
                    sb.append(moves.get(i).toString())
                            .append(" ");
                    whiteTurn = true;
                    clock++;
                }

                firstMovePrinted = true;
            }
        }

        sb.append(pgn.getResult()).append("\n");

        return sb.toString();
    }

}
