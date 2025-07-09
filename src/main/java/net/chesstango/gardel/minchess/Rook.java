package net.chesstango.gardel.minchess;

import java.util.function.BiPredicate;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class Rook extends AbstractPiece {

    Rook(BiPredicate<Long, Long> isLegalMoveFn) {
        super(isLegalMoveFn);
    }

    @Override
    int generateMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        long fromRooks = (workspace.rookPositions | workspace.queenPositions) & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        while (fromRooks != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromRooks);
            size += generateRookMovesNorth(workspace, moves, startIdx + size, from);
            size += generateRookMovesSouth(workspace, moves, startIdx + size, from);
            size += generateRookMovesEast(workspace, moves, startIdx + size, from);
            size += generateRookMovesWest(workspace, moves, startIdx + size, from);
            fromRooks &= ~from;
        }
        return size;
    }

    int generateRookMovesWest(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_WEST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 1;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_WEST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateRookMovesEast(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_EAST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 1;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_EAST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateRookMovesNorth(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_NORTH) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 8;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_NORTH) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateRookMovesSouth(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_SOUTH) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 8;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_SOUTH) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }



    @Override
    boolean isKingInCheckByOpponent(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        return isKingInCheckByOpponentRookNorth(workspace, kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookSouth(workspace, kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookEast(workspace, kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookWest(workspace, kingPosition, kingIdx, opponentColor);

    }

    boolean isKingInCheckByOpponentRookWest(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentRooks = (workspace.rookPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_WEST) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition >>> 1;
                if ((toPosition & opponentRooks) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_WEST) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentRookEast(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentRooks = (workspace.rookPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_EAST) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition << 1;
                if ((toPosition & opponentRooks) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_EAST) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentRookNorth(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentRooks = (workspace.rookPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_NORTH) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition << 8;
                if ((toPosition & opponentRooks) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_NORTH) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentRookSouth(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long opponentRooks = (workspace.rookPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_SOUTH) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition >>> 8;
                if ((toPosition & opponentRooks) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_SOUTH) == 0 && (toPosition & allPositions) == 0);
        }
        return false;
    }
}
