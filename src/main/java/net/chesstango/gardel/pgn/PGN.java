package net.chesstango.gardel.pgn;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.chesstango.gardel.epd.EPD;
import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.minchess.MinChess;
import net.chesstango.gardel.move.Move;
import net.chesstango.gardel.move.SANDecoder;
import org.antlr.v4.runtime.CharStreams;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

/**
 * Represents a chess game in Portable Game Notation (PGN) format.
 * This class encapsulates all the metadata and moves of a chess game,
 * including standard PGN tags and the ability to convert to other formats
 * such as EPD and FEN.
 *
 * @author Mauricio Coria
 */
public class PGN implements Serializable {
    /**
     * Represents the possible results of a chess game.
     */
    public enum Result {
        WHITE_WINS, BLACK_WINS, DRAW, ONGOING;

        @Override
        public String toString() {
            return switch (this) {
                case WHITE_WINS -> "1-0";
                case BLACK_WINS -> "0-1";
                case DRAW -> "1/2-1/2";
                case ONGOING -> "*";
            };
        }
    }

    /**
     * Represents the type of termination for a chess game.
     */
    public enum Termination {
        NORMAL, ABANDONED, TIME_FORFEIT;

        @Override
        public String toString() {
            return switch (this) {
                case NORMAL -> "normal";
                case ABANDONED -> "abandoned";
                case TIME_FORFEIT -> "time forfeit";
            };
        }
    }

    /**
     * The name of the tournament or match event.
     */
    @Getter
    @Setter
    private String event;

    /**
     * The location where the game was played.
     */
    @Getter
    @Setter
    private String site;

    /**
     * The date when the game was played.
     */
    @Getter
    @Setter
    private String date;

    /**
     * The round number of the tournament.
     */
    @Getter
    @Setter
    private String round;

    /**
     * The name of the player with the white pieces.
     */
    @Getter
    @Setter
    private String white;

    /**
     * The name of the player with the black pieces.
     */
    @Getter
    @Setter
    private String black;

    /**
     * The starting position in FEN notation if different from the standard starting position.
     */
    @Getter
    @Setter
    private FEN fen;

    /**
     * The result of the game.
     */
    @Getter
    @Setter
    private Result result;

    /**
     * The type of termination for the game.
     */
    @Getter
    @Setter
    private Termination termination;

    /**
     * Additional PGN tags not covered by the standard fields.
     */
    @Getter(AccessLevel.PACKAGE)
    private final Map<String, String> otherTags = new TreeMap<>();

    /**
     * The list of moves in Standard Algebraic Notation (SAN).
     */
    @Getter
    @Setter
    private List<PGNMove> pgnMoves;


    /**
     * Sets a custom PGN tag with the specified name and value.
     *
     * @param tagName  the name of the tag to set
     * @param tagValue the value of the tag
     * @return the previous value associated with the tag name, or null if there was no mapping
     */
    public String setTag(String tagName, String tagValue) {
        return otherTags.put(tagName, tagValue);
    }

    /**
     * Retrieves the value of a custom PGN tag by its name.
     *
     * @param tagName the name of the tag to retrieve
     * @return an Optional containing the tag value if present, or an empty Optional if not found
     */
    public Optional<String> getTag(String tagName) {
        return Optional.ofNullable(otherTags.get(tagName));
    }

    /**
     * Creates a PGN instance from a PGN format string.
     *
     * @param pgn the PGN string to parse
     * @return a PGN object representing the parsed game, or null if parsing fails
     */
    public static PGN from(String pgn) {
        PGNDecoder pgnDecoder = new PGNDecoder();
        return pgnDecoder.decodePGNs(CharStreams.fromString(pgn)).findFirst().orElse(null);
    }

    /**
     * Creates a new PGN instance from the specified FEN position.
     *
     * @param fen the FEN object representing the chess position
     * @return a PGN object initialized with the given FEN position
     */
    public static PGN from(FEN fen) {
        PGN pgn = new PGN();

        if (!FEN.START_POSITION.equals(fen)) {
            pgn.setFen(fen);
        }

        pgn.setResult(Result.ONGOING);

        pgn.setPgnMoves(Collections.emptyList());

        return pgn;
    }

    /**
     * Converts this PGN object to its string representation in PGN format.
     *
     * @return a string representation of this PGN in standard PGN format
     */
    @Override
    public String toString() {
        return new PGNEncoder().encode(this);
    }

    /**
     * Converts this PGN to a stream of EPD (Extended Position Description) entries.
     * Each EPD entry represents a position in the game along with metadata and the move executed.
     *
     * @return a stream of EPD objects representing each position in the game, or an empty stream if conversion fails
     */
    public Stream<EPD> toEPD() {
        Stream.Builder<EPD> fenStreamBuilder = Stream.builder();

        MinChess game = MinChess.from(getFen() == null ? FEN.START_POSITION : getFen());

        List<EPD> epdList = new ArrayList<>(getPgnMoves().size());

        int lastClock = 2;

        SANDecoder<Short> sanDecoder = new SANDecoder<>(
                (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) ->
                        get(game, fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion)
        );

        for (PGNMove move : getPgnMoves()) {

            FEN fen = game.toFEN();

            Short legalMoveToExecute = sanDecoder.decode(move.getSanMove(), fen);

            if (legalMoveToExecute != null) {
                EPD epd = new EPD();

                epd.setPiecePlacement(fen.getPiecePlacement());
                epd.setActiveColor(fen.getActiveColor());
                epd.setCastingsAllowed(fen.getCastingsAllowed());
                epd.setEnPassantSquare(fen.getEnPassantSquare());
                epd.setHalfMoveClock(fen.getHalfMoveClock());
                epd.setFullMoveClock(fen.getFullMoveClock());

                epd.setId(String.format("%s %s", fen.getFullMoveClock(), fen.getActiveColor()));

                if (event != null) {
                    epd.setC0(String.format("event='%s'", event));
                }
                if (site != null) {
                    epd.setC1(String.format("site='%s'", site));
                }
                if (date != null) {
                    epd.setC2(String.format("date='%s'", date));
                }
                if (white != null) {
                    epd.setC3(String.format("white='%s'", white));
                }
                if (black != null) {
                    epd.setC4(String.format("black='%s'", black));
                }
                if (result != null) {
                    epd.setC5(String.format("result='%s'", result));
                }

                epd.setC6(String.format("clock=%d", lastClock++ / 2));

                epd.setSuppliedMoveStr(move.getSanMove());

                epdList.add(epd);

                game.doMove(legalMoveToExecute);

            } else {
                System.err.printf("[%s] %s is not in the list of legal moves for %s%n", getEvent(), move.getSanMove(), fen.toString());
                return Stream.empty();
            }
        }

        for (EPD epd : epdList) {
            epd.setC7(String.format("totalClock=%d", lastClock - 2));
            fenStreamBuilder.add(epd);
        }

        return fenStreamBuilder.build();
    }

    /**
     * Converts this PGN to a stream of FEN (Forsyth-Edwards Notation) positions.
     * Each FEN entry represents a position after each move in the game.
     *
     * @return a stream of FEN objects representing each position in the game, or an empty stream if conversion fails
     */
    public Stream<FEN> toFEN() {
        Stream.Builder<FEN> fenBuilder = Stream.builder();

        MinChess game = MinChess.from(getFen() == null ? FEN.from(FEN.START_POSITION_STRING) : getFen());

        SANDecoder<Short> sanDecoder = new SANDecoder<>(
                (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) ->
                        get(game, fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion)
        );

        FEN fenGame = game.toFEN();

        fenBuilder.add(fenGame);

        for (PGNMove move : getPgnMoves()) {

            Short minChessMove = sanDecoder.decode(move.getSanMove(), fenGame);

            if (minChessMove != null) {

                game.doMove(minChessMove);

                fenGame = game.toFEN();

                fenBuilder.add(fenGame);

            } else {
                System.err.printf("[%s] %s is not in the list of legal moves for %s%n", getEvent(), move.getSanMove(), fenGame.toString());
                return Stream.empty();
            }
        }

        return fenBuilder.build();
    }

    public List<String> getCoordinateMoves() {
        List<String> coordinateMoves = new ArrayList<>(getPgnMoves().size());

        MinChess game = MinChess.from(getFen() == null ? FEN.from(FEN.START_POSITION_STRING) : getFen());

        SANDecoder<Short> sanDecoder = new SANDecoder<>(
                (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) ->
                        get(game, fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion)
        );

        SANDecoder<Move> sanToMoveDecoder = new SANDecoder<>(new Move.GardelMoveSupplier());


        FEN fenGame = game.toFEN();

        for (PGNMove sanMove : getPgnMoves()) {

            Short minChessMove = sanDecoder.decode(sanMove.getSanMove(), fenGame);

            Move move = sanToMoveDecoder.decode(sanMove.getSanMove(), fenGame);

            if (minChessMove != null && move != null) {

                coordinateMoves.add(move.toString());

                game.doMove(minChessMove);

                fenGame = game.toFEN();

            } else {
                System.err.printf("[%s] %s is not in the list of legal moves for %s%n", getEvent(), sanMove, fenGame.toString());
                return Collections.emptyList();
            }
        }

        return coordinateMoves;
    }

    /**
     * Finds a legal move matching the specified move parameters.
     *
     * @param minchess  the chess engine instance
     * @param fromFile  the source file (column) of the move
     * @param fromRank  the source rank (row) of the move
     * @param toFile    the destination file (column) of the move
     * @param toRank    the destination rank (row) of the move
     * @param fromPiece the piece type being moved
     * @param toPiece   the piece type at the destination square (0 if empty, or captured piece)
     * @param promotion the promotion piece type (0 if no promotion)
     * @return the encoded move as a Short, or null if no matching legal move is found
     */
    private Short get(MinChess minchess, int fromFile, int fromRank, int toFile, int toRank, int fromPiece, int toPiece, int promotion) {
        short[] moves = new short[MAX_MOVES];
        int size = minchess.generateMoves(moves);
        for (int i = 0; i < size; i++) {
            final short move = moves[i];
            final int fromFileFilter = MinChess.fromFile(move);
            final int fromRankFilter = MinChess.fromRank(move);
            final int fromPieceFilter = minchess.getFromPiece(move);

            final int toFileFilter = MinChess.toFile(move);
            final int toRankFilter = MinChess.toRank(move);
            final int toPieceFilter = minchess.getToPiece(move);

            final int promotionFilter = MinChess.getPromotionPiece(move);

            if (fromFile == fromFileFilter && fromRank == fromRankFilter && fromPiece == fromPieceFilter &&
                    toFile == toFileFilter && toRank == toRankFilter && toPiece == toPieceFilter &&
                    promotion == promotionFilter) {
                return move;
            }
        }
        return null;
    }
}
