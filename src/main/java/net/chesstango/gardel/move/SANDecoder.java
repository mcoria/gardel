package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

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
            List<Move> moves = extractMoves(fen);
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

        return null;
    }

    private Move searchQueenCastling(List<Move> moves) {

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
        String pawncapturefile = matcher.group("pawncapturefile");
        String pawncaptureto = matcher.group("pawncaptureto");
        String pawncapturepromotion = matcher.group("pawncapturepromotion");

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

    private List<Move> extractMoves(FEN fen) {
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
            final Move.Piece toPieceMove = toMovePiece(turn, toPiece);

            moveList.add(new Move(fromSquare, toSquare, fromPieceMove, toPieceMove, promotionPieceEnum));
        }

        return moveList;
    }

    private static Move.Piece toMovePiece(final boolean turn, int piece) {
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

    private static Move.PromotionPiece toMovePromotion(int promotionPiece) {
        return switch (promotionPiece) {
            case MinChess.KNIGHT -> Move.PromotionPiece.KNIGHT;
            case MinChess.BISHOP -> Move.PromotionPiece.BISHOP;
            case MinChess.ROOK -> Move.PromotionPiece.ROOK;
            case MinChess.QUEEN -> Move.PromotionPiece.QUEEN;
            default -> null;
        };
    }
}
