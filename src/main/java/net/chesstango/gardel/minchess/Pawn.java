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
        return workspace.whiteTurn ? pawnWhite.generateMoves(moves, startIdx) : pawnBlack.generateMoves(moves, startIdx);
    }

    boolean isKingInCheckByOpponentPawn(long kingPosition, int kingIdx, boolean opponentColor) {
        return opponentColor ? pawnWhite.isKingInCheckByOpponent(kingPosition, kingIdx, opponentColor) : pawnBlack.isKingInCheckByOpponent(kingPosition, kingIdx, opponentColor);
    }

}
