package net.chesstango.gardel.pgn;

import lombok.Getter;
import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.internal.antlr4.PGNBaseListener;
import net.chesstango.gardel.internal.antlr4.PGNParser;

import java.util.*;


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
    private List<PGNMove> moveList;
    private int recursiveVariation;

    private PGNMove pgnMove;
    private String commandName;
    private String commandValue;
    private Map<String, String> commands;

    @Override
    public void enterPgn_database(PGNParser.Pgn_databaseContext ctx) {
        pgnList = new LinkedList<>();
    }

    @Override
    public void enterPgn_game(PGNParser.Pgn_gameContext ctx) {
        pgn = new PGN();

        tagName = null;
        tagValue = null;
        moveList = null;
        recursiveVariation = 0;

        pgnMove = null;
        commands = null;
        commandName = null;
        commandValue = null;
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
                pgn.setTag(tagName, tagValue);
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
        pgn.setPgnMoves(moveList);
    }


    @Override
    public void enterRecursive_variation(PGNParser.Recursive_variationContext ctx) {
        recursiveVariation++;
    }

    @Override
    public void exitRecursive_variation(PGNParser.Recursive_variationContext ctx) {
        recursiveVariation--;
    }

    @Override
    public void enterSan_move(PGNParser.San_moveContext ctx) {
        if (recursiveVariation == 0) {
            if (pgnMove != null) {
                moveList.add(pgnMove);
            }
            pgnMove = new PGNMove();
            pgnMove.setSanMove(ctx.getText());
        }
    }

    @Override
    public void enterMove_annotation(PGNParser.Move_annotationContext ctx) {
        if (recursiveVariation == 0) {
            commands = new HashMap<>();
        }
    }


    @Override
    public void exitMove_annotation(PGNParser.Move_annotationContext ctx) {
        pgnMove.setCommands(commands);
    }


    @Override
    public void enterCommand(PGNParser.CommandContext ctx) {
        if (recursiveVariation == 0) {
            commandName = "";
            commandValue = "";
        }
    }

    @Override
    public void exitCommand(PGNParser.CommandContext ctx) {
        if (recursiveVariation == 0) {
            commands.put(commandName, commandValue);
        }
    }

    @Override
    public void enterCommand_name(PGNParser.Command_nameContext ctx) {
        if (recursiveVariation == 0) {
            commandName = ctx.getText();
        }
    }


    @Override
    public void enterCommand_value(PGNParser.Command_valueContext ctx) {
        if (recursiveVariation == 0) {
            commandValue = ctx.getText();
        }
    }

    @Override
    public void enterGame_termination(PGNParser.Game_terminationContext ctx) {
        if (pgnMove != null) {
            moveList.add(pgnMove);
        }
    }
}
