package net.chesstango.gardel.epd;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class reads Extended Position Description files.
 *
 * @author Mauricio Coria
 */
public class EPDDecoder {
    private final EPDParser epdParser = new EPDParser();

    public Stream<EPD> decodeEPDs(Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            System.err.printf("file not found: %s\n", filePath.getFileName());
            throw new RuntimeException(String.format("file not found: %s", filePath.getFileName()));
        }

        try (InputStream in = new FileInputStream(filePath.toFile())) {
            return decodeEPDs(in);
        }
    }

    public Stream<EPD> decodeEPDs(InputStream in) throws IOException {
        List<EPD> epdList = new LinkedList<>();
        try (InputStreamReader inputStreamReader = new InputStreamReader(in);
             BufferedReader rr = new BufferedReader(inputStreamReader)) {

            String line;

            while ((line = rr.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("#") && !line.isEmpty()) {
                    try {
                        EPD entry = epdParser.parseEPD(line);
                        epdList.add(entry);
                    } catch (RuntimeException e) {
                        System.err.printf("Error decoding: %s\n", line);
                        e.printStackTrace(System.err);
                    }
                }
            }
            return epdList.stream();
        }
    }
}
