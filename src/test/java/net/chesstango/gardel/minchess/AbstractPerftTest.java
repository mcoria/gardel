package net.chesstango.gardel.minchess;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENExporter;

/**
 * @author Mauricio Coria
 */
public abstract class AbstractPerftTest {

    protected MinChess createGame(String string) {
        MinChessBuilder builder = new MinChessBuilder();
        FENExporter exporter = new FENExporter(builder);
        exporter.export(FEN.of(string));
        return builder.getPositionRepresentation();
    }

    protected Perft createPerft() {
        return new PerftBrute();
    }

    /*
    protected boolean contieneMove(MoveContainerReader<? extends Move> movimientos, Square from, Square to) {
        for (Move move : movimientos) {
            if (from.equals(move.getFrom().getSquare()) && to.equals(move.getTo().getSquare())) {
                return true;
            }
        }
        return false;
    }

    protected void printForUnitTest(PerftResult result) {
        Map<Move, Long> childs = result.getChilds();

        childs.forEach((move, count) -> {
            System.out.printf("assertEquals(%d, result.getChildNode(Square.%s, Square.%s));\n", count, move.getFrom().getSquare(), move.getTo().getSquare());
        });

        System.out.printf("assertEquals(%d, result.getMovesCount());\n", childs.size());
        System.out.printf("assertEquals(%d, result.getTotalNodes());\n", result.getTotalNodes());
    }*/
}
