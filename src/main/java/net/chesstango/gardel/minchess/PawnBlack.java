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

    @Override
    int generateMoves(short[] moves, int startIdx) {
        int size = 0;
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentPositions = workspace.whitePositions;
        long fromPawns = workspace.pawnPositions & workspace.blackPositions;
        while (fromPawns != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromPawns);
            size += generateMoveForward(moves, startIdx + size, from, emptyPositions);
            size += generateDoubleMoveForward(moves, startIdx + size, from, emptyPositions);
            size += generateCaptureMove(moves, startIdx + size, from, opponentPositions);
            size += generateCaptureEnPassantMove(moves, startIdx + size, from);
            fromPawns &= ~from;
        }
        return size;
    }

    int generateCaptureEnPassantMove(short[] moves, int startIdx, long from) {
        int size = 0;
        if (workspace.enPassantSquare != 0) {
            if ((from & LIMIT_EAST) == 0) {
                final long toPosition = from >>> 7;
                if ((toPosition & workspace.enPassantSquare) != 0 && isLegalEnPassantMove(from, workspace.enPassantSquare)) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, toPosition);
                }
            }
            if ((from & LIMIT_WEST) == 0) {
                final long toPosition = from >>> 9;
                if ((toPosition & workspace.enPassantSquare) != 0 && isLegalMove(from, workspace.enPassantSquare)) {
                    size = createMove(moves, startIdx, from, toPosition);
                }
            }
        }
        return size;
    }

    boolean isLegalEnPassantMove(long from, long enPassantSquare) {
        workspaceTmp.copyFrom(workspace);
        workspaceTmp.doEnPassantMoveImp(from, enPassantSquare);
        return !workspaceTmp.isKingInCheck(workspace.whiteTurn);
    }

    int generateCaptureMove(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        size += generateCaptureSouthEast(moves, startIdx + size, from, opponentPositions);
        size += generateCaptureSouthWest(moves, startIdx + size, from, opponentPositions);
        return size;
    }

    int generateCaptureSouthEast(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        if ((from & LIMIT_EAST) == 0) {
            final long toPosition = from >>> 7;
            if ((toPosition & opponentPositions) != 0 && isLegalMove(from, toPosition)) {
                size = createMove(moves, startIdx, from, toPosition);
            }
        }
        return size;
    }

    int generateCaptureSouthWest(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        if ((from & LIMIT_WEST) == 0) {
            final long toPosition = from >>> 9;
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
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, PromotionPiece.KNIGHT); // Knight
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, PromotionPiece.BISHOP); // Bishop
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, PromotionPiece.ROOK); // Rook
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to, PromotionPiece.QUEEN); // Queen
        } else {
            moves[startIdx + size++] = MinChessConstants.encodeMove(from, to);
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long blackPawns = workspaceTmp.blackPositions & workspaceTmp.pawnPositions;
        if ((kingPosition & LIMIT_NORTH_WEST) == 0) {
            final long pawnPosition = kingPosition << 7;
            return (blackPawns & pawnPosition) != 0;
        }
        if ((kingPosition & LIMIT_NORTH_EAST) == 0) {
            final long pawnPosition = kingPosition << 9;
            return (blackPawns & pawnPosition) != 0;
        }
        return false;
    }
}
