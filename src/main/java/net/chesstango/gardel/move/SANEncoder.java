package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import java.util.ArrayList;
import java.util.List;

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

        short[] moves = new short[MAX_MOVES];
        minchess.generateMoves(moves);

        if (moveExists(move, moves)) {
            int fromPiece = minchess.getPiece(move.from().getFile(), move.from().getRank());
            if (fromPiece == MinChess.PAWN) {
                return encodePawnMove(move, minchess);
            }
            return encodePieceMove(move, minchess, moves);
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
            return String.format("%s=%s", move.to().toString(), encodePromotion(move.promotionPiece()));
        }
        return move.to().toString();
    }

    private String encodePawnCaptureMove(Move move, MinChess minchess) {
        if (move.promotionPiece() != null) {
            return String.format("%sx%s=%s", fileToLetter(move.from().getFile()), move.to().toString(), encodePromotion(move.promotionPiece()));
        }
        return String.format("%sx%s", fileToLetter(move.from().getFile()), move.to().toString());
    }

    private String encodePieceMove(Move move, MinChess minchess, short[] moves) {
        int fromPiece = minchess.getPiece(move.from().getFile(), move.from().getRank());
        if (fromPiece == MinChess.KING) {
            if (move.from().getFile() - move.to().getFile() == 2) {
                return "O-O-O";
            } else if (move.to().getFile() - move.from().getFile() == 2) {
                return "O-O";
            }
        }

        int toPiece = minchess.getPiece(move.to().getFile(), move.to().getRank());

        String fromPieceStr = switch (fromPiece) {
            case MinChess.PAWN -> "P";
            case MinChess.KNIGHT -> "N";
            case MinChess.BISHOP -> "B";
            case MinChess.ROOK -> "R";
            case MinChess.QUEEN -> "Q";
            case MinChess.KING -> "K";
            default -> throw new IllegalArgumentException("Invalid piece: " + fromPiece);
        };

        String solvePieceAmbiguityFrom = solvePieceAmbiguityFrom(move, minchess, moves);

        String captureStr = toPiece == 0 ? "" : "x";

        return String.format("%s%s%s%s", fromPieceStr, solvePieceAmbiguityFrom, captureStr, move.to());
    }

    private String solvePieceAmbiguityFrom(Move move, MinChess minchess, short[] moves) {
        int fromPiece = minchess.getPiece(move.from().getFile(), move.from().getRank());
        List<Short> collisions = new ArrayList<>();
        for (int i = 0; moves[i] != 0; i++) {
            final short theMove = moves[i];
            final int toFile = MinChess.toFile(theMove);
            final int toRank = MinChess.toRank(theMove);
            final int theMovePiece = minchess.getFromPiece(theMove);
            if (theMovePiece == fromPiece &&
                    move.to().getFile() == toFile && move.to().getRank() == toRank) { // different FROM Square
                collisions.add(theMove);
            }
        }

        if (collisions.size() > 1) {
            long fileCount = collisions.stream().map(MinChess::fromFile).distinct().count();
            long rankCount = collisions.stream().map(MinChess::fromRank).distinct().count();
            if (fileCount == collisions.size()) {
                return fileToLetter(move.from().getFile());
            } else if (rankCount == collisions.size()) {
                return rankToLetter(move.from().getRank());
            } else {
                return Move.Square.of(move.from().getFile(), move.from().getRank()).toString();
            }
        }
        return "";
    }


    private boolean moveExists(Move move, short[] moves) {
        for (int i = 0; moves[i] != 0; i++) {
            final short theMove = moves[i];
            final int fromFile = MinChess.fromFile(theMove);
            final int fromRank = MinChess.fromRank(theMove);
            final int toFile = MinChess.toFile(theMove);
            final int toRank = MinChess.toRank(theMove);
            final int promotion = MinChess.getPromotionPiece(theMove);
            if (move.from().getFile() == fromFile && move.from().getRank() == fromRank &&
                    move.to().getFile() == toFile && move.to().getRank() == toRank) {

                if (promotion == 0 && move.promotionPiece() == null ||
                        promotion == MinChess.KNIGHT && move.promotionPiece() == Move.PromotionPiece.KNIGHT ||
                        promotion == MinChess.BISHOP && move.promotionPiece() == Move.PromotionPiece.BISHOP ||
                        promotion == MinChess.ROOK && move.promotionPiece() == Move.PromotionPiece.ROOK ||
                        promotion == MinChess.QUEEN && move.promotionPiece() == Move.PromotionPiece.QUEEN) {
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

    private String rankToLetter(int rank) {
        return switch (rank) {
            case 0 -> "1";
            case 1 -> "2";
            case 2 -> "3";
            case 3 -> "4";
            case 4 -> "5";
            case 5 -> "6";
            case 6 -> "7";
            case 7 -> "8";
            default -> null;
        };
    }

    private String encodePromotion(Move.PromotionPiece promotionPiece) {
        return switch (promotionPiece) {
            case KNIGHT -> "N";
            case BISHOP -> "B";
            case ROOK -> "R";
            case QUEEN -> "Q";
            default -> throw new IllegalArgumentException("Invalid promotion piece: " + promotionPiece);
        };
    }
}
