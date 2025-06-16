package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.KNIGHT_JUMPS;

/**
 * @author Mauricio Coria
 */
class Knight extends AbstractPiece {

    Knight(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
        workspace.setKnight(this);
        workspaceTmp.setKnight(this);
    }

    @Override
    int generateMoves(short[] moves, int startIdx) {
        int size = 0;
        final long emptyOrOpponentPositions = workspace.whiteTurn ? ~workspace.whitePositions : ~workspace.blackPositions;
        long fromPosition = workspace.knightPositions & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        while (fromPosition != 0) {
            final int fromIdx = Long.numberOfTrailingZeros(fromPosition);
            final long jumps = KNIGHT_JUMPS[fromIdx];
            long jumpPositions = jumps & emptyOrOpponentPositions;
            while (jumpPositions != 0) {
                final int jumpIdx = Long.numberOfTrailingZeros(jumpPositions);
                final long toPosition = 1L << jumpIdx;
                if (isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
                jumpPositions &= ~toPosition;
            }
            fromPosition &= ~(1L << fromIdx);
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long kingJumps = KNIGHT_JUMPS[kingIdx];
        final long knightPositionOpponent = workspaceTmp.knightPositions & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
        return (kingJumps & knightPositionOpponent) != 0;
    }
}
