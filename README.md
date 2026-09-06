# 2x2 Cube Solver

A REST API that solves a 2×2 Rubik's Cube using **God's Algorithm**: it returns a shortest solution for a supported cube state. The solver uses a breadth-first-search (BFS) distance table of cube states and an IDA*-style search guided by that table.

## Features

- Find an optimal solution from a scramble in standard notation.
- Find an optimal solution from the colors of all six faces.
- Accepts the moves `U`, `U'`, `R`, `R'`, `F`, and `F'` for scramble input.
- Loads the included precomputed `gods_table.bin` cache at startup; if it is absent, the application generates and saves it.

## Architecture

```text
HTTP client
    |
    v
SolverController                 POST /solve, POST /solve/colors
    |
    v
SolverService                    Coordinates input conversion and solving
    |-------------------------------+-------------------------------+
    v                               v                               v
ScrambleParser /              CubeModel                    GodsTableService
ColorConverter                (corner permutation          loads or generates
                               and orientation)             the distance table
    |                               |                               |
    +-------------------------------+-------------------------------+
                                    v
                              OptimalSolver
                      IDA*-style search using exact distances
                                    |
                                    v
                       MoveApplier + StateEncoder
                                    |
                                    v
                             Shortest move sequence
```

Key packages:

- `controller` — HTTP endpoints and request/response DTOs.
- `service` — application orchestration and move-string formatting.
- `model` — cube state, colors, and supported moves.
- `solver` — state encoding, move application, color conversion, the BFS table, and optimal search.

## Step-by-step setup

### 1. Prerequisites

Install:

- Java 17 or newer
- Maven 3.9 or newer
- Git

Verify the tools:

```bash
java -version
mvn -version
git --version
```

### 2. Clone the project

```bash
git clone https://github.com/Sourav2432/2x2CubeSolver.git
cd 2x2CubeSolver
```

### 3. Run the tests

```bash
mvn test
```

### 4. Start the API

```bash
mvn spring-boot:run
```

The service starts at `http://localhost:8080`.

Alternatively, build and run the packaged JAR:

```bash
mvn clean package
java -jar target/God-sAlgo-0.0.1-SNAPSHOT.jar
```

`gods_table.bin` must be in the directory from which the application is run. It is included in this repository. If it is missing, the application generates it on the first run and writes it to that working directory.

## API usage

### Solve a scramble

`POST /solve`

```bash
curl --request POST http://localhost:8080/solve \
  --header "Content-Type: application/json" \
  --data '{"scramble":"R U R'"}'
```

Example response:

```json
{
  "solution": "R' U' R'"
}
```

Moves are separated by spaces. Only `U`, `U'`, `R`, `R'`, `F`, and `F'` are currently accepted.

### Solve from face colors

`POST /solve/colors`

Each face has four stickers. Use color enum values `W`, `Y`, `G`, `B`, `R`, and `O`; the face names are `U`, `D`, `F`, `B`, `R`, and `L`.

```bash
curl --request POST http://localhost:8080/solve/colors \
  --header "Content-Type: application/json" \
  --data '{
    "faces": {
      "U": ["W", "W", "W", "W"],
      "F": ["G", "G", "G", "G"],
      "R": ["R", "R", "R", "R"],
      "L": ["O", "O", "O", "O"],
      "B": ["B", "B", "B", "B"],
      "D": ["Y", "Y", "Y", "Y"]
    }
  }'
```

A solved cube returns an empty solution string:

```json
{
  "solution": ""
}
```

## Configuration

The default configuration is in `src/main/resources/application.properties`:

```properties
server.port=8080
spring.main.lazy-initialization=true
```

## Contributing

Contributions are welcome. Please run `mvn test` before opening a pull request.

## Contributor

- Sourav Kushwaha ([@Sourav2432](https://github.com/Sourav2432))
