package net.chesstango.gardel.minchess;


import net.chesstango.gardel.MirrorPositionBuilder;
import net.chesstango.gardel.ascii.ASCIIBuilder;
import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.fen.FENExporter;

import java.util.Map;
import java.util.Set;

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

    protected MinChess createMirrorGame(String string) {
        MinChessBuilder builder = new MinChessBuilder();
        MirrorPositionBuilder<MinChess> mirrorBuilder = new MirrorPositionBuilder<>(builder);
        FENExporter exporter = new FENExporter(mirrorBuilder);
        exporter.export(FEN.of(string));
        return builder.getPositionRepresentation();
    }

    protected void debug(PerftResult result) {
        Map<Short, Long> children = result.getChildren();
        Set<Short> moves = children.keySet();
        moves.forEach(this::printMove);
    }

    private void printMove(short move) {
        int toFile = move & 0b00000000_00000111;
        int toRank = (move & 0b00000000_00111000) >>> 3;
        Square to = Square.of(toFile, toRank);

        int fromFile = (move & 0b00000001_11000000) >>> 6;
        int fromRank = (move & 0b00001110_00000000) >>> 9;
        Square from = Square.of(fromFile, fromRank);

        int promotion = (move & 0b11110000_00000000) >>> 12;

        if (promotion != 0) {
            System.out.printf("%s%s - %s\n", from, to, promotion);
        } else {
            System.out.printf("%s%s\n", from, to);
        }
    }

    void printAscii(MinChess minChess){
        ASCIIBuilder asciiBuilder = new ASCIIBuilder();
        MinChessExporter builder = new MinChessExporter(asciiBuilder);
        builder.export(minChess);
        System.out.println(asciiBuilder.getPositionRepresentation());
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
