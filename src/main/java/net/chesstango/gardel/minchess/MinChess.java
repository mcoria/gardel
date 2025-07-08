package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
public class MinChess implements Cloneable {
    final MinChessWorkspace workspace;
    final MinChessWorkspace workspaceTmp;

    final King king;
    final Knight knight;
    final Rook rook;
    final Bishop bishop;
    final Pawn pawn;

    boolean validate = false;

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

        this.king = new King(this::isLegalMove, turn -> this.isKingInCheck(workspace, turn));
        this.knight = new Knight(this::isLegalMove);
        this.rook = new Rook(this::isLegalMove);
        this.bishop = new Bishop(this::isLegalMove);
        this.pawn = new Pawn(this::isLegalMove, this::isLegalEnPassantMove);
    }


    public int generateMoves(short[] moves) {
        int size = 0;
        size += king.generateMoves(workspace, moves, size);
        size += knight.generateMoves(workspace, moves, size);
        size += rook.generateMoves(workspace, moves, size);
        size += bishop.generateMoves(workspace, moves, size);
        size += pawn.generatePawnMoves(workspace, moves, size);
        return size;
    }

    public void doMove(short move) {
        int toFile = move & 0b00000000_00000111;
        int toRank = (move & 0b00000000_00111000) >>> 3;
        long toPosition = 1L << (toRank * 8 + toFile);

        int fromFile = (move & 0b00000001_11000000) >>> 6;
        int fromRank = (move & 0b00001110_00000000) >>> 9;
        long fromPosition = 1L << (fromRank * 8 + fromFile);

        if ((fromPosition & workspace.pawnPositions) != 0) {
            int promotionPiece = (move & 0b01110000_00000000) >>> 12;
            if (promotionPiece != 0) {
                MinChessConstants.PromotionPiece promotionPieceValue = MinChessConstants.PromotionPiece.from(promotionPiece);
                workspace.doMovePromotionImp(fromPosition, toPosition, promotionPieceValue);
            } else if (Math.abs(fromRank - toRank) == 2) {
                int enPassantRank = (fromRank + toRank) / 2;
                workspace.doMoveImp(fromPosition, toPosition);
                workspace.enPassantSquare = 1L << (enPassantRank * 8 + fromFile);
            } else if ((toPosition & workspace.enPassantSquare) != 0) {
                workspace.doEnPassantMoveImp(fromPosition, toPosition);
            } else {
                workspace.doMoveImp(fromPosition, toPosition);
            }
        } else if ((fromPosition & workspace.kingPositions) != 0) {
            if (Math.abs(fromFile - toFile) == 2) {
                workspace.doCastlingMoveImp(fromPosition, toPosition);
            } else {
                workspace.doMoveImp(fromPosition, toPosition);
            }
        } else {
            workspace.doMoveImp(fromPosition, toPosition);
        }

        if (validate) {
            workspace.validate();
        }
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

    boolean isLegalMove(long from, long to) {
        if (Long.bitCount(from) > 1 || Long.bitCount(to) > 1) {
            throw new RuntimeException("Invalid move: " + Long.toBinaryString(from) + " -> " + Long.toBinaryString(to));
        }
        workspaceTmp.copyFrom(workspace);
        workspaceTmp.doMoveImp(from, to);
        return !isKingInCheck(workspaceTmp, workspace.whiteTurn);
    }

    boolean isLegalEnPassantMove(long from, long enPassantSquare) {
        workspaceTmp.copyFrom(workspace);
        workspaceTmp.doEnPassantMoveImp(from, enPassantSquare);
        return !isKingInCheck(workspaceTmp, workspace.whiteTurn);
    }

    boolean isKingInCheck(boolean turn) {
        return isKingInCheck(workspace, turn);
    }

    boolean isKingInCheck(MinChessWorkspace workspace, boolean turn) {
        final long kingPosition = workspace.kingPositions & (turn ? workspace.whitePositions : workspace.blackPositions);
        final int kingIdx = Long.numberOfTrailingZeros(kingPosition);
        return king.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                knight.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                rook.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                bishop.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                pawn.isKingInCheckByOpponentPawn(workspace, kingPosition, kingIdx, !turn);
    }
}
