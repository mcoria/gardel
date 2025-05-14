package net.chesstango.gardel.minchess;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static net.chesstango.gardel.minchess.MinChessConstants.MAX_MOVES;

/**
 * @author Mauricio Coria
 */
public class PerftBrute implements Perft {

    private int maxLevel;


    @Override
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
                totalNodes += visitChild(game, level + 1);
            }
        } else {
            totalNodes = size;
        }
        return totalNodes;
    }


    public void printResult(PerftResult perftResult) {
        System.out.println("Total Moves: " + perftResult.getMovesCount());
        System.out.println("Total Nodes: " + perftResult.getTotalNodes());

        Map<Short, Long> childs = perftResult.getChilds();

        if (childs != null) {
            List<Short> moves = new ArrayList<>(childs.keySet());
            Collections.reverse(moves);

            for (Short move : moves) {
                System.out.println("Move = " + move.toString() +
                        ", Total = " + childs.get(move));
            }
        }
    }

}
