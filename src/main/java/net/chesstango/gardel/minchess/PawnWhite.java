package net.chesstango.gardel.minchess;

import java.util.function.BiPredicate;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class PawnWhite extends AbstractPiece {
    static final long START_POS = 0x000000000000FF00L;
    final BiPredicate<Long, Long> isLegalEnPassantMoveFn;

    PawnWhite(BiPredicate<Long, Long> isLegalMoveFn, BiPredicate<Long, Long> isLegalEnPassantMoveFn) {
        super(isLegalMoveFn);
        this.isLegalEnPassantMoveFn = isLegalEnPassantMoveFn;
    }

    @Override
    int generateMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        final long opponentPositions = workspace.blackPositions;
        long fromPawns = workspace.pawnPositions & workspace.whitePositions;
        while (fromPawns != 0) {
            long from = 1L << Long.numberOfTrailingZeros(fromPawns);
            size += generateMoveForward(moves, startIdx + size, from, emptyPositions);
            size += generateDoubleMoveForward(moves, startIdx + size, from, emptyPositions);
            size += generateCaptureMove(moves, startIdx + size, from, opponentPositions);
            size += generateCaptureEnPassantMove(workspace.enPassantSquare, moves, startIdx + size, from);
            fromPawns &= ~from;
        }
        return size;
    }

    int generateCaptureEnPassantMove(final long enPassantSquare, short[] moves, int startIdx, long from) {
        int size = 0;
        if (enPassantSquare != 0) {
            if ((from & LIMIT_EAST) == 0) {
                final long toPosition = from << 9;
                if ((toPosition & enPassantSquare) != 0 && isLegalEnPassantMoveFn.test(from, enPassantSquare)) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(from, toPosition);
                }
            }
            if ((from & LIMIT_WEST) == 0) {
                final long toPosition = from << 7;
                if ((toPosition & enPassantSquare) != 0 && isLegalEnPassantMoveFn.test(from, enPassantSquare)) {
                    size = createMove(moves, startIdx, from, toPosition);
                }
            }
        }
        return size;
    }

    int generateCaptureMove(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        size += generateCaptureNorthEast(moves, startIdx + size, from, opponentPositions);
        size += generateCaptureNorthWest(moves, startIdx + size, from, opponentPositions);
        return size;
    }

    int generateCaptureNorthEast(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        if ((from & LIMIT_EAST) == 0) {
            final long toPosition = from << 9;
            if ((toPosition & opponentPositions) != 0 && isLegalMoveFn.test(from, toPosition)) {
                size = createMove(moves, startIdx, from, toPosition);
            }
        }
        return size;
    }

    int generateCaptureNorthWest(short[] moves, int startIdx, long from, long opponentPositions) {
        int size = 0;
        if ((from & LIMIT_WEST) == 0) {
            final long toPosition = from << 7;
            if ((toPosition & opponentPositions) != 0 && isLegalMoveFn.test(from, toPosition)) {
                size = createMove(moves, startIdx, from, toPosition);
            }
        }
        return size;
    }

    int generateMoveForward(short[] moves, int startIdx, long from, long emptyPositions) {
        int size = 0;
        final long to = from << 8;
        if ((to & emptyPositions) != 0 && isLegalMoveFn.test(from, to)) {
            size = createMove(moves, startIdx, from, to);
        }
        return size;
    }

    int generateDoubleMoveForward(short[] moves, int startIdx, long from, long emptyPositions) {
        int size = 0;
        if ((from & START_POS) != 0) {
            final long intermediate = from << 8;
            if ((intermediate & emptyPositions) != 0) {
                final long to = intermediate << 8;
                if ((to & emptyPositions) != 0) {
                    if (isLegalMoveFn.test(from, to)) {
                        moves[startIdx + size++] = MinChessConstants.encodeMove(from, to);
                    }
                }
            }
        }
        return size;
    }

    int createMove(short[] moves, int startIdx, long from, long to) {
        int size = 0;
        if ((to & LIMIT_NORTH) != 0) {
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
    boolean isKingInCheckByOpponent(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long whitePawns = workspace.whitePositions & workspace.pawnPositions;
        if ((kingPosition & LIMIT_SOUTH_WEST) == 0) {
            final long pawnPosition = kingPosition >>> 9;
            if ((whitePawns & pawnPosition) != 0) {
                return true;
            }
        }
        if ((kingPosition & LIMIT_SOUTH_EAST) == 0) {
            final long pawnPosition = kingPosition >>> 7;
            if ((whitePawns & pawnPosition) != 0) {
                return true;
            }
        }
        return false;
    }
}
