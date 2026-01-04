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

    private static final Pattern headerPattern = Pattern.compile("\\[(\\w*) \"(.*)\"]");
    private static final Pattern movePattern = Pattern.compile("(?<moveOrder>\\d*)\\.(\\.\\.| )");

    public Stream<PGN> decodePGNs(Path pgnFile) throws IOException {
        try (InputStream inputStream = Files.newInputStream(pgnFile)) {
            return decodePGNs(inputStream);
        }
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

        return listener.pgnList.stream();
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

        return listener.pgn;
    }

    PGN decodePGNHeaders(BufferedReader bufferReader) throws IOException {
        PGN result = new PGN();
        String line;
        while ((line = bufferReader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                break;
            }
            Matcher headerMather = headerPattern.matcher(line);
            if (headerMather.find()) {
                String headerName = headerMather.group(1).toUpperCase();
                String headerText = headerMather.group(2);
                switch (headerName) {
                    case "EVENT":
                        result.setEvent(headerText);
                        break;
                    case "SITE":
                        result.setSite(headerText);
                        break;
                    case "DATE":
                        result.setDate(headerText);
                        break;
                    case "ROUND":
                        result.setRound(headerText);
                        break;
                    case "WHITE":
                        result.setWhite(headerText);
                        break;
                    case "BLACK":
                        result.setBlack(headerText);
                        break;
                    case "FEN":
                        result.setFen(FEN.of(headerText));
                        break;
                    case "RESULT":
                        result.setResult(
                                switch (headerText) {
                                    case "1-0" -> PGN.Result.WHITE_WINS;
                                    case "0-1" -> PGN.Result.BLACK_WINS;
                                    case "1/2-1/2" -> PGN.Result.DRAW;
                                    default -> PGN.Result.ONGOING;
                                });
                        break;

                    case "TERMINATION":
                        result.setTermination(
                                switch (headerText.toUpperCase()) {
                                    case "NORMAL" -> PGN.Termination.NORMAL;
                                    case "ABANDONED" -> PGN.Termination.ABANDONED;
                                    case "TIME FORFEIT" -> PGN.Termination.TIME_FORFEIT;
                                    default ->
                                            throw new IllegalStateException("Unexpected value: " + headerText.toUpperCase());
                                }
                        );
                        break;
                }
            }
        }
        if (result.getEvent() == null) {
            return null;
        }
        return result;
    }

    List<String> decodePGNBody(BufferedReader bufferReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferReader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                break;
            }
            stringBuilder.append(line.trim());
            stringBuilder.append(" ");
        }

        return decodePGNBody(stringBuilder.toString());
    }

    List<String> decodePGNBody(String moveListStr) {
        List<String> result = new ArrayList<>();
        final Matcher matcher = SANDecoder.movePattern.matcher(moveListStr);
        while (matcher.find()) {
            String moveStr = matcher.group(0);
            result.add(moveStr);
        }
        return result;
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
