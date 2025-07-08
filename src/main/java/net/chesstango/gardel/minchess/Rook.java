package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class Rook extends AbstractPiece {

    Rook(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
        workspace.setRook(this);
        workspaceTmp.setRook(this);
    }

    @Override
    int generateMoves(short[] moves, int startIdx) {
        int size = 0;
        long fromRooks = (workspace.rookPositions | workspace.queenPositions) & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        while (fromRooks != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromRooks);
            size += generateRookMovesNorth(moves, startIdx + size, from);
            size += generateRookMovesSouth(moves, startIdx + size, from);
            size += generateRookMovesEast(moves, startIdx + size, from);
            size += generateRookMovesWest(moves, startIdx + size, from);
            fromRooks &= ~from;
        }
        return size;
    }

    int generateRookMovesWest(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_WEST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 1;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_WEST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateRookMovesEast(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_EAST) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 1;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_EAST) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateRookMovesNorth(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_NORTH) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition << 8;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_NORTH) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }

    int generateRookMovesSouth(short[] moves, int startIdx, long fromPosition) {
        int size = 0;
        final long allPositions = workspace.whitePositions | workspace.blackPositions;
        final long ownPositions = workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions;
        if ((fromPosition & LIMIT_SOUTH) == 0) {
            long toPosition = fromPosition;
            do {
                toPosition = toPosition >>> 8;
                if ((toPosition & ownPositions) == 0 && isLegalMove(fromPosition, toPosition)) {
                    moves[startIdx + size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                    size++;
                }
            } while ((toPosition & LIMIT_SOUTH) == 0 && (toPosition & allPositions) == 0);
        }
        return size;
    }



    @Override
    boolean isKingInCheckByOpponent(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        return isKingInCheckByOpponentRookNorth(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookSouth(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookEast(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookWest(kingPosition, kingIdx, opponentColor);

    }

    boolean isKingInCheckByOpponentRookWest(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentRooks = (workspaceTmp.rookPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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

    boolean isKingInCheckByOpponentRookEast(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentRooks = (workspaceTmp.rookPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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

    boolean isKingInCheckByOpponentRookNorth(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(workspaceTmp.whitePositions | workspaceTmp.blackPositions);
        final long opponentRooks = (workspaceTmp.rookPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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

    boolean isKingInCheckByOpponentRookSouth(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long allPositions = workspaceTmp.whitePositions | workspaceTmp.blackPositions;
        final long opponentRooks = (workspaceTmp.rookPositions | workspaceTmp.queenPositions) & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
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
