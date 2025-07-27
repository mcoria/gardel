package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;

/**
 * @author Mauricio Coria
 */
public interface MoveDecoder<M> {
    M decode(String move, FEN fen);
}
