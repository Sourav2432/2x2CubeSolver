package com.God.sAlgo.God.sAlgo.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CubeNormalizer {

    public static Map<String, List<String>> normalize(
            Map<String, List<String>> faces) {

        Map<String, List<String>> cur = deepCopy(faces);

        // Try all orientations (24 total)
        for (int i = 0; i < 24; i++) {

            if (isNormalized(cur)) {
                return cur;
            }

            // Rotate cube systematically
            if (i % 4 == 3) {
                cur = rotateX(cur);
            } else {
                cur = rotateY(cur);
            }
        }

        throw new IllegalArgumentException("Invalid cube orientation");
    }

    // ---------- NORMALIZATION RULE ----------
    private static boolean isNormalized(Map<String, List<String>> f) {
        return f.get("U").get(0).equals("W")
                && f.get("F").get(0).equals("G");
    }

    // ---------- ROTATIONS ----------

    // Rotate cube around X axis
    private static Map<String, List<String>> rotateX(Map<String, List<String>> f) {
        Map<String, List<String>> n = deepCopy(f);

        n.put("U", rotateFace(f.get("F")));
        n.put("F", rotateFace(f.get("D")));
        n.put("D", rotateFace(f.get("B")));
        n.put("B", rotateFace(f.get("U")));

        n.put("L", rotateFaceCCW(f.get("L")));
        n.put("R", rotateFaceCW(f.get("R")));

        return n;
    }

    // Rotate cube around Y axis
    private static Map<String, List<String>> rotateY(Map<String, List<String>> f) {
        Map<String, List<String>> n = deepCopy(f);

        n.put("F", rotateFace(f.get("R")));
        n.put("R", rotateFace(f.get("B")));
        n.put("B", rotateFace(f.get("L")));
        n.put("L", rotateFace(f.get("F")));

        n.put("U", rotateFaceCW(f.get("U")));
        n.put("D", rotateFaceCCW(f.get("D")));

        return n;
    }

    // ---------- FACE ROTATION ----------

    private static List<String> rotateFace(List<String> f) {
        return List.of(f.get(2), f.get(0), f.get(3), f.get(1));
    }

    private static List<String> rotateFaceCW(List<String> f) {
        return List.of(f.get(2), f.get(0), f.get(3), f.get(1));
    }

    private static List<String> rotateFaceCCW(List<String> f) {
        return List.of(f.get(1), f.get(3), f.get(0), f.get(2));
    }

    // ---------- UTIL ----------
    private static Map<String, List<String>> deepCopy(
            Map<String, List<String>> f) {

        Map<String, List<String>> copy = new HashMap<>();
        for (var e : f.entrySet()) {
            copy.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        return copy;
    }

}
