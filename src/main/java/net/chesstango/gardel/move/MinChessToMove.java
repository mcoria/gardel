package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import java.util.ArrayList;
import java.util.List;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

class MinChessToMove {
    static List<Move> extractMoves(FEN fen) {
        MinChess minchess = MinChess.from(fen);
        List<Move> moveList = new ArrayList<>();
        short[] moves = new short[MAX_MOVES];
        int size = minchess.generateMoves(moves);
        for (int i = 0; i < size; i++) {
            final short move = moves[i];

            final int fromIdx = MinChess.getFromIdx(move);
            final int toIdx = MinChess.getToIdx(move);
            final int promotionPiece = MinChess.getPromotionPiece(move);
            final int fromPiece = minchess.getFromPiece(move);
            final int toPiece = minchess.getToPiece(move);

            final boolean turn = minchess.getTurn();
            final Move.Square fromSquare = Move.Square.of(fromIdx);
            final Move.Square toSquare = Move.Square.of(toIdx);
            final Move.PromotionPiece promotionPieceEnum = toMovePromotion(promotionPiece);
            final Move.Piece fromPieceMove = toMovePiece(turn, fromPiece);
            final Move.Piece toPieceMove = toMovePiece(!turn, toPiece);

            moveList.add(new Move(fromSquare, toSquare, fromPieceMove, toPieceMove, promotionPieceEnum));
        }

        return moveList;
    }

    static Move.Piece toMovePiece(final boolean turn, int piece) {
        return switch (piece) {
            case MinChess.KNIGHT -> turn ? Move.Piece.KNIGHT_WHITE : Move.Piece.KNIGHT_BLACK;
            case MinChess.BISHOP -> turn ? Move.Piece.BISHOP_WHITE : Move.Piece.BISHOP_BLACK;
            case MinChess.ROOK -> turn ? Move.Piece.ROOK_WHITE : Move.Piece.ROOK_BLACK;
            case MinChess.QUEEN -> turn ? Move.Piece.QUEEN_WHITE : Move.Piece.QUEEN_BLACK;
            case MinChess.PAWN -> turn ? Move.Piece.PAWN_WHITE : Move.Piece.PAWN_BLACK;
            case MinChess.KING -> turn ? Move.Piece.KING_WHITE : Move.Piece.KING_BLACK;
            default -> Move.Piece.EMPTY;
        };
    }

    static Move.PromotionPiece toMovePromotion(int promotionPiece) {
        return switch (promotionPiece) {
            case MinChess.KNIGHT -> Move.PromotionPiece.KNIGHT;
            case MinChess.BISHOP -> Move.PromotionPiece.BISHOP;
            case MinChess.ROOK -> Move.PromotionPiece.ROOK;
            case MinChess.QUEEN -> Move.PromotionPiece.QUEEN;
            default -> null;
        };
    }
}
