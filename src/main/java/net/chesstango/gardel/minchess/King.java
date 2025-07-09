package net.chesstango.gardel.minchess;


import java.util.function.BiPredicate;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class King extends AbstractPiece {
    King(BiPredicate<Long, Long> isLegalMoveFn) {
        super(isLegalMoveFn);
    }

    @Override
    int generateMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        size += generateJumpMoves(workspace, moves, size);
        size += generateCastlingMoves(workspace, moves, size);
        return size;
    }

    int generateCastlingMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        return workspace.whiteTurn ? generateWhiteCastlingMoves(workspace, moves, startIdx) : generateBlackCastlingMoves(workspace, moves, startIdx);
    }

    final long CASTLING_BLACK_KING = 0x6000000000000000L;
    final long CASTLING_BLACK_QUEEN = 0x0E00000000000000L;

    int generateBlackCastlingMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        if (!workspace.isKingInCheck(false)) {
            final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
            final long fromPosition = workspace.blackPositions & workspace.kingPositions;
            if (workspace.castlingBlackKingAllowed && (CASTLING_BLACK_KING & emptyPositions) == CASTLING_BLACK_KING) {
                if (isLegalMoveFn.test(fromPosition, F8) && isLegalMoveFn.test(fromPosition, G8)) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, G8);
                }
            }
            if (workspace.castlingBlackQueenAllowed && (CASTLING_BLACK_QUEEN & emptyPositions) == CASTLING_BLACK_QUEEN) {
                if (isLegalMoveFn.test(fromPosition, C8) && isLegalMoveFn.test(fromPosition, D8)) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, C8);
                }
            }
        }
        return size;
    }


    final long CASTLING_WHITE_KING = 0x0000000000000060L;
    final long CASTLING_WHITE_QUEEN = 0x000000000000000EL;

    int generateWhiteCastlingMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        if (!workspace.isKingInCheck(true)) {
            final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
            final long fromPosition = workspace.whitePositions & workspace.kingPositions;
            if (workspace.castlingWhiteKingAllowed && (CASTLING_WHITE_KING & emptyPositions) == CASTLING_WHITE_KING) {
                if (isLegalMoveFn.test(fromPosition, F1) && isLegalMoveFn.test(fromPosition, G1)) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, G1);
                }
            }
            if (workspace.castlingWhiteQueenAllowed && (CASTLING_WHITE_QUEEN & emptyPositions) == CASTLING_WHITE_QUEEN) {
                if (isLegalMoveFn.test(fromPosition, C1) && isLegalMoveFn.test(fromPosition, D1)) {
                    moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, C1);
                }
            }
        }
        return size;
    }


    int generateJumpMoves(final MinChessWorkspace workspace, short[] moves, int startIdx) {
        int size = 0;
        final long emptyOrOpponentPositions = workspace.whiteTurn ? ~workspace.whitePositions : ~workspace.blackPositions;
        final long fromPosition = workspace.kingPositions & (workspace.whiteTurn ? workspace.whitePositions : workspace.blackPositions);
        final int fromIdx = Long.numberOfTrailingZeros(fromPosition);
        final long jumps = KING_JUMPS[fromIdx];
        long jumpPositions = jumps & emptyOrOpponentPositions;
        while (jumpPositions != 0) {
            final int jumpIdx = Long.numberOfTrailingZeros(jumpPositions);
            final long toPosition = 1L << jumpIdx;
            if (isLegalMoveFn.test(fromPosition, toPosition)) {
                moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, toPosition);
            }
            jumpPositions &= ~toPosition;
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final MinChessWorkspace workspace, final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long kingJumps = KING_JUMPS[kingIdx];
        final long kingPositionOpponent = workspace.kingPositions & (opponentColor ? workspace.whitePositions : workspace.blackPositions);
        return (kingJumps & kingPositionOpponent) != 0;
    }
}
