package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.CubeModel;
import com.God.sAlgo.God.sAlgo.model.Move;

public class MoveApplier {

    public static CubeModel applyMove(CubeModel cube, Move move) {
        CubeModel c = new CubeModel(cube);

        switch (move) {
            case U -> moveU(c);
            case U_PRIME -> { moveU(c); moveU(c); moveU(c); }

            case R -> moveR(c);
            case R_PRIME -> { moveR(c); moveR(c); moveR(c); }

            case F -> moveF(c);
            case F_PRIME -> { moveF(c); moveF(c); moveF(c); }
        }

        return c;
    }

    private static void moveU(CubeModel c) {
        cycle(c, 0, 1, 2, 3);
    }

    private static void moveR(CubeModel c) {
        cycle(c, 0, 3, 7, 4);

        twist(c, 0, 2);
        twist(c, 3, 1);
        twist(c, 7, 2);
        twist(c, 4, 1);
    }

    private static void moveF(CubeModel c) {
        cycle(c, 0, 4, 5, 1);

        twist(c, 0, 1);
        twist(c, 4, 2);
        twist(c, 5, 1);
        twist(c, 1, 2);
    }

    private static void cycle(CubeModel c, int a, int b, int d, int e) {
        int tempP = c.perm[a];
        int tempO = c.orient[a];

        c.perm[a] = c.perm[b];
        c.orient[a] = c.orient[b];

        c.perm[b] = c.perm[d];
        c.orient[b] = c.orient[d];

        c.perm[d] = c.perm[e];
        c.orient[d] = c.orient[e];

        c.perm[e] = tempP;
        c.orient[e] = tempO;
    }

    private static void twist(CubeModel c, int pos, int delta) {
        c.orient[pos] = (c.orient[pos] + delta) % 3;
    }

}
