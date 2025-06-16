package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class Bishop extends AbstractPiece {

    Bishop(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
        workspace.setBishop(this);
        workspaceTmp.setBishop(this);
    }

    @Override
    int generateMoves(short[] moves, int startIdx) {
        int size = 0;
        long fromBishops = (workspace.bishopPositions | workspace.queenPositions) & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        while (fromBishops != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromBishops);
            size += generateBishopMovesNorthWest(moves, startIdx + size, from);
            size += generateBishopMovesNorthEast(moves, startIdx + size, from);
            size += generateBishopMovesSouthWest(moves, startIdx + size, from);
            size += generateBishopMovesSouthEast(moves, startIdx + size, from);
            fromBishops &= ~from;
        }
        return size;
    }

    int generateBishopMovesNorthWest(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_NORTH_WEST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 7;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_NORTH_WEST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateBishopMovesNorthEast(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_NORTH_EAST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 9;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_NORTH_EAST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateBishopMovesSouthWest(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_SOUTH_WEST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 9;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_SOUTH_WEST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateBishopMovesSouthEast(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_SOUTH_EAST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 7;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_SOUTH_EAST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        return isKingInCheckByOpponentBishopNorthWest(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopNorthEast(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopSouthWest(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopSouthEast(kingPosition, kingIdx, opponentColor);

    }

    boolean isKingInCheckByOpponentBishopNorthWest(long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentBishops = (workspaceTmp.bishopPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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

    boolean isKingInCheckByOpponentBishopNorthEast(long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentBishops = (workspaceTmp.bishopPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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

    boolean isKingInCheckByOpponentBishopSouthWest(long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentBishops = (workspaceTmp.bishopPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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

    boolean isKingInCheckByOpponentBishopSouthEast(long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentBishops = (workspaceTmp.bishopPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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
