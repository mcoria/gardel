package net.chesstango.gardel;

/**
 *
 * @author Mauricio Coria
 */
public interface PositionExporter {
    <T> T export(PositionBuilder<T> positionBuilder);
}
