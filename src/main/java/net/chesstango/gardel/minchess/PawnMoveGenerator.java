package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
class PawnMoveGenerator extends AbstractMoveGenerator {

    PawnMoveGenerator(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        super(workspace, workspaceTmp);
    }

    int generatePawnMoves(short[] moves, int startIdx) {
        return 0;
    }

    int generatePawnWhiteMoves(short[] moves) {
        return 0; // TODO: Implement queen move generation
    }

    int generatePawnBlackMoves(short[] moves) {
        return 0;
    }
}
