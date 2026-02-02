package com.God.sAlgo.God.sAlgo.service;

import com.God.sAlgo.God.sAlgo.model.CubeModel;
import com.God.sAlgo.God.sAlgo.model.Move;
import com.God.sAlgo.God.sAlgo.solver.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SolverService {

    private final OptimalSolver solver;

    public SolverService() {
        Map<Long, Integer> table = GodsTableService.getTable();
        this.solver=new OptimalSolver(table);
    }

    public String solve(String scramble) {

        List<Move> scrambleMoves = ScrambleParser.parse(scramble);
        CubeModel cube = new CubeModel();
        cube = ScrambleApplier.apply(cube, scrambleMoves);

        List<Move> solutionMoves = solver.solve(cube);

        return solutionMoves.stream()
                .map(this::moveToString)
                .collect(Collectors.joining(" "));
    }

    private String moveToString(Move m){
        return switch (m) {
            case U -> "U";
            case U_PRIME -> "U'";
            case R -> "R";
            case R_PRIME -> "R'";
            case F -> "F";
            case F_PRIME -> "F'";
        };
    }

    public String solveFromColors(Map<String, List<String>> faces){

        faces = CubeNormalizer.normalize(faces);
        CubeModel cube = ColorConverter.fromColors(faces);

        List<Move> solutionMoves = solver.solve(cube);

        return solutionMoves.stream()
                .map(this::moveToString)
                .collect(Collectors.joining(" "));

    }
}
