package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

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


    public MoveCoordinates decode(String moveSAN, FEN fen) {
        final Matcher matcher = movePattern.matcher(moveSAN);
        if (matcher.matches()) {
            MinChess minchess = MinChess.from(fen);
            short[] moves = new short[MAX_MOVES];
            minchess.generateMoves(moves);
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

    private MoveCoordinates searchKingCastling(short[] moves) {

        return null;
    }

    private MoveCoordinates searchQueenCastling(short[] moves) {

        return null;
    }

    private MoveCoordinates decodePawnPush(Matcher matcher, short[] moves) {
        String pawnto = matcher.group("pawnto");
        String pawnpushpromotion = matcher.group("pawnpushpromotion");

        if (pawnpushpromotion.equals("")) {
            pawnpushpromotion = switch (pawnto) {
                case "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1", "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8" ->
                        "Q";
                default -> pawnpushpromotion;
            };
        }


        return null;
    }

    private MoveCoordinates decodePawnCapture(Matcher matcher, short[] moves) {
        String pawncapturefile = matcher.group("pawncapturefile");
        String pawncaptureto = matcher.group("pawncaptureto");
        String pawncapturepromotion = matcher.group("pawncapturepromotion");

        return null;
    }

    private MoveCoordinates decodePieceMove(Matcher matcher, short[] moves) {
        String piece = matcher.group("piece");
        String piecefrom = matcher.group("piecefrom");
        String pieceto = matcher.group("pieceto");

        return null;
    }
}
