package net.chesstango.gardel.minchess;

import net.chesstango.gardel.move.MoveSupplier;

import static net.chesstango.gardel.minchess.MinChess.MAX_MOVES;

/**
 * @author Mauricio Coria
 */
public class MinChessMoveSupplier implements MoveSupplier<Short> {

    private final MinChess minchess;

    public MinChessMoveSupplier(MinChess minchess) {
        this.minchess = minchess;
    }

    @Override
    public Short get(int fromFile, int fromRank, int toFile, int toRank, int fromPiece, int toPiece, int promotion) {
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
