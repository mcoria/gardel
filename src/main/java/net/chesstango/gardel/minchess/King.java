package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.KING_JUMPS;

/**
 * @author Mauricio Coria
 */
class King extends AbstractPiece {

    King(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
        workspace.setKing(this);
        workspaceTmp.setKing(this);
    }

    @Override
    int generateMoves(short[] moves, int startIdx) {
        int size = 0;
        size += generateJumpMoves(moves, size);
        size += generateCastlingMoves(moves, size);
        return size;
    }

    final long CASTLING_WHITE_KING = 0x0000000000000060L;
    final long CASTLING_WHITE_QUEEN = 0x000000000000000EL;

    final long C1 = 0x04L;
    final long D1 = 0x08L;

    final long E1 = 0x10L;

    final long F1 = 0x20L;
    final long G1 = 0x40L;

    int generateCastlingMoves(short[] moves, int startIdx) {
        int size = 0;
        final long emptyPositions = ~(workspace.whitePositions | workspace.blackPositions);
        if (workspace.whiteTurn) {
            final long fromPosition = workspace.kingPositions;
            if ((fromPosition & E1) != 0) {
                if (workspace.castlingWhiteKingAllowed && (CASTLING_WHITE_KING & emptyPositions) == CASTLING_WHITE_KING) {
                    if (isLegalMove(fromPosition, F1) && isLegalMove(fromPosition, G1)) {
                        moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, G1);
                    }
                }
                if (workspace.castlingWhiteQueenAllowed && (CASTLING_WHITE_QUEEN & emptyPositions) == CASTLING_WHITE_QUEEN) {
                    if (isLegalMove(fromPosition, C1) && isLegalMove(fromPosition, D1)) {
                        moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, C1);
                    }
                }
            }
        } else {
            final long fromPosition = workspace.blackPositions;
            if (workspace.castlingBlackKingAllowed) {

            } else if (workspace.castlingBlackQueenAllowed) {

            }
        }
        return size;
    }


    int generateJumpMoves(short[] moves, int startIdx) {
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
                moves[startIdx + size++] = MinChessConstants.encodeMove(fromPosition, toPosition);
            }
            jumpPositions &= ~toPosition;
        }
        return size;
    }

    @Override
    boolean isKingInCheckByOpponent(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long kingJumps = KING_JUMPS[kingIdx];
        final long kingPositionOpponent = workspaceTmp.kingPositions & (opponentColor ? workspaceTmp.whitePositions : workspaceTmp.blackPositions);
        return (kingJumps & kingPositionOpponent) != 0;
    }
}
