package net.chesstango.gardel.pgn;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Mauricio Coria
 */
public class PGNStringEncoder {

    public String encode(PGN pgn) {
        StringBuilder sb = new StringBuilder();

        sb.append("[Event \"").append(pgn.getEvent() == null ? "?" : pgn.getEvent()).append("\"]\n");
        sb.append("[Site \"").append(pgn.getSite() == null ? getComputerName() : pgn.getSite()).append("\"]\n");
        sb.append("[Date \"").append(pgn.getDate() == null ? getToday() : pgn.getDate()).append("\"]\n");
        sb.append("[Round \"").append(pgn.getRound() == null ? "?" : pgn.getRound()).append("\"]\n");
        sb.append("[White \"").append(pgn.getWhite() == null ? "X" : pgn.getWhite()).append("\"]\n");
        sb.append("[Black \"").append(pgn.getBlack() == null ? "X" : pgn.getBlack()).append("\"]\n");
        if (pgn.getFen() != null) {
            sb.append("[FEN \"").append(pgn.getFen()).append("\"]\n");
        }
        sb.append("[Result \"").append(pgn.getResult()).append("\"]\n");
        if(pgn.getTermination()!=null){
            sb.append("[Termination \"").append(pgn.getTermination()).append("\"]\n");
        }
        sb.append("\n");


        List<String> moveList = pgn.getMoveList();


        if (!moveList.isEmpty()) {
            int clock = pgn.getFen() != null ? Integer.parseInt(pgn.getFen().getFullMoveClock()) : 1;

            boolean whiteTurn = pgn.getFen() != null ? pgn.getFen().getActiveColor().equals("w") : true;

            boolean firstMovePrinted = false;

            for (int i = 0; i < moveList.size(); i++) {
                if (i > 0 && i % 10 == 0) {
                    sb.append("\n");
                }

                if (whiteTurn) {
                    sb.append(clock)
                            .append(". ")
                            .append(moveList.get(i))
                            .append(" ");
                    whiteTurn = false;
                } else {
                    if (!firstMovePrinted) {
                        sb.append(clock)
                                .append("... ");
                    }
                    sb.append(moveList.get(i))
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

    private String getToday() {
        String pattern = "yyyy.MM.dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    private String getComputerName() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else return env.getOrDefault("HOSTNAME", "Unknown Computer");
    }

}
