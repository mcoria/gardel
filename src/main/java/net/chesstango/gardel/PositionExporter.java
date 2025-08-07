package net.chesstango.gardel;

/**
 *
 * @author Mauricio Coria
 */
public interface PositionExporter {
    <T> void export(PositionBuilder<T> positionBuilder);
}
