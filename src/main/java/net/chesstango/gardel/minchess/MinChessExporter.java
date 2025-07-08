package net.chesstango.gardel.minchess;

import net.chesstango.gardel.PositionBuilder;

import java.util.function.BiConsumer;

/**
 * @author Mauricio Coria
 */
public class MinChessExporter {
    private final PositionBuilder<?> positionBuilder;

    public MinChessExporter(PositionBuilder<?> positionBuilder) {
        this.positionBuilder = positionBuilder;
    }


    public void export(MinChess minChess) {
        MinChessWorkspace workspace = minChess.workspace;

        this.positionBuilder.withWhiteTurn(workspace.whiteTurn);
        this.positionBuilder.withCastlingBlackKingAllowed(workspace.castlingBlackKingAllowed);
        this.positionBuilder.withCastlingBlackQueenAllowed(workspace.castlingBlackQueenAllowed);
        this.positionBuilder.withCastlingWhiteKingAllowed(workspace.castlingWhiteKingAllowed);
        this.positionBuilder.withCastlingWhiteQueenAllowed(workspace.castlingWhiteQueenAllowed);

        if (workspace.enPassantSquare != 0) {
            final int enPassantSquareIdx = Long.numberOfTrailingZeros(workspace.enPassantSquare);
            final int file = enPassantSquareIdx / 8;
            final int rank = enPassantSquareIdx % 8;
            this.positionBuilder.withEnPassantSquare(file, rank);
        }

        setPosition(workspace.whitePositions & workspace.pawnPositions, this.positionBuilder::withWhitePawn);
        setPosition(workspace.whitePositions & workspace.knightPositions, this.positionBuilder::withWhiteKnight);
        setPosition(workspace.whitePositions & workspace.bishopPositions, this.positionBuilder::withWhiteBishop);
        setPosition(workspace.whitePositions & workspace.rookPositions, this.positionBuilder::withWhiteRook);
        setPosition(workspace.whitePositions & workspace.queenPositions, this.positionBuilder::withWhiteQueen);
        setPosition(workspace.whitePositions & workspace.kingPositions, this.positionBuilder::withWhiteKing);


        setPosition(workspace.blackPositions & workspace.pawnPositions, this.positionBuilder::withBlackPawn);
        setPosition(workspace.blackPositions & workspace.knightPositions, this.positionBuilder::withBlackKnight);
        setPosition(workspace.blackPositions & workspace.bishopPositions, this.positionBuilder::withBlackBishop);
        setPosition(workspace.blackPositions & workspace.rookPositions, this.positionBuilder::withBlackRook);
        setPosition(workspace.blackPositions & workspace.queenPositions, this.positionBuilder::withBlackQueen);
        setPosition(workspace.blackPositions & workspace.kingPositions, this.positionBuilder::withBlackKing);
    }

    void setPosition(long position, BiConsumer<Integer, Integer> setPositionFn) {
        while (position != 0) {
            final int idx = Long.numberOfTrailingZeros(position);
            final int file = idx % 8;
            final int rank = idx / 8;
            setPositionFn.accept(file, rank);
            position &= ~(1L << idx);
        }
    }

}
