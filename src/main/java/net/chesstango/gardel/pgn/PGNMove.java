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
 * @author Mauricio Coria
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PGNMove implements Serializable {
    public static final String EVAL_COMMAND = "eval";

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
