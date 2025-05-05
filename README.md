[![Java CI with Maven](https://github.com/mcoria/gardel/actions/workflows/maven.yml/badge.svg)](https://github.com/mcoria/gardel/actions/workflows/maven.yml)

# gardel
A lightweight Java library for encoding and decoding common chess notations, including FEN (Forsyth–Edwards Notation), PGN (Portable Game Notation), and SAN (Standard Algebraic Notation).


## Features
- **FEN**: Encode and decode Forsyth–Edwards Notation, a standard notation for describing a particular board position of a chess game.
- **PGN**: Encode and decode Portable Game Notation, a standard format for recording chess games.
- **SAN**: Encode and decode Standard Algebraic Notation, a notation for describing chess moves.

This library is designed to be non-intrusive, you can plug it into your existing chess engine or GUI without modifying your codebase.


## Prerequisites

- **Java 21** or higher
- **Maven** or **Gradle** for dependency management (optional)

## Installation

To use **gardel** in your project, add the dependency to your `pom.xml` (for Maven) or `build.gradle` (for Gradle).

### Maven

```xml
<dependency>
    <groupId>net.chesstango</groupId>
    <artifactId>gardel</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Gradle

```groovy
implementation 'net.chesstango:gardel:1.0.1'
```

## Usage

### FEN

**FEN.of()** creates a FEN object from a string. Once you have a FEN object, you can export it using a **PositionBuilder**.

#### ASCIIBuilder
   ```
    ...
    FEN fen = FEN.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

    // ACSIIBuilder is a class that implements the PositionBuilder interface
    ASCIIBuilder asciiBuilder = new ASCIIBuilder();

    // Export FEN to the ASCII builder
    fen.export(asciiBuilder);

    // Get the ASCII representation of the position
    String position = asciiBuilder.getPositionRepresentation();

    System.out.println(position);
   ```
Result:
   ```
      -------------------------------
    8| r | n | b | q | k | b | n | r |
      -------------------------------
    7| p | p | p | p | p | p | p | p |
      -------------------------------
    6|   |   |   |   |   |   |   |   |
      -------------------------------
    5|   |   |   |   |   |   |   |   |
      -------------------------------
    4|   |   |   |   |   |   |   |   |
      -------------------------------
    3|   |   |   |   |   |   |   |   |
      -------------------------------
    2| P | P | P | P | P | P | P | P |
      -------------------------------
    1| R | N | B | Q | K | B | N | R |
      -------------------------------
       a   b   c   d   e   f   g   h
    FEN: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
   ```

#### MirrorPositionBuilder
   ```
    ...
    FEN fen = FEN.of("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");

    ASCIIBuilder asciiBuilder = new ASCIIBuilder();

    // MirrorPositionBuilder is a PositionBuilder that mirrors the position and wraps another PositionBuilder.
    MirrorPositionBuilder<String> mirrorPositionBuilder = new MirrorPositionBuilder<>(asciiBuilder);

    // Export FEN to the mirror position builder
    fen.export(mirrorPositionBuilder);

    // Get the ASCII representation of the position, but mirrored.
    String position = asciiBuilder.getPositionRepresentation();

    System.out.println(position);
   ```
Result:
   ```
      -------------------------------
    8| R | N | B | Q | K | B | N | R |
      -------------------------------
    7| P | P | P | P | P | P | P | P |
      -------------------------------
    6|   |   |   |   |   |   |   |   |
      -------------------------------
    5|   |   |   |   |   |   |   |   |
      -------------------------------
    4|   |   |   |   |   |   |   |   |
      -------------------------------
    3|   |   |   |   |   |   |   |   |
      -------------------------------
    2| p | p | p | p | p | p | p | p |
      -------------------------------
    1| r | n | b | q | k | b | n | r |
      -------------------------------
       a   b   c   d   e   f   g   h
    FEN: RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr b KQkq - 0 1
   ```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the BSD 3-Clause License - see the [LICENSE](LICENSE) file for details.
