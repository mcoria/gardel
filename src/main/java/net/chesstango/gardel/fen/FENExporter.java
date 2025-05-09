package net.chesstango.gardel.fen;

import net.chesstango.gardel.PositionBuilder;

/**
 * @author Mauricio Coria
 */
public class FENExporter {
    private final PositionBuilder<?> positionBuilder;

    public FENExporter(PositionBuilder<?> positionBuilder) {
        this.positionBuilder = positionBuilder;
    }


    public void export(FEN fen) {
        positionBuilder.withWhiteTurn(parseTurn(fen.getActiveColor()));

        parsePiecePlacement(fen.getPiecePlacement());

        if (isCastlingWhiteQueenAllowed(fen.getCastingsAllowed())) {
            positionBuilder.withCastlingWhiteQueenAllowed(true);
        }

        if (isCastlingWhiteKingAllowed(fen.getCastingsAllowed())) {
            positionBuilder.withCastlingWhiteKingAllowed(true);
        }

        if (isCastlingBlackQueenAllowed(fen.getCastingsAllowed())) {
            positionBuilder.withCastlingBlackQueenAllowed(true);
        }

        if (isCastlingBlackKingAllowed(fen.getCastingsAllowed())) {
            positionBuilder.withCastlingBlackKingAllowed(true);
        }

        if (!"-".equals(fen.getEnPassantSquare())) {
            positionBuilder.withEnPassantSquare(parseFileEnPassantSquare(fen.getEnPassantSquare()), parseRankEnPassantSquare(fen.getEnPassantSquare()));
        }

        positionBuilder.withHalfMoveClock(Integer.parseInt(fen.getHalfMoveClock()));

        positionBuilder.withFullMoveClock(Integer.parseInt(fen.getFullMoveClock()));
    }

    void parsePiecePlacement(String piecePlacement) {
        int rank = 7;
        int file = 0;
        int idx = 0;
        while (idx < piecePlacement.length()) {
            char currentChar = piecePlacement.charAt(idx++);
            switch (currentChar) {
                case 'K' -> positionBuilder.withWhiteKing(file++, rank);
                case 'Q' -> positionBuilder.withWhiteQueen(file++, rank);
                case 'R' -> positionBuilder.withWhiteRook(file++, rank);
                case 'B' -> positionBuilder.withWhiteBishop(file++, rank);
                case 'N' -> positionBuilder.withWhiteKnight(file++, rank);
                case 'P' -> positionBuilder.withWhitePawn(file++, rank);
                case 'k' -> positionBuilder.withBlackKing(file++, rank);
                case 'q' -> positionBuilder.withBlackQueen(file++, rank);
                case 'r' -> positionBuilder.withBlackRook(file++, rank);
                case 'b' -> positionBuilder.withBlackBishop(file++, rank);
                case 'n' -> positionBuilder.withBlackKnight(file++, rank);
                case 'p' -> positionBuilder.withBlackPawn(file++, rank);
                case '1', '2', '3', '4', '5', '6', '7', '8' -> file += Integer.parseInt(String.valueOf(currentChar));
                case '/' -> {
                    rank--;
                    file = 0;
                }
                default -> throw new RuntimeException("FEN: Malformed piece placement: " + piecePlacement);
            }
        }
    }

    int parseFileEnPassantSquare(String pawnPasante) {
        if (!"-".equals(pawnPasante)) {
            char file = pawnPasante.charAt(0);
            return switch (file) {
                case 'a' -> 0;
                case 'b' -> 1;
                case 'c' -> 2;
                case 'd' -> 3;
                case 'e' -> 4;
                case 'f' -> 5;
                case 'g' -> 6;
                case 'h' -> 7;
                default -> throw new IllegalArgumentException("Invalid EnPassantSquare");
            };
        }
        throw new IllegalArgumentException("Invalid EnPassantSquare");
    }

    int parseRankEnPassantSquare(String pawnPasante) {
        if (!"-".equals(pawnPasante)) {
            char rank = pawnPasante.charAt(1);
            return Integer.parseInt(String.valueOf(rank)) - 1;
        }
        throw new IllegalArgumentException("Invalid EnPassantSquare");
    }

    boolean parseTurn(String activeColor) {
        return 'w' == activeColor.charAt(0);
    }

    boolean isCastlingWhiteQueenAllowed(String castlingAllowed) {
        return castlingAllowed.contains("Q");
    }

    boolean isCastlingWhiteKingAllowed(String castlingAllowed) {
        return castlingAllowed.contains("K");
    }

    boolean isCastlingBlackQueenAllowed(String castlingAllowed) {
        return castlingAllowed.contains("q");
    }

    boolean isCastlingBlackKingAllowed(String castlingAllowed) {
        return castlingAllowed.contains("k");
    }

}
