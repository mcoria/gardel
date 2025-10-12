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
    final MinChess minChess;

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
                      long pawnPositions,
                      MinChess minChess) {
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
        this.minChess = minChess;
    }

    MinChessWorkspace(MinChess minChess) {
        this.minChess = minChess;
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
            if (from == E1) {
                castlingWhiteKingAllowed = false;
                castlingWhiteQueenAllowed = false;
            } else if (from == E8) {
                castlingBlackKingAllowed = false;
                castlingBlackQueenAllowed = false;
            }
        }
        if ((from & queenPositions) != 0) {
            queenPositions &= ~from;
            queenPositions |= to;
        }
        if ((from & rookPositions) != 0) {
            rookPositions &= ~from;
            rookPositions |= to;
            if (from == A1) {
                castlingWhiteQueenAllowed = false;
            } else if (from == H1) {
                castlingWhiteKingAllowed = false;
            } else if (from == A8) {
                castlingBlackQueenAllowed = false;
            } else if (from == H8) {
                castlingBlackKingAllowed = false;
            }
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

    void doMovePromotionImp(long from, long to, int promotionPiece) {
        pawnPositions &= ~from;
        switch (promotionPiece) {
            case 1:
                knightPositions |= from;
                break;
            case 2:
                bishopPositions |= from;
                break;
            case 3:
                rookPositions |= from;
                break;
            case 4:
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

    void doCastlingMoveImp(long from, long to) {
        if (whiteTurn) {
            if (from == E1 && to == G1) {
                rookPositions &= ~H1;
                rookPositions |= F1;

                whitePositions &= ~H1;
                whitePositions |= F1;
            } else if (from == E1 && to == C1) {
                rookPositions &= ~A1;
                rookPositions |= D1;

                whitePositions &= ~A1;
                whitePositions |= D1;
            } else {
                throw new RuntimeException("Error");
            }
            castlingWhiteKingAllowed = false;
            castlingWhiteQueenAllowed = false;
        } else {
            if (from == E8 && to == G8) {
                rookPositions &= ~H8;
                rookPositions |= F8;

                blackPositions &= ~H8;
                blackPositions |= F8;
            } else if (from == E8 && to == C8) {
                rookPositions &= ~A8;
                rookPositions |= D8;

                blackPositions &= ~A8;
                blackPositions |= D8;
            } else {
                throw new RuntimeException("Error");
            }
            castlingBlackKingAllowed = false;
            castlingBlackQueenAllowed = false;
        }

        doMoveImp(from, to);
    }

    boolean isKingInCheck(boolean turn) {
        return minChess.isKingInCheck(this, turn);
    }

    void validate() {
        if ((whitePositions & blackPositions) != 0) {
            throw new RuntimeException("White and Black positions collide");
        }
        long allPositions = whitePositions | blackPositions;
        if ((allPositions & kingPositions) != kingPositions) {
            throw new RuntimeException("King positions don't match");
        }
        allPositions &= ~kingPositions;
        if ((allPositions & queenPositions) != queenPositions) {
            throw new RuntimeException("Queen positions don't match");
        }
        allPositions &= ~queenPositions;
        if ((allPositions & rookPositions) != rookPositions) {
            throw new RuntimeException("Rook positions don't match");
        }
        allPositions &= ~rookPositions;
        if ((allPositions & bishopPositions) != bishopPositions) {
            throw new RuntimeException("Bishop positions don't match");
        }
        allPositions &= ~bishopPositions;
        if ((allPositions & knightPositions) != knightPositions) {
            throw new RuntimeException("Knight positions don't match");
        }
        allPositions &= ~knightPositions;
        if ((allPositions & pawnPositions) != pawnPositions) {
            throw new RuntimeException("Pawn positions don't match");
        }
        allPositions &= ~pawnPositions;
        if (allPositions != 0) {
            throw new RuntimeException("All positions don't match");
        }
    }
}
