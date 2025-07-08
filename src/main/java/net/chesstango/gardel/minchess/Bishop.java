package net.chesstango.gardel.minchess;

import java.util.function.BiPredicate;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class Bishop extends AbstractPiece {

    Bishop(BiPredicate<Long, Long> isLegalMoveFn) {
        super(isLegalMoveFn);
    }

    @Override
    int generateMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        long fromBishops = (workspace.bishopPositions | workspace.queenPositions) & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        while (fromBishops != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromBishops);
            size += generateBishopMovesNorthWest(workspace, moves, startIdx + size, from);
            size += generateBishopMovesNorthEast(workspace, moves, startIdx + size, from);
            size += generateBishopMovesSouthWest(workspace, moves, startIdx + size, from);
            size += generateBishopMovesSouthEast(workspace, moves, startIdx + size, from);
            fromBishops &= ~from;
        }
        return size;
    }

    int generateBishopMovesNorthWest(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_NORTH_WEST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 7;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_NORTH_WEST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateBishopMovesNorthEast(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_NORTH_EAST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 9;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_NORTH_EAST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateBishopMovesSouthWest(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_SOUTH_WEST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 9;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_SOUTH_WEST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateBishopMovesSouthEast(final MinChessWorkspace workspace, short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_SOUTH_EAST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 7;
                if ((toPosition & ownPositions) == 0 && isLegalMoveFn.test(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_SOUTH_EAST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        return isKingInCheckByOpponentBishopNorthWest(workspace, kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopNorthEast(workspace, kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopSouthWest(workspace, kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopSouthEast(workspace, kingPosition, kingIdx, opponentColor);

    }

    boolean isKingInCheckByOpponentBishopNorthWest(final MinChessWorkspace workspace, long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentBishops = (workspace.bishopPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_NORTH_WEST) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition << 7;
                if ((toPosition & opponentBishops) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_NORTH_WEST) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentBishopNorthEast(final MinChessWorkspace workspace, long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentBishops = (workspace.bishopPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_NORTH_EAST) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition << 9;
                if ((toPosition & opponentBishops) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_NORTH_EAST) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentBishopSouthWest(final MinChessWorkspace workspace, long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentBishops = (workspace.bishopPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_SOUTH_WEST) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition >>> 9;
                if ((toPosition & opponentBishops) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_SOUTH_WEST) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentBishopSouthEast(final MinChessWorkspace workspace, long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentBishops = (workspace.bishopPositions | workspace.queenPositions) & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        if ((kingPosition & LIMIT_SOUTH_EAST) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition >>> 7;
                if ((toPosition & opponentBishops) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_SOUTH_EAST) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

}
