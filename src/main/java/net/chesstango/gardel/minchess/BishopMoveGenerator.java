package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.*;
import static net.chesstango.gardel.minchess.MinChessConstants.LIMIT_NORTH_EAST;
import static net.chesstango.gardel.minchess.MinChessConstants.LIMIT_SOUTH_EAST;
import static net.chesstango.gardel.minchess.MinChessConstants.LIMIT_SOUTH_WEST;

/**
 * @author Mauricio Coria
 */
class BishopMoveGenerator extends AbstractMoveGenerator {

    BishopMoveGenerator(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
    }

    int generateBishopMoves(short[] moves, int startIdx) {
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
}
