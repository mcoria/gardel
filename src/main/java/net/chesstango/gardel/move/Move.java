package net.chesstango.gardel.move;

import lombok.Getter;

/**
 * Represents a chess move in pure algebraic notation.
 * This record encapsulates the essential parts of a chess move: the source square,
 * destination square, and an optional promotion piece for pawn promotions.
 *
 * @author Mauricio Coria
 */
public record Move(Square from,
                   Square to,
                   PromotionPiece promotionPiece) {

    /**
     * Represents the possible pieces a pawn can promote to in chess.
     * Each enum constant corresponds to a specific promotion piece and provides
     * its standard algebraic notation representation.
     */
    public enum PromotionPiece {
        KNIGHT,
        BISHOP,
        ROOK,
        QUEEN;

        @Override
        public String toString() {
            return switch (this) {
                case KNIGHT -> "n";
                case QUEEN -> "q";
                case ROOK -> "r";
                case BISHOP -> "b";
            };
        }

        static PromotionPiece from(String charRepresentation) {
            return switch (charRepresentation) {
                case "N" -> KNIGHT;
                case "B" -> BISHOP;
                case "R" -> ROOK;
                case "Q" -> QUEEN;
                case null, default -> null;
            };
        }
    }

    /**
     * Represents the squares on a chess board using standard algebraic notation.
     * Each square is defined by its file (a-h) and rank (1-8) coordinates.
     * The squares are ordered from a8 to h1, following standard chess notation.
     */
    public enum Square {
        a8(0, 7), b8(1, 7), c8(2, 7), d8(3, 7), e8(4, 7), f8(5, 7), g8(6, 7), h8(7, 7),
        a7(0, 6), b7(1, 6), c7(2, 6), d7(3, 6), e7(4, 6), f7(5, 6), g7(6, 6), h7(7, 6),
        a6(0, 5), b6(1, 5), c6(2, 5), d6(3, 5), e6(4, 5), f6(5, 5), g6(6, 5), h6(7, 5),
        a5(0, 4), b5(1, 4), c5(2, 4), d5(3, 4), e5(4, 4), f5(5, 4), g5(6, 4), h5(7, 4),
        a4(0, 3), b4(1, 3), c4(2, 3), d4(3, 3), e4(4, 3), f4(5, 3), g4(6, 3), h4(7, 3),
        a3(0, 2), b3(1, 2), c3(2, 2), d3(3, 2), e3(4, 2), f3(5, 2), g3(6, 2), h3(7, 2),
        a2(0, 1), b2(1, 1), c2(2, 1), d2(3, 1), e2(4, 1), f2(5, 1), g2(6, 1), h2(7, 1),
        a1(0, 0), b1(1, 0), c1(2, 0), d1(3, 0), e1(4, 0), f1(5, 0), g1(6, 0), h1(7, 0);

        private static final Square[] array = {
                Square.a1, Square.b1, Square.c1, Square.d1, Square.e1, Square.f1, Square.g1, Square.h1,
                Square.a2, Square.b2, Square.c2, Square.d2, Square.e2, Square.f2, Square.g2, Square.h2,
                Square.a3, Square.b3, Square.c3, Square.d3, Square.e3, Square.f3, Square.g3, Square.h3,
                Square.a4, Square.b4, Square.c4, Square.d4, Square.e4, Square.f4, Square.g4, Square.h4,
                Square.a5, Square.b5, Square.c5, Square.d5, Square.e5, Square.f5, Square.g5, Square.h5,
                Square.a6, Square.b6, Square.c6, Square.d6, Square.e6, Square.f6, Square.g6, Square.h6,
                Square.a7, Square.b7, Square.c7, Square.d7, Square.e7, Square.f7, Square.g7, Square.h7,
                Square.a8, Square.b8, Square.c8, Square.d8, Square.e8, Square.f8, Square.g8, Square.h8};

        @Getter
        private final int file;

        @Getter
        private final int rank;

        Square(int file, int rank) {
            this.file = file;
            this.rank = rank;
        }

        public static Square of(int file, int rank) {
            if (file < 0 || rank < 0) {
                return null;
            }
            if (file > 7 || rank > 7) {
                return null;
            }
            return array[rank * 8 + file];
        }

        public static Square of(int idx) {
            return of(idx % 8, idx / 8);
        }
    }


    public static Move of(Square from, Square to) {
        return of(from, to, null);
    }

    public static Move of(Square from, Square to, PromotionPiece promotionPiece) {
        return new Move(from, to, promotionPiece);
    }

    /**
     * Returns the string representation of the move in pure algebraic notation.
     * Format: [from square][to square][promotion piece]
     * Example: "e2e4" for a regular move, "e7e8q" for a promotion
     *
     * @return the string representation of the move
     */
    @Override
    public String toString() {
        return String.format("%s%s%s",
                from.toString(),
                to.toString(),
                promotionPiece == null ? "" : promotionPiece.toString()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move that = (Move) o;
        return from == that.from && to == that.to && promotionPiece == that.promotionPiece;
    }
}
