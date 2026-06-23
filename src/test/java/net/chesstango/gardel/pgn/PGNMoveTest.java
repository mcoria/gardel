// PGNMoveTest.java
package net.chesstango.gardel.pgn;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the putCommand method of the PGNMove class.
 * <p>
 * The putCommand method is responsible for adding a name-value pair to
 * the commands map and returning the previous value associated with the name,
 * or null if no such value existed.
 */

public class PGNMoveTest {

    @Test
    public void test_putCommand_NewCommand() {
        // Given: a PGNMove instance with no commands
        PGNMove move = new PGNMove();
        String commandName = "eval";
        String commandValue = "1.5";

        // When: adding a new command
        String result = move.putCommand(commandName, commandValue);

        // Then: the command should be added and the return value should be null
        assertNull(result);
        assertEquals(commandValue, move.getCommands().get(commandName));
    }

    @Test
    public void test_putCommand_UpdateExistingCommand() {
        // Given: a PGNMove instance with an existing command
        Map<String, String> initialCommands = new HashMap<>();
        initialCommands.put("eval", "1.5");
        PGNMove move = new PGNMove("e4", initialCommands);
        String commandName = "eval";
        String newCommandValue = "0.8";

        // When: updating the command value
        String oldValue = move.putCommand(commandName, newCommandValue);

        // Then: the old value should be returned, and the new value should overwrite the existing one
        assertEquals("1.5", oldValue);
        assertEquals(newCommandValue, move.getCommands().get(commandName));
    }

    @Test
    public void test_putCommand_CommandsInitiallyNull() {
        // Given: a PGNMove instance with null commands
        PGNMove move = new PGNMove("e4", null);
        String commandName = "time";
        String commandValue = "10s";

        // When: adding a new command
        String result = move.putCommand(commandName, commandValue);

        // Then: the commands map should be initialized, and the command should be added
        assertNull(result);
        assertNotNull(move.getCommands());
        assertEquals(commandValue, move.getCommands().get(commandName));
    }

    @Test
    public void test_putCommand_MultipleCommands() {
        // Given: a PGNMove instance with a map of commands
        PGNMove move = new PGNMove("e4", new HashMap<>());
        String command1Name = "time";
        String command1Value = "20s";
        String command2Name = "eval";
        String command2Value = "-0.4";

        // When: adding multiple commands
        String result1 = move.putCommand(command1Name, command1Value);
        String result2 = move.putCommand(command2Name, command2Value);

        // Then: both commands should be stored correctly with null returned for new commands
        assertNull(result1);
        assertNull(result2);
        assertEquals(command1Value, move.getCommands().get(command1Name));
        assertEquals(command2Value, move.getCommands().get(command2Name));
    }

    @Test
    public void test_putCommand_NullValue() {
        // Given: a PGNMove instance with no commands
        PGNMove move = new PGNMove("e4", new HashMap<>());
        String commandName = "eval";
        String commandValue = null;

        // When: adding a command with a null value
        String result = move.putCommand(commandName, commandValue);

        // Then: the command should be added with a null value
        assertNull(result);
        assertTrue(move.getCommands().containsKey(commandName));
        assertNull(move.getCommands().get(commandName));
    }

    @Test
    public void test_toString_WithSanMoveAndCommands() {
        // Given: a PGNMove instance with sanMove and commands
        Map<String, String> commands = new HashMap<>();
        commands.put("eval", "1.5");
        commands.put("time", "10s");
        PGNMove move = new PGNMove("e4", commands);

        // When: calling toString()
        String result = move.toString();

        // Then: the string should contain the sanMove and commands in PGN format
        assertTrue(result.startsWith("e4 {"));
        assertTrue(result.contains("[%eval 1.5]"));
        assertTrue(result.contains("[%time 10s]"));
        assertTrue(result.endsWith("}"));
    }

    @Test
    public void test_toString_WithOnlySanMove() {
        // Given: a PGNMove instance with only sanMove
        PGNMove move = new PGNMove("Nf3", null);

        // When: calling toString()
        String result = move.toString();

        // Then: the string should contain only the sanMove without commands
        assertEquals("Nf3", result);
    }

    @Test
    public void test_toString_WithNullValues() {
        // Given: a PGNMove instance with null values
        PGNMove move = new PGNMove();

        // When: calling toString()
        String result = move.toString();

        // Then: the string should show null
        assertEquals("null", result);
    }

    @Test
    public void test_toString_WithEmptyCommands() {
        // Given: a PGNMove instance with empty commands map
        PGNMove move = new PGNMove("d4", new HashMap<>());

        // When: calling toString()
        String result = move.toString();

        // Then: the string should contain only the sanMove without command braces
        assertEquals("d4", result);
    }
}