package net.chesstango.gardel.move;


/**
 * @author Mauricio Coria
 */
@FunctionalInterface
public interface MovePredicate {
    boolean test(int fromFile, int fromRank, int toFile, int toRank, int fromPiece, int toPiece, int promotion);
}
