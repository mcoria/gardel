package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mauricio Coria
 * <p>
 * <SAN move descriptor piece moves>   ::= <Piece symbol>[<from file>|<from rank>|<from square>]['x']<to square>
 * <SAN move descriptor pawn captures> ::= 			      <from file>[<from rank>]               'x' <to square>[<promoted to>]
 * <SAN move descriptor pawn push>     ::= 														     <to square>[<promoted to>]
 */
public class SANDecoder {
    public static final Pattern movePattern = Pattern.compile("(" +
            "(?<piecemove>(?<piece>[RNBQK])((?<piecefromfile>[a-h])|(?<piecefromrank>[1-8])|(?<piecefromsquare>[a-h][1-8]))?x?(?<pieceto>[a-h][1-8]))|" +
            "(?<pawncapture>(?<pawncapturefile>[a-h])[1-8]?x(?<pawncaptureto>[a-h][1-8])=?(?<pawncapturepromotion>[RNBQ]?))|" +
            "(?<pawnpush>(?<pawnto>[a-h][1-8])=?(?<pawnpushpromotion>[RNBQ]?))|" +
            "(?<queencaslting>O-O-O)|" +
            "(?<kingcastling>O-O)" +
            ")[+#]?"
    );


    public Move decode(String moveSAN, FEN fen) {
        final Matcher matcher = movePattern.matcher(moveSAN);
        if (matcher.matches()) {
            List<Move> moves = MinChessToMove.extractMoves(fen);
            if (matcher.group("piecemove") != null) {
                return decodePieceMove(matcher, moves);
            } else if (matcher.group("pawnpush") != null) {
                return decodePawnPush(matcher, moves);
            } else if (matcher.group("pawncapture") != null) {
                return decodePawnCapture(matcher, moves);
            } else if (matcher.group("queencaslting") != null) {
                return searchQueenCastling(moves);
            } else if (matcher.group("kingcastling") != null) {
                return searchKingCastling(moves);
            }
        }
        return null;
    }

    private Move searchKingCastling(List<Move> moves) {
        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if (Move.Piece.KING_WHITE.equals(fromPiece) || Move.Piece.KING_BLACK.equals(fromPiece)) {
                if (move.to().getFile() - move.from().getFile() == 2) {
                    return move;
                }
            }
        }
        return null;
    }

    private Move searchQueenCastling(List<Move> moves) {
        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if (Move.Piece.KING_WHITE.equals(fromPiece) || Move.Piece.KING_BLACK.equals(fromPiece)) {
                if (move.from().getFile() - move.to().getFile() == 2) {
                    return move;
                }
            }
        }
        return null;
    }

    private Move decodePawnPush(Matcher matcher, List<Move> moves) {
        String pawnTo = matcher.group("pawnto");
        String pawnPushPromotion = matcher.group("pawnpushpromotion");

        Move.Square toSquare = Move.Square.valueOf(pawnTo);
        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if (Move.Piece.PAWN_WHITE.equals(fromPiece) || Move.Piece.PAWN_BLACK.equals(fromPiece)) {
                if (move.to() == toSquare) {
                    if(Objects.equals(move.promotionPiece(), Move.PromotionPiece.from(pawnPushPromotion))) {
                        return move;
                    }
                }
            }
        }

        return null;
    }

    private Move decodePawnCapture(Matcher matcher, List<Move> moves) {
        String pawnCaptureFile = matcher.group("pawncapturefile");
        String pawnCaptureTo = matcher.group("pawncaptureto");
        String pawnCapturePromotion = matcher.group("pawncapturepromotion");

        int pawnCaptureFileInt = getFileInt(pawnCaptureFile);

        Move.Square toSquare = Move.Square.valueOf(pawnCaptureTo);
        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if (Move.Piece.PAWN_WHITE.equals(fromPiece) || Move.Piece.PAWN_BLACK.equals(fromPiece)) {
                if (move.to() == toSquare) {
                    if(move.from().getFile() == pawnCaptureFileInt) {
                        if(Objects.equals(move.promotionPiece(), Move.PromotionPiece.from(pawnCapturePromotion))) {
                            return move;
                        }
                    }
                }
            }
        }

        return null;
    }

    private Move decodePieceMove(Matcher matcher, List<Move> moves) {
        String pieceStr = matcher.group("piece");
        String pieceFromFile = matcher.group("piecefromfile");
        String pieceFromRank = matcher.group("piecefromrank");
        String pieceFromSquare = matcher.group("piecefromsquare");
        String pieceTo = matcher.group("pieceto");

        Move.Square pieceToSquare = Move.Square.valueOf(pieceTo);

        int pieceFromFileInt = getFileInt(pieceFromFile);

        int pieceFromRankInt = switch (pieceFromRank) {
            case "1" -> 0;
            case "2" -> 1;
            case "3" -> 2;
            case "4" -> 3;
            case "5" -> 4;
            case "6" -> 5;
            case "7" -> 6;
            case "8" -> 7;
            case null, default -> -1;
        };

        for (Move move : moves) {
            if (isPiece(move, pieceStr) && move.to() == pieceToSquare &&
                    (pieceFromFileInt == -1 || pieceFromFileInt == move.from().getFile()) &&
                    (pieceFromRankInt == -1 || pieceFromRankInt == move.from().getRank())
            ) {
                return move;
            }
        }

        return null;
    }

    private static boolean isPiece(Move move, String pieceStr) {
        Move.Piece fromPiece = move.fromPiece();
        return "B".equalsIgnoreCase(pieceStr) && (Move.Piece.BISHOP_WHITE.equals(fromPiece) || Move.Piece.BISHOP_BLACK.equals(fromPiece)) ||
                "N".equalsIgnoreCase(pieceStr) && (Move.Piece.KNIGHT_WHITE.equals(fromPiece) || Move.Piece.KNIGHT_BLACK.equals(fromPiece)) ||
                "R".equalsIgnoreCase(pieceStr) && (Move.Piece.ROOK_WHITE.equals(fromPiece) || Move.Piece.ROOK_BLACK.equals(fromPiece)) ||
                "Q".equalsIgnoreCase(pieceStr) && (Move.Piece.QUEEN_WHITE.equals(fromPiece) || Move.Piece.QUEEN_BLACK.equals(fromPiece));
    }

    private static int getFileInt(String pawnCaptureFile) {
        return switch (pawnCaptureFile) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            case null, default -> -1;
        };
    }
}
