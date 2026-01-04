package net.chesstango.gardel.pgn;


import net.chesstango.gardel.fen.FEN;
import net.chesstango.gardel.internal.antlr4.PGNLexer;
import net.chesstango.gardel.internal.antlr4.PGNParser;
import net.chesstango.gardel.move.SANDecoder;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Mauricio Coria
 */
public class PGNStringDecoder {
    public Stream<PGN> decodePGNs(Path pgnFile) throws IOException {
        // 1. Create a CharStream from the input source
        CharStream input = CharStreams.fromPath(pgnFile);
        return decodePGNs(input);
    }

    public Stream<PGN> decodePGNs(InputStream inputStream) throws IOException {
        // 1. Create a CharStream from the input source
        CharStream input = CharStreams.fromStream(inputStream);
        return decodePGNs(input);
    }

    Stream<PGN> decodePGNs(CharStream input) {
        // 4. Create a parser that feeds off the tokens buffer
        PGNParser parser = createParser(input);

        // 5. Begin parsing at the 'prog' rule
        ParseTree tree = parser.parse();

        // 6. Create the walker and hook up the listener
        ParseTreeWalker walker = ParseTreeWalker.DEFAULT;

        PGNGardelListener listener = new PGNGardelListener();

        walker.walk(listener, tree); // Initiate the walk through the parse tree

        return listener.getPgnList().stream();
    }

    PGN decodePGN(CharStream input) {
        // 4. Create a parser that feeds off the tokens buffer
        PGNParser parser = createParser(input);

        // 5. Begin parsing at the 'prog' rule
        ParseTree tree = parser.pgn_game();

        // 6. Create the walker and hook up the listener
        ParseTreeWalker walker = ParseTreeWalker.DEFAULT;

        PGNGardelListener listener = new PGNGardelListener();

        walker.walk(listener, tree); // Initiate the walk through the parse tree

        return listener.getPgn();
    }

    private PGNParser createParser(CharStream input) {
        // 2. Create a lexer that feeds off the input CharStream
        PGNLexer lexer = new PGNLexer(input);

        // 3. Create a buffer of tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. Create a parser that feeds off the tokens buffer
        return new PGNParser(tokens);
    }

}
