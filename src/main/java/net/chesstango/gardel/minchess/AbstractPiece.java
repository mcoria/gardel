package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
class AbstractPiece {
    final MinChessWorkspace workspace;
    final MinChessWorkspace workspaceTmp;

    AbstractPiece(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        this.workspace = workspace;
        this.workspaceTmp = workspaceTmp;
    }

    boolean isLegalMove(long from, long to) {
        workspaceTmp.copyFrom(workspace);
        workspaceTmp.doMoveImp(from, to);
        return !workspaceTmp.isKingInCheck(workspace.whiteTurn);
    }
}
