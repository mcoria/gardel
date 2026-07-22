package net.chesstango.gardel.pgn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a single move in PGN (Portable Game Notation) format.
 * <p>
 * This class encapsulates a chess move in Standard Algebraic Notation (SAN) along with
 * optional commands that provide additional move metadata such as evaluation scores,
 * clock times, and elapsed move times.
 * </p>
 * <p>
 * Supported commands include:
 * <ul>
 *   <li>{@link #EVAL_COMMAND} - evaluation score of the position</li>
 *   <li>{@link #CLOCK_COMMAND} - remaining time on player's clock after the move</li>
 *   <li>{@link #ELAPSED_MOVE_TIME_COMMAND} - time spent thinking on this move</li>
 * </ul>
 * </p>
 *
 * @author Mauricio Coria
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PGNMove implements Serializable {
    // Evaluates the position after the move is played
    public static final String EVAL_COMMAND = "eval";

    // Tracks the total time remaining on the player's clock after completing that move
    public static final String CLOCK_COMMAND = "clk";


    // Tracks the exact duration spent thinking on the preceding move (e.g., 1 minute and 23 seconds).
    public static final String ELAPSED_MOVE_TIME_COMMAND = "emt";


    private String sanMove;
    private Map<String, String> commands;

    public String putCommand(String name, String value) {
        if (commands == null) commands = new HashMap<>();
        return commands.put(name, value);
    }

    public Optional<String> getCommand(String name) {
        if (commands == null) return Optional.empty();
        return Optional.ofNullable(commands.get(name));
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sanMove);
        if (commands != null && !commands.isEmpty()) {
            sb.append(" {");
            commands.forEach((key, value) -> sb.append(String.format("[%%%s %s]", key, value)));
            sb.append("}");
        }
        return sb.toString();
    }

}
