package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

/**
 * @author Mauricio Coria
 * <p>
 * <SAN move descriptor piece moves>   ::= <Piece symbol>[<from file>|<from rank>|<from square>]['x']<to square>
 * <SAN move descriptor pawn captures> ::= 			      <from file>[<from rank>]               'x' <to square>[<promoted to>]
 * <SAN move descriptor pawn push>     ::= 														     <to square>[<promoted to>]
 */
public class SANEncoder {

    public String encodeAlgebraicNotation(MoveCoordinates moveCoordinates, FEN fen) {
        MinChess minchess = MinChess.from(fen);
        short[] moves = new short[MAX_MOVES];
        minchess.generateMoves(moves);
        /*
        if (move instanceof MoveCastling moveCastling) {
            return encodeMoveCastling(moves);
        } else if (move.getFrom().getPiece().isPawn()) {
            return encodePawnMove(move, moves);
        }
         */
        return encodePieceMove(moveCoordinates, moves);
    }

    public String encodePieceMove(MoveCoordinates moveCoordinates, short[] moves) {


        return null;
    }

    private String encodePawnMove(MoveCoordinates moveCoordinates, short[] moves) {

        return null;
    }

    private String encodeMoveCastling(MoveCoordinates moveCoordinatesCastling) {

        return null;
    }


    private String solvePieceAmbiguityFrom(MoveCoordinates moveCoordinates, short[] moves) {

        return null;
    }


}
