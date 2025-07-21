package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;

/**
 * @author Mauricio Coria
 *
 */
public class AgregateMoveDecoder implements MoveDecoder {

    @Override
    public Move decode(String moveStr, FEN fen) {
        SANDecoder sanDecoder = new SANDecoder();
        LANDecoder lanDecoder = new LANDecoder();

        Move move = sanDecoder.decode(moveStr, fen);
        if (move == null) {
            move = lanDecoder.decode(moveStr, fen);
        }

        return move;
    }
}
