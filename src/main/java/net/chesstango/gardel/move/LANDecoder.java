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
 * <LAN move descriptor piece moves> ::= <Piece symbol><from square>['-'|'x']<to square>
 * <LAN move descriptor pawn moves>  ::= <from square>['-'|'x']<to square>[<promoted to>]
 * <Piece symbol> ::= 'N' | 'B' | 'R' | 'Q' | 'K'
 */
public class LANDecoder {
    private static final Pattern edpMovePattern = Pattern.compile("(" +
            "(?<piecemove>(?<piece>[RNBQK]?)((?<from>[a-h][1-8])|(?<fromfile>[a-h])|(?<fromrank>[1-8]))?[-x]?(?<to>[a-h][1-8]))|" +
            "(?<pawnmove>(?<pawnfrom>[a-h][1-8])[-x](?<pawnto>[a-h][1-8])(?<promotionpiece>[RNBQK]))" +
            ")[+#]?");

    public Move decode(String moveLongAlgNotation, FEN fen) {
        final Matcher matcher = edpMovePattern.matcher(moveLongAlgNotation);
        if (matcher.matches()) {
            List<Move> moves = extractMoves(fen);
            if (matcher.group("piecemove") != null) {
                return decodePieceMove(matcher, moves);
            } else if (matcher.group("pawnmove") != null) {
                return decodePawnMove(matcher, moves);
            }
        }
        return null;
    }

    private Move decodePieceMove(Matcher matcher, List<Move> moves) {
        String pieceStr = matcher.group("piece");
        String fromStr = matcher.group("from");
        String fromFileStr = matcher.group("fromfile");
        String fromRankStr = matcher.group("fromrank");
        String toStr = matcher.group("to");


        return null;
    }

    private Move decodePawnMove(Matcher matcher, List<Move> moves) {
        String promotionPieceStr = matcher.group("promotionpiece");
        String fromStr = matcher.group("pawnfrom");
        String toStr = matcher.group("pawnto");

        Move.Square fromSquare = Move.Square.valueOf(fromStr);
        Move.Square toSquare = Move.Square.valueOf(toStr);
        Move.PromotionPiece promotionPiece = Move.PromotionPiece.from(promotionPieceStr);

        for (Move move : moves) {
            Move.Piece fromPiece = move.fromPiece();
            if (Move.Piece.PAWN_WHITE.equals(fromPiece) || Move.Piece.PAWN_BLACK.equals(fromPiece)) {
                if (move.from() == fromSquare && move.to() == toSquare) {
                    if (move.promotionPiece() == promotionPiece) {
                        return move;
                    }
                }
            }
        }
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
