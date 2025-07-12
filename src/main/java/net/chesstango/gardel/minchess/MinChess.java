package net.chesstango.gardel.minchess;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENExporter;

/**
 * @author Mauricio Coria
 */
public class MinChess implements Cloneable {
    public static final int MAX_MOVES = 128;
    public static final int KNIGHT = 1;
    public static final int BISHOP = 2;
    public static final int ROOK = 3;
    public static final int QUEEN = 4;
    public static final int PAWN = 5;
    public static final int KING = 6;

    final MinChessWorkspace workspace;
    final MinChessWorkspace workspaceTmp;

    final King king;
    final Knight knight;
    final Rook rook;
    final Bishop bishop;
    final Pawn pawn;

    boolean validate = false;

    public static MinChess from(FEN fen) {
        MinChessBuilder builder = new MinChessBuilder();
        FENExporter exporter = new FENExporter(builder);
        exporter.export(fen);
        return builder.getPositionRepresentation();
    }

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
                pawnPositions,
                this
        );

        this.workspaceTmp = new MinChessWorkspace(this);

        this.king = new King(this::isLegalMove);
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
            final int promotionPiece = (move & 0b01110000_00000000) >>> 12;
            if (promotionPiece != 0) {
                workspace.doMovePromotionImp(fromPosition, toPosition, promotionPiece);
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

    public static int getFromIdx(short move) {
        final int fromFile = (move & 0b00000001_11000000) >>> 6;
        final int fromRank = (move & 0b00001110_00000000) >>> 9;
        return fromRank * 8 + fromFile;
    }

    public static int getToIdx(short move) {
        final int toFile = move & 0b00000000_00000111;
        final int toRank = (move & 0b00000000_00111000) >>> 3;
        return toRank * 8 + toFile;
    }

    public static int getPromotionPiece(short move) {
        return (move & 0b01110000_00000000) >>> 12;
    }

    public int getFromPiece(short move) {
        return getPiece(1L << getFromIdx(move));
    }

    public int getToPiece(short move) {
        return getPiece(1L << getToIdx(move));
    }

    public int getPiece(long position) {
        int piece = 0;

        if ((position & workspace.pawnPositions) != 0) {
            piece = PAWN;
        } else if ((position & workspace.kingPositions) != 0) {
            piece = KING;
        } else if ((position & workspace.queenPositions) != 0) {
            piece = QUEEN;
        } else if ((position & workspace.rookPositions) != 0) {
            piece = ROOK;
        } else if ((position & workspace.bishopPositions) != 0) {
            piece = BISHOP;
        } else if ((position & workspace.knightPositions) != 0) {
            piece = KNIGHT;
        }

        return piece;
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

    boolean isKingInCheck(MinChessWorkspace workspace, boolean turn) {
        final long kingPosition = workspace.kingPositions & (turn ? workspace.whitePositions : workspace.blackPositions);
        final int kingIdx = Long.numberOfTrailingZeros(kingPosition);
        return king.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                knight.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                rook.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                bishop.isKingInCheckByOpponent(workspace, kingPosition, kingIdx, !turn) ||
                pawn.isKingInCheckByOpponentPawn(workspace, kingPosition, kingIdx, !turn);
    }

    public boolean getTurn() {
        return workspace.whiteTurn;
    }
}
