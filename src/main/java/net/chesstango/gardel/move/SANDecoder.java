package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;

import java.util.List;
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
            "(?<piecemove>(?<piece>[RNBQK])(?<piecefrom>[a-h]|[1-8]|[a-h][1-8])?x?(?<pieceto>[a-h][1-8]))|" +
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
                    return move;
                }
            }
        }

        return null;
    }

    private Move decodePawnCapture(Matcher matcher, List<Move> moves) {
        String pawnCaptureFile = matcher.group("pawncapturefile");
        String pawnCaptureTo = matcher.group("pawncaptureto");
        String pawnCapturePromotion = matcher.group("pawncapturepromotion");

        int pawnCaptureFileInt = switch (pawnCaptureFile) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            default -> throw new IllegalStateException("Unexpected value: " + pawnCaptureFile);
        };

        Move.Square toSquare = Move.Square.valueOf(pawnCaptureTo);
        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if (Move.Piece.PAWN_WHITE.equals(fromPiece) || Move.Piece.PAWN_BLACK.equals(fromPiece)) {
                if (move.from().getFile() == pawnCaptureFileInt && move.to() == toSquare) {
                    return move;
                }
            }
        }

        return null;
    }

    private Move decodePieceMove(Matcher matcher, List<Move> moves) {
        String piece = matcher.group("piece");
        String pieceFrom = matcher.group("piecefrom");
        String pieceTo = matcher.group("pieceto");

        Move.Square toSquare = Move.Square.valueOf(pieceTo);

        return pieceFrom == null ?
                decodePieceMove(piece, toSquare, moves) :
                decodePieceMove(piece, pieceFrom, toSquare, moves);
    }

    private Move decodePieceMove(String piece, Move.Square toSquare, List<Move> moves) {
        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if ("B".equalsIgnoreCase(piece) && (Move.Piece.BISHOP_WHITE.equals(fromPiece) || Move.Piece.BISHOP_BLACK.equals(fromPiece)) ||
                    "N".equalsIgnoreCase(piece) && (Move.Piece.KNIGHT_WHITE.equals(fromPiece) || Move.Piece.KNIGHT_BLACK.equals(fromPiece)) ||
                    "R".equalsIgnoreCase(piece) && (Move.Piece.ROOK_WHITE.equals(fromPiece) || Move.Piece.ROOK_BLACK.equals(fromPiece)) ||
                    "Q".equalsIgnoreCase(piece) && (Move.Piece.QUEEN_WHITE.equals(fromPiece) || Move.Piece.QUEEN_BLACK.equals(fromPiece))) {
                if (move.to().equals(toSquare)) {
                    return move;
                }
            }
        }
        return null;
    }

    private Move decodePieceMove(String piece, String pieceFrom, Move.Square toSquare, List<Move> moves) {
        return null;
    }

}
