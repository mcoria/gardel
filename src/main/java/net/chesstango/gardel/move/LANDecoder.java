package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import java.util.Objects;
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
            MinChess minchess = MinChess.from(fen);
            if (matcher.group("piecemove") != null) {
                return decodePieceMove(matcher, minchess);
            } else if (matcher.group("pawnmove") != null) {
                return decodePawnMove(matcher, minchess);
            }
        }
        return null;
    }

    private Move decodePieceMove(Matcher matcher, MinChess minchess) {
        String pieceStr = matcher.group("piece");
        String fromStr = matcher.group("from");
        String fromFileStr = matcher.group("fromfile");
        String fromRankStr = matcher.group("fromrank");
        String toStr = matcher.group("to");


        return null;
    }

    private Move decodePawnMove(Matcher matcher, MinChess minchess) {
        String promotionPieceStr = matcher.group("promotionpiece");
        String fromStr = matcher.group("pawnfrom");
        String toStr = matcher.group("pawnto");

        Move.Square fromSquareFilter = Move.Square.valueOf(fromStr);
        Move.Square toSquareFilter = Move.Square.valueOf(toStr);
        Move.PromotionPiece promotionPieceFilter = Move.PromotionPiece.from(promotionPieceStr);

        MovePredicate moveFilter = (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) -> {
            final Move.Square fromSquare = Move.Square.of(fromFile, fromRank);
            final Move.Square toSquare = Move.Square.of(toFile, toRank);
            final Move.PromotionPiece promotionPiece = toMovePromotion(promotion);
            return fromPiece == MinChess.PAWN &&
                    fromSquareFilter.equals(fromSquare) &&
                    toSquareFilter.equals(toSquare) &&
                    Objects.equals(promotionPieceFilter, promotionPiece);
        };

        return extractMoves(minchess, moveFilter);
    }


    private Move extractMoves(MinChess minchess, MovePredicate moveFilter) {
        short[] moves = new short[MAX_MOVES];
        int size = minchess.generateMoves(moves);
        for (int i = 0; i < size; i++) {
            final short move = moves[i];
            final int fromFile = MinChess.fromFile(move);
            final int fromRank = MinChess.fromRank(move);
            final int toFile = MinChess.toFile(move);
            final int toRank = MinChess.toRank(move);
            final int fromPiece = minchess.getFromPiece(move);
            final int toPiece = minchess.getToPiece(move);
            final int promotion = MinChess.getPromotionPiece(move);

            if (moveFilter.test(fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion)) {
                final Move.Square fromSquare = Move.Square.of(fromFile, fromRank);
                final Move.Square toSquare = Move.Square.of(toFile, toRank);
                final Move.PromotionPiece promotionPieceEnum = toMovePromotion(promotion);
                return new Move(fromSquare, toSquare, promotionPieceEnum);
            }
        }

        return null;
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
