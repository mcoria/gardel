package net.chesstango.gardel.minchess;


import static net.chesstango.gardel.minchess.MinChessConstants.MAX_MOVES;

/**
 * @author Mauricio Coria
 */
public class PerftBrute {

    private int maxLevel;


    public PerftResult start(MinChess game, int maxLevel) {
        this.maxLevel = maxLevel;
        PerftResult perftResult = new PerftResult();
        long totalNodes = 0;
        short[] moves = new short[MAX_MOVES];
        int size = game.generateMoves(moves);
        for (int i = 0; i < size; i++) {
            MinChess gameClone = game.clone();
            long nodeCount = 0;
            if (maxLevel > 1) {
                gameClone.doMove(moves[i]);
                nodeCount = visitChild(gameClone, 2);
            } else {
                nodeCount = 1;
            }
            perftResult.add(moves[i], nodeCount);
            totalNodes += nodeCount;
        }
        perftResult.setTotalNodes(totalNodes);
        return perftResult;
    }

    private long visitChild(MinChess game, int level) {
        long totalNodes = 0;
        short[] moves = new short[MAX_MOVES];
        int size = game.generateMoves(moves);
        if (level < this.maxLevel) {
            for (int i = 0; i < size; i++) {
                MinChess gameClone = game.clone();
                gameClone.doMove(moves[i]);
                totalNodes += visitChild(gameClone, level + 1);
            }
        } else {
            totalNodes = size;
        }
        return totalNodes;
    }


}
