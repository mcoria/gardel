package net.chesstango.gardel.minchess;

import net.chesstango.gardel.AbstractPositionBuilder;

/**
 * @author Mauricio Coria
 */
public class MinChessBuilder extends AbstractPositionBuilder<MinChess> {


    @Override
    public MinChess getPositionRepresentation() {
        return new MinChess(whiteTurn,
                castlingBlackKingAllowed,
                castlingBlackQueenAllowed,
                castlingWhiteKingAllowed,
                castlingWhiteQueenAllowed,
                enPassantSquare,
                whitePositions,
                blackPositions,
                kingPositions,
                queenPositions,
                rookPositions,
                bishopPositions,
                knightPositions,
                pawnPositions
        );
    }
}
