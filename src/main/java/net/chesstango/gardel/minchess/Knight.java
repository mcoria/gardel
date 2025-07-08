package net.chesstango.gardel.minchess;

import java.util.function.BiPredicate;

import static net.chesstango.gardel.minchess.MinChessConstants.KNIGHT_JUMPS;

/**
 * @author Mauricio Coria
 */
class Knight extends AbstractPiece {

    Knight(BiPredicate<Long, Long> isLegalMoveFn) {
        super(isLegalMoveFn);
    }

    @Override
    int generateMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        final long emptyOrOpponentPositions = workspace.whiteTurn ? ~workspace.whitePositions : ~workspace.blackPositions;
        long knights = workspace.knightPositions & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        while (knights != 0) {
            final int fromIdx = Long.numberOfTrailingZeros(knights);
            final long fromPosition = 1L << fromIdx;
            final long jumps = KNIGHT_JUMPS[fromIdx];
            long jumpPositions = jumps & emptyOrOpponentPositions;
            while (jumpPositions != 0) {
                final int jumpIdx = Long.numberOfTrailingZeros(jumpPositions);
                final long toPosition = 1L << jumpIdx;
                if (isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
                jumpPositions &= ~toPosition;
            }
            knights &= ~fromPosition;
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long kingJumps = KNIGHT_JUMPS[kingIdx];
        final long knightPositionOpponent = workspace.knightPositions & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        return (kingJumps & knightPositionOpponent) != 0;
    }
}
