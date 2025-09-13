package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

/**
 * <p>
 * <SAN move descriptor piece moves>   ::= <Piece symbol>[<from file>|<from rank>|<from square>]['x']<to square>
 * <SAN move descriptor pawn captures> ::= 			      <from file>[<from rank>]               'x' <to square>[<promoted to>]
 * <SAN move descriptor pawn push>     ::= 														     <to square>[<promoted to>]
 *
 * @author Mauricio Coria
 */
public class SANDecoder<M> implements MoveDecoder<M> {
    public static final Pattern movePattern = Pattern.compile("(" +
            "(?<piecemove>(?<piece>[RNBQK])((?<piecefromfile>[a-h])|(?<piecefromrank>[1-8])|(?<piecefromsquare>[a-h][1-8]))?x?(?<pieceto>[a-h][1-8]))|" +
            "(?<pawncapture>(?<pawncapturefile>[a-h])[1-8]?x(?<pawncaptureto>[a-h][1-8])=?(?<pawncapturepromotion>[RNBQ]?))|" +
            "(?<pawnpush>(?<pawnto>[a-h][1-8])=?(?<pawnpushpromotion>[RNBQ]?))|" +
            "(?<queencaslting>O-O-O)|" +
            "(?<kingcastling>O-O)" +
            ")[+#]?"
    );

    private final MoveSupplier<M> moveSupplier;

    public SANDecoder(MoveSupplier<M> moveSupplier) {
        this.moveSupplier = moveSupplier;
    }

    @Override
    public M decode(String moveStr, FEN fen) {
        final Matcher matcher = movePattern.matcher(moveStr);
        if (matcher.matches()) {
            MinChess minchess = MinChess.from(fen);
            if (matcher.group("piecemove") != null) {
                return decodePieceMove(matcher, minchess);
            } else if (matcher.group("pawnpush") != null) {
                return decodePawnPush(matcher, minchess);
            } else if (matcher.group("pawncapture") != null) {
                return decodePawnCapture(matcher, minchess);
            } else if (matcher.group("queencaslting") != null) {
                return searchQueenCastling(minchess);
            } else if (matcher.group("kingcastling") != null) {
                return searchKingCastling(minchess);
            }
        }
        return null;
    }

    private M searchKingCastling(MinChess minchess) {
        MovePredicate moveFilter = (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) ->
                fromPiece == MinChess.KING && toFile - fromFile == 2;
        return extractMoves(minchess, moveFilter);
    }

    private M searchQueenCastling(MinChess minchess) {
        MovePredicate moveFilter = (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) ->
                fromPiece == MinChess.KING && fromFile - toFile == 2;
        return extractMoves(minchess, moveFilter);
    }

    private M decodePawnPush(Matcher matcher, MinChess minchess) {
        String pawnTo = matcher.group("pawnto");
        String pawnPushPromotion = matcher.group("pawnpushpromotion");

        Move.Square toSquareFilter = Move.Square.valueOf(pawnTo);

        if (toSquareFilter.getRank() == 0 || toSquareFilter.getRank() == 7) {
            if (pawnPushPromotion.isEmpty()) {
                pawnPushPromotion = "Q";
            }
        }
        int pawnPushPromotionFilter = switch (pawnPushPromotion) {
            case "N" -> MinChess.KNIGHT;
            case "B" -> MinChess.BISHOP;
            case "R" -> MinChess.ROOK;
            case "Q" -> MinChess.QUEEN;
            default -> 0;
        };

        MovePredicate moveFilter = (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) -> {
            if (fromPiece == MinChess.PAWN) {
                Move.Square toSquare = Move.Square.of(toFile, toRank);
                if (toSquare == toSquareFilter) {
                    if (promotion == pawnPushPromotionFilter) {
                        return true;
                    }
                }
            }
            return false;
        };

        return extractMoves(minchess, moveFilter);
    }


    private M decodePawnCapture(Matcher matcher, MinChess minchess) {
        String pawnCaptureFile = matcher.group("pawncapturefile");
        String pawnCaptureTo = matcher.group("pawncaptureto");
        String pawnCapturePromotion = matcher.group("pawncapturepromotion");

        int pawnCaptureFileInt = getFileInt(pawnCaptureFile);

        Move.Square toSquareFilter = Move.Square.valueOf(pawnCaptureTo);
        if (toSquareFilter.getRank() == 0 || toSquareFilter.getRank() == 7) {
            if (pawnCapturePromotion.isEmpty()) {
                pawnCapturePromotion = "Q";
            }
        }
        int pawnPushPromotionFilter = switch (pawnCapturePromotion) {
            case "N" -> MinChess.KNIGHT;
            case "B" -> MinChess.BISHOP;
            case "R" -> MinChess.ROOK;
            case "Q" -> MinChess.QUEEN;
            default -> 0;
        };

        MovePredicate moveFilter = (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) -> {
            if (fromPiece == MinChess.PAWN) {
                Move.Square toSquare = Move.Square.of(toFile, toRank);
                if (toSquare == toSquareFilter) {
                    if (fromFile == pawnCaptureFileInt) {
                        return promotion == pawnPushPromotionFilter;
                    }
                }
            }
            return false;
        };

        return extractMoves(minchess, moveFilter);
    }

    private M decodePieceMove(Matcher matcher, MinChess minchess) {
        String pieceStr = matcher.group("piece");
        String pieceFromFile = matcher.group("piecefromfile");
        String pieceFromRank = matcher.group("piecefromrank");
        String pieceFromSquareStr = matcher.group("piecefromsquare");
        String pieceTo = matcher.group("pieceto");

        int fromPieceFilter = switch (pieceStr) {
            case "N" -> MinChess.KNIGHT;
            case "B" -> MinChess.BISHOP;
            case "R" -> MinChess.ROOK;
            case "Q" -> MinChess.QUEEN;
            case "K" -> MinChess.KING;
            default -> throw new IllegalArgumentException("Invalid piece: " + pieceStr);
        };

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

        Move.Square pieceFromSquare = pieceFromSquareStr != null ? Move.Square.valueOf(pieceFromSquareStr) : null;

        Move.Square pieceToSquare = Move.Square.valueOf(pieceTo);

        MovePredicate moveFilter = (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) -> {
            if (fromPieceFilter == fromPiece) {
                Move.Square fromSquare = Move.Square.of(fromFile, fromRank);
                if (pieceFromFileInt == -1 && pieceFromRankInt == -1 && pieceFromSquare == null ||
                        pieceFromFileInt == fromFile ||
                        pieceFromRankInt == fromRank ||
                        Objects.equals(fromSquare, pieceFromSquare)
                ) {
                    Move.Square toSquare = Move.Square.of(toFile, toRank);
                    return toSquare == pieceToSquare;
                }
            }
            return false;
        };

        return extractMoves(minchess, moveFilter);
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


    private M extractMoves(MinChess minchess, MovePredicate moveFilter) {
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
                return moveSupplier.get(fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion);
            }
        }
        return null;
    }
}
