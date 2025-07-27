package net.chesstango.gardel.pgn;

import lombok.Getter;
import lombok.Setter;
import net.chesstango.gardel.epd.EPD;
import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENParser;
import net.chesstango.gardel.minchess.MinChess;
import net.chesstango.gardel.move.SANDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

/**
 * @author Mauricio Coria
 */
@Getter
@Setter
public class PGN {
    private String event;
    private String site;
    private String date;
    private String round;
    private String white;
    private String black;
    private FEN fen;
    private String result;
    private List<String> moveList;

    @Override
    public String toString() {
        return new PGNStringEncoder().encode(this);
    }

    /**
     * Cada entrada EPD representa la posicion y el movimiento ejecutado
     *
     * @return
     */
    public Stream<EPD> toEPD() {
        Stream.Builder<EPD> fenStreamBuilder = Stream.builder();

        MinChess game = MinChess.from(getFen() == null ? FEN.of(FENParser.INITIAL_FEN) : getFen());

        List<EPD> epdList = new ArrayList<>(getMoveList().size());

        int lastClock = 2;

        SANDecoder<Short> sanDecoder = new SANDecoder<>(
                (fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion) ->
                        get(game, fromFile, fromRank, toFile, toRank, fromPiece, toPiece, promotion)
        );

        for (String moveStr : getMoveList()) {

            FEN fenGame = game.toFEN();

            Short legalMoveToExecute = sanDecoder.decode(moveStr, fenGame);

            if (legalMoveToExecute != null) {
                EPD epd = new EPD();

                epd.setPiecePlacement(fenGame.getPiecePlacement());
                epd.setActiveColor(fenGame.getActiveColor());
                epd.setCastingsAllowed(fenGame.getCastingsAllowed());
                epd.setEnPassantSquare(fenGame.getEnPassantSquare());

                epd.setId(String.format("%s", Long.toHexString(game.toPolyglotKey())));

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

                epd.setSuppliedMoveStr(moveStr);

                epdList.add(epd);

                game.doMove(legalMoveToExecute);

            } else {
                throw new RuntimeException(String.format("[%s] %s is not in the list of legal moves for %s", getEvent(), moveStr, fenGame.toString()));
            }
        }

        for (EPD epd : epdList) {
            epd.setC7(String.format("totalClock=%d", lastClock - 2));
            fenStreamBuilder.add(epd);
        }

        return fenStreamBuilder.build();
    }

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
