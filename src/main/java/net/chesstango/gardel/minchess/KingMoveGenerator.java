package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.KING_JUMPS;

/**
 * @author Mauricio Coria
 */
class KingMoveGenerator extends AbstractMoveGenerator {

    KingMoveGenerator(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
    }

    int generateKingMoves(short[] moves, int startIdx) {
        int size = 0;
        final long emptyOrOpponentPositions = workspace.whiteTurn ? ~workspace.whitePositions : ~workspace.blackPositions;
        final long fromPosition = workspace.kingPositions & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        final int fromIdx = Long.numberOfTrailingZeros(fromPosition);
        final long jumps = KING_JUMPS[fromIdx];
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
        return size;
    }
}
