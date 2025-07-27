package net.chesstango.gardel.move;


import net.chesstango.gardel.fen.FEN;

/**
 * @author Mauricio Coria
 *
 */
public class AgregateMoveDecoder<M> implements MoveDecoder<M> {

    private final SANDecoder<M> sanDecoder;
    private final LANDecoder<M> lanDecoder;

    public AgregateMoveDecoder(MoveSupplier<M> moveSupplier) {
        this.sanDecoder = new SANDecoder<>(moveSupplier);
        this.lanDecoder = new LANDecoder<>(moveSupplier);
    }

    @Override
    public M decode(String moveStr, FEN fen) {

        M move = sanDecoder.decode(moveStr, fen);
        if (move == null) {
            move = lanDecoder.decode(moveStr, fen);
        }

        return move;
    }
}
