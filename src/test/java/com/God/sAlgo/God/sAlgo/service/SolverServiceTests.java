package com.God.sAlgo.God.sAlgo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class SolverServiceTests {

    private Map<String, List<String>> solvedFaces() {
        Map<String, List<String>> f = new HashMap<>();
        f.put("U", List.of("W","W","W","W"));
        f.put("F", List.of("G","G","G","G"));
        f.put("R", List.of("R","R","R","R"));
        f.put("L", List.of("O","O","O","O"));
        f.put("B", List.of("B","B","B","B"));
        f.put("D", List.of("Y","Y","Y","Y"));
        return f;
    }

    @Test
    public void normalize_rotatedSolvedCube_shouldReturnNormalizedAndNoMoves() {
        SolverService service = new SolverService();
        Map<String, List<String>> solved = solvedFaces();

        // Manually rotate solved cube once around X to simulate user's holding
        Map<String, List<String>> rotated = new HashMap<>();
        rotated.put("U", solved.get("F"));
        rotated.put("F", solved.get("D"));
        rotated.put("D", solved.get("B"));
        rotated.put("B", solved.get("U"));
        rotated.put("L", solved.get("L"));
        rotated.put("R", solved.get("R"));

        String res = service.solveFromColors(rotated);
        assertEquals("", res, "Solved (rotated) cube should produce no moves after normalization");
    }

}
