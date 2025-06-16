package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
class PawnMoveGenerator {

    final MinChessWorkspace workspace;
    final PawnWhiteMoveGenerator pawnWhiteMoveGenerator;
    final PawnBlackMoveGenerator pawnBlackMoveGenerator;


    PawnMoveGenerator(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        this.workspace = workspace;
        this.pawnWhiteMoveGenerator = new PawnWhiteMoveGenerator(workspace, workspaceTmp);
        this.pawnBlackMoveGenerator = new PawnBlackMoveGenerator(workspace, workspaceTmp);
    }

    int generatePawnMoves(short[] moves, int startIdx) {
        return workspace.whiteTurn ? pawnWhiteMoveGenerator.generatePawnMoves(moves, startIdx) : pawnBlackMoveGenerator.generatePawnMoves(moves, startIdx);
    }
}
