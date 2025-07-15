package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

/**
 * <p>
 * <SAN move descriptor piece moves>   ::= <Piece symbol>[<from file>|<from rank>|<from square>]['x']<to square>
 * <SAN move descriptor pawn captures> ::= 			      <from file>[<from rank>]               'x' <to square>[<promoted to>]
 * <SAN move descriptor pawn push>     ::= 														     <to square>[<promoted to>]
 *
 * @author Mauricio Coria
 */
public class SANEncoder {

    public String encodeAlgebraicNotation(Move move, FEN fen) {
        MinChess minchess = MinChess.from(fen);

        if (moveExists(move, minchess)) {
            int fromPiece = minchess.getPiece(move.from().getFile(), move.from().getRank());
            if (fromPiece == MinChess.PAWN) {
                return encodePawnMove(move, minchess);
            }
            return encodePieceMove(move, minchess);
        }
        return null;
    }

    private String encodePawnMove(Move move, MinChess minchess) {
        if (move.from().getFile() == move.to().getFile()) {
            return encodePawnPushMove(move, minchess);
        } else {
            return encodePawnCaptureMove(move, minchess);
        }
    }

    private String encodePawnPushMove(Move move, MinChess minchess) {
        if (move.promotionPiece() != null) {
            return String.format("%s=%s", move.to().toString(), move.promotionPiece());
        }
        return move.to().toString();
    }

    private String encodePawnCaptureMove(Move move, MinChess minchess) {
        if (move.promotionPiece() != null) {
            return String.format("%sx%s=%s", fileToLetter(move.from().getFile()), move.to().toString(), move.promotionPiece());
        }
        return String.format("%sx%s", fileToLetter(move.from().getFile()), move.to().toString());
    }

    private String encodePieceMove(Move move, MinChess minchess) {
        int fromPiece = minchess.getPiece(move.from().getFile(), move.from().getRank());
        if (fromPiece == MinChess.KING) {
            if (move.from().getFile() - move.to().getFile() == 2) {
                return "O-O-O";
            } else if (move.to().getFile() - move.from().getFile() == 2) {
                return "O-O";
            }
        }

        return null;
    }

    private String solvePieceAmbiguityFrom(Move move, MinChess minchess) {
        return null;
    }

    private boolean moveExists(Move theMove, MinChess minchess) {
        short[] moves = new short[MAX_MOVES];
        int size = minchess.generateMoves(moves);
        for (int i = 0; i < size; i++) {
            final short move = moves[i];
            final int fromFile = MinChess.fromFile(move);
            final int fromRank = MinChess.fromRank(move);
            final int toFile = MinChess.toFile(move);
            final int toRank = MinChess.toRank(move);
            final int promotion = MinChess.getPromotionPiece(move);
            if (theMove.from().getFile() == fromFile && theMove.from().getRank() == fromRank &&
                    theMove.to().getFile() == toFile && theMove.to().getRank() == toRank) {

                if (promotion == 0 && theMove.promotionPiece() == null ||
                        promotion == MinChess.KNIGHT && theMove.promotionPiece() == Move.PromotionPiece.KNIGHT ||
                        promotion == MinChess.BISHOP && theMove.promotionPiece() == Move.PromotionPiece.BISHOP ||
                        promotion == MinChess.ROOK && theMove.promotionPiece() == Move.PromotionPiece.ROOK ||
                        promotion == MinChess.QUEEN && theMove.promotionPiece() == Move.PromotionPiece.QUEEN) {
                    return true;
                }
            }
        }
        return false;
    }

    private String fileToLetter(int file) {
        return switch (file) {
            case 0 -> "a";
            case 1 -> "b";
            case 2 -> "c";
            case 3 -> "d";
            case 4 -> "e";
            case 5 -> "f";
            case 6 -> "g";
            case 7 -> "h";
            default -> null;
        };
    }

}
