package net.chesstango.gardel.minchess;

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
        if ((to & whitePositions) != 0 || (to & blackPositions) != 0) {
            if ((to & queenPositions) != 0) {
                queenPositions &= ~to;
            }
            if ((to & rookPositions) != 0) {
                rookPositions &= ~to;
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
        final long kingPosition = kingPositions & (turn ? whitePositions : blackPositions);
        final int kingIdx = Long.numberOfTrailingZeros(kingPosition);
        return isKingInCheckByOpponentKing(kingPosition, kingIdx, !turn) ||
                isKingInCheckByOpponentKnights(kingPosition, kingIdx, !turn) ||
                isKingInCheckByOpponentRook(kingPosition, kingIdx, !turn) ||
                isKingInCheckByOpponentBishop(kingPosition, kingIdx, !turn);
    }

    boolean isKingInCheckByOpponentBishop(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        return isKingInCheckByOpponentBishopNorthWest(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopNorthEast(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopSouthWest(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentBishopSouthEast(kingPosition, kingIdx, opponentColor);

    }

    boolean isKingInCheckByOpponentBishopNorthWest(long kingPosition, int kingIdx, boolean opponentColor) {
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentBishops = (bishopPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentBishops = (bishopPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentBishops = (bishopPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentBishops = (bishopPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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

    boolean isKingInCheckByOpponentRook(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        return isKingInCheckByOpponentRookNorth(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookSouth(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookEast(kingPosition, kingIdx, opponentColor) ||
                isKingInCheckByOpponentRookWest(kingPosition, kingIdx, opponentColor);

    }

    boolean isKingInCheckByOpponentRookWest(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentRooks = (rookPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentRooks = (rookPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentRooks = (rookPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
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
        final long emptyPositions = ~(whitePositions | blackPositions);
        final long opponentRooks = (rookPositions | queenPositions) & (opponentColor ? whitePositions : blackPositions);
        if ((kingPosition & LIMIT_SOUTH) == 0) {
            long toPosition = kingPosition;
            do {
                toPosition = toPosition >>> 8;
                if ((toPosition & opponentRooks) != 0) {
                    return true;
                }
            } while ((toPosition & LIMIT_SOUTH) == 0 && (toPosition & emptyPositions) != 0);
        }
        return false;
    }

    boolean isKingInCheckByOpponentKing(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long kingJumps = KING_JUMPS[kingIdx];
        final long kingPositionOpponent = kingPositions & (opponentColor ? whitePositions : blackPositions);
        return (kingJumps & kingPositionOpponent) != 0;
    }

    boolean isKingInCheckByOpponentKnights(final long kingPosition, final int kingIdx, final boolean opponentColor) {
        final long kingJumps = KNIGHT_JUMPS[kingIdx];
        final long knightPositionOpponent = knightPositions & (opponentColor ? whitePositions : blackPositions);
        return (kingJumps & knightPositionOpponent) != 0;
    }
}
