package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class PawnBlack extends AbstractPiece {
    static final long START_POS = 0x00FF000000000000L;

    PawnBlack(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
    }

    int generatePawnMoves(short[] moves, int startIdx) {
        int size = 0;
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentPositions = workspace.whitePositions;
        long fromPawns = workspace.pawnPositions & workspace.blackPositions;
        while (fromPawns != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromPawns);
            size += generateMoveForward(moves, startIdx + size, from, emptyPositions);
            size += generateDoubleMoveForward(moves, startIdx + size, from, emptyPositions);
            size += generateCaptureMove(moves, startIdx + size, from, opponentPositions);
            fromPawns &= ~from;
        }
        return size;
    }

    int generateCaptureMove(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        size += generateCaptureSouthEast(moves, startIdx + size, from, opponentPositions);
        return size;
    }

    int generateCaptureSouthEast(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        if ((from & LIMIT_EAST) == 0) {
            long toPosition = from >>> 7;
            if ((toPosition & opponentPositions) != 0 && isLegalMove(from, toPosition)) {
                size = createMove(moves, startIdx, from, toPosition);
            }
        }
        return size;
    }

    int generateMoveForward(short[] moves, int startIdx, long from, long emptyPositions) {
        int size = 0;
        final long to = from >>> 8;
        if ((to & emptyPositions) != 0 && isLegalMove(from, to)) {
            size = createMove(moves, startIdx, from, to);
        }
        return size;
    }

    int generateDoubleMoveForward(short[] moves, int startIdx, long from, long emptyPositions) {
        int size = 0;
        if ((from & START_POS) != 0) {
            final long intermediate = from >>> 8;
            if ((intermediate & emptyPositions) != 0) {
                final long to = intermediate >>> 8;
                if ((to & emptyPositions) != 0) {
                    if (isLegalMove(from, to)) {
                        moves[startIdx + size++] = MinChessConstants.encodeMove(from, to);
                    }
                }
            }
        }
        return size;
    }


    int createMove(short[] moves, int startIdx, long from, long to) {
        int size = 0;
        if ((to & LIMIT_SOUTH) != 0) {
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 1); // Knight
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 2); // Bishop
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 3); // Rook
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, 4); // Queen
        } else {
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to);
        }
        return size;
    }
}
