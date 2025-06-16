package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.LIMIT_NORTH;

/**
 * @author Mauricio Coria
 */
class PawnWhiteMoveGenerator extends AbstractMoveGenerator {

    PawnWhiteMoveGenerator(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
    }

    int generatePawnMoves(short[] moves, int startIdx) {
        int size = 0;
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentPositions = workspace.blackPositions;
        long fromPawns = workspace.pawnPositions & workspace.whitePositions;
        while (fromPawns != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromPawns);
            size += generateMoveForward(moves, startIdx + size, from, emptyPositions);
            fromPawns &= ~from;
        }
        return size;
    }

    int generateMoveForward(short[] moves, int startIdx, long from, long emptyPositions) {
        int size = 0;
        final long to = from << 8;
        if ((to & emptyPositions) != 0) {
            if (isLegalMove(from, to)) {
                if ((to & LIMIT_NORTH) != 0) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 1); // Knight
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 2); // Bishop
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 3); // Rook
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 4); // Queen
                } else {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, to);
                }
            }
        }
        return size;
    }
}
