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
            return move.to().toString();
        }
        return null;
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
