package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.KNIGHT_JUMPS;

/**
 * @author Mauricio Coria
 */
class KnightMoveGenerator extends AbstractMoveGenerator {

    KnightMoveGenerator(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
    }

    int generateKnightMoves(short[] moves, int startIdx) {
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
}
