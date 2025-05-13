package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
public class MinChess {
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
    }

    public int generateMoves(short[] moves) {
        int size = 0;
        size += generateKingMoves(moves);
        size += generateKnightMoves(moves);
        size += generateBishopMoves(moves);
        size += generateRookMoves(moves);
        size += generateQueenMoves(moves);
        size += whiteTurn ? generatePawnWhiteMoves(moves) : generatePawnBlackMoves(moves);
        return size;
    }

    int generateKingMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
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

    int generatePawnWhiteMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    int generatePawnBlackMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    boolean isLegalMove(short move) {
        boolean isKingInCheck;
        doMove(move);
        isKingInCheck = isKingInCheck();
        undoMove(move);
        return isKingInCheck;
    }

    private boolean isKingInCheck() {
        return false;
    }

    void undoMove(short move) {
    }

    void doMove(short move) {

    }


}
