package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

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
            short[] moves = new short[MAX_MOVES];
            minchess.generateMoves(moves);
            if (matcher.group("piecemove") != null) {
                return decodePieceMove(matcher, moves);
            } else if (matcher.group("pawnmove") != null) {
                return decodePawnMove(matcher, moves);
            }
        }
        return null;
    }

    private Move decodePieceMove(Matcher matcher, short[] moves) {
        String pieceStr = matcher.group("piece");
        String fromStr = matcher.group("from");
        String fromFileStr = matcher.group("fromfile");
        String fromRankStr = matcher.group("fromrank");
        String toStr = matcher.group("to");


        return null;
    }

    private Move decodePawnMove(Matcher matcher, short[] moves) {
        String promotionPieceStr = matcher.group("promotionpiece");
        String fromStr = matcher.group("pawnfrom");
        String toStr = matcher.group("pawnto");


        return Move.of(Move.Square.valueOf(fromStr),
                Move.Square.valueOf(toStr),
                null,
                null,
                promotionPieceStr != null ? Move.PromotionPiece.from(promotionPieceStr.toLowerCase()) : null
        );
    }


}
