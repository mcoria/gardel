package net.chesstango.gardel.epd;

import lombok.Getter;
import lombok.Setter;
import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.move.AgregateMoveDecoder;
import net.chesstango.gardel.move.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Mauricio Coria
 */
@Getter
@Setter
public class EPD {
    private String text;

    private String piecePlacement;
    private String activeColor;
    private String castingsAllowed;
    private String enPassantSquare;

    private String id;
    private String c0;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String c7;

    private String bestMovesStr;

    private String avoidMovesStr;

    private String suppliedMoveStr;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        EPD epd = (EPD) object;
        return Objects.equals(getText(), epd.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText());
    }


    @Override
    public String toString() {
        return text != null ? text : new EPDEncoder().encode(this);
    }

    public String getFenWithoutClocks() {
        return piecePlacement +
                " " +
                activeColor +
                " " +
                castingsAllowed +
                " " +
                enPassantSquare;
    }

    /**
     * @param move coordinate encoding
     * @return
     */
    public boolean isMoveSuccess(String move) {
        if (bestMovesStr != null && !bestMovesStr.isEmpty()) {
            return isMoveSuccess(move, bestMovesStr);
        } else if (avoidMovesStr != null && !avoidMovesStr.isEmpty()) {
            return isMoveSuccess(move, avoidMovesStr);
        } else if (suppliedMoveStr != null && !suppliedMoveStr.isEmpty()) {
            return isMoveSuccess(move, suppliedMoveStr);
        } else {
            throw new RuntimeException("Undefined expected EPD result");
        }
    }


    boolean isMoveSuccess(String moveStr, String movesListStr) {
        Move theMove = Move.of(moveStr);
        List<Move> moveList = movesStringToMoves(movesListStr);
        for (Move move : moveList) {
            if (move.equals(theMove)) {
                return true;
            }
        }
        return false;
    }

    List<Move> movesStringToMoves(String movesString) {
        FEN fen = FEN.of(getFenWithoutClocks() + " 0 1");
        String[] movesStringArray = movesString.split(" ");
        List<Move> moveList = new ArrayList<>(movesStringArray.length);
        AgregateMoveDecoder<Move> moveDecoder = new AgregateMoveDecoder<>(new Move.GardelMoveSupplier());
        for (String moveStr : movesStringArray) {
            Move move = moveDecoder.decode(moveStr, fen);
            if (move != null) {
                moveList.add(move);
            } else {
                throw new RuntimeException(String.format("Unable to find move %s", moveStr));
            }
        }
        return moveList;
    }
}
