package net.chesstango.gardel.pgn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mauricio Coria
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PGNMove {
    private String sanMove;
    private Map<String, String> commands;
}
