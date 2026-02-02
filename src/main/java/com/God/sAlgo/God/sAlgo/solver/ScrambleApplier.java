package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.CubeModel;
import com.God.sAlgo.God.sAlgo.model.Move;

import java.util.List;

public class ScrambleApplier {

    public static CubeModel apply(CubeModel cube, List<Move> moves) {
        CubeModel cur = new CubeModel(cube);
        for (Move m : moves) {
            cur = MoveApplier.applyMove(cur, m);
        }
        return cur;
    }

}
