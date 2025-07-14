package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

/**
 * <p>
 * <SAN move descriptor piece moves>   ::= <Piece symbol>[<from file>|<from rank>|<from square>]['x']<to square>
 * <SAN move descriptor pawn captures> ::= 			      <from file>[<from rank>]               'x' <to square>[<promoted to>]
 * <SAN move descriptor pawn push>     ::= 														     <to square>[<promoted to>]
 *
 * @author Mauricio Coria
 */
public class SANEncoder {

    public String encodeAlgebraicNotation(Move move, FEN fen) {
        MinChess minchess = MinChess.from(fen);

        if (move.fromPiece() == Move.Piece.PAWN_WHITE || move.fromPiece() == Move.Piece.PAWN_BLACK) {
            return encodePawnMove(move, minchess);
        }

        return encodePieceMove(move, minchess);
    }

    private String encodePawnMove(Move move, MinChess minchess) {
        if (move.from().getFile() == move.to().getFile()) {
            return encodePawnPushMove(move, minchess);
        } else {
            return encodePawnCaptureMove(move, minchess);
        }
    }

    private String encodePawnPushMove(Move move, MinChess minchess) {
        return move.to().toString();
    }

    private String encodePawnCaptureMove(Move move, MinChess minchess) {
        return String.format("%sx%s", fileToLetter(move.from().getFile()), move.to().toString());
    }

    private String fileToLetter(int file) {
        return switch (file) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> null;
        };
    }


    private String encodePieceMove(Move move, MinChess minchess) {
        return null;
    }

    private String encodeMoveCastling(Move moveCastling) {
        return null;
    }

    private String solvePieceAmbiguityFrom(Move move, MinChess minchess) {
        return null;
    }

}
