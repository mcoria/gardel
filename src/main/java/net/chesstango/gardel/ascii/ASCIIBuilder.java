package net.chesstango.gardel.ascii;

import net.chesstango.gardel.AbstractPositionBuilder;
import net.chesstango.gardel.fen.FENBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Mauricio Coria
 */
public class ASCIIBuilder extends AbstractPositionBuilder<String> {

    private FENBuilder fenBuilder = new FENBuilder();

    @Override
    public String getPositionRepresentation() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream ps = new PrintStream(baos)) {
            printPiecePlacement(ps);
            printFEN(ps);
        }
        return baos.toString();
    }


    public void printFEN(PrintStream printStream) {
        printStream.printf("FEN: %s%n", fenBuilder.getPositionRepresentation());
    }

    public void printPiecePlacement(PrintStream printStream) {
        printStream.println("  -------------------------------");
        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                if (file == 0) {
                    printStream.print((rank + 1));
                }
                long position = 1L << rank * 8 + file;
                printStream.print("| " + getChar(position) + " ");
                if (file == 7) {
                    printStream.println("|");
                    printStream.println("  -------------------------------");
                }
            }
        }
        printStream.println("   a   b   c   d   e   f   g   h");
        printStream.flush();
    }

    private char getChar(long position) {
        if ((whitePositions & kingPositions & position) != 0) {
            return 'K';
        }
        if ((whitePositions & queenPositions & position) != 0) {
            return 'Q';
        }
        if ((whitePositions & rookPositions & position) != 0) {
            return 'R';
        }
        if ((whitePositions & bishopPositions & position) != 0) {
            return 'B';
        }
        if ((whitePositions & knightPositions & position) != 0) {
            return 'N';
        }
        if ((whitePositions & pawnPositions & position) != 0) {
            return 'P';
        }

        if ((blackPositions & kingPositions & position) != 0) {
            return 'k';
        }
        if ((blackPositions & queenPositions & position) != 0) {
            return 'q';
        }
        if ((blackPositions & rookPositions & position) != 0) {
            return 'r';
        }
        if ((blackPositions & bishopPositions & position) != 0) {
            return 'b';
        }
        if ((blackPositions & knightPositions & position) != 0) {
            return 'n';
        }
        if ((blackPositions & pawnPositions & position) != 0) {
            return 'p';
        }
        return ' ';
    }


    @Override
    public ASCIIBuilder withWhiteTurn(boolean whiteTurn) {
        super.withWhiteTurn(whiteTurn);
        fenBuilder.withWhiteTurn(whiteTurn);
        return this;
    }

    @Override
    public ASCIIBuilder withWhiteKing(int file, int rank) {
        super.withWhiteKing(file, rank);
        fenBuilder.withWhiteKing(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withWhiteQueen(int file, int rank) {
        super.withWhiteQueen(file, rank);
        fenBuilder.withWhiteQueen(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withWhiteRook(int file, int rank) {
        super.withWhiteRook(file, rank);
        fenBuilder.withWhiteRook(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withWhiteBishop(int file, int rank) {
        super.withWhiteBishop(file, rank);
        fenBuilder.withWhiteBishop(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withWhiteKnight(int file, int rank) {
        super.withWhiteKnight(file, rank);
        fenBuilder.withWhiteKnight(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withWhitePawn(int file, int rank) {
        super.withWhitePawn(file, rank);
        fenBuilder.withWhitePawn(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withBlackKing(int file, int rank) {
        super.withBlackKing(file, rank);
        fenBuilder.withBlackKing(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withBlackQueen(int file, int rank) {
        super.withBlackQueen(file, rank);
        fenBuilder.withBlackQueen(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withBlackRook(int file, int rank) {
        super.withBlackRook(file, rank);
        fenBuilder.withBlackRook(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withBlackBishop(int file, int rank) {
        super.withBlackBishop(file, rank);
        fenBuilder.withBlackBishop(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withBlackKnight(int file, int rank) {
        super.withBlackKnight(file, rank);
        fenBuilder.withBlackKnight(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withBlackPawn(int file, int rank) {
        super.withBlackPawn(file, rank);
        fenBuilder.withBlackPawn(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withEnPassantSquare(int file, int rank) {
        super.withEnPassantSquare(file, rank);
        fenBuilder.withEnPassantSquare(file, rank);
        return this;
    }

    @Override
    public ASCIIBuilder withCastlingBlackKingAllowed(boolean castlingBlackKingAllowed) {
        super.withCastlingBlackKingAllowed(castlingBlackKingAllowed);
        fenBuilder.withCastlingBlackKingAllowed(castlingBlackKingAllowed);
        return this;
    }

    @Override
    public ASCIIBuilder withCastlingBlackQueenAllowed(boolean castlingBlackQueenAllowed) {
        super.withCastlingBlackQueenAllowed(castlingBlackQueenAllowed);
        fenBuilder.withCastlingBlackQueenAllowed(castlingBlackQueenAllowed);
        return this;
    }

    @Override
    public ASCIIBuilder withCastlingWhiteKingAllowed(boolean castlingWhiteKingAllowed) {
        super.withCastlingWhiteKingAllowed(castlingWhiteKingAllowed);
        fenBuilder.withCastlingWhiteKingAllowed(castlingWhiteKingAllowed);
        return this;
    }

    @Override
    public ASCIIBuilder withCastlingWhiteQueenAllowed(boolean castlingWhiteQueenAllowed) {
        super.withCastlingWhiteQueenAllowed(castlingWhiteQueenAllowed);
        fenBuilder.withCastlingWhiteQueenAllowed(castlingWhiteQueenAllowed);
        return this;
    }

    @Override
    public ASCIIBuilder withHalfMoveClock(int halfMoveClock) {
        super.withHalfMoveClock(halfMoveClock);
        fenBuilder.withHalfMoveClock(halfMoveClock);
        return this;
    }

    @Override
    public ASCIIBuilder withFullMoveClock(int fullMoveClock) {
        super.withFullMoveClock(fullMoveClock);
        fenBuilder.withFullMoveClock(fullMoveClock);
        return this;
    }
}
