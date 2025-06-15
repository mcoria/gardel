package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
public class MinChess implements Cloneable {

    private final MinChessWorkspace workspace;
    private final MinChessWorkspace workspaceTmp;

    public MinChess(boolean whiteTurn,
                    boolean castlingBlackKingAllowed,
                    boolean castlingBlackQueenAllowed,
                    boolean castlingWhiteKingAllowed,
                    boolean castlingWhiteQueenAllowed,
                    long enPassantSquare,
                    long whitePositions,
                    long blackPositions,
                    long kingPositions,
                    long queenPositions,
                    long rookPositions,
                    long bishopPositions,
                    long knightPositions,
                    long pawnPositions) {

        this.workspace = new MinChessWorkspace(whiteTurn,
                castlingBlackKingAllowed,
                castlingBlackQueenAllowed,
                castlingWhiteKingAllowed,
                castlingWhiteQueenAllowed,
                enPassantSquare,
                whitePositions,
                blackPositions,
                kingPositions,
                queenPositions,
                rookPositions,
                bishopPositions,
                knightPositions,
                pawnPositions
        );

        this.workspaceTmp = new MinChessWorkspace();
    }


    public int generateMoves(short[] moves) {
        int size = 0;
        size += generateKingMoves(moves, size);
        size += generateKnightMoves(moves, size);
        size += generateRookMoves(moves, size);
        size += generateBishopMoves(moves, size);
        //size += generatePawnMoves(moves);
        return size;
    }

    public boolean isKingInCheck() {
        return workspace.isKingInCheck(workspace.whiteTurn);
    }

    public void doMove(short move) {
        int toFile = move & 0b00000000_00000111;
        int toRank = (move & 0b00000000_00111000) >>> 3;
        long toPosition = 1L << (toRank * 8 + toFile);


        int fromFile = (move & 0b00000001_11000000) >>> 6;
        int fromRank = (move & 0b00001110_00000000) >>> 9;
        long fromPosition = 1L << (fromRank * 8 + fromFile);

        workspace.doMoveImp(fromPosition, toPosition);
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

    int generateRookMoves(short[] moves, int startIdx) {
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

    int generateQueenMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    int generatePawnMoves(short[] moves) {
        return 0;
    }

    int generatePawnWhiteMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    int generatePawnBlackMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    boolean isLegalMove(long from, long to) {
        workspaceTmp.copyFrom(workspace);
        workspaceTmp.doMoveImp(from, to);
        return !workspaceTmp.isKingInCheck(workspace.whiteTurn);
    }


    public MinChess clone() {
        return new MinChess(workspace.whiteTurn,
                workspace.castlingBlackKingAllowed,
                workspace.castlingBlackQueenAllowed,
                workspace.castlingWhiteKingAllowed,
                workspace.castlingWhiteQueenAllowed,
                workspace.enPassantSquare,
                workspace.whitePositions,
                workspace.blackPositions,
                workspace.kingPositions,
                workspace.queenPositions,
                workspace.rookPositions,
                workspace.bishopPositions,
                workspace.knightPositions,
                workspace.pawnPositions
        );
    }
}
