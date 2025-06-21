package net.chesstango.gardel.minchess;

import lombok.Setter;

import static net.chesstango.gardel.minchess.MinChessConstants.*;

/**
 * @author Mauricio Coria
 */
class MinChessWorkspace {
    boolean whiteTurn;
    boolean castlingBlackKingAllowed;
    boolean castlingBlackQueenAllowed;
    boolean castlingWhiteKingAllowed;
    boolean castlingWhiteQueenAllowed;

    long enPassantSquare = 0;
    long whitePositions = 0;
    long blackPositions = 0;
    long kingPositions = 0;
    long queenPositions = 0;
    long rookPositions = 0;
    long bishopPositions = 0;
    long knightPositions = 0;
    long pawnPositions = 0;

    @Setter
    King king;

    @Setter
    Knight knight;

    @Setter
    Rook rook;

    @Setter
    Bishop bishop;

    @Setter
    Pawn pawn;

    MinChessWorkspace(boolean whiteTurn,
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
    }

    MinChessWorkspace() {
    }

    void copyFrom(MinChessWorkspace other) {
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
        // Capture, clean to square
        if ((to & whitePositions) != 0 || (to & blackPositions) != 0) {
            if ((to & queenPositions) != 0) {
                queenPositions &= ~to;
            }
            if ((to & rookPositions) != 0) {
                rookPositions &= ~to;
                if (to == A1) {
                    castlingWhiteQueenAllowed = false;
                } else if (to == H1) {
                    castlingWhiteKingAllowed = false;
                } else if (to == A8) {
                    castlingBlackQueenAllowed = false;
                } else if (to == H8) {
                    castlingBlackKingAllowed = false;
                }
            }
            if ((to & bishopPositions) != 0) {
                bishopPositions &= ~to;
            }
            if ((to & knightPositions) != 0) {
                knightPositions &= ~to;
            }
            if ((to & pawnPositions) != 0) {
                pawnPositions &= ~to;
            }
            if (whiteTurn) {
                blackPositions &= ~to;
            } else {
                whitePositions &= ~to;
            }
        }

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

        enPassantSquare = 0;

        if (whiteTurn) {
            whitePositions &= ~from;
            whitePositions |= to;
        } else {
            blackPositions &= ~from;
            blackPositions |= to;
        }
        whiteTurn = !whiteTurn;
    }

    void doMovePromotionImp(long from, long to, MinChessConstants.PromotionPiece promotionPiece) {
        pawnPositions &= ~from;
        switch (promotionPiece) {
            case KNIGHT:
                knightPositions |= from;
                break;
            case BISHOP:
                bishopPositions |= from;
                break;
            case ROOK:
                rookPositions |= from;
                break;
            case QUEEN:
                queenPositions |= from;
                break;
            default:
                throw new IllegalArgumentException("Invalid promotion piece: " + promotionPiece);
        }
        doMoveImp(from, to);
    }

    void doEnPassantMoveImp(long from, long enPassantSquare) {
        if (whiteTurn) {
            long enPassantPawn = enPassantSquare >>> 8;
            blackPositions &= ~enPassantPawn;
            pawnPositions &= ~enPassantPawn;
        } else {
            long enPassantPawn = enPassantSquare << 8;
            whitePositions &= ~enPassantPawn;
            pawnPositions &= ~enPassantPawn;
        }
        doMoveImp(from, enPassantSquare);
    }


    boolean isKingInCheck(boolean turn) {
        final long kingPosition = kingPositions & (turn ? whitePositions : blackPositions);
        final int kingIdx = Long.numberOfTrailingZeros(kingPosition);
        return king.isKingInCheckByOpponent(kingPosition, kingIdx, !turn) ||
                knight.isKingInCheckByOpponent(kingPosition, kingIdx, !turn) ||
                rook.isKingInCheckByOpponent(kingPosition, kingIdx, !turn) ||
                bishop.isKingInCheckByOpponent(kingPosition, kingIdx, !turn) ||
                pawn.isKingInCheckByOpponentPawn(kingPosition, kingIdx, !turn);
    }
}
