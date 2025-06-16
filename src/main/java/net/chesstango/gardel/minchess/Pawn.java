package net.chesstango.gardel.minchess;

/**
 * @author Mauricio Coria
 */
class Pawn {

    final MinChessWorkspace workspace;
    final PawnWhite pawnWhite;
    final PawnBlack pawnBlack;


    Pawn(MinChessWorkspace workspace, MinChessWorkspace workspaceTmp) {
        this.workspace = workspace;
        this.pawnWhite = new PawnWhite(workspace, workspaceTmp);
        this.pawnBlack = new PawnBlack(workspace, workspaceTmp);

        workspace.setPawn(this);
        workspaceTmp.setPawn(this);
    }

    int generatePawnMoves(short[] moves, int startIdx) {
        return workspace.whiteTurn ? pawnWhite.generatePawnMoves(moves, startIdx) : pawnBlack.generatePawnMoves(moves, startIdx);
    }
}
