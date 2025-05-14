package net.chesstango.gardel.minchess;

import static net.chesstango.gardel.minchess.MinChessConstants.KING_JUMPS;

/**
 * @author Mauricio Coria
 */
public class MinChess implements Cloneable {
    private boolean whiteTurn;
    private boolean castlingBlackKingAllowed;
    private boolean castlingBlackQueenAllowed;
    private boolean castlingWhiteKingAllowed;
    private boolean castlingWhiteQueenAllowed;

    private long enPassantSquare = 0;
    private long whitePositions = 0;
    private long blackPositions = 0;
    private long kingPositions = 0;
    private long queenPositions = 0;
    private long rookPositions = 0;
    private long bishopPositions = 0;
    private long knightPositions = 0;
    private long pawnPositions = 0;

    private MinChess workspace;

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
        this.whiteTurn = whiteTurn;
        this.castlingBlackKingAllowed = castlingBlackKingAllowed;
        this.castlingBlackQueenAllowed = castlingBlackQueenAllowed;
        this.castlingWhiteKingAllowed = castlingWhiteKingAllowed;
        this.castlingWhiteQueenAllowed = castlingWhiteQueenAllowed;
        this.enPassantSquare = enPassantSquare;
        this.whitePositions = whitePositions;
        this.blackPositions = blackPositions;

        this.kingPositions = kingPositions;
        this.queenPositions = queenPositions;
        this.rookPositions = rookPositions;
        this.bishopPositions = bishopPositions;
        this.knightPositions = knightPositions;
        this.pawnPositions = pawnPositions;

        this.workspace = new MinChess();
    }

    private MinChess() {
    }

    public int generateMoves(short[] moves) {
        int size = 0;
        size += generateKingMoves(moves);
        size += generateKnightMoves(moves);
        size += generateBishopMoves(moves);
        size += generateRookMoves(moves);
        size += generateQueenMoves(moves);
        size += generatePawnMoves(moves);
        return size;
    }

    public boolean isKingInCheck() {
        return isKingInCheck(whiteTurn);
    }

    public void doMove(short move) {

    }

    int generateKingMoves(short[] moves) {
        int size = 0;
        final long fromPosition = kingPositions & (whiteTurn ? whitePositions : blackPositions);
        final long emptyOrOpponentPositions = whiteTurn ? ~whitePositions : ~blackPositions;
        final int kingIdx = Long.numberOfTrailingZeros(fromPosition);
        final long kingJumps = KING_JUMPS[kingIdx];
        long jumpPositions = kingJumps & emptyOrOpponentPositions;
        while (jumpPositions != 0) {
            final int jumpIdx = Long.numberOfTrailingZeros(jumpPositions);
            final long toPosition = 1L << jumpIdx;
            if (isLegalMove(fromPosition, toPosition)) {
                moves[size] = MinChessConstants.encodeMove(fromPosition, toPosition);
                size++;
            }
            jumpPositions &= ~toPosition;
        }
        return size;
    }

    int generateKnightMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    int generateRookMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    int generateBishopMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
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
        workspace.copyFrom(this);
        workspace.doMoveImp(from, to);
        return !workspace.isKingInCheck(whiteTurn);
    }

    void copyFrom(MinChess other) {
        this.whiteTurn = other.whiteTurn;
        this.castlingBlackKingAllowed = other.castlingBlackKingAllowed;
        this.castlingBlackQueenAllowed = other.castlingBlackQueenAllowed;
        this.castlingWhiteKingAllowed = other.castlingWhiteKingAllowed;
        this.castlingWhiteQueenAllowed = other.castlingWhiteQueenAllowed;
        this.enPassantSquare = other.enPassantSquare;
        this.whitePositions = other.whitePositions;
        this.blackPositions = other.blackPositions;

        this.kingPositions = other.kingPositions;
        this.queenPositions = other.queenPositions;
        this.rookPositions = other.rookPositions;
        this.bishopPositions = other.bishopPositions;
        this.knightPositions = other.knightPositions;
        this.pawnPositions = other.pawnPositions;
    }

    void doMoveImp(long from, long to) {
        if ((from & kingPositions) != 0) {
            kingPositions &= ~from;
            kingPositions |= to;
        }
        if ((from & queenPositions) != 0) {
            queenPositions &= ~from;
            queenPositions |= to;
        }
        if ((from & rookPositions) != 0) {
            rookPositions &= ~from;
            rookPositions |= to;
        }
        if ((from & bishopPositions) != 0) {
            bishopPositions &= ~from;
            bishopPositions |= to;
        }
        if ((from & knightPositions) != 0) {
            knightPositions &= ~from;
            knightPositions |= to;
        }
        if ((from & pawnPositions) != 0) {
            pawnPositions &= ~from;
            pawnPositions |= to;
        }
        if (whiteTurn) {
            whitePositions &= ~from;
            whitePositions |= to;
        } else {
            blackPositions &= ~from;
            blackPositions |= to;
        }
        whiteTurn = !whiteTurn;
    }


    boolean isKingInCheck(boolean turn) {
        return isKingInCheckByOpponentKing(turn);
    }

    boolean isKingInCheckByOpponentKing(boolean kingColor) {
        final long kingPosition = kingPositions & (kingColor ? whitePositions : blackPositions);
        final int kingIdx = Long.numberOfTrailingZeros(kingPosition);
        final long kingJumps = KING_JUMPS[kingIdx];

        final long kingPositionOpponent = kingPositions & (!kingColor ? whitePositions : blackPositions);
        return (kingJumps & kingPositionOpponent) != 0;
    }


    public MinChess clone() {
        return new MinChess(whiteTurn,
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
    }
}
