package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.Color;
import com.God.sAlgo.God.sAlgo.model.CubeModel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ColorConverter {

    public static CubeModel fromColors(Map<String, List<String>> faces) {
//        if(faces==null){System.out.println("ye to khali hai");}
        CubeModel cube = new CubeModel();

        // Convert face strings to Color enum
        Map<String, Color[]> f = new HashMap<>();
        for (var e : faces.entrySet()) {
            Color[] arr = new Color[4];
            for (int i = 0; i < 4; i++) {
                arr[i] = Color.valueOf(e.getValue().get(i));
            }
            f.put(e.getKey(), arr);
        }

        // Build corners
        // URF
        // URF (0)
        assignCorner(cube, 0,
                f.get("U")[3], f.get("R")[2], f.get("F")[1]);

// UFL (1)
        assignCorner(cube, 1,
                f.get("U")[2], f.get("F")[0], f.get("L")[3]);

// ULB (2)
        assignCorner(cube, 2,
                f.get("U")[0], f.get("L")[2], f.get("B")[1]);

// UBR (3)
        assignCorner(cube, 3,
                f.get("U")[1], f.get("B")[0], f.get("R")[3]);

// DFR (4)
        assignCorner(cube, 4,
                f.get("D")[1], f.get("F")[3], f.get("R")[0]);

// DLF (5)
        assignCorner(cube, 5,
                f.get("D")[0], f.get("L")[1], f.get("F")[2]);

// DBL (6)
        assignCorner(cube, 6,
                f.get("D")[2], f.get("B")[3], f.get("L")[0]);

// DRB (7)
        assignCorner(cube, 7,
                f.get("D")[3], f.get("R")[1], f.get("B")[2]);



        return cube;
    }

    private static void assignCorner(
            CubeModel cube, int pos, Color a, Color b, Color c) {

        Color[] observed = new Color[]{a, b, c};

        for (int i = 0; i < CornerDefinition.SOLVED_CORNER.size(); i++) {
            Color[] solved = CornerDefinition.SOLVED_CORNER.get(i);

            if (sameColors(observed, solved)) {
                cube.perm[pos] = i;
                cube.orient[pos] = computeOrientation(observed, solved);
                return;
            }
        }

        throw new IllegalArgumentException("Invalid corner colors");
    }

    private static boolean sameColors(Color[] a, Color[] b) {
        return new HashSet<>(List.of(a)).equals(new HashSet<>(List.of(b)));
    }

    private static int computeOrientation(Color[] observed, Color[] solved) {
        // Orientation = index where U/D color matches
        for (int i = 0; i < 3; i++) {
            if (observed[i] == solved[0]) {
                return i % 3;
            }
        }
        throw new IllegalStateException("Invalid orientation");
    }

}
