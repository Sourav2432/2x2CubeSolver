package com.God.sAlgo.God.sAlgo.solver;

import com.God.sAlgo.God.sAlgo.model.CubeModel;
import com.God.sAlgo.God.sAlgo.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OptimalSolver {

    private final Map<Long, Integer> distanceTable;
    private final List<Move> solution = new ArrayList<>();

    public OptimalSolver(Map<Long, Integer> distanceTable) {
        this.distanceTable = distanceTable;
    }

    public List<Move> solve(CubeModel start){
        solution.clear();

        long key = StateEncoder.encode(start);
        Integer bound = distanceTable.get(key);
        if (bound == null) {
            throw new IllegalStateException(
                    "State not found in table: " + key
            );
        }
        while(true) {
            int t = dfs(start, 0, bound, null);
            if (t == -1) {
                return new ArrayList<>(solution);
            }
            bound = t;
        }
    }

    private int dfs(CubeModel cube, int depth, int bound, Move lastMove){
        long key = StateEncoder.encode(cube);
        int h = distanceTable.get(key);
        int f = depth + h;
        if(f > bound) return f;
        if(h == 0){ return -1;}

        int min = Integer.MAX_VALUE;

        for (Move move : Move.values()){

            if(isInverse(move, lastMove)){continue;}

            CubeModel next =  MoveApplier.applyMove(cube, move);
            solution.add(move);

            int t = dfs(next, depth + 1, bound, move);
            if(t == -1) {return -1;}

            min = Math.min(min, t);
            solution.remove(solution.size() - 1);
        }
        return min;
    }

    private boolean isInverse(Move a,Move b){
        if(a == null || b == null) return false;

        return  (a == Move.U && b == Move.U_PRIME) ||
                (a == Move.U_PRIME && b == Move.U) ||
                (a == Move.R && b == Move.R_PRIME) ||
                (a == Move.R_PRIME && b == Move.R) ||
                (a == Move.F && b == Move.F_PRIME) ||
                (a == Move.F_PRIME && b == Move.F);
    }

}
