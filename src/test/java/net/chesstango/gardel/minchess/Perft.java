package net.chesstango.gardel.minchess;


/**
 * @author Mauricio Coria
 *
 */
public interface Perft {

	PerftResult start(MinChess game, int maxLevel);
	
	void printResult(PerftResult result);

}