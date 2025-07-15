package net.chesstango.gardel.move;

import net.chesstango.gardel.fen.FEN;

/**
 * @author Mauricio Coria
 */
public interface MoveDecoder {
    Move decode(String move, FEN fen);

    default MoveDecoder getInstance() {
        return new AgregateMoveDecoder();
    }
}
