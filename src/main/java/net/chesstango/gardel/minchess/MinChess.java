package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
public class MinChess implements Cloneable {

    final MinChessWorkspace workspace;
    final MinChessWorkspace workspaceTmp;

    final KingMoveGenerator kingMoveGenerator;
    final KnightMoveGenerator knightMoveGenerator;
    final RookMoveGenerator rookMoveGenerator;
    final BishopMoveGenerator bishopMoveGenerator;

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

        this.kingMoveGenerator = new KingMoveGenerator(workspace, workspaceTmp);
        this.knightMoveGenerator = new KnightMoveGenerator(workspace, workspaceTmp);
        this.rookMoveGenerator = new RookMoveGenerator(workspace, workspaceTmp);
        this.bishopMoveGenerator = new BishopMoveGenerator(workspace, workspaceTmp);
    }


    public int generateMoves(short[] moves) {
        int size = 0;
        size += kingMoveGenerator.generateKingMoves(moves, size);
        size += knightMoveGenerator.generateKnightMoves(moves, size);
        size += rookMoveGenerator.generateRookMoves(moves, size);
        size += bishopMoveGenerator.generateBishopMoves(moves, size);
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
