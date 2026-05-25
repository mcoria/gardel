# Development Guide for `gardel`

This document provides essential information for developers working on the `gardel` chess project.

## 1. Build and Configuration Instructions

This is a Java project managed with Maven.

- **Requirements**:
  - JDK 11 or higher.
  - Maven 3.6+ installed.

- **Building the Project**:
  You can build the project by running the following command from the root directory:

  ```bash
  mvn clean install
  ```

## 2. Testing Information

This project uses JUnit 5 for testing.

- **Running Tests**:
  You can run all tests using:

  ```bash
  mvn test
  ```

  To run a specific test file, you can use:

  ```bash
  mvn test -Dtest=ClassName
  ```

- **Adding New Tests**:
  - New tests should be added to the `src/test/java` directory, maintaining the package structure corresponding to the source code.
  - Use JUnit 5 annotations (e.g., `@Test`, `@BeforeEach`).
  - Follow the existing testing patterns, such as those found in `src/test/java/net/chesstango/gardel/epd/EPDTest.java`.

## 3. Additional Development Information

- **Code Style**:
  - Follow the existing code style: consistent indentation, clear naming conventions, and proper Javadoc usage.
  - The project uses Lombok for boilerplate reduction (e.g., `@Getter`, `@Setter`). Ensure your IDE is configured to support Lombok.
- **Dependencies**:
  - Review `pom.xml` for current dependencies.
- **Project Structure**:
  - `src/main/java`: Source code.
  - `src/main/antlr4`: ANTLR4 grammar files (if applicable).
  - `src/test/java`: Test source code.
