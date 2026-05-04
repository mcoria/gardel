package net.chesstango.gardel.pgn;

import lombok.Getter;
import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.internal.antlr4.PGNBaseListener;
import net.chesstango.gardel.internal.antlr4.PGNParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author Mauricio Coria
 */
class PGNGardelListener extends PGNBaseListener {
    @Getter
    private List<PGN> pgnList;

    @Getter
    private PGN pgn;

    private String tagName;
    private String tagValue;
    private List<String> moveList;
    private int recursiveVariation;

    @Override
    public void enterPgn_database(PGNParser.Pgn_databaseContext ctx) {
        pgnList = new LinkedList<>();
    }

    @Override
    public void enterPgn_game(PGNParser.Pgn_gameContext ctx) {
        pgn = new PGN();
    }

    @Override
    public void exitPgn_game(PGNParser.Pgn_gameContext ctx) {
        if (pgnList != null) {
            pgnList.add(pgn);
        }
    }

    @Override
    public void enterTag_name(PGNParser.Tag_nameContext ctx) {
        tagName = ctx.getText();
    }

    @Override
    public void enterTag_value(PGNParser.Tag_valueContext ctx) {
        tagValue = ctx.getText().replace("\"", "");
    }

    @Override
    public void exitTag_pair(PGNParser.Tag_pairContext ctx) {
        switch (tagName.toUpperCase()) {
            case "EVENT":
                pgn.setEvent(tagValue);
                break;
            case "SITE":
                pgn.setSite(tagValue);
                break;
            case "DATE":
                pgn.setDate(tagValue);
                break;
            case "ROUND":
                pgn.setRound(tagValue);
                break;
            case "WHITE":
                pgn.setWhite(tagValue);
                break;
            case "BLACK":
                pgn.setBlack(tagValue);
                break;
            case "FEN":
                pgn.setFen(FEN.from(tagValue));
                break;
            case "RESULT":
                pgn.setResult(
                        switch (tagValue) {
                            case "1-0" -> PGN.Result.WHITE_WINS;
                            case "0-1" -> PGN.Result.BLACK_WINS;
                            case "1/2-1/2" -> PGN.Result.DRAW;
                            default -> PGN.Result.ONGOING;
                        });
                break;

            case "TERMINATION":
                pgn.setTermination(
                        switch (tagValue.toUpperCase()) {
                            case "NORMAL" -> PGN.Termination.NORMAL;
                            case "ABANDONED" -> PGN.Termination.ABANDONED;
                            case "TIME FORFEIT" -> PGN.Termination.TIME_FORFEIT;
                            default -> throw new IllegalStateException("Unexpected value: " + tagValue.toUpperCase());
                        }
                );
                break;

            default:
                Map<String, String> otherTags = pgn.getOtherTags();
                otherTags.put(tagName, tagValue);
                break;
        }
    }

    @Override
    public void enterMovetext_section(PGNParser.Movetext_sectionContext ctx) {
        moveList = new ArrayList<>();
        recursiveVariation = 0;
    }

    @Override
    public void exitMovetext_section(PGNParser.Movetext_sectionContext ctx) {
        pgn.setSanMoves(moveList);
    }

    @Override
    public void enterSan_move(PGNParser.San_moveContext ctx) {
        if (recursiveVariation == 0) {
            moveList.add(ctx.getText());
        }
    }

    @Override
    public void enterRecursive_variation(PGNParser.Recursive_variationContext ctx) {
        recursiveVariation++;
    }

    @Override
    public void exitRecursive_variation(PGNParser.Recursive_variationContext ctx) {
        recursiveVariation--;
    }
}
