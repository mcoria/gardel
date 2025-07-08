package net.chesstango.gardel.minchess;

import java.util.function.BiPredicate;

/**
 * @author Mauricio Coria
 */
class Pawn {

    final PawnWhite pawnWhite;
    final PawnBlack pawnBlack;


    Pawn(BiPredicate<Long, Long> isLegalMoveFn, BiPredicate<Long, Long> isLegalEnPassantMoveFn) {
        this.pawnWhite = new PawnWhite(isLegalMoveFn, isLegalEnPassantMoveFn);
        this.pawnBlack = new PawnBlack(isLegalMoveFn, isLegalEnPassantMoveFn);
    }

    int generatePawnMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        return workspace.whiteTurn ? pawnWhite.generateMoves(workspace, moves, startIdx) : pawnBlack.generateMoves(workspace, moves, startIdx);
    }

    boolean isKingInCheckByOpponentPawn(final MinChessWorkspace workspace, long kingPosition, int kingIdx, boolean opponentColor) {
        return opponentColor ? pawnWhite.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, opponentColor) : pawnBlack.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, opponentColor);
    }

}
