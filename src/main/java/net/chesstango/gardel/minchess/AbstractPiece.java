package net.chesstango.gardel.minchess;

import java.util.function.BiPredicate;

/**
 * @author Mauricio Coria
 */
abstract class AbstractPiece {
    final BiPredicate<Long, Long> isLegalMoveFn;

    AbstractPiece(BiPredicate<Long, Long> isLegalMoveFn) {
        this.isLegalMoveFn = isLegalMoveFn;
    }

    abstract int generateMoves(final MinChessWorkspace workspace, short[] moves, int startIdx);

    abstract boolean isKingInCheckByOpponent(final MinChessWorkspace workspace, long kingPosition, int kingIdx, boolean opponentColor);
}
